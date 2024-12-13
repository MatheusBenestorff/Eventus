package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;
import model.Filme;
import model.FilmeDAO;
import utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarPreEstreiaServlet", urlPatterns = {"/ListarPreEstreiaServlet"})
public class ListarPreEstreiaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            FilmeDAO filmeDAO = new FilmeDAO(conn);

            
            String search = request.getParameter("search");
            List<Filme> filmes;

            if (search != null && !search.trim().isEmpty()) {
                
                filmes = filmeDAO.buscarFilmes(search).stream()
                        .filter(Filme::isPreEstreia)
                        .collect(Collectors.toList());
            } else {
                
                filmes = filmeDAO.listarTodos().stream()
                        .filter(Filme::isPreEstreia)
                        .collect(Collectors.toList());
            }

            request.setAttribute("filmes", filmes);
            request.getRequestDispatcher("public/preEstreia.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar filmes em pré-estreia.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista filmes em pré-estreia com suporte a busca por nome.";
    }
}
