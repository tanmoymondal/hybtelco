/**
 *
 */
package org.bonstore.storefront.security;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.acceleratorstorefrontcommons.security.AbstractAcceleratorAuthenticationProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Tanmoy_Mondal
 *
 */
public class BonstoreLoginFailureHandler extends AbstractAcceleratorAuthenticationProvider
{
	private static final Logger LOG = Logger.getLogger(BonstoreLoginFailureHandler.class);//NOPMD

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

}
