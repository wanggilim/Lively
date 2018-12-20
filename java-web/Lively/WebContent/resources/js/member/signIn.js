/**
 * Lively SignIn
 * 
 * origin source written by jinju
 * 
 * 1. logo를 누를 때, 인덱스로 간다
 * 2. signup 버튼을 누를 때, 회원가입 페이지로 간다
 */


$(document).ready(function() {

	$(".card-heading").click(function() {
		location.href = "../";
	});

	$(".signup").click(function() {
		location.href = "signUp.html";
	});

});