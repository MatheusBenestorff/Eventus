package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import model.Cinema;
import model.CinemaDAO;
import utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarCinemasServlet", urlPatterns = {"/ListarCinemasServlet"})
public class ListarCinemasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
        
            CinemaDAO cinemaDAO = new CinemaDAO(conn);

            
            String search = request.getParameter("search");
            List<Cinema> cinemas;
            if (search != null && !search.trim().isEmpty()) {
                cinemas = cinemaDAO.buscarCinemas(search); 
            } else {
                cinemas = cinemaDAO.listarCinemas(); 
            }
            request.setAttribute("cinemas", cinemas); 

         
            request.getRequestDispatcher("public/listarCinemas.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar cinemas.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista cinemas.";
    }
}
