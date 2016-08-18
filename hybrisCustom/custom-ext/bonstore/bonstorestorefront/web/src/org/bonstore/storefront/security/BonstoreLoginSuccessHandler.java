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
public class BonstoreLoginSuccessHandler extends AbstractAcceleratorAuthenticationProvider
{
	private static final Logger LOG = Logger.getLogger(BonstoreLoginSuccessHandler.class);

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

}
