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
    //check if we can actually get the lexicons
    public void lexiconSmokeTest() {
        HashSet<String> posLex = this.parser.getPositiveWords();
        HashSet<String> negLex = this.parser.getNegativeWords();

        Assert.assertTrue(negLex != null);
        Assert.assertTrue(posLex != null);

    }

    @Test
    //check to make sure words in the lexicon are actually in the set
    public void posLexiconContainsDesiredWords() {
        HashSet<String> posLex = this.parser.getPositiveWords();

        Assert.assertTrue(posLex.contains("admirable"));
        Assert.assertTrue(posLex.contains("appreciated"));
        Assert.assertTrue(posLex.contains("beckoning"));
        Assert.assertTrue(posLex.contains("wowing"));
    }

    @Test
    //check to make sure words in the lexicon are actually in the set
    public void negLexiconContainsDesiredWords() {
        HashSet<String> negLex = this.parser.getNegativeWords();

        Assert.assertTrue(negLex.contains("absence"));
        Assert.assertTrue(negLex.contains("battered"));
        Assert.assertTrue(negLex.contains("blatantly"));
        Assert.assertTrue(negLex.contains("weary"));
    }

    @Test
    //make sure that there are no undesirable things within the lexicon
    public void posLexiconDoesNotContainUndesirableWords() {
        HashSet<String> posLex = this.parser.getPositiveWords();

        Assert.assertFalse(posLex.contains("\n"));
        Assert.assertFalse(posLex.contains(""));
        Assert.assertFalse(posLex.contains(";"));
    }

    @Test
    //make sure that there are no undesirable things within the lexicon
    public void negLexiconDoesNotContainUndesirableWords() {
        HashSet<String> negLex = this.parser.getNegativeWords();

        Assert.assertFalse(negLex.contains("\n"));
        Assert.assertFalse(negLex.contains(""));
        Assert.assertFalse(negLex.contains(";"));
    }

}
