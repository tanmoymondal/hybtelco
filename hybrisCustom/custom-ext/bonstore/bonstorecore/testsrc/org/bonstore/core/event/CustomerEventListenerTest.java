/**
 *
 */
package org.bonstore.core.event;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

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
public class CustomerEventListenerTest
{

	@Mock
	private ModelService modelService;
	@Mock
	private UserService userService;
	@Mock
	private CustomerModel customerModel;
	@Mock
	private LoginChangeEvent loginChangeEvent;
	@InjectMocks
	private CustomerEventListener customerEventListener;

	@Before
	public void setUp()
	{
		// userService.getUserForUID
		when(userService.getUserForUID(anyString())).thenReturn(customerModel);
		doNothing().when(modelService).save(customerModel);
		given(customerModel.getAttemptCount()).willReturn(0);
		given(customerModel.isStatus()).willReturn(false);
	}

	@Test
	public void testOnEvent()
	{
		customerEventListener.onEvent(loginChangeEvent);
		assertEquals(0, customerModel.getAttemptCount());
		assertEquals(Boolean.FALSE, customerModel.isStatus());
	}

}
