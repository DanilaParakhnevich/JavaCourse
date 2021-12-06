<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: beylo
  Date: 11/28/2021
  Time: 1:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="hamburger-menu">
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${path}/styles/error.css">
</div>
<main>
    <div class="container">
        <div class="col-md-6 align-self-center">
            <h1>404</h1>
            <h2>UH OH! You're lost.</h2>
            <p>The page you are looking for does not exist.
            How you got here is a mystery. But you can click the button below
            to go back to the homepage.
            </p>
            <a href="?command=publications">
                <button type="submit"  class="btn green">HOME</button>
            </a>
        </div>
    </div>
</main>
<script src="${path}/js/error.js"></script>
