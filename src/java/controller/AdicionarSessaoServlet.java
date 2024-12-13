package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Cinema;
import model.Filme;
import model.CinemaDAO;
import model.FilmeDAO;
import model.Sessao;
import model.SessaoDAO;
import utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adicionarSessao")
public class AdicionarSessaoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            FilmeDAO filmeDAO = new FilmeDAO(conn);
            CinemaDAO cinemaDAO = new CinemaDAO(conn);
            SessaoDAO sessaoDAO = new SessaoDAO(conn);

            
            String mensagem = adicionarSessao(request, sessaoDAO);

           
            List<Filme> filmes = filmeDAO.listarTodos();
            List<Cinema> cinemas = cinemaDAO.listarCinemas();

            
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("filmes", filmes);
            request.setAttribute("cinemas", cinemas);

            
            request.getRequestDispatcher("WEB-INF/adicionarSessao.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
        }
    }

    private String adicionarSessao(HttpServletRequest request, SessaoDAO sessaoDAO) {
        String mensagem = ""; 

     
        String idFilmeStr = request.getParameter("idFilme");
        String idCinemaStr = request.getParameter("idCinema");
        String horario = request.getParameter("horario");
        String tipo = request.getParameter("tipo");
        String data = request.getParameter("data");

        
        if (idFilmeStr != null && idCinemaStr != null && horario != null && tipo != null && data != null) {
            try {
               
                int idFilme = Integer.parseInt(idFilmeStr);
                int idCinema = Integer.parseInt(idCinemaStr);

                
                Sessao sessao = new Sessao();
                sessao.setIdFilme(idFilme);
                sessao.setIdCinema(idCinema);
                sessao.setHorario(horario);
                sessao.setTipo(tipo);
                sessao.setData(data);

                sessaoDAO.adicionarSessao(sessao);
                mensagem = "Sessão adicionada com sucesso!";
                

            } catch (SQLException e) {
                e.printStackTrace();
                mensagem = "Erro ao adicionar a sessão: " + e.getMessage();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                mensagem = "Erro ao processar os dados: " + e.getMessage();
            }
        } 

        return mensagem;
    }
}
