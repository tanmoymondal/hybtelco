package org.bonstore.core.organization.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.bonstore.core.model.OrganizationModel;
import org.bonstore.core.organization.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public class UsersDaoImpl implements UsersDao
{

    @Autowired
    private FlexibleSearchService flexibleSearchService;


    @Override
    public List<OrganizationModel> getOrganizations()
    {
        // Build a query for the flexible search.
        final String queryString = //
                "SELECT {o:" + OrganizationModel.PK + "} " + "FROM {" + OrganizationModel._TYPECODE + " AS o}";
        final SearchResult<OrganizationModel> searchResult = flexibleSearchService.search(queryString);
        return searchResult.getResult();
    }

    @Override
    public List<OrganizationModel> getOrganizationByID(final String id)
    {
        final String queryString = "SELECT {o:" + OrganizationModel.PK + "}" + "FROM {" + OrganizationModel._TYPECODE + " AS o}"
                + "WHERE {o:" + OrganizationModel.ID + "}=?id";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("id", id);
        final SearchResult<OrganizationModel> searchResult = flexibleSearchService.search(query);
        return searchResult.getResult();
    }

}
