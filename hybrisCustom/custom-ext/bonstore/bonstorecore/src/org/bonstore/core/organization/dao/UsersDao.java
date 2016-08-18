package org.bonstore.core.organization.dao;


import java.util.List;

import org.bonstore.core.model.OrganizationModel;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public interface UsersDao {

    List<OrganizationModel> getOrganizations();

    List<OrganizationModel> getOrganizationByID(final String organizationId);
}
