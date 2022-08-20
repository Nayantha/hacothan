import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String userWindows = System.getenv("USERNAME");
        System.out.println("Username using environment variable in windows : " + userWindows);


        File file = new File("registration.txt");
        if (!file.exists()) {
            Registration.getRegistration();
        } else {
            try {
                Scanner myReader = new Scanner(file);
                String data = "";
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
                MainMenu.UI(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}