/**
 *
 */
package org.bonstore.core.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

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
public class OrganizationServiceUnitTest
{
	@InjectMocks
	private OrganizationModel organizationModel;

	@InjectMocks
	private OrganizationServiceImpl organizationServiceImpl;

	@Mock
	private OrganizationDao organizationDao;

	@Test
	public void testGetOrganizationList()
	{
		final List<OrganizationModel> models = Arrays.asList(organizationModel);
		when(organizationDao.getOrganizationList()).thenReturn(models);

		final List<OrganizationModel> result = organizationServiceImpl.getOrganizationList();
		assertEquals("We should find one", 1, result.size());
	}

}
