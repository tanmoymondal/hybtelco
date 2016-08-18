<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="span-20 last">

	<div class="accountContentPane clearfix">

		<div class="headline">
			<spring:theme code="text.account.organizations" text="Organizations"/>
		</div>
		<div class="description">
			<spring:theme code="text.account.organizations.manageYourOrganizations" text="Manage your Organizations"/>
		</div>
		<c:choose>

			<c:when test="${not empty organizationsData}">

			  <table>
            <tr style="border-color: black; border: 2pt;">
                <th style="padding: 10pt;">ID</th>
                <th style="padding: 10pt;">Name</th>
                <th style="padding: 10pt;">Phone</th>
                <th style="padding: 10pt;">Email</th>
            </tr>

				<c:forEach items="${organizationsData}" var="organization">
					<div class="organizationItem">


						<ycommerce:testId code="organizations_label">
							<tr>
                   			    <td style="padding: 10pt;">${fn:escapeXml(organization.id)}</td>
								<td style="padding: 10pt;">${fn:escapeXml(organization.name)}</td>
								<td style="padding: 10pt;">${fn:escapeXml(organization.phonenumber)}</td>
								<td style="padding: 10pt;">${fn:escapeXml(organization.email)}</td>

						<td style="padding: 10pt;">
						<div class="buttons">
								<ycommerce:testId code="addressBook_editAddress_button">
									<a class="button" href="edit-organization/${organization.id}">
										<spring:theme code="text.edit" text="Edit"/>
									</a>
								</ycommerce:testId>
						</div>
						</td>
						<td style="padding: 10pt;">
						<div class="buttons">
								<ycommerce:testId code="addressBook_removeAddress_button">
									<a class="button removeOrganizationButton" data-organization-id="${organization.id}" href="remove-organization/${organization.id}"><spring:theme code="text.remove" text="Remove"/></a>
								</ycommerce:testId>
						</div>
						</td>
						</tr>
						</ycommerce:testId>

					</div>
				</c:forEach>
				</table>
			</c:when>

			<c:otherwise>
				<p class="emptyMessage">
					<spring:theme code="text.account.organizations.noSavedOrganizations" text="No organizations for this customer"/>
				</p>
			</c:otherwise>

		</c:choose>
		<ycommerce:testId code="addressBook_addNewOrganization_button">
			<a href="add-organization" class="button positive">
				<spring:theme code="text.account.organizations.addOrganization" text="Add new organization"/>
			</a>
		</ycommerce:testId>
	</div>
</div>