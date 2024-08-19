package app2;

import java.sql.*;  

class MysqlCon{  
    public static void main(String[] args) {
        String host = "jdbc:mysql://localhost:3306/agencija za fizicko-tehnicko obezbedjenje - evidencija dezurstav";
        String username = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database");
            e.printStackTrace();
        }
        
    }

    public MysqlCon() {
    }
}  
