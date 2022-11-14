/**
*
*/

$(function(){
	fn_checkAll();
	fn_checkOne();
    fn_toggleCheck();
    fn_submit();
});
	
// 모두 동의 (모두 동의의 체크 상태 = 개별 선택들의 체크 상태)
function fn_checkAll() {
		
	$('#check_all').click(function() {
		// 체크 상태 변경 .prop('checked', true/false)의 상태에 따라
		$('.check_one').prop('checked', $(this).prop('checked'));
		// 체크 이미지 변경
		if($(this).is(':checked')) { // 모두동의가 체크되었다면? (is를 이용하여 상태체크)
			$('.lbl_one').addClass('lbl_checked');
		} else {
			$('.lbl_one').removeClass('lbl_checked');
		}
	});
		
}
	
// 개별 선택 (항상 개별 선택 4개를 모두 순회하면서 어떤 상태인지 체크해야 함)
function fn_checkOne() {
	$('.check_one').click(function(){
		// 체크 상태 변경
		let checkCount = 0;
		for(let i = 0; i < $('.check_one').length; i++) {
			checkCount += $($('.check_one')[i]).prop('checked'); // 인덱스가 붙어있으면 일반변수이다. 그래서 한번 더 jquery로 감싸줌
			// $($(this)[i]).prop('checked') = true(1) false(0) 더해주는 방식 (체크박스 개수)
		}
			
		// 체크박스개수 vs 체크된박스개수
		$('#check_all').prop('checked', $('.check_one').length == checkCount);
			
		// 체크 이미지 변경
		if($('#check_all').is(':checked')) {
			$('.lbl_all').addClass('lbl_checked');
		} else {
			$('.lbl_all').removeClass('lbl_checked');
		}
	});
}
	
	// 체크할때마다 lbl_checked 클래스를 줬다 뻈었다
	function fn_toggleCheck(){
		$('.lbl_all, .lbl_one').click(function(){
		$(this).toggleClass('lbl_checked');
	});
}
	
// submit 할 때 할일 (check required)
function fn_submit(){
		$('#frm_agree').submit(function(event){
		if($('#service').is(':checked') == false || $('#privacy').is(':checked') == false) {
			alert('필수약관에 동의하시길 바랍니다.')
			event.preventDefault();
			return;
		}
		
	});
}