package com.elsevier.tweetsapp.twitter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.elsevier.tweetsapp.data.Tweet;
import com.elsevier.tweetsapp.data.TweetFactory;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/*
 * An Twitter4J-based implementation of the TwitterUtil interface.
 */
public class Twitter4JUtil implements TwitterUtil {

	private static final String PROPERTIES_FILE = "tweetsapp.properties";

	private static final Logger log = Logger.getLogger(Twitter4JUtil.class);

	private Twitter twitter;

	public Twitter4JUtil() {
		Properties prop = loadProperties();
		Configuration conf = new ConfigurationBuilder().setDebugEnabled(true)
				.setOAuthConsumerKey(prop.getProperty("OAuthConsumerKey"))
				.setOAuthConsumerSecret(prop.getProperty("OAuthConsumerSecret"))
				.setOAuthAccessToken(prop.getProperty("OAuthAccessToken"))
				.setOAuthAccessTokenSecret(prop.getProperty("OAuthAccessTokenSecret")).build();
		log.debug("Creating Twitter4J instance");
		twitter = new TwitterFactory(conf).getInstance();
	}

	@Override
	public List<Tweet> fetchTweets(String user, int page, int pageSize) {
		try {
			Paging paging = new Paging(page, pageSize);
			log.info(String.format("Fetching page %d of size %d from %s's statuses", page, pageSize, user));
			List<Status> statuses = twitter.getUserTimeline(user, paging);
			log.debug(String.format("Retrieved %d statuses", statuses.size()));
			return statuses.stream().map(s -> TweetFactory.fromStatus(s)).collect(Collectors.toList());
		} catch (TwitterException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	private Properties loadProperties() {
		try {
			Properties prop = new Properties();
			log.debug("Loading properties from: " + PROPERTIES_FILE);
			prop.load(new FileInputStream(PROPERTIES_FILE));
			return prop;
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
}
