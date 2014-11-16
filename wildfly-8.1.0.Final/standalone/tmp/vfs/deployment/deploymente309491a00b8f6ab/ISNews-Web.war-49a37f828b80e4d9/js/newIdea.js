$('document').ready(function() {
	  $('#ideaSubmit').click(function(){
		  
		  var checked = $("input:checked");
		  var title = $('#ideaTitle').val();
		  var text = $('#ideaText').val();
		  var price = $('#ideaPrice').val();
		  var coins = $('#coinsLabel').text();
		  
		  var topics = checked.map(function(){

	            return this.value;
	            
	        }).get();
		  
		  if(topics.length==0){
			  alert("Selecione at least one topic");
		  }
		  
		  else if(title.length==0){
			  alert("Insert a title");
		  }
		  
		  else if(text.length==0){
			  alert("Insert a text for the idea");
		  }
		  
		  else if(price.length==0){
			  alert("Define a price for the shares");
		  }
		  
		  else if(coins-price<0){
			  alert("You don't have enough money");
		  }
		  
		  
		  else{
		  $.post('NewIdea?topics=' + topics + "&title="+title + "&text=" + text + "&price=" + price + "&coins=" + coins);
		  }
	  });
});

