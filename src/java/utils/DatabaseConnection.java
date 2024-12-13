package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/eventusdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "T@rff685685";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";  

    public static Connection getConnection() {
    try {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException e) {
        System.err.println("Erro: Driver JDBC não encontrado.");
        e.printStackTrace();
        throw new RuntimeException("Driver JDBC não encontrado.");
    } catch (SQLException e) {
        System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Erro na conexão com o banco de dados.");
    }
}

    public static void main(String[] args) {
     
        getConnection();
    }
}
