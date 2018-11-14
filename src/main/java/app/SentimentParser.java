package app;

import java.util.HashSet;

enum Sentiments {POSITIVE, NEGATIVE, NEUTRAL}

public class SentimentParser {
    HashSet<String> positiveWords;
    HashSet<String> negativeWords;

    /*loadWords*/
    /**
     * Loads the lexicons associated with posLexicon and negLexiconLocation in the positiveWordsLocation
     * and negativeWords HashSets. This must be done before the parseSentiment method is used. Otherwise an error will
     * be thrown.
     * @param posLexiconLocation String that points to the location of the file containing the positive words lexicon.
     * @param negLexiconLocation String that points to the location of the file containing the negative words lexicon.
     */
    void loadWords(String posLexiconLocation, String negLexiconLocation) {

    }

    /**
     * Analyzes the given phrase using the preloaded lexicons and returns the sentiment associated with the phrase.
     * @param phrase String that contains data that contains the sentiment that will be parsed
     * @return The sentiment (POSITIVE, NEGATIVE, or NEUTRAL) associated with the given phrase
     */
    Sentiments parseSentiment(String phrase) {
        return Sentiments.NEUTRAL;
    }
}
