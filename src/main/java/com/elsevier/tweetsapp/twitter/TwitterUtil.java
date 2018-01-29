package com.elsevier.tweetsapp.twitter;

import java.util.List;

import com.elsevier.tweetsapp.data.Tweet;

/*
 * An interface that defines all the necessary twitter operations required
 * by the app.
 */
public interface TwitterUtil {

	/*
	 * Fetches a list of tweets of a given user. The tweets are expected to
	 * be returned in reverse ID order (Tweets with a higher ID first).
	 */
	public List<Tweet> fetchTweets(String user, int page, int pageSize);
	
	/*
	 * Factory method to return an implementation of this interface.
	 */
	public static TwitterUtil createInstance() {
		return new Twitter4JUtil();
	}
}
