package com.elsevier.tweetsapp.programs;

import com.elsevier.tweetsapp.sync.TweetSyncer;

/*
 * A console program that calls the TweetSyncer. The program makes one single
 * call to TweetSyncer and then exits, which makes it suitable for running from
 * a shell's command line or from a crontab.
 */
public class SyncTweets {

	public static void main(String[] args) {
		TweetSyncer.syncTweets();
		System.exit(0);
	}
}
