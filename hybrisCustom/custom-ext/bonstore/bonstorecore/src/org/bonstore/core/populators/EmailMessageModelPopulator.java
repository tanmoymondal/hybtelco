/**
 *
 */
package org.bonstore.core.populators;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bonstore.core.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Value;


/**
 * @author Tanmoy_Mondal
 *
 */
public class EmailMessageModelPopulator implements Populator<OrganizationModel, EmailMessageModel>
{
	private static final String NO_USERS = "No users exists in the organization";
	private static final String LIST_OF_USERS = "List of users in your organization :-";
	private static final String DISPLAY_NAME = "HybrisUser ";
	private ModelService modelService;

	static final private Logger LOG = Logger.getLogger(EmailMessageModelPopulator.class);

	@Value("${mail.from}")
	private String mailFromAddress;
	@Value("${mail.replyto}")
	private String replyToAddress;

	@Override
	public void populate(final OrganizationModel source, final EmailMessageModel target) throws ConversionException
	{
		LOG.debug("### Entering populate method ###");
		if (null == source)
		{
			return;
		}
		validateParameterNotNull(target, "EmailMessageModel cannot be null");
		target.setCreationtime(new Date());
		target.setReplyToAddress(replyToAddress);
		target.setBody(getEmailBodyContent(source.getCustomers()));

		final EmailAddressModel emailAddressModel = (EmailAddressModel) modelService.create(EmailAddressModel.class);
		emailAddressModel.setEmailAddress(source.getEmail());
		emailAddressModel.setDisplayName(DISPLAY_NAME + Math.random());

		final List<EmailAddressModel> toAddresses = new ArrayList<EmailAddressModel>();
		toAddresses.add(emailAddressModel);

		final EmailAddressModel fromAddress = new EmailAddressModel();
		fromAddress.setEmailAddress(mailFromAddress);
		fromAddress.setDisplayName("HrbrisAdmin-" + Math.random());

		target.setToAddresses(toAddresses);
		target.setFromAddress(fromAddress);
		LOG.debug("### Exiting populate method ###");
	}

	/**
	 * Returns the body for the email.
	 *
	 * @param customersList
	 * @return mail body in the form of list of customers.
	 */
	private String getEmailBodyContent(final List<CustomerModel> customersList)
	{
		LOG.debug("### Entering getEmailBodyContent method ###");
		if (customersList.isEmpty())
		{
			return NO_USERS;
		}
		final StringBuilder sBuilder = new StringBuilder();
		for (final CustomerModel customerModel : customersList)
		{
			sBuilder.append("<br>");
			sBuilder.append(customerModel.getName() + "<br>");
		}
		LOG.debug("### Exiting getEmailBodyContent method ###");
		return LIST_OF_USERS + "<br>" + sBuilder.toString();
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
