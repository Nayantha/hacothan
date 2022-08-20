import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File file = new File("registration.txt");
        if (!file.exists()) {
            Registration.getRegistration();
        } else {
            try {
                Scanner myReader = new Scanner(file);
                String name = "";
                int duration = 0;

                Registration.name = myReader.nextLine();
                Registration.courseDuration = myReader.nextInt();

                myReader.close();
                MainMenu.UI(name);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}