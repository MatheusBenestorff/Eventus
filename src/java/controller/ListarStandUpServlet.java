package controller;

import java.util.List;
import java.sql.Connection;
import model.EventoDAO;
import model.Evento;
import utils.DatabaseConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarStandUpServlet", urlPatterns = {"/ListarStandUpServlet"})
public class ListarStandUpServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            EventoDAO eventoDAO = new EventoDAO(conn);
            
            List<Evento> standUpEventos = eventoDAO.listarPorTipo("Stand-up");
            
            request.setAttribute("eventos", standUpEventos);
            

            request.getRequestDispatcher("public/standUpEventos.jsp").forward(request, response);
        } catch (Exception e) {

            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar eventos Stand-up.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista apenas eventos do tipo Stand-up.";
    }
}
