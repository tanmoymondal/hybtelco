/**
 *
 */
package org.bonstore.core.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import org.bonstore.core.dao.BonstoreCustomerDao;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Tanmoy_Mondal
 *
 */
public class BonstoreCustomerDaoImpl implements BonstoreCustomerDao
{
	@Autowired
	private ModelService modelService;

	@Override
	public void changeCustomerDetails(final CustomerModel customer)
	{
		customer.setAttemptCount(0);
		customer.setStatus(false);
		modelService.save(customer);
	}
}
