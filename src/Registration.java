import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registration {

    public static void getRegistration() {
        System.out.println("----GPA Calculator----");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Yor Name: ");
        String name = scanner.nextLine();

        System.out.print("Course duration (in years): ");
        int courseDuration = scanner.nextInt();

        File file = new File("registration.txt");
        try {
            FileWriter fileWriter = new FileWriter("registration.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter.write("" + name);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Congratulations! registration was complete.");
        MainMenu.UI(name);
    }
}

