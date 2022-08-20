import java.sql.*;

public class Sql {

    static String url = "jdbc:mysql://localhost:3306/student?createDatabaseIfNotExist=true"; // create schema if it does not exist
    static String userName = "root";
    static String password = "password";

    private static void createTable(Connection connection) throws SQLException {
//        String sql = "DROP TABLE IF EXISTS courseDetails";
        String query = "CREATE TABLE courseDetails (courseCode VARCHAR(15) NOT NULL,credit TINYINT NOT NULL,results VARCHAR(5) NOT NULL DEFAULT 0 ,year MEDIUMINT NOT NULL,sem VARCHAR(10) NOT NULL,PRIMARY KEY (courseCode));";

        Statement statement = connection.createStatement();

//        statement.executeUpdate(sql);

        if (statement.executeUpdate(query) == 0) {
            System.out.println("Table Created.");
        }
    }

    public static double getGPV(String grade) {
        double gpv = switch (grade) {
            case "A+", "A" -> 4.0;
            case "A-" -> 3.7;
            case "B+" -> 3.3;
            case "B" -> 3.0;
            case "B-" -> 2.7;
            case "C+" -> 2.3;
            case "C" -> 2.0;
            case "C-" -> 1.7;
            case "D+" -> 1.3;
            case "D" -> 1.0;
            default -> 0.0;
        };
        return gpv;
    }

    public static int getRowCountOfTable(Connection connection) throws SQLException {
        String sql = "Select " +
                "* " +
                "from courseDetails";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0; //row number
        while (result.next()) {
            count++;
        }
        statement.close();
        return count;
    }

    public static void addData(Connection connection, String courseCode, int courseCredit, String resultLetter, int year, String semester) throws SQLException {
        int prevRowCount = getRowCountOfTable(connection);
        //<editor-fold desc="Add data to a row of a table">
        String sql = "INSERT INTO courseDetails " +
                "(courseCode,credit, results, year, sem) " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, courseCode.toUpperCase().trim());
        statement.setInt(2, courseCredit);
        statement.setString(3, resultLetter.toUpperCase());
        statement.setInt(4, year);
        statement.setString(5, semester.toLowerCase());
        statement.executeUpdate();

        int nowRowCount = getRowCountOfTable(connection);

        if (nowRowCount > prevRowCount) {
            System.out.println("Entry Added.");
        }

        statement.close();
        //</editor-fold>
    }

    public static void deleteData(Connection connection, String courseCode) throws SQLException {
        int prevRowCount = getRowCountOfTable(connection);
        //<editor-fold desc="delete record">
        String sql = "DELETE FROM courseDetails WHERE courseCode=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, courseCode);

        statement.executeUpdate();

        int nowRowCount = getRowCountOfTable(connection);

        if (nowRowCount > prevRowCount) {
            System.out.println("Entry deleted");
        }
        //</editor-fold>
    }

    public static void viewDataAllGPA(Connection connection) throws SQLException {
        //<editor-fold desc="retrieve data from table">
        String sql = "Select * from courseDetails";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int count = 0;
        int totalCredits = 0;
        double GPA = 0;
        while (result.next()) {
            count++;

            String grade = result.getString("results");
            String courseCode = result.getString("courseCode");
            int credit = result.getInt("credit");
            System.out.println("Course code : " + courseCode + " result-" + grade);

            totalCredits += credit;

            double gpv = getGPV(grade);

            GPA = gpv * credit;
        }
        if (count == 0) {
            System.out.println("No records were found.");
        } else {
            GPA = GPA / totalCredits;
        }
        statement.close();
//        </editor-fold>

        GPA = GPA * 100;
        GPA = Math.round(GPA);
        GPA = GPA / 100;

        System.out.println("Your Full GPA:" + GPA);
    }

    public static void viewDataForYear(Connection connection, int year) throws SQLException {
        //<editor-fold desc="retrieve data from table">
        String sql = "Select * from courseDetails WHERE year=" + year;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;
        int totalCredits = 0;
        double GPA = 0;
        while (result.next()) {
            count++;

            String grade = result.getString("results");
            String courseCode = result.getString("courseCode");
            int credit = result.getInt("courseCode");
            System.out.println("Course code : " + courseCode + " result-" + grade);

            totalCredits += credit;

            double gpv = getGPV(grade);

            GPA = gpv * credit;
        }
        if (count == 0) {
            System.out.println("No records were found.");
        } else {
            GPA = GPA / totalCredits;
        }
        statement.close();
//        </editor-fold>
        GPA = GPA * 100;
        GPA = Math.round(GPA);
        GPA = GPA / 100;
        System.out.println("Your for year" + year + " GPA:" + GPA);
    }

    public static void updateOnCourseCode(Connection connection, String courseCode, String newGrade, int newYear, String newSem) throws SQLException {
        //<editor-fold desc="update record">
        String sql = "Select * from courseDetails WHERE courseCode=" + "'" + courseCode + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            String grade = result.getString("results");
            int year = result.getInt("year");
            String sem = result.getString("sem");

            sql = "UPDATE courseDetails SET results=?, year=?, sem=? WHERE courseCode=?";

            if (!newGrade.equals("")) {
                grade = newGrade;
            }
            if (!newSem.equals("")) {
                sem = newSem;
            }
            if (year != 0) {
                year = newYear;
            }

            PreparedStatement updateStatement = connection.prepareStatement(sql);
            updateStatement.setString(1, grade);
            updateStatement.setInt(2, year);
            updateStatement.setString(3, sem);
            updateStatement.setString(4, courseCode);

            int rows = updateStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Row updated");
            }

            updateStatement.close();

        }
        statement.close();
        //</editor-fold>
    }


}
