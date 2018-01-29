package com.elsevier.tweetsapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elsevier.tweetsapp.data.Tweet;
import com.elsevier.tweetsapp.data.TweetDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * A servlet that supplies clients with tweets from the local database.
 */
@WebServlet("/TweetsServlet")
public class TweetsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * Returns a page of tweets from the local database, encoded as a JSON array.
	 * Optionally queries Twitter to poll for new tweets from @Elsevier.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<Tweet> tweets = TweetDAO.fetchTweets(page, pageSize);

		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(tweets);
		response.getWriter().append(json);
	}
}
