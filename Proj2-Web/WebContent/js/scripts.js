$('document').ready(
		function() {

			var requestTab;

			if ($('#userStats').text() != "Admin") {
				$('#admin').hide();
			}

			$('#admin').click(function() {
				console.log("Admin");
				$('#dinamicDiv').load('Admin?');
			});

			$('#searchAuthor').click(function() {
				$('#dinamicDiv').load('SearchAuthor?');
			});

			$('#search').click(function() {
				var toSearch = $('#toSearch').val();
				console.log("Send");
				$('#dinamicDiv').load('HighlightSearch?'+toSearch);
			});

			$('#authorTable > tbody > tr').each(function() {
				var user = $(this);
				var toSearch = user.attr("id");

				var sendBtn = user.find(".btn-success");

				$(sendBtn).click(function() {
					console.log(toSearch);
					$('#dinamicDiv').load('AuthorNews?'+toSearch);
				});

			});

			$('#adminTable > tbody > tr').each(
					function() {
						var user = $(this);
						console.log(user.attr("id"));
						var deleteBtn = user.find(".btn-danger");
						var sendBtn = user.find(".btn-success");

						$(sendBtn).click(
								function() {

									console.log("sucess");

									var row_data = [];

									$('td', user).each(function() {

										row_data.push($(this).text());

									});

									var username = row_data[0];
									var email = row_data[1];
									var password = row_data[2];

									console.log(username);
									console.log(email);
									console.log(password);

									$.post('Admin?userId=' + user.attr("id")
											+ "&action=edit" + "&username="
											+ username + "&email=" + email
											+ "&password=" + password);
								});

						deleteBtn
								.click(function() {
									var userId = user.attr("id");
									$("#" + userId + "").remove();
									$.post('Admin?userId=' + userId
											+ "&action=remove");

								});
					});
		});
