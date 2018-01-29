package com.elsevier.tweetsapp.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elsevier.tweetsapp.data.Tweet;
import com.elsevier.tweetsapp.data.TweetFactory;
import com.elsevier.tweetsapp.tests.DummyStatus;

import twitter4j.Status;

public class TweetFactoryTest {

	@Test
	public void testTwitter4JStatus() {
		Status status = new DummyStatus();
		Tweet tweet = TweetFactory.fromStatus(status);
		assertEquals(status.getId(), tweet.getId());
		assertEquals(status.getCreatedAt(), tweet.getDate());
		assertEquals(status.getText(), tweet.getText());
	}
}
