package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

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
    
    public static void createNewTable() {
        
        String url = "jdbc:sqlite:PEOPLE.db";
        // Instrucción SQL para crear nueva tabla
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " Mail text NOT NULL);";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void fillTable(){
        String url = "jdbc:sqlite:PEOPLE.db";
        String path = "C:\\Users\\Eli\\Documents\\NetBeansProjects\\Kata5P1\\email.txt";
        
        List<String> emails = MailListReader.read(path);
        
        try(Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            String insert = "INSERT INTO EMAIL(Mail) VALUES(?)";
            PreparedStatement pstmt= conn.prepareStatement(insert);
            
            for (String email : emails) {
                pstmt.setString(1, email);
                pstmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Kata5P1 app = new Kata5P1();
        app.selectAll();
        app.fillTable();
    }    
}
