/**
 *
 */
package org.bonstore.core.services.impl;

import java.util.List;

import org.bonstore.core.dao.OrganizationDao;
import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.services.OrganizationService;
import org.springframework.stereotype.Component;


/**
 * @author Tanmoy_Mondal
 *
 */
@Component(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService
{

	private OrganizationDao organizationDao;

	public void setOrganizationDao(final OrganizationDao organizationDao)
	{
		this.organizationDao = organizationDao;
	}



	public List<OrganizationModel> getOrganizationList()
	{
		return organizationDao.getOrganizationList();
	}



}
