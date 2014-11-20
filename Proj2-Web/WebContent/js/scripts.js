


$('document').ready(function () {
	var requestTab;
	
	if($('#userStats').text()!="Admin"){
		$('#admin').hide();
	}
	
	$('#usNews').click(function () {
        $('#dinamicDiv').load('Us?');
    });
	
	$('#admin').click(function () {
		$('#dinamicDiv').load('Admin?');
	});
	

	$('#newTopic').click(function () {
		if(requestTab){
			requestTab.abort();
		}
		requestTab = $.ajax({
			url: 'NewTopic.jsp',
			success: function(data) {
				$("#dinamicDiv").html(data);
			}
		});
	});
});

