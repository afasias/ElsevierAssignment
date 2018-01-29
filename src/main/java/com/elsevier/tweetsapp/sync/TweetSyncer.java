package com.elsevier.tweetsapp.sync;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.elsevier.tweetsapp.data.Tweet;
import com.elsevier.tweetsapp.data.TweetDAO;
import com.elsevier.tweetsapp.twitter.TwitterUtil;

/*
 * A class that syncs the local database with Elsevier's news feed.
 */
public class TweetSyncer {

	private static final int TWEETS_PER_PAGE = 200;

	private static final int MAX_PAGES = Integer.MAX_VALUE;

	private static final String USER_TO_SYNC_FROM = "ElsevierNews";

	private static final TwitterUtil twitter = TwitterUtil.createInstance();

	private static final Logger log = Logger.getLogger(TweetSyncer.class);

	/*
	 * Fetches the latest tweets from Elsevier in batches (pages). It keeps
	 * fetching and storing those tweets in the local database until a tweet
	 * that has been already stored is encountered. A tweet is considered
	 * "already stored" if it has an ID equal or smaller than the max tweet ID
	 * stored in the database.
	 */
	public static void syncTweets() {

		final long maxTweetId = TweetDAO.getMaxId();
		log.debug("max tweet id: " + maxTweetId);

		List<Tweet> tweetsToSave = new ArrayList<>();
		page_loop:
		for (int page = 1; page <= MAX_PAGES; page++) {
			log.debug("Fetching page: "+page);
			List<Tweet> tweets = twitter.fetchTweets(USER_TO_SYNC_FROM, page, TWEETS_PER_PAGE);
			log.debug(String.format("%d tweets were fetched", tweets.size()));
			if (tweets.isEmpty()) {
				break;
			}
			for (Tweet tweet : tweets) {
				if (tweet.getId() > maxTweetId) {
					log.debug("New tweet id found: " + tweet.getId());
					tweetsToSave.add(tweet);
				} else {
					log.debug(String.format("Tweet ID %d already in database, leaving loop.", tweet.getId()));
					break page_loop;
				}
			}
		}
		
		if (! tweetsToSave.isEmpty()) {
			log.info(String.format("Saving %d new tweets into the database...", tweetsToSave.size()));
			TweetDAO.saveTweets(tweetsToSave);
		} else {
			log.info("No new tweets found.");
		}
	}
}
