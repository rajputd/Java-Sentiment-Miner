package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class SentimentParser {

    private HashSet<String> positiveWords;
    private HashSet<String> negativeWords;

    public SentimentParser(String posLexiconLocation, String negLexiconLocation) throws Exception {
        //initialize empty sets before loading them
        this.positiveWords = new HashSet<>();
        this.negativeWords = new HashSet<>();

        //load each set with their associated lexicon
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

        //get ready to read the file at lexiconLocation
        File file = new File(lexiconLocation);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //add each word found at lexicon to the set
        String line;
        while ((line = reader.readLine()) != null) {

            //do not add any empty lines, comments, or new lines to the set
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
    public Sentiments parseSentiment(String phrase) {

        //format all characters to lowercase so they can match in the lexicons
        String lower = phrase.toLowerCase();

        //split phrase into words on spaces and other grammatical marks.
        String words[] = lower.split("\\W");

        //go through each word and keep a running score tracking sentiment
        int sentimentScore = 0;
        for (String word: words) {
            if (this.positiveWords.contains(word)) {
                //if word is positive add one to score
                sentimentScore += 1;
            } else if (this.negativeWords.contains(word)) {
                //if word is negative subtract one from score
                sentimentScore -= 1;
            }
        }

        //normalize score by length of phrase
        float averageScore = (float)sentimentScore / (float)words.length;

        //return the appropriate sentiment according to the normalized score
        if (averageScore > 0) {
            return Sentiments.POSITIVE;
        }

        if (averageScore < 0) {
            return Sentiments.NEGATIVE;
        }

        return Sentiments.NEUTRAL;
    }
}
