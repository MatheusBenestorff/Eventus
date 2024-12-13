package controller;

import model.Filme;
import model.FilmeDAO;
import utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.io.IOException;

@WebServlet("/editarFilme")
public class EditarFilmeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        int id = Integer.parseInt(request.getParameter("id"));
        Filme filme = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            FilmeDAO dao = new FilmeDAO(conn);
            filme = dao.buscarPorId(id); 
        } catch (Exception e) {
            e.printStackTrace();
        }

 
        request.setAttribute("filme", filme);
        request.getRequestDispatcher("WEB-INF/editarConteudo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String diretor = request.getParameter("diretor");
        String genero = request.getParameter("genero");
        String cartaz = request.getParameter("cartaz");
        String banner = request.getParameter("banner");

        
        boolean emCartaz = request.getParameter("emCartaz") != null;
        boolean emBreve = request.getParameter("emBreve") != null;
        boolean preEstreia = request.getParameter("preEstreia") != null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            FilmeDAO dao = new FilmeDAO(conn);
            Filme filme = dao.buscarPorId(id); 

          
            filme.setNome(nome);
            filme.setDescricao(descricao);
            filme.setDiretor(diretor);
            filme.setGenero(genero);
            filme.setCartaz(cartaz);
            filme.setBanner(banner);
            filme.setEmCartaz(emCartaz);
            filme.setEmBreve(emBreve);
            filme.setPreEstreia(preEstreia);

            
            dao.atualizarFilme(filme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        response.sendRedirect("gerenciarFilmes");
    }
}
