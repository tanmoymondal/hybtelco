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
public class BonstoreLoginFailureHandler
{
	private static final Logger LOG = Logger.getLogger(BonstoreLoginFailureHandler.class);//NOPMD
	private UserService userService;
	private ModelService modelService;


	public void registerFailedLogin(final String uid)
	{
		LOG.info("### Entering the registerFailedLogin method ###");

		if (StringUtils.isNotEmpty(uid))
		{
			final CustomerModel customerModel = (CustomerModel) getUserService().getUserForUID(StringUtils.lowerCase(uid));
			int attemptCount = customerModel.getAttemptCount();
			++attemptCount;
			if (attemptCount == 3)
			{
				customerModel.setStatus(true);
				customerModel.setLoginDisabled(true);
			}
			customerModel.setAttemptCount(attemptCount);
			getModelService().save(customerModel);

		}
		LOG.info("### Exiting the registerFailedLogin method ###");

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
