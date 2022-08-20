import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Add {
    public static void add(String name) {
        Scanner pl = new Scanner(System.in);
        // Scanner ye = new Scanner(System.in);

        System.out.println("-------Add result-------");
        System.out.print("Year(1,2,3,4): ");
        int year = pl.nextInt();
        System.out.print("Grade: ");
        pl.nextLine();
        String grade = pl.nextLine();
        System.out.print("Course Code: ");
        String courseCode = pl.nextLine();
        System.out.print("Credits: ");
        int credit = pl.nextInt();
        System.out.print("\nConfirm (y/n): ");
        pl.nextLine();
        String yn = pl.nextLine();
        System.out.println();
        System.out.println();

        if (year >= 1 && year <= 4 && Sql.getGPV(grade) != 0 && yn.equals("y")) {
            System.out.println("Record was added!");
            Connection connection;
            try {
                connection = DriverManager.getConnection(Sql.url, Sql.userName, Sql.password);
                System.out.println("Database connected");
                Sql.addData(connection, courseCode, credit, grade.toUpperCase(), year, "sem");
                connection.close();

            } catch (SQLException e) {
                System.out.println("Unable to connect to database");
                e.printStackTrace();
            }
        } else if (yn.equals("n")) {
            Add.add(name);
        } else {
            System.out.println("Error");
            MainMenu.UI(name);
        }
    }
}
