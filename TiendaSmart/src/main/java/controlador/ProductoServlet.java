package controlador;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet" , urlPatterns = "/ProductoServlet") 
public class ProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertProducto(request, response);
                    break;
                case "delete":
                    deleteProducto(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProducto(request, response);
                    break;
                default:
                    listProductos(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String[]> listProductos = ProductoDAO.listarProductos();
        request.setAttribute("listProductos", listProductos);
        request.getRequestDispatcher("/admin/producto-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String[]> listCategorias = CategoriaDAO.listarCategorias();
        request.setAttribute("listCategorias", listCategorias);
        request.getRequestDispatcher("/admin/producto-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String[] existingProducto = ProductoDAO.obtenerProducto(id);
        List<String[]> listCategorias = CategoriaDAO.listarCategorias();
        request.setAttribute("producto", existingProducto);
        request.setAttribute("listCategorias", listCategorias);
        request.getRequestDispatcher("/admin/producto-form.jsp").forward(request, response);
    }

    private void insertProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int categoriaId = Integer.parseInt(request.getParameter("categoria_id"));

        ProductoDAO.agregarProducto(nombre, descripcion, precio, categoriaId);
        response.sendRedirect("ProductoServlet");
    }

    private void updateProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del producto es requerido");
            return;
        }
        int id = Integer.parseInt(idStr);

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int categoriaId = Integer.parseInt(request.getParameter("categoria_id"));

        ProductoDAO.actualizarProducto(id, nombre, descripcion, precio, categoriaId);
        response.sendRedirect("ProductoServlet");
    }

    private void deleteProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del producto es requerido");
            return;
        }
        int id = Integer.parseInt(idStr);

        ProductoDAO.eliminarProducto(id);
        response.sendRedirect("ProductoServlet");
    }
}