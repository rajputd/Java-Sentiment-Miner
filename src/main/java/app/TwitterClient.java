package app;

import twitter4j.*;

import java.util.List;

public class TwitterClient {

    static public List<Status> getTweets(String hashtag, int num) throws Exception{
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query();

        //set api query parameters
        query.setQuery(hashtag);    //search for hashtag
        query.count(num);           //get num Tweets
        query.lang("en");           //only use Tweets written in english
        QueryResult result = twitter.search(query);

        return result.getTweets();
    }
}
