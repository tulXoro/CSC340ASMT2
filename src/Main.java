import java.util.Scanner;

public class Main {
    private static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("! Loading data...");
        Dictionary dictionary = new Dictionary();
        dictionary.loadData();
        System.out.println("! Data loaded...");

        System.out.println("===== DICTIONARY 340 JAVA =====");
        System.out.println("----- Keywords: " + dictionary.getWordCount());
        System.out.println("----- Definitions: " + dictionary.getDefCount());

        Query query = new Query(dictionary);
        int counter = 1;
        Scanner input = new Scanner(System.in);

        // Program Loop
        while(isRunning) {
            System.out.print("Search [" + counter++ + "]: ");
            String userInput = input.nextLine();

            query.parse(userInput);
            query = new Query(dictionary);
        }

        System.out.println("\n-----THANK YOU-----");
    }

    public static void quitProgram() {
        isRunning = false;
    }

    public static void print(String str) {
        System.out.println("\t|");
        System.out.println("\t " + str);
        System.out.println("\t|");
    }

    public static void print(String str, boolean words) {
        System.out.println("\t|");
        System.out.print(str);
        System.out.println("\t|");

    }

}
