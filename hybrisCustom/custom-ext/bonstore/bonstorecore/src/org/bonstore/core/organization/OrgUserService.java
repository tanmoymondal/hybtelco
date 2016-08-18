package org.bonstore.core.organization;

import de.hybris.platform.core.model.user.CustomerModel;
import java.util.List;
import org.bonstore.core.model.OrganizationModel;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public interface OrgUserService
{

    List<OrganizationModel> getOrganizations();

    List<OrganizationModel> getOrganizationByID(String organizationId);

    void editOrganization(OrganizationModel organizationModel);

    boolean removeOrganization(CustomerModel customerModel, String organizationId);

    void addOrganization(OrganizationModel organizationModel);

}
