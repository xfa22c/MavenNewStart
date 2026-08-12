package dgf.xfa22c.jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBCTest {

    static Scanner sc = new Scanner(System.in);
    static int step = 1;
    public static void main(String[] args) {



        String url = DatabaseConfig.getUrl();
        String user = DatabaseConfig.getUser();
        String password = DatabaseConfig.getPassword();

        try(Connection conn = DriverManager.getConnection(url, user, password)){

            while (step == 1) {

            menu();
            switch (getInt()){
                case 1 -> {
                    select(conn);
                    again();
                }

                case 2 ->{
                    insert(conn);
                    again();
                }

                case 3 ->{
                    update(conn);
                    again();
                }

                case 4 ->{
                    delete(conn);
                    again();
                }

                case 5 ->{
                    logs(conn);
                    again();
                }

                case 0 ->{
                    step = 0;
                    return;
                }

                default -> {
                    System.err.println("\n Ты Слепой? ");
                    return;
                }

            }

            }

        }catch (SQLException e){
            System.err.println("Connection went wrong " + e.getMessage());
        }


    }

    public static void menu(){
        System.out.println("\n--Select action for users table--");
        System.out.println("1. SELECT    2. INSERT ");
        System.out.println("3. UPDATE    4. DELETE");
        System.out.println("5. Logs");
        System.out.println("0. Exit");
    }

    // SELECT SQL command

    public static void select(Connection conn) throws SQLException {
        String selectSQL = "SELECT * FROM users";
        try(Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(selectSQL)){
            while (rs.next()) {
                System.out.println("\nID - " + rs.getInt("id") + " | name - "
                        + rs.getString("name")
                        + " | " + rs.getTimestamp("created_at")
                );
            }
        }
    }

    public static void logs(Connection conn){
        String selectLogs = "SELECT * FROM logs";
        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectLogs)){
            while (rs.next()){
                System.out.println("\nlog ID - " + rs.getInt("id") + " | user ID - "
                        + rs.getInt("user_id") + " | action - " + rs.getString("action"));
            }
        }catch (SQLException e){
            System.out.println("Statement creation exception " +e.getMessage());
        }
    }




    // INSERT SQL command

        public static void insert(Connection conn) throws SQLException {
            String insertSQL = "INSERT INTO users (name) VALUES (?) RETURNING id";
            String insertLog = "INSERT INTO logs (action, user_id) VALUES (?, ?)";

            conn.setAutoCommit(false);

            try (PreparedStatement pstmtUser =  conn.prepareStatement(insertSQL);
                 PreparedStatement pstmtLog = conn.prepareStatement(insertLog)){
                String userName = sc.next();
                pstmtUser.setString(1, userName);
                ResultSet rs = pstmtUser.executeQuery();
                rs.next();
                int userID = rs.getInt(1);

                pstmtLog.setString(1, "User Added ");
                pstmtLog.setInt(2, userID);
                pstmtLog.executeUpdate();

                conn.commit();
                System.out.println("Added new user, log created");
            }catch (SQLException e){
                conn.rollback();
                System.err.println("Transaction failed " + e.getMessage());
            }finally {
                conn.setAutoCommit(true);
            }
        }





//                      UPDATE SQL command
        public static void update(Connection conn) throws SQLException {
            String updateSQL = "UPDATE users SET name = ? WHERE id = ?";

            conn.setAutoCommit(false);

            try(PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

                System.out.println("Enter new user Name");
                String newName = sc.next();
                System.out.println("Choose ID");
                select(conn);
                int chosenId = getInt();
                pstmt.setString(1, newName);
                pstmt.setInt(2, chosenId);
                int rows = pstmt.executeUpdate();
                if (rows != 0 ){
                    System.out.println("Rows updated " + rows);
                    conn.commit();
                }else{
                    conn.rollback();
                    System.err.println("ID not found, update didn't happen");
                }

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction failed " + e.getMessage());
            }finally {
                conn.setAutoCommit(true);
            }

        }




    //DELETE SQL Command
        public static void delete(Connection conn) throws SQLException {
            String deleteSQL = "DELETE FROM users WHERE id = ?";

            conn.setAutoCommit(false);

            try(PreparedStatement pstmt = conn.prepareStatement(deleteSQL)){

                System.out.println("Choose ID");
                select(conn);
                int chosenID = getInt();
                pstmt.setInt(1, chosenID);
                int rows = pstmt.executeUpdate();
                if (rows != 0){
                    System.out.println("Rows deleted " + rows);
                    conn.commit();
                }else{
                    conn.rollback();
                    System.err.println("ID not found, no one's deleted");
                }

            }catch (SQLException e){
                conn.rollback();
                System.out.println("Transaction failed " + e.getMessage());
            }finally {
                conn.setAutoCommit(true);
            }


        }




    public static int getInt(){
        while(!sc.hasNextInt()){
            String input = sc.next();
            System.err.println("В жопу свой '" + input + "' засунь, ладно?");
        }
        return sc.nextInt();
    }

    public static void again(){
        System.out.println("\nAnother action?");
        System.out.println("1. Yes   2. No");
        step = (getInt() == 1) ? 1 : 0;
    }

}
