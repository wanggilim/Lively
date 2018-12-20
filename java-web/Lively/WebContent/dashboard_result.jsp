<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" href="resources/css/dashboard/dashboard_result.css">
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

		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--grey-900">

			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<h3>Lively</h3>

				<div class="mdl-layout-spacer"></div>
				
				<!-- 검색창1 -->
				<span class="search-window"> <input type="text"
					placeholder="단어 검색" class="search-main"><br>
				</span> <img alt="search" src="resources/images/common/search_2x_w.png"
					class="search-icon" id="doSearch"><br>
				<!-- 검색창1 끝 -->

				<div class="mdl-layout-spacer"></div>
	
				<!-- 마이 페이지 관련 버튼 -->
				<img alt="account" src="resources/images/common/account_2x_w.png"
					class="#" id="#">
			</div>
		</header>


		<main class="mdl-layout__content"> <!-- 콘텐츠 -->
		<div class="mdl-layout__tab-panel is-active" id="overview">

			<!--1.검색 단어+뜻 매칭 // 시작 -->
			<section
				class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
				<div class="mdl-card mdl-cell mdl-cell--12-col">
					<div class="mdl-card__supporting-text">
						<h4 id="word">${word }</h4>
						<h6 id="mean">${means }</h6>

						<!-- 단어 뜻 들어 오는 것 보고 없앨것(의미 없음! 지울 예정)	시작 -->
						Meaning
						<!-- 단어 뜻 들어 오는 것 보고 없앨것(의미 없음! 지울 예정)	끝 -->
						
						<!-- 검색 유사 단어 -->
						<br>
						<c:forEach var="i" items="${perhapsKeywords }">
							<a id="perhaps" href="./dashboard.do?word=${i }">${i }</a>&nbsp;&nbsp; 
						</c:forEach>
						
					</div>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon"
					id="btn3" data-upgraded=",MaterialButton,MaterialRipple">
					<i class="material-icons">more_vert</i> <span
						class="mdl-button__ripple-container"><span
						class="mdl-ripple"></span></span>
				</button>
				<div class="mdl-menu__container is-upgraded">
					<div class="mdl-menu__outline mdl-menu--bottom-right"></div>
					<ul class="mdl-menu mdl-js-menu mdl-menu--bottom-right" for="btn3"
						data-upgraded=",MaterialMenu">
						<li class="mdl-menu__item" tabindex="-1">신고</li>
						<li class="mdl-menu__item" disabled="" tabindex="-1">수정</li>
					</ul>
				</div>
			</section>

			<!--1.검색 단어+뜻 매칭 // 끝 -->

			<!--2.검색 결과 SNS 예문 // 시작 -->
			<c:forEach var="i" items="${stmtVOList }" begin="0"
				end="${stmtVOList.size() }">
				<section
					class="section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp">
					<div class="mdl-card mdl-cell mdl-cell--12-col">
						<div
							class="mdl-card__supporting-text mdl-grid mdl-grid--no-spacing">
							<h4 class="mdl-cell mdl-cell--12-col">${i.getCellbNo() }
								셀럽이름</h4>
							<div
								class="section__circle-container mdl-cell mdl-cell--2-col mdl-cell--1-col-phone">
								<div
									class="section__circle-container__circle mdl-color--primary"></div>
							</div>
							<div
								class="section__text mdl-cell mdl-cell--10-col-desktop mdl-cell--6-col-tablet mdl-cell--3-col-phone">
								<h5>#계정아이디 |위치:${i.getLocation() }</h5>
								${i.getStmt() }
							</div>
						</div>
					</div>
					<!-- 포스트 내, 옵션 버튼 (사용 x) -->
					<button
						class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon"
						id="btn2" data-upgraded=",MaterialButton,MaterialRipple">
						<i class="material-icons">more_vert</i> <span
							class="mdl-button__ripple-container"><span
							class="mdl-ripple is-animating"
							style="width: 92.4876px; height: 92.4876px; transform: translate(-50%, -50%) translate(14px, 13px);"></span></span>
					</button>
					<div class="mdl-menu__container is-upgraded"
						style="right: 7.98828px; top: 41px; width: 123.984px; height: 159.941px;">
						<div class="mdl-menu__outline mdl-menu--bottom-right"
							style="width: 123.984px; height: 159.941px;"></div>
						<ul class="mdl-menu mdl-js-menu mdl-menu--bottom-right" for="btn2"
							data-upgraded=",MaterialMenu"
							style="clip: rect(0px, 123.984px, 0px, 123.984px);">
							<li class="mdl-menu__item" tabindex="-1" style="">예문 저장</li>
							<li class="mdl-menu__item" disabled="" tabindex="-1" style="">신고</li>
						</ul>
					</div>

				</section>
			</c:forEach>
			<!--2.검색 결과 SNS 예문 // 끝 -->


		</div>
		<footer class="mdl-mega-footer">
			<div class="mdl-mega-footer--middle-section">
				<div class="mdl-mega-footer--drop-down-section">
					<input class="mdl-mega-footer--heading-checkbox" type="checkbox"
						checked>
					<h1 class="mdl-mega-footer--heading">Features</h1>
					<ul class="mdl-mega-footer--link-list">
						<li><a href="#">About</a></li>
						<li><a href="#">Terms</a></li>
						<li><a href="#">Partners</a></li>
						<li><a href="#">Updates</a></li>
					</ul>
				</div>
				<div class="mdl-mega-footer--drop-down-section">
					<input class="mdl-mega-footer--heading-checkbox" type="checkbox"
						checked>
					<h1 class="mdl-mega-footer--heading">Details</h1>
					<ul class="mdl-mega-footer--link-list">
						<li><a href="#">Spec</a></li>
						<li><a href="#">Tools</a></li>
						<li><a href="#">Resources</a></li>
					</ul>
				</div>
				<div class="mdl-mega-footer--drop-down-section">
					<input class="mdl-mega-footer--heading-checkbox" type="checkbox"
						checked>
					<h1 class="mdl-mega-footer--heading">Technology</h1>
					<ul class="mdl-mega-footer--link-list">
						<li><a href="#">How it works</a></li>
						<li><a href="#">Patterns</a></li>
						<li><a href="#">Usage</a></li>
						<li><a href="#">Products</a></li>
						<li><a href="#">Contracts</a></li>
					</ul>
				</div>
				<div class="mdl-mega-footer--drop-down-section">
					<input class="mdl-mega-footer--heading-checkbox" type="checkbox"
						checked>
					<h1 class="mdl-mega-footer--heading">FAQ</h1>
					<ul class="mdl-mega-footer--link-list">
						<li><a href="#">Questions</a></li>
						<li><a href="#">Answers</a></li>
						<li><a href="#">Contact us</a></li>
					</ul>
				</div>
			</div>
			<div class="mdl-mega-footer--bottom-section">
				<div class="mdl-logo">More Information</div>
				<ul class="mdl-mega-footer--link-list">
					<li><a href="https://developers.google.com/web/starter-kit/">Web
							Starter Kit</a></li>
					<li><a href="#">Help</a></li>
					<li><a href="#">Privacy and Terms</a></li>
				</ul>
			</div>
		</footer> </main>
	</div>
</body>
</html>
