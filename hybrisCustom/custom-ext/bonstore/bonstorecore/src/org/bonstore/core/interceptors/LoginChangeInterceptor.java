/**
 * 
 */
package org.bonstore.core.interceptors;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import org.bonstore.core.event.LoginChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tanmoy_Mondal
 *
 */
public class LoginChangeInterceptor implements PrepareInterceptor
{
	private static final int MAX_ATTEMPT_COUNT = 3;

	@Autowired
	private EventService eventService;


	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof CustomerModel)
		{
			final CustomerModel customer = (CustomerModel) model;
			//If it is true, then publish the event
			if (checkLoginDisabledStatus(customer))
			{
				eventService.publishEvent(new LoginChangeEvent(customer.getUid(), customer.isLoginDisabled()));
			}
		}
	}

	private boolean checkLoginDisabledStatus(final CustomerModel customer)
	{
		if (!customer.isLoginDisabled() && customer.getAttemptCount() >= MAX_ATTEMPT_COUNT)
		{
			return true;
		}
		return false;
	}

}