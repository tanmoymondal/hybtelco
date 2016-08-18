/**
 *
 */
package org.bonstore.core.services;

import java.util.List;

import org.bonstore.core.model.OrganizationModel;


/**
 * @author Tanmoy_Mondal
 *
 */
public interface OrganizationService
{
	public List<OrganizationModel> getOrganizationList();

	/**
	 * @param organizationId
	 * @return
	 */
	public List<OrganizationModel> getOrganizationByID(String organizationId);


}
