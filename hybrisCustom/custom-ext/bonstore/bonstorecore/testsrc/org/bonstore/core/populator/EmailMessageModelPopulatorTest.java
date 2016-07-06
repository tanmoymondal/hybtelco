/**
 *
 */
package org.bonstore.core.populator;

import static org.mockito.Mockito.doNothing;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.bonstore.core.model.OrganizationModel;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Tanmoy_Mondal
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class EmailMessageModelPopulatorTest
{

	private static final String NO_USERS = "No users exists in the organization";
	private static final String LIST_OF_USERS = "List of users in your organization :-";
	private static final String DISPLAY_NAME = "HybrisUser ";
	private String replyToAddress;
	private String mailFromAddress;
	@Mock
	private OrganizationModel source;
	@Mock
	private EmailMessageModel target;
	@Mock
	private ModelService modelService;
	@Mock
	private ServicesUtil servicesUtil;

	@SuppressWarnings("static-access")
	@Before
	public void setUp()
	{
		//		when(customerModel.getAttemptCount()).thenReturn(MAX_ATTEMPT_COUNT);
		doNothing().when(servicesUtil).validateParameterNotNull(target, "EmailMessageModel cannot be null");
	}


}
