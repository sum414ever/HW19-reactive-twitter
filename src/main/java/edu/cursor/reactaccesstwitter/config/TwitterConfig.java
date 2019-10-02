package edu.cursor.reactaccesstwitter.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfig {
    static final String CONSUMER_KEY = "hlDYkRIwyUn5KuWLkQOO9iNit";
    static final String CONSUMER_SECRET = "R3GwrLcQ5R99gQkEQwbji8K9JqHPRXF3wJf3wjUawc6s9nsWSV";
    static final String ACCESS_TOKEN = "984305954886995970-vyjKnKlXT7RR2LgveI7Oyyg1KibFfEA";
    static final String ACCESS_TOKEN_SECRET = "Jjo3ZI54rKUrmAfji9C0HoFDL4RzZKGuAgC27FTEYoU6p";

    public TwitterStream getTwitterConfiguration() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        return new TwitterStreamFactory(cb.build()).getInstance();
    }
}
