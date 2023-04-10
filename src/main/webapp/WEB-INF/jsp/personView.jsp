<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">

	<p>Full name: <c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/></p>
	<p>Email: <c:out value="${person.email}"/></p>
<form>
  <label for="date">Select a date:</label>
  <input type="date" name="date" value="${person.dateOfBirth}">
  <input type="submit" value="Submit">
  <input type="text" name="txt" value="${person.dateOfBirth}">
</form>
</div>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>