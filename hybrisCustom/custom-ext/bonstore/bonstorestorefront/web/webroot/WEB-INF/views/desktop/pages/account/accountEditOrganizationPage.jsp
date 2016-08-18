<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="organization" tagdir="/WEB-INF/tags/desktop/organization"%>
<div class="span-24">
	<div class="span-20 last">
		<div class="accountContentPane clearfix">
			<div class="headline"><spring:theme code="text.account.organizations.organizationDetails" text="Organization Details"/></div>
			<div class="required right"><spring:theme code="form.required" text="Fields marked * are required"/></div>
			<div class="description"><spring:theme code="text.account.organizations.addEditform" text="Please use this form to add/edit an organization."/></div>
			<organization:organizationFormSelector cancelUrl="/my-account/organizations"/>
		</div>
	</div>
</div>