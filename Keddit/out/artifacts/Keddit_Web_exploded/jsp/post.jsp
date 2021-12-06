<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../styles/post.css">
  <c:set var="path" value="${pageContext.request.contextPath}"/>
  <title>Post</title>
</head>
<jsp:useBean id="user" scope="request" type="by.parakhnevich.keddit.bean.user.User"/>
<jsp:useBean id="publication" scope="request" type="by.parakhnevich.keddit.bean.publication.Publication"/>
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
              <a href="?command=my_communities&id=${id}" class="header-left-link link-reset">My communities</a>
            </li>
            <li class="header-left-list-item">
              <a href="?command=about" class="header-left-link link-reset">About</a>
            </li>
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
                  <p class="header-right-text">${user.nickname}</p>
                </a>
              </c:if>
              <c:if test="${user.photo != null}">
                <a href="?command=user_page&id=${user.id}" class="header-right-link link-reset header-right-link-user">
                  <img src="${path}/photos/${user.photo.name}" class="image-file" id="image3" />
                  <p class="header-right-text">${user.nickname}</p>
                </a>
              </c:if>
            </c:if>
            <c:if test="${user == null}">
              <a href="?command=login_page" class="header-right-link link-reset header-right-link-user">
                <img src="${path}/photos/unnamed-avatar.png" class="image-file" id="image3" />
                <p class="header-right-text">Quest</p>
              </a>
            </c:if>
          </a>
        </div>
      </div>
    </header>
    <div class="main-wrap">
      <div class="main-content">
        <div class="main-head-func">
          <a href="?command=edit_publication&id=${publication.id}" class="main-new-post-link link-reset">Edit post</a>
          <a href="?command=delete_publication&id=${publication.id}" class="main-new-post-link link-reset">Delete post</a>
        </div>
        <ul class="main-post-list list-reset">
          <li class="main-post-item">
            <div class="post-content">
              <div class="post-head">
                <a href="#" class="header-right-link link-reset header-right-link-user">
                  <img src="/images/unnamed-avatar.png" class="image-file" id="image1" />
                  <p class="header-right-text">nickname</p>
                </a>
                <a href="#" class="open-post-link link-reset">Back</a>
              </div>
              <h2 class="post-text">Title</h2>
              <p class="post-text">fklds;flsk;flks;k lkd;flsk lkd;flskd ldkf;slkdf ldkf;slkfs iopo kld;fsl dlkfs;fks
                dlkfs;lfdks</p>
              <div class="post-img">
                <img src="/images/login-and-sign-in-bg.jpg" alt="post-image" class="post-img-item">
              </div>
              <div class="post-tegs-links">
                <p class="tegs-text">tegs:</p>
                <a href="#" class="teg-link link-reset">hobbys</a>
              </div>
              <div class="like-dislike">
                <div class="like-dislike-wrap">
                  <div class="like-dislike-item">
                    <svg fill="#fff " width="40px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                      <path
                        d="M22.5,5c-2.892,0-5.327,1.804-6.5,2.854C14.827,6.804,12.392,5,9.5,5C5.364,5,2,8.364,2,12.5c0,2.59,2.365,4.947,2.46,5.041 L16,29.081l11.534-11.534C27.635,17.447,30,15.09,30,12.5C30,8.364,26.636,5,22.5,5z" />
                    </svg>
                    <span class="like-dislike-text">: 0</span>
                  </div>
                  <div class="like-dislike-item">
                    <svg width="45px"
                      style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
                      viewBox="0 0 6827 6827" width="6.82666in" xml:space="preserve" xmlns="http://www.w3.org/2000/svg"
                      xmlns:xlink="http://www.w3.org/1999/xlink">
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
                    <span class="like-dislike-text">: 0</span>
                  </div>
                </div>

              </div>
            </div>
          </li>
        </ul>
        <div class="comment-wrap">
          <div class="comment-content">
            <form class="comment-form" action="">
              <a href="#" class="header-right-link link-reset header-right-link-user">
                <img src="../images/unnamed-avatar.png" class="image-file" id="image1" />
                <p class="header-right-text">nickname</p>
              </a>
              <p class="comment-text">Your comment:</p>
              <div class="comment-file">
                <input class="reg-input inputfile" name="file" type="file" id="file" />
                <label for="file">Edit image: </label>
                <img src="/images/upload-image.png" class="image-post" id="image1-post" />
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
              </div>
              <div class="your-comment-wrap">
                <textarea class="comment-input btn-reset" cols="61" rows="2"></textarea>
                <button class="comment-button btn-reset" type="submit">Send</button>
              </div>
            </form>
            <ul class="comment-list list-reset">
              <li class="comment-item">
                <a href="#" class="header-right-link link-reset header-right-link-user">
                  <img src="/images/unnamed-avatar.png" class="image-file" id="image1" />
                  <p class="header-right-text">nickname</p>
                </a>
                <p class="comment-text">comment text comment text comment text comment text comment text comment text comment text comment text comment text comment textcomment text comment text comment text comment text comment text</p>
                <img src="/images/upload-image.png" class="comment-img" id="image1-post" />
                <div class="like-dislike">
                  <div class="like-dislike-wrap">
                    <div class="like-dislike-item">
                      <svg fill="#fff " width="40px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                        <path
                          d="M22.5,5c-2.892,0-5.327,1.804-6.5,2.854C14.827,6.804,12.392,5,9.5,5C5.364,5,2,8.364,2,12.5c0,2.59,2.365,4.947,2.46,5.041 L16,29.081l11.534-11.534C27.635,17.447,30,15.09,30,12.5C30,8.364,26.636,5,22.5,5z" />
                      </svg>
                      <span class="like-dislike-text">: 0</span>
                    </div>
                    <div class="like-dislike-item">
                      <svg width="45px"
                        style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
                        viewBox="0 0 6827 6827" width="6.82666in" xml:space="preserve" xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink">
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
                      <span class="like-dislike-text">: 0</span>
                    </div>
                  </div>
                
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <footer class="footer">
      <div class="footer-wrapper wrapper">
        <div class="left-footer">
          <a href="#" class="header-left-link link-reset logo-img">
            <img src="../images/logo.jpg">
          </a>
          <p class="footer-subtitle">© я хз как твоё имя на англе - 2021</p>
        </div>
        <div class="right-footer">
          <button class="footer-logout btn-reset"><a class="header-btn-link">Logout</a></button>
        </div>
      </div>
    </footer>
  </main>
</body>
<script src="../js/main.js"></script>
