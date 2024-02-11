import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

public class Wc{

    public static void cRun(String fileName) {
        //System.out.println("Running cRun function with: " + f);
        File cfile = new File(fileName);
        if (!cfile.exists() || !cfile.isFile()){
            throw new IllegalArgumentException(cfile + " does not exist");
        }
        
        long size = cfile.length();
        System.out.print(size + " " + fileName);
    }

    public static void lRun(String fileName) {
        File file = new File(fileName);
        
        long lines = 0;
        try (BufferReader reader = new BufferReader(new FileReader(file))) {
                while (reader.readline() != null){
                    lines++;
                }
        } catch (IOExcpetion e) {
            e.printStackTrace();
        }
        
        System.out.println(lines+" "+fileName);

    }



    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println ("Enter command (-c <filename>):");
        String input = scanner.nextLine();

        //Parse input
        String[] inputArgs = input.split("\\s+");

        if (inputArgs.length != 2 || !inputArgs[0].equals("-c")){
            System.out.println("Usage: ccwc -c <filename>");
        }

        String fileName = inputArgs[1];

        // Call cRun i command is "-c"
        if (inputArgs[0].equals("-c")){
            cRun(fileName);
        } else if (inputArgs[0].equals("l")){
            lRun(fileName);            
        } else{
            System.out.println("Invalid command");
        }

        scanner.close();
    }
}
