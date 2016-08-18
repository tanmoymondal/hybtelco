/**
 *
 */
package org.bonstore.core.services.impl;

import java.util.List;

import org.bonstore.core.dao.OrganizationDao;
import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.services.OrganizationService;


/**
 * @author Tanmoy_Mondal
 *
 */

public class OrganizationServiceImpl implements OrganizationService
{

	private OrganizationDao organizationDao;

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public List<OrganizationModel> getOrganizationList()
	{
		return organizationDao.getOrganizationList();
	}



	@Override
	public List<OrganizationModel> getOrganizationByID(final String organizationId)
	{
		return organizationDao.getOrganizationByID(organizationId);
	}

}
