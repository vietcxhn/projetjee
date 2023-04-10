<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="list" value="/course/list" />

<div class="container">
	<h1>Annuaire</h1>
	<sec:authorize access="!isAuthenticated()">
		<a href="/login">login</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="/logout">logout</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
	    Manage Users
	</sec:authorize>
	<p>
		Message is
		<c:out value="${message}" />
	</p>
	<p>
		<a href="${list}">Course</a>
	</p>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
