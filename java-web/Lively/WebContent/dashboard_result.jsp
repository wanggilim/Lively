<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색창•Lively</title>
<link rel="stylesheet" href="resources/css/common/styles.css">
<script src="resources/js/common/jquery-3.3.1.min.js"></script>
<script src="resources/js/dashboard/dashboard.js"></script>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="resources/css/common/material.teal-red.min.css">
<link rel="stylesheet" href="resources/css/dashboard/styles.css">

<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="images/android-desktop.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Material Design Lite">
<link rel="apple-touch-icon-precomposed" href="resources/images/common/ios-desktop.png">

<meta name="msapplication-TileImage"
	content="resources/images/common/touch/ms-touch-icon-144x144-precomposed.png">
<meta name="msapplication-TileColor" content="#3372DF">

<link rel="shortcut icon" href="images/favicon.png">

</head>
<body>

<div class="demo-layout-waterfall mdl-layout mdl-js-layout">
  <header class="mdl-layout__header mdl-layout__header--waterfall">
    <!-- Top row, always visible -->
    <div class="mdl-layout__header-row">
      <!-- Title -->
      <span class="mdl-layout-title">Lively</span>
      <div class="mdl-layout-spacer"></div>
      <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
                  mdl-textfield--floating-label mdl-textfield--align-right">
        <label class="mdl-button mdl-js-button mdl-button--icon"
               for="waterfall-exp">
          <i class="material-icons">search</i>
        </label>
        <div class="mdl-textfield__expandable-holder">
          <input class="mdl-textfield__input" type="text" name="sample"
                 id="waterfall-exp">
        </div>
      </div>
    </div>
    <!-- Bottom row, not visible on scroll -->
    <div class="mdl-layout__header-row">
      <div class="mdl-layout-spacer"></div>
      <!-- Navigation -->
      <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="">마이페이지</a>
        <a class="mdl-navigation__link" href="">로그아웃</a>
      </nav>
    </div>
  </header>
  <div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Title</span>
    <nav class="mdl-navigation">
      <a class="mdl-navigation__link" href="">Link</a>
      <a class="mdl-navigation__link" href="">Link</a>
      <a class="mdl-navigation__link" href="">Link</a>
      <a class="mdl-navigation__link" href="">Link</a>
    </nav>
  </div>
  <main class="mdl-layout__content">
	<!-- 콘텐츠 -->
    <div class="page-content">
    	
    	<!-- 검색 단어 -->
		<div class="page-content-word">
			<div class="page-content-word-name">
				<h2 id="word">${word }</h2>
				<h3 id="mean">${means }</h3>
				<c:forEach var="i" items="${stmtVOList }" begin="0" end="${stmtVOList.size() }">
					${i.getStmt() }<br>
				</c:forEach>
			</div>
			
			<br>
			
			<div class="page-content-word-perhaps">
				<c:forEach var="i" items="${perhapsKeywords }" begin="0" end="${perhapsKeywords.size() }">
					${i }<br>
				</c:forEach>
			</div>
		</div>
		
		<!-- SNS 표출 -->
		<div class="page-content-word-examples">
		</div>
		
		
		
		
    </div>
  </main>
</div>
