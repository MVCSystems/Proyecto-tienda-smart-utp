package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public static List<String[]> listarProductos() {
        List<String[]> productos = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.precio, p.categoria_id " +
                     "FROM productos p " +
                     "JOIN categorias c ON p.categoria_id = c.id";
        try (Connection con = DatabaseConnection.initializeDatabase();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] producto = new String[5];
                producto[0] = String.valueOf(rs.getInt("id"));
                producto[1] = rs.getString("nombre");
                producto[2] = rs.getString("descripcion");
                producto[3] = String.valueOf(rs.getDouble("precio"));
                producto[4] = String.valueOf(rs.getInt("categoria_id")); 
                productos.add(producto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public static String[] obtenerProducto(int id) {
        String[] producto = null;
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.precio, c.nombre AS categoria " +
                     "FROM productos p " +
                     "JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.id = ?";
        try (Connection con = DatabaseConnection.initializeDatabase();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new String[5];
                    producto[0] = String.valueOf(rs.getInt("id"));
                    producto[1] = rs.getString("nombre");
                    producto[2] = rs.getString("descripcion");
                    producto[3] = String.valueOf(rs.getDouble("precio"));
                    producto[4] = String.valueOf(rs.getInt("categoria_id"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public static boolean agregarProducto(String nombre, String descripcion, double precio, int categoria_id) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, categoria_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.initializeDatabase();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setInt(4, categoria_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean actualizarProducto(int id, String nombre, String descripcion, double precio, int categoria_id) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, categoria_id = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.initializeDatabase();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setInt(4, categoria_id);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection con = DatabaseConnection.initializeDatabase();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}

