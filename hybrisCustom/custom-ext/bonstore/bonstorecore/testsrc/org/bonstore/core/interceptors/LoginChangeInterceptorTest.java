/**
 *
 */
package org.bonstore.core.interceptors;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.bonstore.core.event.LoginChangeEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Tanmoy_Mondal
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class LoginChangeInterceptorTest
{
	@Mock
	private EventService eventService;
	@Mock
	private CustomerModel customerModel;
	@Mock
	private LoginChangeEvent loginChangeEvent;
	@Mock
	InterceptorContext ctx;

	private static final int MAX_ATTEMPT_COUNT = 3;
	@InjectMocks
	private LoginChangeInterceptor loginChangeInterceptor;

	@SuppressWarnings("boxing")
	@Before
	public void setUp()
	{
		when(customerModel.getAttemptCount()).thenReturn(MAX_ATTEMPT_COUNT);
		doNothing().when(eventService).publishEvent(loginChangeEvent);
	}

	@SuppressWarnings("boxing")
	@Test
	public void testLoginDisabledStatusFalse() throws InterceptorException
	{
		when(customerModel.isLoginDisabled()).thenReturn(true);
		loginChangeInterceptor.onPrepare(customerModel, ctx);
	}

	@SuppressWarnings("boxing")
	@Test
	public void testLoginDisabledStatusTrue() throws InterceptorException
	{
		when(customerModel.isLoginDisabled()).thenReturn(false);
		loginChangeInterceptor.onPrepare(customerModel, ctx);
	}

}
