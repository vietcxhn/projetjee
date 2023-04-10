<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrap_css"
	value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	value="/webjars/bootstrap/4.6.0-1/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="css" value="/style.css" />

<html>
	<head>
	<meta charset="UTF-8">
	<title>Annuaire</title>
	<link rel="stylesheet" href="${bootstrap_css}">
	<script src="${jquery_js}"></script>
	<script src="${bootstrap_js}"></script>
</head>

<body>
	
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="/">Annuaire</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <sec:authorize access="isAuthenticated()">
            
	            <li class="nav-item">
	            	
                  <a href="/group/list" class="btn">
                      Group
                  </a>
				</li>
	            <li class="nav-item">
		            <div class="dropdown">
					  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                		<sec:authentication property="name"/>
					    <span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					    <li>
		                  <a href="/person/<sec:authentication property="name"/>" class="btn dropdown-item">
		                      Profil
		                  </a>
		                </li>
		                <li><hr class="dropdown-divider"></li>
		                <li>
		                  <a href="/logout" class="btn dropdown-item">
		                      Logout
		                  </a>
		                </li>
					  </ul>
					</div>
				</li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
	            <li class="nav-item">
	              <a class="btn" href="/login">Se connecter</a>
	            </li>
            </sec:authorize>
          </ul>
<!--           {{#authenticated}} -->
<!--           <form class="d-flex" action="/search"> -->
<!--             <input class="form-control me-2" type="text" placeholder="Requête" name="query" aria-label="Search" value="{{query}}"> -->
<!--             <button class="btn btn-outline-success" type="submit">Chercher</button> -->
<!--           </form> -->
<!--           {{/authenticated}} -->
        </div>
      </div>
    </nav>