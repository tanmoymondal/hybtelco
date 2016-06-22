/**
 *
 */
package org.bonstore.core.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.bonstore.core.dao.OrganizationDao;
import org.bonstore.core.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Tanmoy_Mondal
 *
 */
@Component(value = "organizationDao")
public class OrganizationDaoImpl implements OrganizationDao
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.bonstore.core.dao.OrganizationDao#getOrganizationList()
	 */
	@Override
	public List<OrganizationModel> getOrganizationList()
	{
		final String queryString = //
				"SELECT {p:" + OrganizationModel.PK + "} "//
						+ "FROM {" + OrganizationModel._TYPECODE + " AS p} ";


		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		return flexibleSearchService.<OrganizationModel> search(query).getResult();
	}
}
