/**
 *
 */
package org.bonstore.core.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.List;

import org.bonstore.core.model.OrganizationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Tanmoy_Mondal
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class OrganizationDaoImplTest
{
	@Spy
	@InjectMocks
	private OrganizationDaoImpl organizationDao;
	@Mock
	private OrganizationModel organizationModel;
	@Mock
	private FlexibleSearchService flexibleSearchService;
	@Mock
	private SearchResult<Object> searchResult;
	@Mock
	private FlexibleSearchQuery query;

	final String queryString = //
			"SELECT {p:" + OrganizationModel.PK + "} "//
					+ "FROM {" + OrganizationModel._TYPECODE + " AS p} ";


	@Test
	public void shouldReturnOrganizationList()
	{
		final List<Object> objectModels = Arrays.asList(organizationModel);
		final List<OrganizationModel> organizationModels = Arrays.asList(organizationModel);
		when(flexibleSearchService.search(queryString)).thenReturn(searchResult);
		when(searchResult.getResult()).thenReturn(objectModels);
		assertEquals(organizationModels, organizationDao.getOrganizationList());
	}
}
