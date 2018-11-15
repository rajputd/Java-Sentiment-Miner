import app.Sentiments;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import app.SentimentParser;
import java.util.HashSet;



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

    @Test
    //verify that parseSentiment returns positive for generally positive phrases
    public void parseReturnsPositiveForPositiveWords() {

        //sentences that are totally positive
        Assert.assertTrue(Sentiments.POSITIVE == parser.parseSentiment("I am so happy!"));
        Assert.assertTrue(Sentiments.POSITIVE == parser.parseSentiment("This has been an amazing day"));

        //sentences that have more positive than negative words
        Assert.assertTrue(Sentiments.POSITIVE == parser.parseSentiment("The food was so worthwhile and good that the patrons begged for more"));
        Assert.assertTrue(Sentiments.POSITIVE == parser.parseSentiment("His presentation was superbly topnotch despite the unnerved crowd"));
    }

    @Test
    //verify that parseSentiment returns positive for generally positive phrases
    public void parseReturnsPositiveForNegativeWords() {

        //sentences that are totally positive
        Assert.assertTrue(Sentiments.NEGATIVE == parser.parseSentiment("I am so sad!"));
        Assert.assertTrue(Sentiments.NEGATIVE == parser.parseSentiment("This has been an terrible day."));

        //sentences that have more positive than negative words
        Assert.assertTrue(Sentiments.NEGATIVE == parser.parseSentiment("The food was so terrible and poor that the patrons where kind not to leave."));
        Assert.assertTrue(Sentiments.NEGATIVE == parser.parseSentiment("His presentation was disappointing unpleasant despite the excited crowd."));
    }

    @Test
    //verify that parseSentiment returns positive for generally positive phrases
    public void parseReturnsPositiveForNeutralWords() {

        //sentences that are totally positive
        Assert.assertTrue(Sentiments.NEUTRAL == parser.parseSentiment("The soup is ok."));
        Assert.assertTrue(Sentiments.NEUTRAL == parser.parseSentiment("This has been a day."));

        //sentences that have more positive than negative words
        Assert.assertTrue(Sentiments.NEUTRAL == parser.parseSentiment("The food was ok and the patrons ate it"));
        Assert.assertTrue(Sentiments.NEUTRAL == parser.parseSentiment("His presentation was ok despite the crowd"));
    }



}
