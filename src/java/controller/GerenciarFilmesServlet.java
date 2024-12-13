package controller;

import model.Filme;
import model.FilmeDAO;
import utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gerenciarFilmes")
public class GerenciarFilmesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        List<Filme> filmes = new ArrayList<>(); 

        try (Connection conn = DatabaseConnection.getConnection()) {
            FilmeDAO dao = new FilmeDAO(conn);

            if (idStr != null && !idStr.trim().isEmpty()) {
                
                int id = Integer.parseInt(idStr);
                Filme filme = dao.buscarPorId(id);
                if (filme != null) {
                    filmes.add(filme); 
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

     
        request.setAttribute("filmes", filmes);
        request.getRequestDispatcher("WEB-INF/gerenciarFilmes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String idStr = request.getParameter("id");

        if (acao != null && idStr != null) {
            int id = Integer.parseInt(idStr);

            try (Connection conn = DatabaseConnection.getConnection()) {
                FilmeDAO dao = new FilmeDAO(conn);

                if ("excluir".equals(acao)) {
                    
                    dao.excluirFilme(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
        response.sendRedirect("gerenciarFilmes");
    }
}
