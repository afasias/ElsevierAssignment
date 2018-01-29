package com.elsevier.tweetsapp.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

public class TweetDAO {
	
	/*
	 * Fetches the max Tweet Id from the database, or 0 if there are no Tweets
	 * stored.
	 */
	public static long getMaxId() {
		Session session = HibernateUtil.openSession();
		String hql = "SELECT MAX(id) FROM Tweet";
		Long result = session.createQuery(hql,Long.class).getSingleResult();
		long maxId = result != null ? result.longValue() : 0;
		session.close();
		return maxId;
	}

	/*
	 * Stores the provided Tweets in the database.
	 */
	public static void saveTweets(List<Tweet> tweetsToSave) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		tweetsToSave.forEach(session::save);
		session.getTransaction().commit();
		session.close();
	}

	/*
	 * Fetches a page of tweets from the database. Newer tweets are returned first.
	 */
	public static List<Tweet> fetchTweets(int page, int pageSize) {
		Session session = HibernateUtil.openSession();
		TypedQuery<Tweet> query = session.createQuery("FROM Tweet ORDER BY id DESC", Tweet.class);
		query.setFirstResult(pageSize * (page-1));
		query.setMaxResults(pageSize);
		List<Tweet> tweets = query.getResultList();
		session.close();
		return tweets;
	}
}
