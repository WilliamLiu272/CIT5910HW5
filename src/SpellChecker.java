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
        String textFilename = getTextFile();
        String outputFileName = getOutputFilename(textFilename);

        System.out.printf(
                Util.FILE_SUCCESS_NOTIFICATION,
                textFilename,
                outputFileName
        );
      // TODO: Complete the body of this method, as necessary.
      inputReader.close();  // DO NOT MODIFY - must be the last line of this method!
    }

    private String getDictionaryFile(){
        while (true) {

            System.out.printf(Util.DICTIONARY_PROMPT);
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
    private String getTextFile(){
        while(true){
            System.out.printf(Util.FILENAME_PROMPT);
            String filename = inputReader.nextLine();
            try{
                Scanner fileReader = new Scanner(new File(filename));
                fileReader.close();

                return filename;
            } catch (FileNotFoundException e){
                System.out.println(Util.FILE_OPENING_ERROR);
            }
        }
    }
    private String getOutputFilenames(String filename){

        if (filename.endsWith(".txt")){
            return filename.substring(
                    0, filename.length() - 4
            ) +"_chk_txt";
        }
        return filename + "_chk.txt";
    }
}
  //test
    // You can of course write other methods as well.