package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import twitter4j.Status;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

@Controller
public class SentimentMinerController {
    SentimentParser parser;

    public SentimentMinerController() {
        super();

        Properties config = new Properties();

        try {
            config.load(new FileReader(new File("config.properties")));
            this.parser = new SentimentParser(config.getProperty("positiveLexiconLocation"), config.getProperty("negativeLexiconLocation"));
        } catch (Exception e) {
            System.out.println("Could not open needed config/resource files");
            e.printStackTrace();
            System.exit(0); //abort application
        }
    }

    @PostMapping("/displaySentiment")
    public String displaySentimentData(@RequestParam(name="hashtag", required = true) String hashtag, Model model) {

        //contain data that will be passed to frontend
        String dataset = "false";
        String warning = "";


        try {
            //get tweets related to given hashtag
            List<Status> tweets = TwitterClient.getTweets("#" + hashtag, 100);

            int positiveCount = 0;
            int negativeCount = 0;
            int neutralCount = 0;
            for (Status tweet : tweets) {
                //get sentiment of each tweet
                String tweetContent = tweet.getText();
                Sentiments sentiment = this.parser.parseSentiment(tweetContent);

                //update running count of sentiments
                switch(sentiment) {
                    case POSITIVE: positiveCount += 1; break;
                    case NEGATIVE: negativeCount += 1; break;
                    case NEUTRAL: neutralCount += 1; break;
                }
            }

            //format results into a js variable for the frontend to render;
            dataset = "{positive: " + positiveCount + ", negative: " + negativeCount + ", neutral: " + neutralCount + "}";

        } catch (Exception e) {
            //give the frontend a warning message to display
            warning = "Could not access Twitter to gather data. Please try again at some other time.";
        }

        //pass data to frontend
        model.addAttribute("dataset", dataset);
        model.addAttribute("warning", warning);
        return "minerDisplay";
    }

}