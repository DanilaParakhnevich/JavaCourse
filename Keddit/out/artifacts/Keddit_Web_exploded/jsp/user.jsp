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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/user.css">
  <title><fmt:message key="USER_TITLE"/></title>
  <c:set var="path" value="${pageContext.request.contextPath}"/>
</head>
<jsp:useBean id="user_service" class="by.parakhnevich.keddit.service.impl.UserServiceImpl"/>
<jsp:useBean id="user" scope="session" type="by.parakhnevich.keddit.bean.user.User"/>
<jsp:useBean id="user_owner" scope="request" type="by.parakhnevich.keddit.bean.user.User"/>
<body>
  <main class="main">
      <header class="header">
          <c:set var="id" value="${user.id}"/>
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
    <div class="main-wrap">
      <div class="main-content">
        <div class="community-head">
          <div class="community-wrap">
            <c:if test="${user_owner.photo == null}">
              <a href="?command=user_page&id=${user_owner.id}" class="header-right-link link-reset header-right-link-user">
                <img src="${path}/photos/unnamed-avatar.png" class="image-file" id="image1" />
              </a>
            </c:if>
            <c:if test="${user_owner.photo != null}">
              <a href="?command=user_page&id=${user_owner.id}" class="header-right-link link-reset header-right-link-user">
                <img src="${path}/photos/${user_owner.photo.name}" class="image-file" id="image1" />
              </a>
            </c:if>
            <ul class="community-list list-reset">
              <li class="community-item">
                <a><fmt:message key="ROLE"/> ${user_owner.role.toString()}</a>
              </li>
              <li class="community-item">
                <a href="?command=user_page&id=${user_owner.id}" class="community-text community-list-link link-reset"><fmt:message key="NAME"/> ${user_owner.nickname}</a>
              </li>
              <li class="community-item">
                <a href="?command=user_communities&id=${user_owner.id}" class="community-text community-list-link link-reset"><fmt:message key="COMMUNITIES"/> ${user_owner.followingCommunities.size()}</a>
              </li>
              <li class="community-item">
                <div class="like-dislike">
                  <div class="like-dislike-wrap">
                    <div class="like-dislike-item">
                      <svg fill="var(--red) " width="40px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                        <path
                          d="M22.5,5c-2.892,0-5.327,1.804-6.5,2.854C14.827,6.804,12.392,5,9.5,5C5.364,5,2,8.364,2,12.5c0,2.59,2.365,4.947,2.46,5.041 L16,29.081l11.534-11.534C27.635,17.447,30,15.09,30,12.5C30,8.364,26.636,5,22.5,5z" />
                      </svg>
                      <span class="user-text like-dislike-text">: ${user_service.getCountOfLikes(user_owner)}</span>
                    </div>
                    <div class=" like-dislike-item">
                      <svg width="45px"
                        style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
                        viewBox="0 0 6827 6827" width="6.82666in" xml:space="preserve" xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink">
                        <defs>

                        </defs>
                        <g id="Layer_x0020_1">
                          <g id="_271569344">
                            <path class="fil00"
                              d="M3413 853c707,0 1347,287 1810,750 463,463 750,1103 750,1810 0,707 -287,1347 -750,1810 -463,463 -1103,750 -1810,750 -707,0 -1347,-287 -1810,-750 -463,-463 -750,-1103 -750,-1810 0,-707 287,-1347 750,-1810 463,-463 1103,-750 1810,-750zm1697 863c-434,-434 -1034,-703 -1697,-703 -663,0 -1263,269 -1697,703 -434,434 -703,1034 -703,1697 0,663 269,1263 703,1697 434,434 1034,703 1697,703 663,0 1263,-269 1697,-703 434,-434 703,-1034 703,-1697 0,-663 -269,-1263 -703,-1697z" />
                            <path class="fil00"
                              d="M4733 4736c21,39 7,87 -32,108 -39,21 -87,7 -108,-32 -102,-187 -267,-345 -475,-456 -203,-108 -445,-171 -705,-171 -261,0 -503,63 -705,171 -207,111 -373,269 -475,456 -21,39 -69,53 -108,32 -39,-21 -53,-69 -32,-108 116,-214 305,-395 540,-520 225,-121 493,-191 780,-191 287,0 555,70 780,191 235,126 423,306 540,520z" />
                            <path class="fil00"
                              d="M2519 2566c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm173 257c-46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262z" />
                            <path class="fil00"
                              d="M4308 2566c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm173 257c-46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262z" />
                            <path class="fil00"
                              d="M2123 2123c-39,-20 -55,-68 -34,-108 20,-39 68,-55 108,-34l680 353c39,20 55,68 34,108 -20,39 -68,55 -108,34l-680 -353z" />
                            <path class="fil00"
                              d="M4630 1981c39,-20 87,-5 108,34 20,39 5,87 -34,108l-680 353c-39,20 -87,5 -108,-34 -20,-39 -5,-87 34,-108l680 -353z" />
                          </g>
                          <path class="fil10"
                            d="M3915 2442c-20,-39 -5,-87 34,-108l680 -353c39,-20 87,-5 108,34 20,39 5,87 -34,108l-680 353c-39,20 -87,5 -108,-34zm92 284c75,-99 181,-160 300,-160 119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359zm1103 -1009c-434,-434 -1034,-703 -1697,-703 -663,0 -1263,269 -1697,703 -434,434 -703,1034 -703,1697 0,663 269,1263 703,1697 434,434 1034,703 1697,703 663,0 1263,-269 1697,-703 434,-434 703,-1034 703,-1697 0,-663 -269,-1263 -703,-1697zm-2233 618c39,20 55,68 34,108 -20,39 -68,55 -108,34l-680 -353c-39,-20 -55,-68 -34,-108 20,-39 68,-55 108,-34l680 353zm-358 231c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm1675 1650c235,126 423,306 540,520 21,39 7,87 -32,108 -39,21 -87,7 -108,-32 -102,-187 -267,-345 -475,-456 -203,-108 -445,-171 -705,-171 -261,0 -503,63 -705,171 -207,111 -373,269 -475,456 -21,39 -69,53 -108,32 -39,-21 -53,-69 -32,-108 116,-214 305,-395 540,-520 225,-121 493,-191 780,-191 287,0 555,70 780,191z" />
                          <path class="fil20"
                            d="M4308 2726c-66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262 -46,-60 -107,-97 -173,-97z" />
                          <path class="fil20"
                            d="M2774 3085c0,-103 -31,-196 -81,-262 -46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262z" />
                        </g>
                        <rect class="fil3" height="6827" width="6827" />
                      </svg>
                      <span class=" user-text like-dislike-text">: ${user_service.getCountOfDislikes(user_owner)}</span>
                    </div>
                  </div>
                </div>
              </li>
              <a class="community-item">
              <c:if test="${user.role.toString().equals('ADMIN')}">
                    <a href="?command=edit_user_page&id=${user_owner.id}" class="community-btn link-reset"><fmt:message key="EDIT"/></a>
                  <a href="?command=delete_user&id=${user_owner.id}" class="community-btn link-reset"><fmt:message key="DELETE"/> </a>
                  <c:if test="${user_owner.banned}">
                      <a href="?command=block_user&id=${user_owner.id}" class="community-btn link-reset"><fmt:message key="UNBLOCK"/> </a>
                  </c:if>
                  <c:if test="${!user_owner.banned}">
                      <a href="?command=block_user&id=${user_owner.id}" class="community-btn link-reset"><fmt:message key="BLOCK"/> </a>
                  </c:if>
                    <a href="?command=edit_role_page&id=${user_owner.id}" class="community-btn link-reset"><fmt:message key="CHANGE_ROLE"/></a>
              </c:if>
                  <c:if test="${user_owner.equals(user) && !user_owner.role.toString().equals('ADMIN')}">
                      <a href="?command=edit_user_page&id=${user_owner.id}" class="community-btn link-reset"><fmt:message key="EDIT"/></a>
                      <a href="?command=delete_user&id=${user_owner.id}"> <button class="community-btn btn-reset"><fmt:message key="DELETE"/></button> </a>
                  </c:if>
              </a>
            </ul>
          </div>
        </div>
          <ul class="main-post-list list-reset">
              <c:forEach var="publication" items="${publications}">
                  <c:if test="${!publication.onModeration}">
                      <li class="main-post-item">
                          <div class="post-content">
                              <div class="post-head">
                                  <c:if test="${publication.communityOwner == null}">
                                      <c:if test="${publication.user.photo == null}">
                                          <a href="?command=user_page&id=${publication.user.id}" class="header-right-link link-reset header-right-link-user">
                                              <img src="${path}/photos/unnamed-avatar.png" class="image-file" id="image2" />
                                              <p class="header-right-text"><c:out value="${publication.user.nickname}"/></p>
                                          </a>
                                      </c:if>
                                      <c:if test="${publication.user.photo != null}">
                                          <a href="?command=user_page&id=${publication.user.id}" class="header-right-link link-reset header-right-link-user">
                                              <img src="${path}/photos/${publication.user.photo.name}" class="image-file" id="image3" />
                                              <p class="header-right-text"><c:out value="${publication.user.nickname}"/></p>
                                          </a>
                                      </c:if>
                                  </c:if>
                                  <c:if test="${publication.communityOwner != null}">
                                      <c:if test="${publication.communityOwner.photo == null}">
                                          <a href="?command=community_page&id=${publication.communityOwner.id}" class="header-right-link link-reset header-right-link-user">
                                              <img src="${path}/photos/unnamed-avatar.png" class="image-file" id="image2" />
                                              <p class="header-right-text"><c:out value="${publication.communityOwner.name}"/></p>
                                          </a>
                                      </c:if>
                                      <c:if test="${publication.communityOwner.photo != null}">
                                          <a href="?command=community_page&id=${publication.communityOwner.id}" class="header-right-link link-reset header-right-link-user">
                                              <img src="${path}/photos/${publication.communityOwner.photo.name}" class="image-file" id="image3" />
                                              <p class="header-right-text"><c:out value="${publication.communityOwner.name}"/></p>
                                          </a>
                                      </c:if>
                                  </c:if>
                                  <a href="?command=publication_page&id=${publication.id}" class="open-post-link link-reset">Open post</a>
                              </div>
                              <div class="post-img">
                                  <c:if test="${publication.photo != null}">
                                      <img src="${path}/photos/${publication.photo.name}" alt="post-image" class="post-img-item">
                                  </c:if>
                              </div>
                              <p class="post-text"><c:out value="${publication.heading}"/></p>
                              <p class="post-text"><c:out value="${publication.textContent}"/></p>
                              <div class="post-tegs-links">
                                  <p class="tegs-text"><fmt:message key="TAGS"/></p>
                                  <c:forEach var="tag" items="${publication.tags}">
                                      <a href="?command=search_by_tag&tag=${tag}" class="teg-link link-reset"><c:out value="${tag}"/></a>
                                  </c:forEach>
                              </div>
                              <jsp:useBean id="publication_service" class="by.parakhnevich.keddit.service.impl.PublicationServiceImpl"/>
                              <div class="like-dislike">
                                  <div class="like-dislike-wrap">
                                      <div class="like-dislike-item">
                                          <a href="?command=set_like_publication&id=${publication.id}">
                                              <c:if test="${!user_service.hasLikedPublication(publication, user)}">
                                                  <svg fill="#fff " width="40px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                      <path
                                                              d="M22.5,5c-2.892,0-5.327,1.804-6.5,2.854C14.827,6.804,12.392,5,9.5,5C5.364,5,2,8.364,2,12.5c0,2.59,2.365,4.947,2.46,5.041 L16,29.081l11.534-11.534C27.635,17.447,30,15.09,30,12.5C30,8.364,26.636,5,22.5,5z" />
                                                  </svg>
                                              </c:if>
                                              <c:if test="${user_service.hasLikedPublication(publication, user)}">
                                                  <svg fill="var(--red) " width="40px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                                                      <path
                                                              d="M22.5,5c-2.892,0-5.327,1.804-6.5,2.854C14.827,6.804,12.392,5,9.5,5C5.364,5,2,8.364,2,12.5c0,2.59,2.365,4.947,2.46,5.041 L16,29.081l11.534-11.534C27.635,17.447,30,15.09,30,12.5C30,8.364,26.636,5,22.5,5z" />
                                                  </svg>
                                              </c:if>
                                          </a>
                                          <span class="like-dislike-text">: ${publication_service.getCountOfLikes(publication)}</span>
                                      </div>
                                      <div class="like-dislike-item">
                                          <a href="?command=set_dislike_publication&id=${publication.id}">
                                              <c:if test="${!user_service.hasDislikedPublication(publication, user)}">
                                                  <svg width="45px"
                                                       style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
                                                       viewBox="0 0 6827 6827" xml:space="preserve" xmlns="http://www.w3.org/2000/svg"
                                                       xmlns:xlink="http://www.w3.org/1999/xlink">
                      <defs>
                          <style type="text/css">
                              .fil3 {
                                  fill: none
                              }

                              .fil1 {
                                  fill: #fff
                              }

                              .fil2 {
                                  fill: #dcdcdc
                              }

                              .fil0 {
                                  fill: #333;
                                  fill-rule: nonzero
                              }
                          </style>
                      </defs>
                                                      <g id="Layer_x0020_1">
                                                          <g id="_271569344">
                                                              <path class="fil0"
                                                                    d="M3413 853c707,0 1347,287 1810,750 463,463 750,1103 750,1810 0,707 -287,1347 -750,1810 -463,463 -1103,750 -1810,750 -707,0 -1347,-287 -1810,-750 -463,-463 -750,-1103 -750,-1810 0,-707 287,-1347 750,-1810 463,-463 1103,-750 1810,-750zm1697 863c-434,-434 -1034,-703 -1697,-703 -663,0 -1263,269 -1697,703 -434,434 -703,1034 -703,1697 0,663 269,1263 703,1697 434,434 1034,703 1697,703 663,0 1263,-269 1697,-703 434,-434 703,-1034 703,-1697 0,-663 -269,-1263 -703,-1697z" />
                                                              <path class="fil0"
                                                                    d="M4733 4736c21,39 7,87 -32,108 -39,21 -87,7 -108,-32 -102,-187 -267,-345 -475,-456 -203,-108 -445,-171 -705,-171 -261,0 -503,63 -705,171 -207,111 -373,269 -475,456 -21,39 -69,53 -108,32 -39,-21 -53,-69 -32,-108 116,-214 305,-395 540,-520 225,-121 493,-191 780,-191 287,0 555,70 780,191 235,126 423,306 540,520z" />
                                                              <path class="fil0"
                                                                    d="M2519 2566c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm173 257c-46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262z" />
                                                              <path class="fil0"
                                                                    d="M4308 2566c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm173 257c-46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262z" />
                                                              <path class="fil0"
                                                                    d="M2123 2123c-39,-20 -55,-68 -34,-108 20,-39 68,-55 108,-34l680 353c39,20 55,68 34,108 -20,39 -68,55 -108,34l-680 -353z" />
                                                              <path class="fil0"
                                                                    d="M4630 1981c39,-20 87,-5 108,34 20,39 5,87 -34,108l-680 353c-39,20 -87,5 -108,-34 -20,-39 -5,-87 34,-108l680 -353z" />
                                                          </g>
                                                          <path class="fil1"
                                                                d="M3915 2442c-20,-39 -5,-87 34,-108l680 -353c39,-20 87,-5 108,34 20,39 5,87 -34,108l-680 353c-39,20 -87,5 -108,-34zm92 284c75,-99 181,-160 300,-160 119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359zm1103 -1009c-434,-434 -1034,-703 -1697,-703 -663,0 -1263,269 -1697,703 -434,434 -703,1034 -703,1697 0,663 269,1263 703,1697 434,434 1034,703 1697,703 663,0 1263,-269 1697,-703 434,-434 703,-1034 703,-1697 0,-663 -269,-1263 -703,-1697zm-2233 618c39,20 55,68 34,108 -20,39 -68,55 -108,34l-680 -353c-39,-20 -55,-68 -34,-108 20,-39 68,-55 108,-34l680 353zm-358 231c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm1675 1650c235,126 423,306 540,520 21,39 7,87 -32,108 -39,21 -87,7 -108,-32 -102,-187 -267,-345 -475,-456 -203,-108 -445,-171 -705,-171 -261,0 -503,63 -705,171 -207,111 -373,269 -475,456 -21,39 -69,53 -108,32 -39,-21 -53,-69 -32,-108 116,-214 305,-395 540,-520 225,-121 493,-191 780,-191 287,0 555,70 780,191z" />
                                                          <path class="fil2"
                                                                d="M4308 2726c-66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262 -46,-60 -107,-97 -173,-97z" />
                                                          <path class="fil2"
                                                                d="M2774 3085c0,-103 -31,-196 -81,-262 -46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262z" />
                                                      </g>
                                                      <rect class="fil3" height="6827" width="6827" />
                    </svg>
                                              </c:if>
                                              <c:if test="${user_service.hasDislikedPublication(publication, user)}">
                                                  <svg width="45px"
                                                       style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
                                                       viewBox="0 0 6827 6827" width="6.82666in" xml:space="preserve" xmlns="http://www.w3.org/2000/svg"
                                                       xmlns:xlink="http://www.w3.org/1999/xlink">
                        <defs>

                        </defs>
                                                      <g id="Layer_x0020_1">
                                                          <g id="_271569344">
                                                              <path class="fil00"
                                                                       d="M3413 853c707,0 1347,287 1810,750 463,463 750,1103 750,1810 0,707 -287,1347 -750,1810 -463,463 -1103,750 -1810,750 -707,0 -1347,-287 -1810,-750 -463,-463 -750,-1103 -750,-1810 0,-707 287,-1347 750,-1810 463,-463 1103,-750 1810,-750zm1697 863c-434,-434 -1034,-703 -1697,-703 -663,0 -1263,269 -1697,703 -434,434 -703,1034 -703,1697 0,663 269,1263 703,1697 434,434 1034,703 1697,703 663,0 1263,-269 1697,-703 434,-434 703,-1034 703,-1697 0,-663 -269,-1263 -703,-1697z" />
                                                              <path class="fil00"
                                                                    d="M4733 4736c21,39 7,87 -32,108 -39,21 -87,7 -108,-32 -102,-187 -267,-345 -475,-456 -203,-108 -445,-171 -705,-171 -261,0 -503,63 -705,171 -207,111 -373,269 -475,456 -21,39 -69,53 -108,32 -39,-21 -53,-69 -32,-108 116,-214 305,-395 540,-520 225,-121 493,-191 780,-191 287,0 555,70 780,191 235,126 423,306 540,520z" />
                                                              <path class="fil00"
                                                                    d="M2519 2566c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm173 257c-46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262z" />
                                                              <path class="fil00"
                                                                    d="M4308 2566c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm173 257c-46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262z" />
                                                              <path class="fil00"
                                                                    d="M2123 2123c-39,-20 -55,-68 -34,-108 20,-39 68,-55 108,-34l680 353c39,20 55,68 34,108 -20,39 -68,55 -108,34l-680 -353z" />
                                                              <path class="fil00"
                                                                    d="M4630 1981c39,-20 87,-5 108,34 20,39 5,87 -34,108l-680 353c-39,20 -87,5 -108,-34 -20,-39 -5,-87 34,-108l680 -353z" />
                                                          </g>
                                                          <path class="fil10"
                                                                d="M3915 2442c-20,-39 -5,-87 34,-108l680 -353c39,-20 87,-5 108,34 20,39 5,87 -34,108l-680 353c-39,20 -87,5 -108,-34zm92 284c75,-99 181,-160 300,-160 119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359zm1103 -1009c-434,-434 -1034,-703 -1697,-703 -663,0 -1263,269 -1697,703 -434,434 -703,1034 -703,1697 0,663 269,1263 703,1697 434,434 1034,703 1697,703 663,0 1263,-269 1697,-703 434,-434 703,-1034 703,-1697 0,-663 -269,-1263 -703,-1697zm-2233 618c39,20 55,68 34,108 -20,39 -68,55 -108,34l-680 -353c-39,-20 -55,-68 -34,-108 20,-39 68,-55 108,-34l680 353zm-358 231c119,0 225,61 300,160 71,93 115,220 115,359 0,139 -44,266 -115,359 -75,99 -181,160 -300,160 -119,0 -225,-61 -300,-160 -71,-93 -115,-220 -115,-359 0,-139 44,-266 115,-359 75,-99 181,-160 300,-160zm1675 1650c235,126 423,306 540,520 21,39 7,87 -32,108 -39,21 -87,7 -108,-32 -102,-187 -267,-345 -475,-456 -203,-108 -445,-171 -705,-171 -261,0 -503,63 -705,171 -207,111 -373,269 -475,456 -21,39 -69,53 -108,32 -39,-21 -53,-69 -32,-108 116,-214 305,-395 540,-520 225,-121 493,-191 780,-191 287,0 555,70 780,191z" />
                                                          <path class="fil20"
                                                                d="M4308 2726c-66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262 0,-103 -31,-196 -81,-262 -46,-60 -107,-97 -173,-97z" />
                                                          <path class="fil20"
                                                                d="M2774 3085c0,-103 -31,-196 -81,-262 -46,-60 -107,-97 -173,-97 -66,0 -128,37 -173,97 -50,66 -81,159 -81,262 0,103 31,196 81,262 46,60 107,97 173,97 66,0 128,-37 173,-97 50,-66 81,-159 81,-262z" />
                                                      </g>
                                                      <rect class="fil3" height="6827" width="6827" />
                      </svg>
                                              </c:if>
                                          </a>
                                          <span class="like-dislike-text">: ${publication_service.getCountOfDislikes(publication)}</span>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </li>
                  </c:if>
              </c:forEach>
          </ul>
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
<script src="${path}/js/main.js"></script>
<% request.getSession().setAttribute("prev_link", "/keddit.by/controller?command=user_page&id=" + request.getParameter("id")); %>

