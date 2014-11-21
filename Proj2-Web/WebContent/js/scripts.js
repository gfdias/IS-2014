$('document').ready(
		function() {
			
			$.fn.toggleAttr = function(a, b) {
			    var c = (b === undefined);
			    return this.each(function() {
			        if((c && !$(this).is("["+a+"]")) || (!c && b)) $(this).attr(a,a);
			        else $(this).removeAttr(a);
			    });
			};
			
			var requestTab;

			if ($('#userStats').text() != "Admin") {
				$('#admin').hide();
			}

			$('#usNews').click(function() {
				$('#dinamicDiv').load('Us?');
			});

			$('#admin').click(function() {
				$('#dinamicDiv').load('Admin?');
			});
			
			

			$('#adminTable > tbody > tr').each(
					function() {
						var user = $(this);
						console.log(user.attr("id"));
						var deleteBtn = user.find(".btn-danger");
						var editBtn1 = user.find(".btn-info");
						var editBtn2 = user.find(".glyphicon-edit");
						var sendBtn = user.find(".btn-success");

						
						$(sendBtn).click(function(){
						
								console.log("sucess");
								
								var row_data = [];    

							    $('td', user).each(function(){

							        row_data.push($(this).text());   

							    });    
								
								var username = row_data[0];
								var email = row_data[1];
								var password = row_data[2];
								
								console.log(username);
								console.log(email);
								console.log(password);
								
								$.post('Admin?userId='+
										user.attr("id")+
										"&action=edit"+
										"&username="+username+
										"&email="+email+
										"&password=" + password
								);
						});

						deleteBtn
								.click(function() {
									var userId = user.attr("id");
									$("#" + userId + "").remove();
									$.post('Admin?userId=' + userId
											+ "&action=remove");

								});

						editBtn1.click(function() {
							
							$('td', user).each(function() {
								
									$(this).attr('contenteditable','true');
									//$(this).attr('contenteditable','false');
							});
							
							
						});

					});

			
			$('#newTopic').click(function() {
				if (requestTab) {
					requestTab.abort();
				}
				requestTab = $.ajax({
					url : 'NewTopic.jsp',
					success : function(data) {
						$("#dinamicDiv").html(data);
					}
				});
			});
		});
