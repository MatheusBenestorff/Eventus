package controller;

import java.sql.Connection;
import java.util.List;
import model.Evento;
import model.EventoDAO;
import utils.DatabaseConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DetalhesEventoServlet", urlPatterns = {"/DetalhesEventoServlet"})
public class DetalhesEventoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                int id = Integer.parseInt(idParam);
                EventoDAO eventoDAO = new EventoDAO(conn);
                Evento evento = eventoDAO.buscarPorId(id);

                if (evento != null) {
                   
                    request.setAttribute("evento", evento);

                    
                    request.getRequestDispatcher("public/detalhesEvento.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Evento não encontrado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar detalhes do evento.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do evento não fornecido.");
        }
    }
}
