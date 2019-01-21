package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {
    
 private Connection connect() {

        String url = "jdbc:sqlite:PEOPLE.db";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t" +
                                rs.getString("Name") + "\t" +
                                rs.getString("Apellidos") + "\t" +
                                rs.getString("Departamento") + "\t");    
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Kata5P1 app = new Kata5P1();
        app.selectAll();
    }    
}
