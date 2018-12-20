<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description"
	content="A front-end template that helps you build fast, modern mobile web apps.">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">

<title>main•Lively</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href='resources/css/dashboard/dashboard.css' rel='stylesheet'
	type='text/css'>
<link rel="stylesheet" href="resources/css/common/material.min.css">
<link rel="stylesheet" href="resources/css/common/styles.css"
	type="text/css">
<script src="resources/js/common/jquery-3.3.1.min.js"></script>
<script src="resources/js/dashboard/dashboard.js"></script>


</head>

<body class="index">

	<!-- 헤더 -->
	<header
		class="mdl-layout__header mdl-layout__header--scroll mdl-color--grey-900">

		<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			<!-- 			<h3>Lively</h3> -->

			<div class="mdl-layout-spacer"></div>

			<div class="mdl-layout-spacer"></div>

			<c:if test="${userMail==null }">
				<button class="mdl-button mdl-js-button mdl-button--raised"
						onclick="location.href='member/signIn.do'">로그인</button>
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
				<button class="mdl-button mdl-js-button mdl-button--raised" onclick="location.href='member/signOut.do'">
				로그아웃
				</button>
			</c:if>
		</div>
	</header>
	<!-- 헤더 끝 -->

	<section id="container">

		<section class="sample" id="headline">


			<div class="logo_big">
				<h1>Lively</h1>
			</div>

			<div align="center">
				<section class="text">
					<!-- dashboard 검색창 -->
					<span class="search-window"> <input type="text"
						placeholder="단어 검색" class="search-main"><br>
					</span> <img alt="search" src="resources/images/common/search_2x_w.png"
						class="search-icon" id="doSearch"><br>
				</section>
			</div>


		</section>
	</section>


</body>
</html>