$('document').ready(function() {
	  $('#deleteButton').click(function(){
		  
		  var checked = $("input:checked");
		 
		  var ideas = checked.map(function(){

	            return this.value;
	            
	        }).get();
		  
		  if(ideas.length==0){
			  alert("Select at least one idea");
		  }
		
		  else{
		  $.post('DeleteIdea?ideas=' + ideas);
		  }
	  });
});

