package controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Evento;
import model.Filme;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DatabaseConnection;

@WebServlet("/adicionarConteudo")
public class AdicionarConteudoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String tipoConteudo = request.getParameter("tipoConteudo"); 
    String mensagem;

    if ("filme".equals(tipoConteudo)) {
        mensagem = adicionarFilme(request);
        
        List<Filme> filmes = listarFilmes();
        request.setAttribute("filmes", filmes);
        request.setAttribute("mensagem", mensagem);
        
        request.getRequestDispatcher("WEB-INF/adicionarFilme.jsp").forward(request, response);
    } else if ("evento".equals(tipoConteudo)) {
        mensagem = adicionarEvento(request);
      
        List<Evento> eventos = listarEventos();
        request.setAttribute("eventos", eventos);
        request.setAttribute("mensagem", mensagem);
       
        request.getRequestDispatcher("WEB-INF/adicionarEvento.jsp").forward(request, response);
    } else {
        request.setAttribute("mensagem", "Tipo de conteúdo inválido!");
    }
}


   private String adicionarFilme(HttpServletRequest request) {
    String nome = request.getParameter("nome");
    String descricao = request.getParameter("descricao");
    String diretor = request.getParameter("diretor");
    String genero = request.getParameter("genero");
    String duracaoStr = request.getParameter("duracao");
    String dataEstreiaStr = request.getParameter("dataEstreia");
    boolean emCartaz = "on".equals(request.getParameter("emCartaz"));
    boolean emBreve = "on".equals(request.getParameter("emBreve"));
    boolean preEstreia = "on".equals(request.getParameter("preEstreia"));
    String cartaz = request.getParameter("cartaz");
    String banner = request.getParameter("banner");

    
    int duracao = 0;  
    if (duracaoStr != null && !duracaoStr.isEmpty()) {
        try {
            duracao = Integer.parseInt(duracaoStr);
        } catch (NumberFormatException e) {
            
            return "Erro: duração inválida!";
        }
    }

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "INSERT INTO filmes (nome, descricao, diretor, genero, duracao, data_estreia, cartaz, banner, em_cartaz, em_breve, pre_estreia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

        stmt.setString(1, nome);
        stmt.setString(2, descricao);
        stmt.setString(3, diretor);
        stmt.setString(4, genero);
        stmt.setInt(5, duracao); 
        stmt.setDate(6, dataEstreiaStr != null ? Date.valueOf(dataEstreiaStr) : null);
        stmt.setString(7, cartaz);
        stmt.setString(8, banner);
        stmt.setBoolean(9, emCartaz);
        stmt.setBoolean(10, emBreve);
        stmt.setBoolean(11, preEstreia);

        stmt.executeUpdate();
        return "Filme adicionado com sucesso!";
    } catch (SQLException e) {
        e.printStackTrace();
        return "Erro ao adicionar o filme: " + e.getMessage();
    }
}


    private String adicionarEvento(HttpServletRequest request) {
    String nome = request.getParameter("nomeEvento");
    String descricao = request.getParameter("descricaoEvento");
    String cartaz = request.getParameter("cartaz");

  
    Evento evento = new Evento();
    evento.setNome(nome);
    evento.setDescricao(descricao);
    evento.setCartaz(cartaz);


    boolean isStandUp = request.getParameter("standUp") != null;
    boolean isFestival = request.getParameter("festival") != null;
    boolean isExposicao = request.getParameter("exposicao") != null;

   
    evento.setStandUp(isStandUp);
    evento.setFestival(isFestival);
    evento.setExposicao(isExposicao);

    
    String tipo = "";
    if (isStandUp) {
        tipo = "Stand-up";
    } else if (isFestival) {
        tipo = "Festival";
    } else if (isExposicao) {
        tipo = "Exposição";
    } else {
        tipo = "Outro"; 
    }

    evento.setTipo(tipo); 

    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "INSERT INTO eventos (nome, descricao, tipo, isStandUp, isFestival, isExposicao, cartaz) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

        
        stmt.setString(1, evento.getNome());
        stmt.setString(2, evento.getDescricao());
        stmt.setString(3, evento.getTipo());  
        stmt.setBoolean(4, evento.isStandUp());
        stmt.setBoolean(5, evento.isFestival());
        stmt.setBoolean(6, evento.isExposicao());
        stmt.setString(7, evento.getCartaz());

        
        stmt.executeUpdate();
        return "Evento adicionado com sucesso!";
    } catch (SQLException e) {
        e.printStackTrace();
        return "Erro ao adicionar o evento: " + e.getMessage();
    }
}




    private List<Filme> listarFilmes() {
        List<Filme> filmes = new ArrayList<>();
        String query = "SELECT * FROM filmes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setGenero(rs.getString("genero"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setCartaz(rs.getString("cartaz"));
                filme.setEmCartaz(rs.getBoolean("em_cartaz"));
                filme.setEmBreve(rs.getBoolean("em_breve"));
                filme.setPreEstreia(rs.getBoolean("pre_estreia"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmes;
    }

    private List<Evento> listarEventos() {
        List<Evento> eventos = new ArrayList<>();
        String query = "SELECT * FROM eventos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getInt("id"));
                evento.setNome(rs.getString("nome"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setTipo(rs.getString("tipo"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }
}
