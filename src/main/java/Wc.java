import java.io.*;
import java.util.Scanner;
import java.io.FileReader;

public class Wc {

    public static void c(String fileName) {
        //System.out.println("Running cRun function with: " + f);
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException(file + " does not exist");
        }

        long size = file.length();
        System.out.print(size + " " + fileName);
    }

    public static long l(String fileName) {
        File file = new File(fileName);

        long lines = 0;
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