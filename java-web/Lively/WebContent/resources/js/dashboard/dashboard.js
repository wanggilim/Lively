/**
 * dashboard.js
 */

$(document).ready(function() {
	//1. 로고 클릭 (dashboard 로 이동)
	$(".logo").click(function() {
		location.href = "dashboard.jsp";
	});

	$(".btn").click(function() {
		$(".input").val("");
	});
	
	// 전송
	$("#doSearch").click(function() {
		//var $path = $(location).attr('host'); // 나중에는 바꿀 것
		var $path = $(location).attr('pathname');
		var $pathBranch = $path.substr(0, $path.length-5)
		if ($pathBranch != '.jsp') {
			$pathBranch = $path.substr(0, $path.length-4) 
		}
		var $newPath = $pathBranch + '.do?';
		var $param = 'word=' + $('.search-main').val();
		
		$(location).attr('href', $newPath+$param);
	});
	
});