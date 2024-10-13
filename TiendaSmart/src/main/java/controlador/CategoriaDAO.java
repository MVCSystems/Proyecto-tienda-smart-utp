package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public static List<String[]> listarCategorias() {
        List<String[]> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM categorias";
        try (Connection con = DatabaseConnection.initializeDatabase();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] categoria = new String[2];
                categoria[0] = String.valueOf(rs.getInt("id"));
                categoria[1] = rs.getString("nombre");
                categorias.add(categoria);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}