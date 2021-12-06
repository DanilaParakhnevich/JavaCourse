<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="localization.locale"/>
<c:url value="/styles/login.css" var="login_css_path"/>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${login_css_path}">
  <title><fmt:message key="LOGIN"/></title>
</head>
<body>
  <main class="main">
    <div class="login">
      <div class="login-content">
        <h1 class="login-title"><fmt:message key="LOGIN"/></h1>
        <form class="login-form" action="/keddit.by/controller" method="POST">
          <input type="hidden" name="command" value="login">
          <ul class="login-list list-reset">
            <li class="login-item ">
              <input class="login-input" type="text" name="name" placeholder=<fmt:message key="USR"/> value="">
            </li>
            <li class="login-item">
              <input class="login-input" type="password" name="password" placeholder=<fmt:message key="PSWRD"/> value="">
            </li>
          </ul>
          <input class="login-submit btn-reset" type="submit" value=<fmt:message key="LOGIN_BUTTON"/>>
        <a href="?command=registration_page" class="login-link link-reset"><fmt:message key="IDH"/></a>
          <c:if test="${error_message_login != null}">
            <p class="message_error"><fmt:message key="${error_message_login}"/></p>
          </c:if>
        </form>
      </div>
    </div>
  </main>
</body>