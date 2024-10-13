package controlador;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaServlet" ,urlPatterns = "/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listCategorias(request, response);
                    break;
                case "listAjax":
                    listCategoriasAjax(request, response);
                    break;
                default:
                    listCategorias(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String[]> listCategorias = CategoriaDAO.listarCategorias();
        request.setAttribute("listCategorias", listCategorias);
        request.getRequestDispatcher("/categoria-list.jsp").forward(request, response);
    }

    private void listCategoriasAjax(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<String[]> listCategorias = CategoriaDAO.listarCategorias();
        String json = new Gson().toJson(listCategorias);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}