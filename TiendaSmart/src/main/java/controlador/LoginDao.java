package controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public static boolean validate(String email, String contrasenha) {
        boolean status = false;
        try {
            String query = "SELECT contrasenha FROM usuarios WHERE email = ?";
            try (Connection con = DatabaseConnection.initializeDatabase();
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("contrasenha");
                        String hashedInputPassword = hashPasswordSHA256(contrasenha);
                        if (storedHash.equals(hashedInputPassword)) {
                            status = true;
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return status;
    }

    private static String hashPasswordSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Clase de prueba para verificar el inicio de sesión
    public static void main(String[] args) {
        String email = "juan.perez@example.com"; // Reemplaza con tu email
        String contrasenha = "123456"; // Reemplaza con tu clave

        boolean isValid = validate(email, contrasenha);

        if (isValid) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Email o clave incorrectos.");
        }
    }
}