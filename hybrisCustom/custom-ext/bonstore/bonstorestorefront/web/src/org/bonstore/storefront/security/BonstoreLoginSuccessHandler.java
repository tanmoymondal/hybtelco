/**
 *
 */
package org.bonstore.storefront.security;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Tanmoy_Mondal
 *
 */
public class BonstoreLoginSuccessHandler
{
	private static final Logger LOG = Logger.getLogger(BonstoreLoginSuccessHandler.class);
	private UserService userService;
	private ModelService modelService;

	public void registerSuccessLogin(final String uid)
	{
		LOG.info("### Entering the registerSuccessLogin method ###");
		if (StringUtils.isNotEmpty(uid))
		{
			final CustomerModel customerModel = (CustomerModel) getUserService().getUserForUID(StringUtils.lowerCase(uid));

			getModelService().refresh(customerModel);
			final int attemptCount = customerModel.getAttemptCount();
			if (attemptCount > 0 && attemptCount < 3)
			{
				customerModel.setAttemptCount(0);
				getModelService().save(customerModel);
			}

		}
		LOG.info("### Exiting the registerSuccessLogin method ###");

	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

}
