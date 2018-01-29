# Elsevier Java Developer Assignment

## Solution Design

The solution consists of two parts: The tweet synchronizer, and a GUI.

The tweet synchronizer is responsible for polling Twitter for new tweets, fetching them and storing them locally in a database. The synchronizer is an entity that can be called virtually from any point in the application, e.g. from a command line program or a crontab.

The GUI consists of one a single page that displays the tweets that have been synced in the local database in chronological order (most recent tweets come first). Because the number of tweets can be large, only a limited number is displayed originally, and then the user can use the "load more" button to load the next chunk of tweets.

The application is designed so that it can be easily extendable. This is achieved with programming to interfaces instead of implementations, and by well-defining the field of responsibility of each classes.
 	
## Tools / Technology Choices

* Twitter API: Twitter4J
* Persistence: Hibernate, MySQL
* JSON: Jackson
* Logging: Log4j
* Unit testing: JUnit
* Build automation: Maven
