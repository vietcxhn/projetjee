<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
    <h1>Group <c:out value="${group.name}" /></h1>
    <table class="table table-hover">
        <c:forEach items="${group.persons}" var="person">
        
			<c:url var="view" value="/person/${person.id}" />
            <tr>
                <td>
                    <c:out value="${person.id}" />
                </td>
                <td><c:out value="${person.firstName} ${person.lastName}" /></td>
                <td><a href="${view}">view</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>