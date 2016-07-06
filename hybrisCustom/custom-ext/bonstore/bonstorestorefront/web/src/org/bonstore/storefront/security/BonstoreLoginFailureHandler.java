/**
 *
 */
package org.bonstore.storefront.security;

import de.hybris.platform.acceleratorstorefrontcommons.security.AbstractAcceleratorAuthenticationProvider;
import de.hybris.platform.core.model.user.CustomerModel;

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
		if (StringUtils.isNotEmpty(uid))
		{
			final CustomerModel customerModel = (CustomerModel) getUserService().getUserForUID(StringUtils.lowerCase(uid));

			getModelService().refresh(customerModel);
			int attemptCount = customerModel.getAttemptCount();
			++attemptCount;
			boolean flag = false;
			if (attemptCount < 3)
			{
				flag = true;
				customerModel.setAttemptCount(attemptCount);
			}
			if (attemptCount == 3)
			{
				flag = true;
				customerModel.setAttemptCount(attemptCount);
				customerModel.setStatus(true);
				customerModel.setLoginDisabled(true);
			}
			if (flag)
			{
				getModelService().save(customerModel);
			}

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Failed Login for user " + uid + ", count now " + attemptCount);
			}
		}

	}

}
