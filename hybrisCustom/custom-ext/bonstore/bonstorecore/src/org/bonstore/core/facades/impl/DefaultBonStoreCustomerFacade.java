package org.bonstore.core.facades.impl;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.List;

import org.bonstore.core.facades.BonStoreCustomerFacade;
import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.organization.OrgUserService;
import org.bonstore.data.OrganizationData;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public class DefaultBonStoreCustomerFacade implements BonStoreCustomerFacade
{
    private OrgUserService orgUserService;
    private UserService userService;

    @Autowired
    private ModelService modelService;


    @Override
    public List<OrganizationData> getOrganizations()
    {
        return getOrganizationDataList(orgUserService.getOrganizations());
    }

    @Override
    public List<OrganizationData> getOrganizationsForCurrentUser()
    {
        return getOrganizationDataList(getCurrentUser().getOrganizations());
    }


    @Override
    public void editOrganization(final OrganizationData organizationData)
    {
        final List<OrganizationModel> listOrgModel = orgUserService.getOrganizationByID(organizationData.getId());

        if (listOrgModel != null && listOrgModel.size() == 1)
        {
            final OrganizationModel organizationModel = listOrgModel.get(0);
            orgUserService.editOrganization(mapOrgDataToOrgModel(organizationModel, organizationData));
        }
    }

    @Override
    public boolean removeOrganization(final String organizationId)
    {
        return orgUserService.removeOrganization(getCurrentUser(), organizationId);

    }

    @Override
    public void addOrganization(final OrganizationData organizationData)
    {
        final OrganizationModel organizationModel = modelService.create(OrganizationModel.class);
        orgUserService.addOrganization(mapOrgDataToOrgModel(organizationModel, organizationData));
    }


    protected OrganizationModel mapOrgDataToOrgModel(final OrganizationModel organizationModel,
                                                     final OrganizationData organizationData)
    {
        organizationModel.setId(Integer.parseInt(organizationData.getId()));
        organizationModel.setPhone(organizationData.getPhonenumber());
        organizationModel.setName(organizationData.getName());
        organizationModel.setEmail(organizationData.getEmail());
        return organizationModel;
    }

    protected List<OrganizationData> getOrganizationDataList(final List<OrganizationModel> organizationModels)
    {

        final List<OrganizationData> organizationGroupDataList = new ArrayList<>();

        for (final OrganizationModel om : organizationModels)
        {
            final OrganizationData orgData = new OrganizationData();
            orgData.setId(Integer.toString(om.getId()));
            orgData.setName(om.getName());
            orgData.setPhonenumber(om.getPhone());
            orgData.setEmail(om.getEmail());
            organizationGroupDataList.add(orgData);
        }
        return organizationGroupDataList;

    }

    protected CustomerModel getCurrentUser()
    {
        CustomerModel currentUser = null;
        if (userService.getCurrentUser() instanceof CustomerModel)
        {
            currentUser = (CustomerModel) userService.getCurrentUser();
        }
        return currentUser;
    }

    /**
     * @return the orgUserService
     */
    public OrgUserService getOrgUserService()
    {
        return orgUserService;
    }

    /**
     * @param orgUserService
     *           the orgUserService to set
     */
    public void setOrgUserService(final OrgUserService orgUserService)
    {
        this.orgUserService = orgUserService;
    }

    /**
     * @return the userService
     */
    public UserService getUserService()
    {
        return userService;
    }

    /**
     * @param userService
     *           the userService to set
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}
