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
        return file.length();
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

    // ####     Step 3 | -w                    ####
    public static long w(String fileName){
        // open file in read mode
        File file = new File(fileName);
        long count = 0;
        long empty = 0;

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
        return count-empty;
    }

    // ####     Step 4 | -m                    ####
    public static long mChar(String fileName){
        File file = new File(fileName);
        long count = 0;

        try(Scanner input = new Scanner(file, "UTF_8")){
            // initalise variables
            String line;

            // count every character (including spaces)
            while (input.hasNextLine()){
                line = input.nextLine();
                count += line.length(); //add length of entire line
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return count;
    }


    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(">ccwc ");
        String input = scanner.nextLine();
        // TODO : some sort of try catch for errors
        //Parse input
        String[] inputArgs = input.split("\\s+");
        if (inputArgs.length != 2){
            System.out.println("Usage: ccwc -c <filename>");
        }

        String fileName = inputArgs[1];

        // Call cRun i command is "-c"
        switch (inputArgs[0]) {
            case "-c":
                System.out.println(c(fileName) + " " + fileName);
                break;
            case "l":
                long lines = l(fileName);
                System.out.println(lines + " " + fileName);
                break;
            case "-w":
                System.out.println(w(fileName) + " " + fileName);
                break;
            case "-m":
                System.out.println(mChar(fileName) + " " + fileName);
                break;
            default:
                // default output
                System.out.println("   " + c(fileName) + " " + l(fileName) + " " + w(fileName) + " " + fileName);
                break;
        }
        scanner.close();
    }
}
