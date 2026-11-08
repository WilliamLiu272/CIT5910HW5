import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class SpellChecker {

    private Scanner inputReader; // DO NOT MODIFY

    public SpellChecker() {
        inputReader = new Scanner(System.in); // DO NOT MODIFY
    }

    public void start() {

        //Get dictionary filename
        String dictionaryFilename = getDictionaryFile();

        //Get text filename
        String textFilename = getTextFile();
        String outputFileName = getOutputFilenames(textFilename);

        System.out.printf(
                Util.FILE_SUCCESS_NOTIFICATION,
                textFilename,
                outputFileName
        );

        //Load dictionary
        HashSet<String> dictionary =
                loadDictionary(dictionaryFilename);

        //Create WordRecommender
        WordRecommender recommender =
                new WordRecommender(dictionaryFilename);

        //Check words & handle user choices
        checkWords(textFilename, dictionary, recommender);

        // TODO: Write corrected words to output file.

        inputReader.close(); // DO NOT MODIFY - must be last line!
    }

    private String getDictionaryFile() {

        while (true) {

            System.out.printf(Util.DICTIONARY_PROMPT);
            String filename = inputReader.nextLine();

            try (Scanner fileReader =
                         new Scanner(new File(filename))) {

                System.out.printf(
                        Util.DICTIONARY_SUCCESS_NOTIFICATION,
                        filename
                );

                return filename;

            } catch (FileNotFoundException e) {
                System.out.printf(Util.FILE_OPENING_ERROR);
            }
        }
    }

    private String getTextFile() {

        while (true) {

            System.out.printf(Util.FILENAME_PROMPT);
            String filename = inputReader.nextLine();

            try (Scanner fileReader =
                         new Scanner(new File(filename))) {

                return filename;

            } catch (FileNotFoundException e) {
                System.out.printf(Util.FILE_OPENING_ERROR);
            }
        }
    }

    private String getOutputFilenames(String filename) {

        if (filename.endsWith(".txt")) {
            return filename.substring(
                    0, filename.length() - 4
            ) + "_chk.txt";
        }

        return filename + "_chk.txt";
    }

    private HashSet<String> loadDictionary(String filename) {

        HashSet<String> dictionary = new HashSet<>();

        try (Scanner fileReader =
                     new Scanner(new File(filename))) {

            while (fileReader.hasNext()) {
                dictionary.add(fileReader.next());
            }

        } catch (FileNotFoundException e) {
            System.out.printf(Util.FILE_OPENING_ERROR);
        }

        return dictionary;
    }

    private void checkWords(
            String filename,
            HashSet<String> dictionary,
            WordRecommender recommender
    ) {

        try (Scanner fileReader =
                     new Scanner(new File(filename))) {

            while (fileReader.hasNext()) {

                String word = fileReader.next();

                if (!dictionary.contains(word)) {

                    System.out.printf(
                            Util.MISSPELL_NOTIFICATION,
                            word
                    );

                    ArrayList<String> suggestions =
                            recommender.getWordSuggestions(
                                    word, 2, 0.5, 4
                            );

                    if (suggestions.isEmpty()) {

                        System.out.printf(Util.NO_SUGGESTIONS);

                    } else {

                        System.out.printf(
                                Util.FOLLOWING_SUGGESTIONS
                        );

                        for (int i = 0; i < suggestions.size(); i++) {
                            System.out.printf(
                                    Util.SUGGESTION_ENTRY,
                                    i + 1,
                                    suggestions.get(i)
                            );
                        }
                    }

                    //Ask user what to do
                    String correctedWord =
                            handleMisspelledWord(word, suggestions);

                    //Write correctedWord to a file.
                }
            }

        } catch (FileNotFoundException e) {
            System.out.printf(Util.FILE_OPENING_ERROR);
        }
    }

    private String handleMisspelledWord(
            String word,
            ArrayList<String> suggestions
    ) {

        boolean hasSuggestions = !suggestions.isEmpty();

        while (true) {

            if (hasSuggestions) {
                System.out.printf(Util.THREE_OPTION_PROMPT);
            } else {
                System.out.printf(Util.TWO_OPTION_PROMPT);
            }

            String choice = inputReader.nextLine().trim();

            if (choice.equals("a")) {

                return word;

            } else if (choice.equals("t")) {

                System.out.printf(Util.MANUAL_REPLACEMENT_PROMPT);
                return inputReader.nextLine();

            } else if (choice.equals("r") && hasSuggestions) {

                return chooseSuggestion(suggestions);

            } else {

                System.out.printf(Util.INVALID_RESPONSE);
            }
        }
    }

    private String chooseSuggestion(ArrayList<String> suggestions) {

        System.out.printf(Util.AUTOMATIC_REPLACEMENT_PROMPT);

        while (true) {

            String input = inputReader.nextLine().trim();

            try {

                int number = Integer.parseInt(input);

                if (number >= 1 && number <= suggestions.size()) {
                    return suggestions.get(number - 1);
                }

            } catch (NumberFormatException e) {
            }

            System.out.printf(Util.INVALID_RESPONSE);
        }
    }
}