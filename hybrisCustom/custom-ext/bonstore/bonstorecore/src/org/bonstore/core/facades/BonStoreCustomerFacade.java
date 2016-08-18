package org.bonstore.core.facades;

import java.util.List;

import org.bonstore.data.OrganizationData;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */

public interface BonStoreCustomerFacade {

    List<OrganizationData> getOrganizations();

    List<OrganizationData> getOrganizationsForCurrentUser();

    void editOrganization(OrganizationData organizationData);

    boolean removeOrganization(String organizationId);

    void addOrganization(OrganizationData organizationData);

}
