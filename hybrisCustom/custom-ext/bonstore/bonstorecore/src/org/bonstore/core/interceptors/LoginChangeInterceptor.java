/**
 *
 */
package org.bonstore.core.interceptors;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import org.apache.log4j.Logger;
import org.bonstore.core.dao.BonstoreCustomerDao;


/**
 * @author Tanmoy_Mondal
 *
 */
public class LoginChangeInterceptor implements PrepareInterceptor
{
	static final private Logger LOG = Logger.getLogger(LoginChangeInterceptor.class);
	private static final int MAX_ATTEMPT_COUNT = 3;

	private BonstoreCustomerDao bonstoreCustomerDao;

	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		LOG.debug("Entering onPrepare method");
		if (model instanceof CustomerModel)
		{
			final CustomerModel customer = (CustomerModel) model;
			//If it is true, then publish the event
			if (checkLoginDisabledStatus(customer))
			{
				bonstoreCustomerDao.changeCustomerDetails(customer);
			}
		}
		LOG.debug("Exiting onPrepare method");
	}

	private boolean checkLoginDisabledStatus(final CustomerModel customer)
	{
		if (!customer.isLoginDisabled() && customer.getAttemptCount() >= MAX_ATTEMPT_COUNT)
		{
			return true;
		}
		return false;
	}

	/**
	 * @return the bonstoreCustomerDao
	 */
	public BonstoreCustomerDao getBonstoreCustomerDao()
	{
		return bonstoreCustomerDao;
	}

	/**
	 * @param bonstoreCustomerDao
	 *           the bonstoreCustomerDao to set
	 */
	public void setBonstoreCustomerDao(final BonstoreCustomerDao bonstoreCustomerDao)
	{
		this.bonstoreCustomerDao = bonstoreCustomerDao;
	}

}