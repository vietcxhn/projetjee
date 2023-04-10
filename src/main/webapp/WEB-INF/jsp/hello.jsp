<%@ include file="/WEB-INF/jsp/header.jsp" %>

  <div class="container">
    <h1>Hello - Spring Application</h1>
    <p>Greetings, it is now <c:out value="${now}" default="None" /></p>
    <p>${message}</p>
    <p>${counter}</p>
  </div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>