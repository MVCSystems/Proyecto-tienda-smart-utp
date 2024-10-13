package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String contrasenha = request.getParameter("contrasenha");

        // Agregar mensajes de depuración
        System.out.println("Email recibido: " + email);
        System.out.println("Contraseña recibida: " + contrasenha);

        if (email == null || contrasenha == null || email.isEmpty() || contrasenha.isEmpty()) {
            request.setAttribute("message", "Email y Password no pueden ser null");
            request.getRequestDispatcher("/login/login.jsp").forward(request, response);
            return;
        }

        boolean isValid = LoginDao.validate(email, contrasenha);

        if (isValid) {
            response.sendRedirect(request.getContextPath() + "/admin/principal.jsp");
        } else {
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("/login/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}