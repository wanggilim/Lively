/**
 * Lively SignUp
 * 
 * origin source written by huo223gg
 * created and modified by wgl
 * 
 * 1. 회원아이디 제대로 입력되었는가?
 * 2. 회원아이디가 중복되었는가?
 * 3. 비밀번호 입력조건이 유효하는가?
 */

//중복 체크 Ajax
function validateEmail(){
	 alert("cc");
	var userMail = document.getElementById("userMail").value;
	var patternM =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	if( patternM.test(userMail) ) {
		//alert("메일 주소가 올바르게 입력되었습니다.");
		// 중복 체크
		
		$.ajax({
			type:'POST',
			url:'./userMailDuplication.do',	
			data: {"userMail":userMail},
			async: false,
			success: function(data){
				// jquery ajax 처리(중복 체크) 에 대한 결과
				if (data >= 1) {
					alert('사용할 수 없는 이메일입니다.');
					return false;
				} else {
					alert('사용할 수 있는 이메일입니다.');
					return true;
				}
			},
			error: function(xhr, status, error) {
				alert('관리자에게 문의해주세요.');
				return false;
			}
		});

		
	} else {
		alert("메일 주소가 유효하지 않습니다.");
		document.getElementById("userMail").focus();//입력이 잘못된 곳에 커서 위치
		return false; // submit이 가지 않고 머물도록 지정
	}
}


//메일(validateEmail)과 비밀번호에 대한 유효성 확인
function registerCheckFunction() {
	 alert("aa");
	var userPass = document.getElementById("userPass").value;
	var patternP = /^[A-Za-z0-9.+!@#]{8,15}$/;
	
	var idCheck = validateEmail();
	
	if (idCheck == false) {
		console.log('이메일 검증 실패!');
		return false;
	}
	
	if(! patternP.test(userPass) ) {
		alert("비밀번호가 유효하지 않습니다.");
		document.getElementById("userPass").focus();
		return false;
	}

	
}

//나의 정보 수정 비밀번호 검증 
function registerCheckUserpass(){
	   
    alert("bb");
	var userPass = document.getElementById("userPass").value;
	var patternP = /^[A-Za-z0-9.+!@#]{8,15}$/;
	
	if(! patternP.test(userPass) ) {
		alert("비밀번호가 유효하지 않습니다.");
		document.getElementById("userPass").focus();
		return false;
	}

	return true;
}
