<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">

	<p>Full name: <c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/></p>
	<p>Email: <c:out value="${person.email}"/></p>
    <form>
      <label for="date">Select a date:</label>
      <input type="date" name="date" id="date" value="${person.dateOfBirth}">
      <input type="submit" value="Submit">
      <input type="text" name="txt" value="${person.dateOfBirth}">
    </form>
    <sec:authentication property="principal.username" var="username"/>
    <sec:authorize access="isAuthenticated()">
        <c:if test="${username == person.id}">
            Hello John!
        </c:if>
    </sec:authorize>
</div>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>