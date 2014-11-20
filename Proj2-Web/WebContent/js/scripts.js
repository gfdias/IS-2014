


$('document').ready(function () {
	var requestTab;
	

	    $('#usNews').click(function () {
	        $('#dinamicDiv').load('Us?');
	    });
	    
	    $('#deleteIdea').click(function () {
	        $('#dinamicDiv').load('DeleteIdea?');
	    });
	    
	    $('#buyShares').click(function () {
	        $('#dinamicDiv').load('BuyShares?');
	    });
	    
	    $('#setShares').click(function () {
	        $('#dinamicDiv').load('SetShares?');
	    });
	    
	    $('#userIdeas').click(function () {
	        $('#dinamicDiv').load('Portfolio?');
	    });
	    
	    $('#search').click(function () {
	    	var toSearch=$('#toSearch').val();
	       $('#dinamicDiv').load('Search?toSearch=' + toSearch);
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

