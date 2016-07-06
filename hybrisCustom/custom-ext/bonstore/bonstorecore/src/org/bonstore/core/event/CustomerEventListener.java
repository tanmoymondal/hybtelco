/**
 *
 */
package org.bonstore.core.event;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Tanmoy_Mondal
 *
 */
public class CustomerEventListener extends AbstractEventListener<LoginChangeEvent>
{

	static final private Logger LOG = Logger.getLogger(CustomerEventListener.class);

	@Autowired
	private ModelService modelService;
	@Autowired
	private UserService userService;

	@Override
	public void onEvent(final LoginChangeEvent event)
	{
		try
		{
			LOG.debug("### Entering event handler ###");
			Thread.sleep(2000);
			final CustomerModel customer = (CustomerModel) userService.getUserForUID(StringUtils.lowerCase(event.getUid()));

			customer.setAttemptCount(0);
			customer.setStatus(false);

			modelService.save(customer);
			LOG.debug("### Leaving event handler ###");

		}
		catch (final InterruptedException e)
		{
			// do nothing if thread is interrupted
		}
	}

}
