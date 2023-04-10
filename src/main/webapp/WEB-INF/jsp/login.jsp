<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center mb-4">Login</h2>
                <form action="/login" method="post">
           			<sec:csrfInput />
                
                    <div class="form-group">
                        <label for="username">Id:</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                </form>
            </div>
        </div>
    </div>
    
<%@ include file="/WEB-INF/jsp/footer.jsp"%>