/**
 *
 */
package org.bonstore.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.bonstore.core.dao.OrganizationDao;
import org.bonstore.core.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Tanmoy_Mondal
 *
 */
public class OrganizationDaoImpl implements OrganizationDao
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;
	private SearchResult<OrganizationModel> searchResult;
	private List<OrganizationModel> organizationModels;

	@Override
	public List<OrganizationModel> getOrganizationList()
	{
		final String queryString = //
				"SELECT {p:" + OrganizationModel.PK + "} "//
						+ "FROM {" + OrganizationModel._TYPECODE + " AS p} ";

		searchResult = flexibleSearchService.search(queryString);
		organizationModels = searchResult.getResult();
		return organizationModels;
	}

	@Override
	public List<OrganizationModel> getOrganizationByID(final String organizationId)
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT {o:").append("pk").append("} ");
		builder.append("FROM {").append("Organization").append(" AS o} ");
		builder.append("WHERE ").append("{o:").append("	id").append("}=?organizationId");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(builder.toString());
//		query.addQueryParameter("organizationid", organizationId);
		query.addQueryParameter("id", organizationId);

		return this.flexibleSearchService.<OrganizationModel> search(query).getResult();
	}
}
