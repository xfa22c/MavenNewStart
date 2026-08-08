package dgf.xfa22c.jdbc;

import java.sql.*;

public class JDBCTest {

    public static void main(String[] args) {

        String url = DatabaseConfig.getUrl();
        String user = DatabaseConfig.getUser();
        String password = DatabaseConfig.getPassword();

                    // SELECT SQL command
        try(Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users")){

            System.out.println("Connected");

                while(rs.next()){
                    System.out.println("ID - " + rs.getInt("id") + " | name - "
                            + rs.getString("name")
                            + " | " + rs.getTimestamp("created_at")
                    );
                }

        } catch (SQLException e) {
            System.err.println("Something went wrong - SQL connection error " + e.getMessage());
        }


                    // INSERT SQL command
//        String insertSQL = "INSERT INTO users (name) VALUES (?)";
//        try(Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement pstmt =  conn.prepareStatement(insertSQL)
//        ){
//           pstmt.setString(1, "New User");
//           int rows = pstmt.executeUpdate();
//            System.out.println("Added rows - " + rows);
//        } catch (SQLException e) {
//            System.err.println("Something went wrong - SQL connection error " + e.getMessage());
//        }

                      //UPDATE SQL command
//        String updateSQL = "UPDATE users SET name = ? WHERE id = ?";
//        try(Connection conn = DriverManager.getConnection(url, user, password);
//        PreparedStatement pstmt = conn.prepareStatement(updateSQL)
//        ) {
//            pstmt.setString(1, "UpdateTest");
//            pstmt.setInt(2, 9);
//            int rows = pstmt.executeUpdate();
//            if (rows != 0 ){
//                System.out.println("Rows deleted " + rows);
//            }else{
//                System.err.println("ID not found, update didn't happen");
//            }
//
//        }catch (SQLException e){
//            System.err.println("SQL connection error " + e.getMessage());
//        }

                    //DELETE SQL command
//        String deleteSQL = "DELETE FROM users WHERE id = ?";
//        try(Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)
//        ){
//            pstmt.setInt(1, 9);
//            int rows = pstmt.executeUpdate();
//            if (rows != 0){
//                System.out.println("Rows deleted " + rows);
//            }else{
//                System.err.println("ID not found, no one's deleted");
//            }
//
//        }catch (SQLException e){
//            System.err.println("SQL Connection error " + e.getMessage());
//        }


    }

}
