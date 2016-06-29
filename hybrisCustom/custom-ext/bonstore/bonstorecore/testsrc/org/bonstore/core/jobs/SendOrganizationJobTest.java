/**
 *
 */
package org.bonstore.core.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.services.OrganizationService;
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
public class SendOrganizationJobTest
{
	@InjectMocks
	private SendOrganizationJob sendOrganizationJob;

	private CronJobModel cronJob;

	@Mock
	private EmailAddressModel emailAddressModel;

	@Mock
	private EmailMessageModel emailMessageModel;

	@Mock
	private OrganizationModel organizationModel;

	@Mock
	private CustomerModel customerModel;

	@Mock
	private OrganizationService organizationService;

	@Mock
	private EmailService emailService;

	@Mock
	private CommonI18NService commonI18NService;

	@Mock
	private Converter<OrganizationModel, EmailMessageModel> emailMessageModelConverter;
	@Mock
	private LanguageModel languageModel;

	private final Locale locale = new Locale("EN");
	private List<OrganizationModel> emptyOrganizationModels;
	private List<OrganizationModel> organizationModels;

	@Before
	public void setUp()
	{
		final List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
		customerModelList.add(customerModel);
		cronJob = new CronJobModel();
		emptyOrganizationModels = new ArrayList<OrganizationModel>();
		organizationModels = Arrays.asList(organizationModel);
		when(emailMessageModelConverter.convert(organizationModel)).thenReturn(emailMessageModel);
		when(commonI18NService.getLocaleForLanguage(languageModel)).thenReturn(locale);
	}

	@Test
	public void testOrganizationModelIsEmpty()
	{
		when(organizationService.getOrganizationList()).thenReturn(emptyOrganizationModels);
		final PerformResult performResult = sendOrganizationJob.perform(cronJob);
		assertNotNull(performResult);
		assertEquals(CronJobStatus.FINISHED, performResult.getStatus());
	}

	@Test
	public void testOrganizationModelIsNotEmpty()
	{
		when(organizationService.getOrganizationList()).thenReturn(organizationModels);
		when(emailService.send(emailMessageModel)).thenReturn(true);
		final PerformResult performResult = sendOrganizationJob.perform(cronJob);
		assertNotNull(performResult);
		assertEquals(CronJobResult.SUCCESS, performResult.getResult());
	}

}
