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

	/**
	 * @param bonStoreOrganizationDao
	 *           the bonStoreOrganizationDao to set
	 */
	public void setBonStoreOrganizationDao(final OrganizationDao organizationDao)
	{
		this.organizationDao = organizationDao;
	}



	@Override
	public List<OrganizationModel> getOrganizationList()
	{
		return organizationDao.getOrganizationList();
	}

	/**
	 * @param organizationDao
	 *           the organizationDao to set
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao)
	{
		this.organizationDao = organizationDao;
	}



}
