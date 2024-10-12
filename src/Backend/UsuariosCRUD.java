import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosCRUD {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tienda_virtual";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Crear usuario
    public static boolean registrarUsuario(String nombre, String email, String contraseña) {
        String query = "INSERT INTO usuarios (nombre, email, contraseña) VALUES (?, ?, SHA2(?, 256))";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, contraseña);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer usuario por ID
    public static void leerUsuario(int id) {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Email: " + rs.getString("email"));
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar usuario
    public static boolean actualizarUsuario(int id, String nombre, String email, String contraseña) {
        String query = "UPDATE usuarios SET nombre = ?, email = ?, contraseña = SHA2(?, 256) WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, contraseña);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar usuario
    public static boolean eliminarUsuario(int id) {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        
    }
}