package controller;

import java.io.IOException;
import java.sql.*;
import model.Cinema;
import model.CinemaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DatabaseConnection;

@WebServlet("/adicionarCinema")
public class AdicionarCinemaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DatabaseConnection.getConnection()) {
           
            CinemaDAO cinemaDAO = new CinemaDAO(conn);

            
            String mensagem = adicionarCinema(request, cinemaDAO);

            
            request.setAttribute("mensagem", mensagem);

            
            request.getRequestDispatcher("WEB-INF/adicionarCinema.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
        }
    }

    private String adicionarCinema(HttpServletRequest request, CinemaDAO cinemaDAO) {
        String mensagem = "";  

        
        String nome = request.getParameter("nome");
        String rua = request.getParameter("rua");
        String numeroStr = request.getParameter("numero");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

       
        if (nome != null && rua != null && numeroStr != null && cidade != null && estado != null) {
            try {
       
                int numero = Integer.parseInt(numeroStr);

            
                Cinema cinema = new Cinema();
                cinema.setNome(nome);
                cinema.setRua(rua);
                cinema.setNumero(numero);
                cinema.setCidade(cidade);
                cinema.setEstado(estado);

        
                cinemaDAO.adicionarCinema(cinema);
                mensagem = "Cinema adicionado com sucesso!";

            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
                mensagem = "Erro ao adicionar cinema: " + e.getMessage();
            }
        } 
        return mensagem;
    }
}
