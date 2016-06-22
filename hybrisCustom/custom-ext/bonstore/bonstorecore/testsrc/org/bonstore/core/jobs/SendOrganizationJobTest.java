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
	SendOrganizationJob sendOrganizationJob;

	private CronJobModel cronJob;

	@Mock
	EmailAddressModel emailAddressModel;

	@Mock
	EmailMessageModel emailMessageModel;

	@Mock
	OrganizationModel organizationModel;

	@Mock
	CustomerModel customerModel;

	@Mock
	OrganizationService organizationService;

	@Mock
	EmailService emailService;

	@Mock
	CommonI18NService commonI18NService;

	@Mock
	Converter<OrganizationModel, EmailMessageModel> emailMessageModelConverter;
	@Mock
	LanguageModel languageModel;

	Locale locale = new Locale("EN");

	@Before
	public void setUp()
	{
		//		Registry.activateMasterTenant();
		final List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
		customerModelList.add(customerModel);
		cronJob = new CronJobModel();
	}

	@Test
	public void testOrganizationModelIsEmpty()
	{
		final List<OrganizationModel> models = new ArrayList<OrganizationModel>();
		when(organizationService.getOrganizationList()).thenReturn(models);
		final PerformResult performResult = sendOrganizationJob.perform(cronJob);

		assertEquals(CronJobStatus.FINISHED, performResult.getStatus());
	}

	@Test
	public void testOrganizationModelIsNotEmpty()
	{
		final List<OrganizationModel> models = new ArrayList<OrganizationModel>();
		models.add(organizationModel);
		when(emailMessageModelConverter.convert(organizationModel)).thenReturn(emailMessageModel);
		when(organizationService.getOrganizationList()).thenReturn(models);
		when(commonI18NService.getLocaleForLanguage(languageModel)).thenReturn(locale);
		when(emailService.send(emailMessageModel)).thenReturn(true);

		final PerformResult performResult = sendOrganizationJob.perform(cronJob);
		assertNotNull(sendOrganizationJob.perform(cronJob));
		assertEquals(CronJobResult.SUCCESS, performResult.getResult());
	}

	@Test
	public void testPerform()
	{
		final List<OrganizationModel> models = Arrays.asList(organizationModel);
		when(emailMessageModelConverter.convert(organizationModel)).thenReturn(emailMessageModel);
		when(organizationService.getOrganizationList()).thenReturn(models);
		when(commonI18NService.getLocaleForLanguage(languageModel)).thenReturn(locale);
		when(emailService.send(emailMessageModel)).thenReturn(true);
		final PerformResult performResult = sendOrganizationJob.perform(cronJob);
		assertNotNull(sendOrganizationJob.perform(cronJob));
		assertEquals(CronJobResult.SUCCESS, performResult.getResult());

	}

}
