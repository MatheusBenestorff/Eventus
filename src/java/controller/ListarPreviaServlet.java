package controller;

import java.util.List;
import java.sql.Connection;
import model.FilmeDAO;
import model.Filme;
import model.EventoDAO;
import model.Evento;
import model.CinemaDAO;
import model.Cinema;
import utils.DatabaseConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarPreviaServlet", urlPatterns = {"/ListarPreviaServlet"})
public class ListarPreviaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
          
            FilmeDAO filmeDAO = new FilmeDAO(conn);
            List<Filme> filmes = filmeDAO.listarTodos();
            List<Filme> emCartaz = filmes.stream()
                .filter(Filme::isEmCartaz) 
                .toList();

           
            List<Filme> filmesPreview = emCartaz.stream()
                .limit(6)
                .toList();

            
            EventoDAO eventoDAO = new EventoDAO(conn);
            List<Evento> exposicoes = eventoDAO.listarPorTipo("Exposição");
            List<Evento> exposicoesPreview = exposicoes.stream()
                .limit(6)
                .toList();
            
           
            CinemaDAO cinemaDAO = new CinemaDAO(conn);
            List<Cinema> cinemas = cinemaDAO.listarCinemas().stream()
                .limit(3)
                .toList();

            
            request.setAttribute("filmesPreview", filmesPreview);
            request.setAttribute("exposicoesPreview", exposicoesPreview);
            request.setAttribute("cinemas", cinemas);

           
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar prévia de filmes, exposições e cinemas.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que gera uma prévia de filmes, exposições e cinemas para a página inicial.";
    }
}

