/**
 *
 */
package org.bonstore.core.jobs;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.List;

import org.apache.log4j.Logger;
import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.services.OrganizationService;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Tanmoy_Mondal
 * @Description This method works as a CRON job for sending the mail for a particular Organization with the list of
 *              users in that particular organization.
 *
 */
public class SendOrganizationJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(SendOrganizationJob.class);
	private static final String USER_MESSAGE = "List of Users ";

	private OrganizationService organizationService;
	private EmailService emailService;
	private CommonI18NService commonI18NService;

	private PerformResult performResult;

	private Converter<OrganizationModel, EmailMessageModel> emailMessageModelConverter;

	public void setOrganizationService(final OrganizationService organizationService)
	{
		this.organizationService = organizationService;
	}

	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}

	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		final LanguageModel languageModel = cronJob.getSessionLanguage();
		LOG.info("Sending list of users in organization to all admins");
		performResult = new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
		final List<OrganizationModel> organizationModels = organizationService.getOrganizationList();
		if (organizationModels.isEmpty())
		{
			LOG.info("No organization is available");
			performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		else
		{
			for (final OrganizationModel model : organizationModels)
			{
				final EmailMessageModel emailMessageModel = emailMessageModelConverter.convert(model);
				emailMessageModel.setSubject(USER_MESSAGE + model.getName(commonI18NService.getLocaleForLanguage(languageModel)));
				emailService.send(emailMessageModel);
			}
			performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}

		return performResult;
	}

	protected Converter<OrganizationModel, EmailMessageModel> getEmailMessageModelConverter()
	{
		return emailMessageModelConverter;
	}

	@Required
	public void setEmailMessageModelConverter(final Converter<OrganizationModel, EmailMessageModel> emailMessageModelConverter)
	{
		this.emailMessageModelConverter = emailMessageModelConverter;
	}

}
