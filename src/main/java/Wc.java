import java.io.*;
import java.util.Scanner;
import java.io.FileReader;

public class Wc {

    // ####     Step 1 | -c                    ####
    // -c outputs the number of bytes in a file.
    public static long c(String fileName) {
        // Initialises a new file object and check if a file for the given parameter exists
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        // If a file does exist the size is returned using length() method.
        long size = file.length();
        System.out.print(size + " " + fileName);
        return size;
    }

    // ####     Step 2 | l                      ####
    public static long l(String fileName) {
        File file = new File(fileName);
        long lines = 0;
        // Uses a buffered reader to take each line at a time until there are no more
        //  lines to read.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void w(String fileName){
        // open file in read mode
        File file = new File(fileName);
        int count = 0;
        int empty = 0;

        try(Scanner input = new Scanner(file)){
            // initalise variables
            String line;

            // gets each line until end of file
            while (input.hasNextLine()){
                line = input.nextLine();
                String[] words = line.split("\\s+");
                count += words.length;

                // count "empty" words.
                for(String word:words){
                    if (word.length() == 0){
                        empty++;
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(count-empty + " " + fileName);
    }

    public static void mChar(String fileName){

    }


    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter command (-c <filename>):");
        String input = scanner.nextLine();

        //Parse input
        String[] inputArgs = input.split("\\s+");

        if (inputArgs.length != 2){
            System.out.println("Usage: ccwc -c <filename>");
        }

        String fileName = inputArgs[1];

        // Call cRun i command is "-c"
        if (inputArgs[0].equals("-c")) {
            c(fileName);
        } else if (inputArgs[0].equals("l")) {
            long lines = l(fileName);
            System.out.println(lines + " " + fileName);
        } else if (inputArgs[0].equals("-w")) {
            w(fileName);
        } else if (inputArgs[0].equals("-m")) {
            mChar(fileName);

        } else {
            System.out.println("Invalid command");
        }

        scanner.close();
    }
}