
$(document).ready( function() {
	
	loadMoreTweets();
	
	$('#load_more_button').on( 'click', function() {
		loadMoreTweets();
	});
	
	function loadMoreTweets() {
		
		var $tweets = $("#tweets");
		var tweetCount = $tweets.children().length
		var pageSize = 50;
		var page = tweetCount / pageSize + 1;
		
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/ElsevierAssignment/TweetsServlet',
			data: {
				'page': page,
				'pageSize': pageSize,
			},
			success: function(data) {
				if (data) {
					for (var i in data) {
						$tweets.append($('<li>').append(data[i].text).append($('<i>').append($('<br>')).append(data[i].date)));
					}
				}
			},
			dataType: 'json',
		});
	}

	$('#filter').bind( 'keyup change', function() {
		var keywords = $('#filter').val().toLowerCase().split(' ');
		$('#tweets li').each( function() {
			var text = $(this).text().toLowerCase();
			for (var i in keywords) {
				var keyword = keywords[i];
				if (! keyword) continue;
				if (text.indexOf(keyword) < 0) {
					$(this).hide();
					return;
				}
			}
			$(this).show();
		});
	});
});
