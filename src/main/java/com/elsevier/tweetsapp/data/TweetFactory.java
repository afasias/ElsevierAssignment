package com.elsevier.tweetsapp.data;

import twitter4j.Status;

/*
 * A factory class that creates Tweet objects.
 */
public class TweetFactory {

	/*
	 * Creates a Tweet from a Twitter4J Status object.
	 */
	public static Tweet fromStatus(Status status) {
		Tweet tweet = new Tweet();
		tweet.setId(status.getId());
		tweet.setDate(status.getCreatedAt());
		tweet.setText(status.getText());
		return tweet;
	}
}
