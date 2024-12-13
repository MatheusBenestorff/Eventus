package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {
    private final Connection conn;

   
    public CinemaDAO(Connection conn) {
        this.conn = conn;
    }

    
   public List<Cinema> listarCinemas() throws SQLException {
    List<Cinema> cinemas = new ArrayList<>();
    String sql = "SELECT * FROM cinemas";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Cinema cinema = new Cinema();
            cinema.setId(rs.getInt("id"));
            cinema.setNome(rs.getString("nome"));
            cinema.setRua(rs.getString("rua"));
            cinema.setNumero(rs.getInt("numero"));
            cinema.setCidade(rs.getString("cidade"));
            cinema.setEstado(rs.getString("estado"));
            cinemas.add(cinema);
        }
    }
    return cinemas;
}


    
    public List<Cinema> buscarCinemas(String search) throws SQLException {
    List<Cinema> cinemas = new ArrayList<>();
    String sql = "SELECT * FROM cinemas WHERE nome LIKE ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + search + "%");
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cinema cinema = new Cinema();
                cinema.setId(rs.getInt("id"));
                cinema.setNome(rs.getString("nome"));
                cinema.setRua(rs.getString("rua"));
                cinema.setNumero(rs.getInt("numero"));
                cinema.setCidade(rs.getString("cidade"));
                cinema.setEstado(rs.getString("estado"));
                cinemas.add(cinema);
            }
        }
    }
    return cinemas;
}


    
    public List<Cinema> listarCinemasPorFilme(int idFilme) {
    List<Cinema> cinemas = new ArrayList<>();
    String sql = """
        SELECT DISTINCT c.id, c.nome, c.rua, c.numero, c.cidade, c.estado
        FROM cinemas c
        INNER JOIN sessoes s ON c.id = s.id_cinema
        WHERE s.id_filme = ?
    """;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idFilme);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cinema cinema = new Cinema(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                
                List<Sessao> sessoes = listarSessoesPorCinemaEFilme(cinema.getId(), idFilme);
                cinema.setSessoes(sessoes);

                cinemas.add(cinema);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return cinemas;
}


    
   private List<Sessao> listarSessoesPorCinemaEFilme(int idCinema, int idFilme) {
    List<Sessao> sessoes = new ArrayList<>();
    String sql = """
        SELECT id, id_filme, id_cinema, horario, tipo 
        FROM sessoes 
        WHERE id_cinema = ? AND id_filme = ?
    """;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCinema);
        stmt.setInt(2, idFilme);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sessao sessao = new Sessao();
                sessao.setId(rs.getInt("id"));
                sessao.setIdFilme(rs.getInt("id_filme"));
                sessao.setIdCinema(rs.getInt("id_cinema"));
                sessao.setHorario(rs.getString("horario"));
                sessao.setTipo(rs.getString("tipo"));
                sessoes.add(sessao);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return sessoes;
}
  
public Cinema buscarPorId(int id) throws SQLException {
    Cinema cinema = null;
    String sql = "SELECT * FROM cinemas WHERE id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                cinema = new Cinema();
                cinema.setId(rs.getInt("id"));
                cinema.setNome(rs.getString("nome"));
                cinema.setRua(rs.getString("rua"));
                cinema.setNumero(rs.getInt("numero"));
                cinema.setCidade(rs.getString("cidade"));
                cinema.setEstado(rs.getString("estado"));
                
                
                List<Sessao> sessoes = listarSessoesPorCinema(cinema.getId());
                cinema.setSessoes(sessoes);
            }
        }
    }
    return cinema;
}


private List<Sessao> listarSessoesPorCinema(int idCinema) {
    List<Sessao> sessoes = new ArrayList<>();
    String sql = """
        SELECT id, id_filme, id_cinema, horario, tipo 
        FROM sessoes 
        WHERE id_cinema = ?
    """;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCinema);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sessao sessao = new Sessao();
                sessao.setId(rs.getInt("id"));
                sessao.setIdFilme(rs.getInt("id_filme"));
                sessao.setIdCinema(rs.getInt("id_cinema"));
                sessao.setHorario(rs.getString("horario"));
                sessao.setTipo(rs.getString("tipo"));
                sessoes.add(sessao);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return sessoes;
}
 public boolean adicionarCinema(Cinema cinema) throws SQLException {
        String sql = "INSERT INTO cinemas (nome, rua, numero, cidade, estado) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cinema.getNome());
            stmt.setString(2, cinema.getRua());
            stmt.setInt(3, cinema.getNumero());
            stmt.setString(4, cinema.getCidade());
            stmt.setString(5, cinema.getEstado());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        }
    }


}
