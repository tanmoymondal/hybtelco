/**
 *
 */
package org.bonstore.core.populator;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.commerceservices.util.ConverterFactory;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.List;

import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.populators.EmailMessageModelPopulator;
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
public class EmailMessageModelPopulatorTest
{

	private static final String NO_USERS = "No users exists in the organization";
	private static final String mailFromAddress = "mail@fromaddress.com";
	private AbstractPopulatingConverter<OrganizationModel, EmailMessageModel> emailMessageModelConverter;

	@Mock
	private OrganizationModel source;
	@Mock
	private ModelService modelService;
	@Mock
	private ServicesUtil servicesUtil;
	@Mock
	private CustomerModel customerModel1;
	@Mock
	private CustomerModel customerModel2;
	@InjectMocks
	private EmailMessageModelPopulator emailMessageModelPopulator;

	private EmailMessageModel target;
	private List<CustomerModel> customerList;

	@SuppressWarnings(
	{ "static-access", "deprecation" })
	@Before
	public void setUp()
	{
		emailMessageModelConverter = new ConverterFactory<OrganizationModel, EmailMessageModel, EmailMessageModelPopulator>()
				.create(EmailMessageModel.class, emailMessageModelPopulator);
		customerList = new ArrayList<>();
		given(source.getCustomers()).willReturn(customerList);
		given(source.getEmail()).willReturn(mailFromAddress);
		when(modelService.create(EmailAddressModel.class)).thenReturn(new EmailAddressModel());
	}

	@Test
	public void testPopulateWithCustomersExist()
	{
		customerList.add(customerModel1);
		customerList.add(customerModel2);
		given(customerModel1.getName()).willReturn("customerModel1");
		given(customerModel2.getName()).willReturn("customerModel2");
		target = emailMessageModelConverter.convert(source);
		assertEquals(mailFromAddress, target.getToAddresses().get(0).getEmailAddress());
	}

	@Test
	public void testPopulateWithNoCustomers()
	{
		target = emailMessageModelConverter.convert(source);
		assertEquals(NO_USERS, target.getBody());
	}


}
