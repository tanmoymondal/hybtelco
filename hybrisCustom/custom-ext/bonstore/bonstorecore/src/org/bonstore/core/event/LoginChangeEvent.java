/**
 *
 */
package org.bonstore.core.event;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * @author Tanmoy_Mondal
 *
 */

public class LoginChangeEvent extends AbstractEvent implements ClusterAwareEvent
{
	private final boolean loginDisabled;
	private final String uid;

	public LoginChangeEvent(final String uid, final boolean loginDisabled)
	{
		super();
		this.uid = uid;
		this.loginDisabled = loginDisabled;
	}

	/**
	 * @return the uid
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * @return the loginDisabled
	 */
	public boolean isLoginDisabled()
	{
		return loginDisabled;
	}

	@Override
	public String toString()
	{
		return this.uid + "(" + this.loginDisabled + ")";
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId)
	{
		return (sourceNodeId == targetNodeId);
	}
}

