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

import org.bonstore.core.dao.OrganizationDao;
import org.bonstore.core.model.OrganizationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Tanmoy_Mondal
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class OrganizationDaoImplTest
{
	@InjectMocks
	OrganizationModel organizationModel;
	@Mock
	FlexibleSearchService flexibleSearchService;
	@Mock
	SearchResult<OrganizationModel> searchResult;
	@Mock
	OrganizationDao organizationDao;
	@Mock
	FlexibleSearchQuery query;

	@Test
	public void getOrganizationList()
	{
		final List<OrganizationModel> models = Arrays.asList(organizationModel);
		when(flexibleSearchService.<OrganizationModel> search(query)).thenReturn(searchResult);
		when(flexibleSearchService.<OrganizationModel> search(query).getResult()).thenReturn(models);
		when(organizationDao.getOrganizationList()).thenReturn(models);
		assertEquals(models.size(), organizationDao.getOrganizationList().size());
	}
}
