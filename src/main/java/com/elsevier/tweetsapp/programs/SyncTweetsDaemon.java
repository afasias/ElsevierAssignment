package com.elsevier.tweetsapp.programs;

import org.apache.log4j.Logger;

import com.elsevier.tweetsapp.sync.TweetSyncer;

/*
 * A console program that calls the TweetSyncer. The program runs as a daemon
 * making it suitable to be used as a service. It keeps running forever and calls
 * the TweetSyncer periodically in a predefined frequency.
 */
public class SyncTweetsDaemon {

	private static final int MINUTES_BETWEEN_SYNCS = 15;

	private static final Logger log = Logger.getLogger(SyncTweetsDaemon.class);

	public static void main(String[] args) {
		log.info("Daemon started");
		while (true) {
			log.info("Syncing tweets");
			TweetSyncer.syncTweets();
			try {
				log.info(String.format("Entering sleep for %d minutes", MINUTES_BETWEEN_SYNCS));
				Thread.sleep(MINUTES_BETWEEN_SYNCS * 60000); // 1 minute = 60000 msec
			} catch (InterruptedException e) {
				log.error(e);
				break;
			}
		}
	}
}
