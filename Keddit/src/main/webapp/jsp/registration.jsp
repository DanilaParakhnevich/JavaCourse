<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="localization.locale"/>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/registration.css">
  <title>Registration</title>
</head>

<body>
  <main class="main">
    <div class="reg">
      <div class="reg-content">
        <h1 class="reg-title"><fmt:message key="REGISTRATION"/></h1>
        <form class="reg-inputs" action="/keddit.by/controller?command=sign_up" method="POST" enctype="multipart/form-data">
          <ul class="input-list list-reset">
            <li class="input-item">
              <h2 class="item-text"><fmt:message key="MAIL"/> </h2>
              <input class="input-email reg-input" name="mail" type="email" required placeholder="user@email.com">
            </li>
            <li class="input-item">
              <h2 class="item-text"><fmt:message key="NICKNAME"/> </h2>
              <input class="input-email reg-input" name="nickname" type="text" required placeholder=<fmt:message key="NICKNAME_INTRO"/>>
            </li>
            <li class="input-item pass-item">
              <div class="pass-container">
                <h2 class="item-text"><fmt:message key="PASSWORD"/> </h2>
                <input class="input-pass reg-input" name="password" type="text" minlength="6" placeholder=<fmt:message key="PASSWORD_INTRO"/>>
              </div>
              <p class="pass-text"><fmt:message key="PASSWORD_WARNING"/></p>
            </li>
            <li class="input-item input-item-file">
              <input class="reg-input infile" name="file" type="file" id="file" />
              <label for="file"><fmt:message key="PHOTO"/> </label>
              <img src="${pageContext.request.contextPath}/images/unnamed-avatar.png" class="image-file" id="image1"/>
              <script>
               document.getElementById('file').addEventListener('change', function () {
                  if (this.files && this.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                      document.getElementById('image1').setAttribute('src', e.target.result);
                    };
                    reader.readAsDataURL(this.files[0]);
                  }
                });
              </script>
            </li>
          </ul>
          <c:if test="${error_message_sign_up != null}">
            <p class="message_error"><fmt:message key="${error_message_sign_up}"/></p>
          </c:if>
          <input class="reg-submit btn-reset" type="submit" value=<fmt:message key="REGISTRATION_BUTTON"/>>
        </form>
      </div>
    </div>
  </main>
</body>

</html>