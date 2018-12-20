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
		var $path = $(location).attr('pathname');
		if ($path.endsWith('.jsp')) {
			$path = $path.substr(0, $path.length-3);
		} else {
			$path = $path.substr(0, $path.length-2);
		}
		var $newPath = $path + 'do?';
		var $param = 'word=' + $('.search-main').val();
		
		$(location).attr('href', $newPath+$param);
	});
	
	$('.search-main').keyup(function(e){
		if ((e.keyCode == '13' || e.which == '13')
				&& $('.search-main').val() != '' && $('.search-main').val() != undefined
				&& $('.search-main').val() != null) {
			var $path = $(location).attr('pathname');
			if ($path.endsWith('.jsp')) {
				$path = $path.substr(0, $path.length-3);
			} else {
				$path = $path.substr(0, $path.length-2);
			}
			var $newPath = $path + 'do?';
			var $param = 'word=' + $('.search-main').val();
			
			$(location).attr('href', $newPath+$param);
		}
	});
	
});


