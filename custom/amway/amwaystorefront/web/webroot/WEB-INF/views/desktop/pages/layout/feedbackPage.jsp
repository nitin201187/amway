<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
<c:url var="feedbackSubmitUrl" value="feedbackPage"/>
	<form:form id="submitFeedback" action="${feedbackSubmitUrl}" method="post" commandName="feedbackForm">
		<formElement:formInputBox idKey="userId" labelKey="User Id" path="userId"/>
		<formElement:formSelectBox idKey="action" labelKey="Action" path="action" items="${actionTypes}" skipBlank="true" />
		<formElement:formInputBox idKey="message" labelKey="Message" path="message"/>
		<formElement:formInputBox idKey="url" labelKey="Action Url" path="url"/>
		<button type="submit">Submit</button>
	</form:form>
</div>