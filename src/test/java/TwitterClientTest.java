import app.TwitterClient;
import org.junit.Assert;
import org.junit.Test;
import twitter4j.Status;

import java.util.List;

public class TwitterClientTest {
    @Test
    public void twitterClientSmokeTest() {
        List<Status> result = null;

        try {
            result = TwitterClient.getTweets("#Trump", 100 );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Assert.assertTrue(result != null);
    }

    @Test
    public void checkResponseSize() {
        List<Status> result = null;

        try {
            result = TwitterClient.getTweets("#Trump", 100 );
            Assert.assertTrue(result.size() == 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
