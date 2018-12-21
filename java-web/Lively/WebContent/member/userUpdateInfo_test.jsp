<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- //jquery library -->
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 정보 수정</title>

<link rel="stylesheet" href="../resources/css/member/main.css">
</head>
<body>

	<div class="page-wrapper bg-black p-t-100 p-b-100 font-robo">
		<div class="wrapper wrapper--w680">
			<div class="card card-1">
				<div class="sign-logo"><h1 class="card-heading" align="center">Lively</h1></div>
				<div class="card-body">
					<h3 class="title">회원 정보 수정</h3>
					<form name="join" action="userUpdate.do" method="POST" onsubmit="return return userUpdateAjax();">
						<div class="input-group" style="display:none;">
							회원 넘버 <input class="input--style-1" type="text" name ="userNo" id="userNo" readonly value="${member.userNo }">
						</div>
						<div class="input-group">
							회원 메일 <input class="input--style-1" type="email" name ="userMail" id="userMail" readonly value="${member.userMail }">
						</div>
						<div class="input-group">
							회원 등급<input class="input--style-1" type="number" name ="userLevel" id="userLevel" readonly value="${member.userLevel }"> 
						</div>
						<div class="input-group">
							기존 비밀번호 <input class="input--style-1" type="text" name ="userPass" id="userPass" readonly value="${member.userPass }">
						</div>
						<div class="input-group">
							새 비밀번호 <input class="input--style-1" type="password" name ="setUserPass" id="setUserPass" value="" placeholder="">
						</div>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									생년월일<%-- <div id="birthday_old" style="display:none;">${member.birthday }</div><br> --%>
									<input class="input--style-1 js-datepicker" type="text" name="setUserBirthday" value="${member.birthday }" readonly>
										<i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
								</div>
							</div>
							<div class="col-2">
								<div class="input-group">
									<div class="rs-select2 js-select-simple select--no-search">
										성별<div id="gender_old" style="display:none;">${member.gender }</div><br>									
											<select name="setUserGender">
												<option disabled="disabled">성별</option>
												<option value="M">남성</option>
												<option value="F">여성</option>
											</select>
										<div class="select-dropdown"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="row row-space">
							<div class="col-1">
								<div class="input-group">
									<div class="rs-select2 js-select-simple select--no-search">
										소셜로그인 동의<br>
										<select name="connectSNS">
											<option value="yes">연결</option>
											<option value="no">연결해제</option>
										</select>
										<div class="select-dropdown"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="p-t-20">
							<button class="btn btn--radius btn--black" type="reset">다시쓰기</button> &nbsp; &nbsp;  &nbsp;
							<button class="btn btn--radius btn--black" type="submit">등록</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Jquery JS-->
	<script src="../resources/css/member/vendor/jquery/jquery.min.js"></script>
	<!-- Vendor JS-->
	<script src="../resources/css/member/vendor/select2/select2.min.js"></script>
	<script src="../resources/css/member/vendor/datepicker/moment.min.js"></script>
	<script
		src="../resources/css/member/vendor/datepicker/daterangepicker.js"></script>

	<!-- Main JS-->
	<script src="../resources/js/member/signUp.js"></script>
	<script src="../resources/js/common/global.js"></script>	
</body>
</html>