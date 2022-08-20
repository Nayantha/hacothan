import java.util.*;

public class MainMenu extends Code {
    public static void UI(String name) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("----GPA Calculator----\n");

        System.out.println("Hello " + name + " Welcome!\n");

        System.out.println("-------Menu---------");
        System.out.println("1. View results");
        System.out.println("2. Add results ");
        System.out.println("3. Export data ");
        System.out.println("4. Clear data ");
        System.out.println("5. Exit ");

        System.out.print("Enter your choice: ");
        n = sc.nextInt();

        switch (n) {
            case (2):
                Add.add(name);
                break;
            default:
                System.out.println("Wrong Choice");
                MainMenu.UI(name);
                break;
        }
    }
}
