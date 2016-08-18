<%@ attribute name="cancelUrl" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="organization"
	tagdir="/WEB-INF/tags/desktop/organization"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>



<form:form method="post" commandName="organizationForm">
	<div id="organizationForm" class="organizationForm" class="clearfix">
		<organization:organizationFormElements />
	</div>


<div id="organizationform_button_panel" class="form-actions">
	<c:if test="${not organizationsEmpty}">
		<ycommerce:testId code="cancel_button">
			<c:url value="${cancelUrl}" var="cancel" />
			<a class="button" href="${cancel}"><spring:theme
					code="button.account.cancel" text="Cancel" /></a>
		</ycommerce:testId>

		<ycommerce:testId code="saveOrganization_button">
			<button
				class="positive right change_address_button show_processing_message"
				type="submit">
				<spring:theme code="text.account.saveOrganization"
					text="Save Organization" />
			</button>
		</ycommerce:testId>

	</c:if>

</div>
</form:form>