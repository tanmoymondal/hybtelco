package org.bonstore.storefront.facades;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.services.OrganizationService;
import org.bonstore.data.BonstoreRegisterData;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


public class BonstoreFrontCustomerFacade
{

	private UserService userService;
	private CustomerAccountService customerAccountService;
	private CommonI18NService commonI18NService;
	private ModelService modelService;
	private CustomerNameStrategy customerNameStrategy;
	private OrganizationService organizationService;

	public void register(final BonstoreRegisterData bonstoreRegisterData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", bonstoreRegisterData);
		Assert.hasText(bonstoreRegisterData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(bonstoreRegisterData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(bonstoreRegisterData.getLogin(), "The field [Login] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		newCustomer
				.setName(getCustomerNameStrategy().getName(bonstoreRegisterData.getFirstName(), bonstoreRegisterData.getLastName()));

		if (StringUtils.isNotBlank(bonstoreRegisterData.getFirstName())
				&& StringUtils.isNotBlank(bonstoreRegisterData.getLastName()))
		{
			newCustomer.setName(
					getCustomerNameStrategy().getName(bonstoreRegisterData.getFirstName(), bonstoreRegisterData.getLastName()));
		}
		final TitleModel title = getUserService().getTitleForCode(bonstoreRegisterData.getTitleCode());
		newCustomer.setTitle(title);
		newCustomer.setOrganizations(getListOfOrganizationModels(bonstoreRegisterData.getOrganizationIds()));

		newCustomer.setUid(bonstoreRegisterData.getLogin().toLowerCase());
		newCustomer.setOriginalUid(bonstoreRegisterData.getLogin());
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		getCustomerAccountService().register(newCustomer, bonstoreRegisterData.getPassword());
	}

	private List<OrganizationModel> getListOfOrganizationModels(final List<String> organizations)
	{
		final List<OrganizationModel> listOfOrganizationModels = new ArrayList<>();
		for (final String organizationId : organizations)
		{
			final OrganizationModel organizationModel = organizationService.getOrganizationByID(organizationId).get(0);
			listOfOrganizationModels.add(organizationModel);
		}
		return listOfOrganizationModels;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public CustomerNameStrategy getCustomerNameStrategy()
	{
		return customerNameStrategy;
	}

	@Required
	public void setCustomerNameStrategy(final CustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}


	/**
	 * @return the organizationService
	 */
	public OrganizationService getOrganizationService()
	{
		return organizationService;
	}

	/**
	 * @param organizationService
	 *           the organizationService to set
	 */
	public void setOrganizationService(final OrganizationService organizationService)
	{
		this.organizationService = organizationService;
	}
}
