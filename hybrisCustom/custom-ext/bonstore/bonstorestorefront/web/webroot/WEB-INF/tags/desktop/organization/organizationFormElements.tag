<%@ attribute name="tabIndex" required="false" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<formElement:formInputBox idKey="organization.id"	labelKey="organization.id" path="id" mandatory="true" />
<formElement:formInputBox idKey="organization.name"	labelKey="organization.name" path="name" inputCSS="text" mandatory="true" />
<formElement:formInputBox idKey="organization.phonenumber" labelKey="organization.phonenumber" path="phonenumber" inputCSS="text"	mandatory="true" />
<formElement:formInputBox idKey="organization.email" labelKey="organization.email" path="email" inputCSS="text"	mandatory="true" />