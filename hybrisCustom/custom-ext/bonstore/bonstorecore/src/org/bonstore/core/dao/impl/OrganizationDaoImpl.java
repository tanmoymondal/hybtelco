/**
 *
 */
package org.bonstore.core.dao.impl;

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
	//	private FlexibleSearchQuery query;
	private SearchResult<OrganizationModel> searchResult;
	private List<OrganizationModel> organizationModels;

	@Override
	public List<OrganizationModel> getOrganizationList()
	{
		final String queryString = //
				"SELECT {p:" + OrganizationModel.PK + "} "//
						+ "FROM {" + OrganizationModel._TYPECODE + " AS p} ";


		/*
		 * query = new FlexibleSearchQuery(queryString); searchResult = flexibleSearchService.<OrganizationModel>
		 * search(query); // return flexibleSearchService.<OrganizationModel> search(query).getResult();
		 * organizationModels = searchResult.getResult(); return organizationModels;
		 */
		searchResult = flexibleSearchService.search(queryString);
		organizationModels = searchResult.getResult();
		return organizationModels;
	}
}
