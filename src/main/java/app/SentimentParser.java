package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

enum Sentiments {POSITIVE, NEGATIVE, NEUTRAL}

public class SentimentParser {

    private HashSet<String> positiveWords;
    private HashSet<String> negativeWords;

    public SentimentParser(String posLexiconLocation, String negLexiconLocation) throws Exception {
        this.positiveWords = new HashSet<>();
        this.negativeWords = new HashSet<>();

        this.loadWords(this.positiveWords, posLexiconLocation);
        this.loadWords(this.negativeWords, negLexiconLocation);
    }


    public HashSet<String> getPositiveWords() {
        return positiveWords;
    }

    public HashSet<String> getNegativeWords() {
        return negativeWords;
    }

    /*loadWords*/
    /**
     * Loads the lexicon of words associated with the file at LexiconLocation into wordsSet.
     *
     * @param wordsSet HashSet<String> that is the set that will store the words at LexiconLocation.
     * @param lexiconLocation String that points to the location of the file containing the lexicon.
     */
    void loadWords(HashSet<String> wordsSet, String lexiconLocation) throws IOException {

        File file = new File(lexiconLocation);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() == 0 || line.charAt(0) == ';' || line.charAt(0) == '\n') {
                continue;
            }

            wordsSet.add(line);
        }
    }

    /**
     * Analyzes the given phrase using the preloaded lexicons and returns the sentiment associated with the phrase.
     * @param phrase String that contains data that contains the sentiment that will be parsed.
     * @return The sentiment (POSITIVE, NEGATIVE, or NEUTRAL) associated with the given phrase.
     */
    Sentiments parseSentiment(String phrase) {
        return Sentiments.NEUTRAL;
    }
}
