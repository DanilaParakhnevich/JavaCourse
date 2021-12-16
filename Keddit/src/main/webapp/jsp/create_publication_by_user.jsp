<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="localization.locale"/>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/Copyright.tld"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/post-edit.css">
  <title><fmt:message key="CREATE_POST"/></title>
  <c:set var="path" value="${pageContext.request.contextPath}"/>
</head>
<jsp:useBean id="user" scope="session" type="by.parakhnevich.keddit.bean.user.User"/>
<body>
  <main class="main">
    <header class="header">
      <c:set var="id" value="user.id"/>
      <div class="header-content">
        <div class="header-left">
          <ul class="header-left-list list-reset">
            <li class="header-left-list-item">
              <a href="?command=publications" class="header-left-link link-reset logo-img">
                <img src="${path}/images/logo.jpg">
              </a>
            </li>
            <li class="header-left-list-item">
              <a href="?command=my_communities&id=${id}" class="header-left-link link-reset"><fmt:message key="MY_COMMUNITIES"/></a>
            </li>
            <li class="header-left-list-item">
              <a href="?command=about" class="header-left-link link-reset"><fmt:message key="ABOUT"/></a>
            </li>
            <c:if test="${user.role.toString().equals('MODERATOR') || user.role.toString().equals('ADMIN')}">
              <li class="header-left-list-item">
                <a href="?command=on_moderation_page" class="header-left-link link-reset"><fmt:message key="ON_MODERATION"/></a>
              </li>
            </c:if>
          </ul>
        </div>
        <div class="header-right">

          <a href="?command=search_page" class="header-right-link link-reset">
            <svg fill="#333" xmlns="http://www.w3.org/2000/svg"  viewBox="0 0 126 126" width="40px" height="40px">
              <path d="M 56.599609 21.599609 C 34.099609 21.599609 15.800781 40.100781 15.800781 62.800781 C 15.800781 85.600781 34.099609 104 56.599609 104 C 66.899609 104 76.3 100.09922 83.5 93.699219 L 85.800781 96 L 83.699219 98.199219 C 82.499219 99.399219 82.499219 101.3 83.699219 102.5 L 101.69922 120.69922 C 102.29922 121.29922 103.00078 121.59961 103.80078 121.59961 C 104.60078 121.59961 105.40039 121.29922 105.90039 120.69922 L 113.90039 112.59961 C 115.00039 111.39961 115.00078 109.50039 113.80078 108.40039 L 95.800781 90.199219 C 95.200781 89.599219 94.499219 89.300781 93.699219 89.300781 C 92.899219 89.300781 92.099609 89.599219 91.599609 90.199219 L 89.5 92.400391 L 87.199219 90 C 93.499219 82.7 97.400391 73.200781 97.400391 62.800781 C 97.400391 40.100781 79.099609 21.599609 56.599609 21.599609 z M 56.599609 27.699219 C 75.799609 27.699219 91.400391 43.500391 91.400391 62.900391 C 91.400391 82.300391 75.799609 98 56.599609 98 C 37.399609 98 21.800781 82.300391 21.800781 62.900391 C 21.800781 43.500391 37.399609 27.699219 56.599609 27.699219 z M 56.699219 40.199219 C 47.199219 40.199219 38.7 46.300781 35.5 55.300781 C 35 56.600781 35.699609 58.199609 37.099609 58.599609 C 37.399609 58.699609 37.7 58.800781 38 58.800781 C 39.1 58.800781 40.1 58.1 40.5 57 C 42.9 50.1 49.499219 45.400391 56.699219 45.400391 C 58.099219 45.400391 59.300781 44.200781 59.300781 42.800781 C 59.300781 41.400781 58.099219 40.199219 56.699219 40.199219 z M 37.699219 64.900391 C 36.299219 64.900391 35.099609 66 35.099609 67.5 L 35.099609 67.900391 C 35.199609 69.300391 36.300781 70.5 37.800781 70.5 C 39.200781 70.5 40.400391 69.300391 40.400391 67.900391 L 40.400391 67.599609 C 40.400391 66.099609 39.300781 64.900391 37.800781 64.900391 L 37.699219 64.900391 z M 93.800781 96.599609 L 107.59961 110.59961 L 103.80078 114.40039 L 90 100.40039 L 93.800781 96.599609 z"/>
            </svg>
            <c:if test="${user != null}">
              <c:if test="${user.photo == null}">
                <a href="?command=user_page&id=${user.id}" class="header-right-link link-reset header-right-link-user">
                  <img src="${path}/photos/unnamed-avatar.png" class="image-file" id="image2" />
                  <p class="header-right-text"><c:out value="${user.nickname}"/></p>
                </a>
              </c:if>
              <c:if test="${user.photo != null}">
                <a href="?command=user_page&id=${user.id}" class="header-right-link link-reset header-right-link-user">
                  <img src="${path}/photos/${user.photo.name}" class="image-file" id="image3" />
                  <p class="header-right-text"><c:out value="${user.nickname}"/></p>
                </a>
              </c:if>
            </c:if>
            <a href="?command=change_lang&value=1">
              <img src="${path}/images/rb.jpg" width="20" height="15">
            </a>
            <a href="?command=change_lang&value=2">
              <img src="${path}/images/russia.png" width="20" height="15">
            </a>
            <a href="?command=change_lang&value=3">
              <img src="${path}/images/usa.png" width="20" height="15">
            </a>
          </a>
        </div>
      </div>
    </header>
    <div class="create-post">
      <div class="create-post-content">
        <h1 class="create-post-title"><fmt:message key="NEW_POST"/></h1>
        <form class="login-form" action="/keddit.by/controller?command=create_publication_by_user" enctype='multipart/form-data' method="POST">
          <ul class="create-post-list list-reset">
            <li class="create-post-item">
              <h2 class="create-post-item-text"><fmt:message key="TITLE"/></h2>
              <input class="create-post-item-input btn-reset" name="head" type="text" maxlength="140">
            </li>
            <li class="create-post-item">
              <h2 class="create-post-item-text"><fmt:message key="CAPTION"/></h2>
              <textarea class="create-post-item-input create-post-textarea btn-reset" name="body" cols="61" rows="6"></textarea>
            </li>
            <li class="create-post-item">
              <input class="reg-input inputfile" name="file" type="file" id="file" />
              <label for="file"><fmt:message key="UPLOAD_IMAGE"/></label>
              <img src="${path}/images/upload-image.png" class="image-post" id="image1-post" />
              <script>
                document.getElementById('file').addEventListener('change', function () {
                  if (this.files && this.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                      document.getElementById('image1-post').setAttribute('src', e.target.result);
                    };
                    reader.readAsDataURL(this.files[0]);
                  }
                });
              </script>
            </li>
            <li class="create-post-item">
              <h2 class="create-post-item-text"><fmt:message key="TAGS"/></h2>
              <textarea name="tags" class="create-post-item-input create-post-textarea btn-reset" cols="61" rows="6"></textarea>
              <button type="button" class="post-item-button btn-reset"></button>
            </li>
          </ul>
          <button type="submit" class="submit-btn btn-reset"><fmt:message key="CREATE"/></button>
          <c:if test="${error_message_create_publication != null}">
            <p class="message_error"><fmt:message key="${error_message_create_publication}"/></p>
          </c:if>
        </form>
      </div>
    </div>
    <footer class="footer">
      <div class="footer-wrapper wrapper">
        <div class="left-footer">
          <a href="?command=publications" class="header-left-link link-reset logo-img">
            <img src="${path}/images/logo.jpg">
          </a>
          <p class="footer-subtitle"><tag:copyright developer="Danila Parakhnevich" year="<%=Calendar.getInstance().get(Calendar.YEAR)%>"/></p>
        </div>
        <div class="right-footer">
          <button class="footer-logout btn-reset"><a href="?command=logout" class="header-btn-link"><fmt:message key="LOGOUT"/></a></button>
        </div>
      </div>
    </footer>
  </main>
</body>
<%request.getSession().setAttribute("prev_link", "/keddit.by/controller?create_publication_by_user_page");%>
