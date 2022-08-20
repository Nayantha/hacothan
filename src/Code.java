import java.util.*;


public class Code {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        String username;
        String pass;
        String name;
        System.out.println("Enter your username: ");
        username = sc.nextLine();
        System.out.println("Enter your password: ");
        pass = sc.nextLine();

        System.out.println("----------GPA Calculator---------");
        System.out.println("Enter your name: ");
        name = sc.nextLine();
        System.out.println("Course duration(in years) : ");
        int num = in.nextInt();
        System.out.println();
        System.out.println();


        if (num <= 4) {
            System.out.println("Congratulations! Registration was completed");

            System.out.println();
            System.out.println();
            System.out.println("-------GPA calculator---------");
            System.out.println();
            System.out.println();

            System.out.println("Hello" + name + " " + ", welcome back!");

        } else {
            System.out.println("Error");
        }


    }
}