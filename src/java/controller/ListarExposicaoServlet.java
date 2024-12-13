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

@WebServlet(name = "ListarExposicaoServlet", urlPatterns = {"/ListarExposicaoServlet"})
public class ListarExposicaoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {

            EventoDAO eventoDAO = new EventoDAO(conn);

            List<Evento> exposicaoEventos = eventoDAO.listarPorTipo("Exposição");

            request.setAttribute("eventos", exposicaoEventos);


            request.getRequestDispatcher("public/exposicaoEventos.jsp").forward(request, response);
        } catch (Exception e) {

            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar eventos Exposição.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista apenas eventos do tipo Exposição.";
    }
}
