<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description"
	content="A front-end template that helps you build fast, modern mobile web apps.">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">

<title>result•Lively</title>
<!-- js css 리소스  경로 -->
<link rel="stylesheet" href="resources/css/common/styles.css">
<script src="resources/js/common/jquery-3.3.1.min.js"></script>
<script src="resources/js/dashboard/dashboard.js"></script>


<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192"
	href="resources/images/common/android-desktop.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Material Design Lite">
<link rel="apple-touch-icon-precomposed"
	href="resources/images/common/ios-desktop.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="images/touch/ms-touch-icon-144x144-precomposed.png">
<meta name="msapplication-TileColor" content="#3372DF">

<link rel="shortcut icon" href="resources/images/common/favicon.png">

<!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
<!--
    <link rel="canonical" href="http://www.example.com/">
    -->

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="resources/css/common/material.min.css">
<link rel="stylesheet"
	href="resources/css/dashboard/dashboard_result.css">

<link href='resources/css/dashboard/album.css' rel='stylesheet'
	type='text/css'>
<style>
#view-source {
	position: fixed;
	display: block;
	right: 0;
	bottom: 0;
	margin-right: 40px;
	margin-bottom: 40px;
	z-index: 900;
}
</style>
</head>
<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">

		<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--grey-900">

			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<div class="logo-header">
					<h3>Lively</h3>
				</div>

				<div class="mdl-layout-spacer"></div>

				<div class="mdl-layout-spacer"></div>

				<c:if test="${userMail==null }">
					<button class="mdl-button mdl-js-button bg-white"
						onclick="location.href='member/signIn.do'">로그인</button>
					<%-- 					<button class="mdl-button mdl-js-button mdl-button--raised">
						<a href="${path}/member/signIn.do" style="color: white">로그인</a>
					</button> --%>
				</c:if>
				<c:if test="${userMail!=null }">


					<!-- 관리자 관리 관련 버튼 -->
					<c:if test="${member.getUserLevel()<=3 }">
						<img alt="manage" src="resources/images/common/group_2x.png"
							class="manage-icon" id="manage"> &nbsp;&nbsp;&nbsp;	
					</c:if>

					<!-- 마이 페이지 관련 버튼 -->
					<img alt="myPage" src="resources/images/common/account_2x_w.png"
						class="myPage-icon" id="myPage"> &nbsp;&nbsp;&nbsp;
						<!-- 로그아웃 관련 버튼 -->
					<button
						class="mdl-button mdl-js-button mdl-button--raised bg-white"
						onclick="location.href='member/signOut.do'">로그아웃</button>
				</c:if>


			</div>
		</header>


		<main class="mdl-layout__content"> <!-- 콘텐츠 -->
		<div class="mdl-layout__tab-panel is-active" id="overview">

			<!--1.검색 단어+뜻 매칭 // 시작 -->
			<section
				class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
				<div class="mdl-card mdl-cell mdl-cell--12-col">
					<div class="col-12 searchBar">
						<!-- 검색창1 -->
						<span class="search-window"> <input type="text"
							placeholder="단어 검색" class="search-main"><br>
						</span> <img alt="search" src="resources/images/common/search_2x.png"
							class="search-icon" id="doSearch"><br>
						<!-- 검색창1 끝 -->
					</div>
					<div class="mdl-card__supporting-text">
						<c:forEach var="i" items="${perhapsKeywords }">
							<a id="perhaps" href="./dashboard.do?word=${i }">${i }</a>&nbsp;&nbsp; 
						</c:forEach>
						<br />
						<br />
						<h2 id="word" align="center">${word }</h2>
						<%-- <h6 id="mean">${means }</h6> --%>

						<!-- 단어 뜻 들어 오는 것 보고 없앨것(의미 없음! 지울 예정)	시작 -->
						<!-- 단어 뜻 들어 오는 것 보고 없앨것(의미 없음! 지울 예정)	끝 -->

						<!-- 검색 유사 단어 -->

					</div>
				</div>
			</section>

			<!--1.검색 단어+뜻 매칭 // 끝 -->

			<!--2.검색 결과 SNS 예문 // 시작 -->
			<section
				class="section--center mdl-grid mdl-grid--no-spacing">
				<div class="container2 album py-5 bg-light">
					<div class="row">
						<c:forEach var="i" items="${stmtVOList }">
							<div class="col-md-4">
								<div class="card mb-5 box-shadow">
									<img class="card-img-top" src="${i.profileURL }"
										alt="Card image cap" width="150" style="margin-left:auto;margin-right:auto;">
									<div class="card-body">
										<p class="card-text">
										<h5>${i.screenName }</h5>
										<h5>위치:${i.location }</h5>
										${i.stmt }
										<br/><br/><br/>
										<div class="d-flex justify-content-between align-items-center">
											<div class="btn2-group">
												<button type="button"
													class="btn2 btn2-sm btn2-outline-secondary"
													onclick="location.href='${i.stmtURL }'">View</button>
											</div>
											<small class="text-muted">
												<jsp:useBean id="myDate" class="java.util.Date"/>
												<c:set target="${myDate}" property="time" value="${i.stmtTs }"/>
												<fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd HH:mm:ss"/> 
											</small>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
			<!--2.검색 결과 SNS 예문 // 끝 -->
		</div>
		<footer class="mdl-mega-footer">
			
		</footer> </main>
	</div>
</body>
</html>
