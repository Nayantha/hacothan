import java.sql.*;

public class ViewData {
    public static void viewData() {
        System.out.println("-------View Result-------");

        Connection connection;
        try {
            connection = DriverManager.getConnection(Sql.url, Sql.userName, Sql.password);

            String sql = "Select * from courseDetails";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;
            int totalCredits = 0;
            double GPA = 0;
            while (result.next()) {
                count++;
                String grade = result.getString("results");
                int credit = result.getInt("credit");
                totalCredits += credit;
                double gpv = Sql.getGPV(grade);
                GPA += gpv * credit;
            }
            if (count == 0) {
                System.out.println("No records were found.");
            } else {
                GPA = GPA / totalCredits;
            }
            statement.close();

            GPA = GPA * 100;
            GPA = Math.round(GPA);
            GPA = GPA / 100;
            connection.close();

            System.out.println("Current GPA: " + GPA);
            System.out.println("No. of Subjects: " + count);
            System.out.println("Total credits: " + totalCredits);
            System.out.println("To View Data: ");

            for (int i = 0; i < 4; i++) {
                System.out.println((i + 1) + ".  Year " + (i + 1));
            }

            System.out.println("0 to go back");

        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        viewData();
    }
}
