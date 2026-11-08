import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellChecker {
    // Use this field everytime you need to read user input
    private Scanner inputReader; // DO NOT MODIFY

    public SpellChecker() {
      inputReader = new Scanner(System.in); // DO NOT MODIFY - must be included in this method
      // TODO: Complete the body of this constructor, as necessary.
    }
  
    public void start() {
        String dictionaryFilename = getDictionaryFile();
      // TODO: Complete the body of this method, as necessary.
      inputReader.close();  // DO NOT MODIFY - must be the last line of this method!
    }

    private String getDictionaryFile(){
        while (true) {

            System.out.println(Util.DICTIONARY_PROMPT);
            String filename = inputReader.nextLine();
            try{
                Scanner fileReader = new Scanner(new File(filename));
                fileReader.close();
                System.out.printf(
                        Util.DICTIONARY_SUCCESS_NOTIFICATION,
                        filename
                );
                return filename;

            } catch (FileNotFoundException e){
                System.out.println(Util.FILE_OPENING_ERROR);
            }
        }
    }
}
  //test
    // You can of course write other methods as well.