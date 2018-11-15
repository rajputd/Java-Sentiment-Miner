import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import app.SentimentParser;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.Assert.fail;


public class SentimentParserTest {
    SentimentParser parser;
    String posLexiconLocation = "/home/dileep/IdeaProjects/JavaSentimentMiner/src/main/resources/lexicons/positive-words.txt";
    String negLexiconLocation = "/home/dileep/IdeaProjects/JavaSentimentMiner/src/main/resources/lexicons/negative-words.txt";

    @Before
    public void setupSentimentParser() {
        try {
            this.parser = new SentimentParser(this.posLexiconLocation, this.negLexiconLocation);
        } catch (Exception e) {
            System.out.println("Could not open lexicons for SentimentParser");
            e.printStackTrace();
        }

    }

    @Test
    public void lexiconSmokeTest() {
        HashSet<String> posLex = this.parser.getPositiveWords();
        HashSet<String> negLex = this.parser.getNegativeWords();

        Assert.assertTrue(negLex != null);
        Assert.assertTrue(posLex != null);

    }

    @Test
    public void posLexiconTest() {
        HashSet<String> posLex = this.parser.getPositiveWords();

        Assert.assertTrue(posLex.contains("admirable"));
        Assert.assertTrue(posLex.contains("appreciated"));
        Assert.assertTrue(posLex.contains("beckoning"));
        Assert.assertTrue(posLex.contains("wowing"));
    }

    @Test
    public void negLexiconTest() {
        HashSet<String> negLex = this.parser.getNegativeWords();

        Assert.assertTrue(negLex.contains("absence"));
        Assert.assertTrue(negLex.contains("battered"));
        Assert.assertTrue(negLex.contains("blatantly"));
        Assert.assertTrue(negLex.contains("weary"));
    }

}
