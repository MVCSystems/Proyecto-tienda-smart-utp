package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tienda_virtual";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    } 

    // Método de prueba para verificar la conexión a la base de datos
    public static void main(String[] args) {
        try (Connection con = initializeDatabase()) {
            if (con != null) {
                System.out.println("Conexión a la base de datos exitosa.");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}