package com.elsevier.tweetsapp.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.elsevier.tweetsapp.data.Tweet;
import com.elsevier.tweetsapp.data.TweetDAO;

@FixMethodOrder(MethodSorters.JVM)
public class TweetDAOTest {

	@Test
	public void testSave() {
		Tweet tweet1 = new Tweet();
		tweet1.setId(1001);
		tweet1.setDate(new Date());
		tweet1.setText("status1");
		
		Tweet tweet2 = new Tweet();
		tweet2.setId(1002);
		tweet2.setDate(new Date());
		tweet2.setText("status2");
		
		List<Tweet> tweets = Arrays.asList(tweet1,tweet2);
		TweetDAO.saveTweets(tweets);
	}
	
	@Test
	public void testMaxId() {
		long maxId = TweetDAO.getMaxId();
		assertEquals(1002, maxId);
	}
	
	@Test
	public void testFetch() {
		Tweet tweet2 = TweetDAO.fetchTweets(1,1).get(0);
		assertEquals(tweet2.getId(), 1002);
		assertEquals(tweet2.getText(), "status2");
		Tweet tweet1 = TweetDAO.fetchTweets(2,1).get(0);
		assertEquals(tweet1.getId(), 1001);
		assertEquals(tweet1.getText(), "status1");
	}
}
