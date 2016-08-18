/**
 *
 */
package org.bonstore.core.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import org.bonstore.core.dao.BonstoreCustomerDao;


/**
 * @author Tanmoy_Mondal
 *
 */
public class BonstoreCustomerDaoImpl implements BonstoreCustomerDao
{
	private ModelService modelService;

	@Override
	public void changeCustomerDetails(final CustomerModel customer)
	{
		customer.setAttemptCount(0);
		customer.setStatus(false);
		modelService.save(customer);
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
