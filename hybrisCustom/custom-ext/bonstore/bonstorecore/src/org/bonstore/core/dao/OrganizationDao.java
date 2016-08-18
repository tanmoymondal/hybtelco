/**
 *
 */
package org.bonstore.core.dao;

import java.util.List;

import org.bonstore.core.model.OrganizationModel;


/**
 * @author Tanmoy_Mondal
 *
 */
public interface OrganizationDao
{
	public List<OrganizationModel> getOrganizationList();

	public List<OrganizationModel> getOrganizationByID(String organizationId);
}
