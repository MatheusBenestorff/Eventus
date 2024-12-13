package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import model.Cinema;
import model.CinemaDAO;
import model.Sessao;
import model.SessaoDAO;
import utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarSessoesServlet", urlPatterns = {"/ListarSessoesServlet"})
public class ListarSessoesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            CinemaDAO cinemaDAO = new CinemaDAO(conn);
            SessaoDAO sessaoDAO = new SessaoDAO(conn);

           
            String search = request.getParameter("search");
            List<Cinema> cinemas;
            if (search != null && !search.trim().isEmpty()) {
                cinemas = cinemaDAO.buscarCinemas(search);
            } else {
                cinemas = cinemaDAO.listarCinemas();
            }
            request.setAttribute("cinemas", cinemas);

           
            String filmeIdParam = request.getParameter("filmeId");
            String cinemaIdParam = request.getParameter("cinemaId");
            String dataParam = request.getParameter("data");

            List<Sessao> sessoes = null;

            try {
                
                if (filmeIdParam != null && !filmeIdParam.isEmpty() &&
                    cinemaIdParam != null && !cinemaIdParam.isEmpty() &&
                    dataParam != null && !dataParam.isEmpty()) {
                    
                  
                    int filmeId = Integer.parseInt(filmeIdParam);
                    int cinemaId = Integer.parseInt(cinemaIdParam);
                    sessoes = sessaoDAO.listarSessoesPorFilmeCinemaEData(filmeId, cinemaId, dataParam);

                } else if (filmeIdParam != null && !filmeIdParam.isEmpty()) {
                    int filmeId = Integer.parseInt(filmeIdParam);
                    sessoes = sessaoDAO.listarSessoesPorFilme(filmeId);

                } else if (cinemaIdParam != null && !cinemaIdParam.isEmpty()) {
                    int cinemaId = Integer.parseInt(cinemaIdParam);
                    sessoes = sessaoDAO.listarSessoesPorCinema(cinemaId);

                } else if (dataParam != null && !dataParam.isEmpty()) {
                    
                    sessoes = sessaoDAO.listarSessoesPorData(dataParam);
                }

              
                Map<String, List<Sessao>> sessoesPorData = new LinkedHashMap<>();
                if (sessoes != null) {
                    for (Sessao sessao : sessoes) {
                        String data = sessao.getData();
                        sessoesPorData.computeIfAbsent(data, k -> new ArrayList<>()).add(sessao);
                    }
                }
                request.setAttribute("sessoesPorData", sessoesPorData);

            } catch (NumberFormatException e) {
                System.err.println("Erro nos parâmetros: " + e.getMessage());
                request.setAttribute("sessoesPorData", null);
            }

      
            request.getRequestDispatcher("public/detalhesFilme.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar cinemas e sessões.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista cinemas e exibe sessões associadas aos filmes, cinemas e/ou por data.";
    }
}
