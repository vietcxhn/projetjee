<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
    <h1>Groups</h1>
    <table class="table table-hover">
        <c:forEach items="${groups}" var="group">
        
			<c:url var="view" value="/group/${group.id}" />
            <tr>
                <td><i><c:out value="${group.name}" /></i></td>
                <td><a href="${view}">view</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>