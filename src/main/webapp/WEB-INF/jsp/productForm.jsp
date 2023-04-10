<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <h1>Edit Product</h1>

    <form:form method="POST" modelAttribute="product">

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="form-group">
            <label for="name">Name:</label>
            <form:input class="form-control" path="name" />
            <form:errors path="name" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <form:textarea class="form-control" path="description" rows="4" />
            <form:errors path="description" cssClass="alert alert-warning"
                element="div" />
        </div>
        <div class="form-group">
		    <label for="type">Type:</label>
		    <form:select path="type" multiple="false" class="form-control">
		        <form:option value="" label="--- Select ---" />
		        <form:options items="${productTypes}" />
		    </form:select>
		    <form:errors path="type" cssClass="alert alert-warning"
		        element="div" />
		</div>
		<div class="form-group">
		    <label for="code">Code:</label>
		    <form:input path="code" class="form-control" />
		    <form:errors path="code" cssClass="alert alert-warning"
		        element="div" />
		</div>
        <div class="form-group">
            <label for="price">Price:</label>
            <form:input path="price" class="form-control" />
            <form:errors path="price" cssClass="alert alert-warning"
                element="div" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>