package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {
    private final Connection connection;

    
    public SessaoDAO(Connection connection) {
        this.connection = connection;
    }

    
    public List<Sessao> listarSessoesPorFilme(int filmeId) throws SQLException {
        String sql = """
            SELECT 
                s.id AS sessao_id, s.horario, s.tipo, s.data,
                c.id AS cinema_id, c.nome AS cinema_nome, 
                c.rua AS cinema_rua, c.numero AS cinema_numero, 
                c.cidade AS cinema_cidade, c.estado AS cinema_estado
            FROM sessoes s
            JOIN cinemas c ON s.id_cinema = c.id
            WHERE s.id_filme = ?
            ORDER BY s.horario
        """;

        List<Sessao> sessoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, filmeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sessao sessao = criarSessaoComDetalhes(rs);
                    sessoes.add(sessao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return sessoes;
    }

  
    public List<Sessao> listarSessoesPorCinema(int cinemaId) throws SQLException {
        String sql = """
            SELECT 
                s.id AS sessao_id, s.horario, s.tipo, s.data,
                c.id AS cinema_id, c.nome AS cinema_nome, 
                c.rua AS cinema_rua, c.numero AS cinema_numero, 
                c.cidade AS cinema_cidade, c.estado AS cinema_estado
            FROM sessoes s
            JOIN cinemas c ON s.id_cinema = c.id
            WHERE s.id_cinema = ?
            ORDER BY s.horario
        """;

        List<Sessao> sessoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cinemaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sessao sessao = criarSessaoComDetalhes(rs);
                    sessoes.add(sessao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return sessoes;
    }

    
    public List<Sessao> listarSessoesPorData(String data) throws SQLException {
        String sql = """
            SELECT 
                s.id AS sessao_id, s.horario, s.tipo, s.data,
                c.id AS cinema_id, c.nome AS cinema_nome, 
                c.rua AS cinema_rua, c.numero AS cinema_numero, 
                c.cidade AS cinema_cidade, c.estado AS cinema_estado,
                f.id AS filme_id, f.nome AS filme_nome, f.duracao, f.genero, f.cartaz
            FROM sessoes s
            JOIN cinemas c ON s.id_cinema = c.id
            JOIN filmes f ON s.id_filme = f.id
            WHERE s.data = ?
            ORDER BY s.horario
        """;

        List<Sessao> sessoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, data);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sessao sessao = criarSessaoComFilmeEDetalhes(rs);
                    sessoes.add(sessao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return sessoes;
    }

   
    public List<Sessao> listarSessoesPorFilmeCinemaEData(int filmeId, int cinemaId, String data) throws SQLException {
        String sql = """
            SELECT 
                s.id AS sessao_id, s.horario, s.tipo, s.data,
                c.id AS cinema_id, c.nome AS cinema_nome, 
                c.rua AS cinema_rua, c.numero AS cinema_numero, 
                c.cidade AS cinema_cidade, c.estado AS cinema_estado
            FROM sessoes s
            JOIN cinemas c ON s.id_cinema = c.id
            WHERE s.id_filme = ? AND s.id_cinema = ? AND s.data = ?
            ORDER BY s.horario
        """;

        List<Sessao> sessoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, filmeId);
            stmt.setInt(2, cinemaId);
            stmt.setString(3, data);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sessao sessao = criarSessaoComDetalhes(rs);
                    sessoes.add(sessao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return sessoes;
    }

    
    public boolean adicionarSessao(Sessao sessao) throws SQLException {
        String sql = """
            INSERT INTO sessoes (id_filme, id_cinema, horario, tipo, data)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sessao.getIdFilme());
            stmt.setInt(2, sessao.getIdCinema());
            stmt.setString(3, sessao.getHorario());
            stmt.setString(4, sessao.getTipo());
            stmt.setString(5, sessao.getData());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    public boolean excluirSessao(int sessaoId) throws SQLException {
        String sql = "DELETE FROM sessoes WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sessaoId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

  
    private Sessao criarSessaoComDetalhes(ResultSet rs) throws SQLException {
        Sessao sessao = new Sessao();
        sessao.setId(rs.getInt("sessao_id"));
        sessao.setHorario(rs.getString("horario"));
        sessao.setTipo(rs.getString("tipo"));
        sessao.setData(rs.getString("data"));
        
        Cinema cinema = new Cinema();
        cinema.setId(rs.getInt("cinema_id"));
        cinema.setNome(rs.getString("cinema_nome"));
        cinema.setRua(rs.getString("cinema_rua"));
        cinema.setNumero(rs.getInt("cinema_numero"));
        cinema.setCidade(rs.getString("cinema_cidade"));
        cinema.setEstado(rs.getString("cinema_estado"));
        
        sessao.setCinema(cinema);
        return sessao;
    }

   
    private Sessao criarSessaoComFilmeEDetalhes(ResultSet rs) throws SQLException {
        Sessao sessao = criarSessaoComDetalhes(rs);

        Filme filme = new Filme();
        filme.setId(rs.getInt("filme_id"));
        filme.setNome(rs.getString("filme_nome"));
        filme.setDuracao(rs.getInt("duracao"));
        filme.setGenero(rs.getString("genero"));
        filme.setCartaz(rs.getString("cartaz"));

        sessao.setFilme(filme);
        return sessao;
    }
    
public List<Sessao> listarSessoesPorNomeFilme(String nomeFilme) throws SQLException {
    String sql = """
        SELECT 
            s.id AS sessao_id, s.horario, s.tipo, s.data,
            c.id AS cinema_id, c.nome AS cinema_nome, 
            c.rua AS cinema_rua, c.numero AS cinema_numero, 
            c.cidade AS cinema_cidade, c.estado AS cinema_estado,
            f.id AS filme_id, f.nome AS filme_nome, f.duracao, f.genero, f.cartaz
        FROM sessoes s
        JOIN cinemas c ON s.id_cinema = c.id
        JOIN filmes f ON s.id_filme = f.id
        WHERE f.nome LIKE ?
        ORDER BY s.horario
    """;

    List<Sessao> sessoes = new ArrayList<>();

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, "%" + nomeFilme + "%"); 
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sessao sessao = criarSessaoComFilmeEDetalhes(rs);
                sessoes.add(sessao);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }

    return sessoes;
}
public Sessao buscarPorId(int id) throws Exception {
    String sql = "SELECT * FROM sessoes WHERE id = ?";
    Sessao sessao = null;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                sessao = new Sessao();
                sessao.setId(rs.getInt("id"));
                sessao.setIdFilme(rs.getInt("id_filme"));
                sessao.setIdCinema(rs.getInt("id_cinema"));
                sessao.setData(rs.getDate("data").toString());
                sessao.setHorario(rs.getTime("horario").toString()); 
            }
        }
    }

    if (sessao == null) {
        throw new Exception("Sessão com ID " + id + " não encontrada.");
    }

    return sessao;
}
  public List<Assento> listarAssentosPorSessao(int sessaoId) {
        List<Assento> assentos = new ArrayList<>();
        String sql = "SELECT * FROM assentos WHERE id_sessao = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sessaoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Assento assento = new Assento();
                    assento.setId(rs.getInt("id"));
                    assento.setLinha(rs.getInt("linha"));
                    assento.setColuna(rs.getInt("coluna"));
                    assento.setOcupado(rs.getBoolean("ocupado"));
                    assento.setIdSessao(rs.getInt("id_sessao"));
                    assentos.add(assento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assentos;
    }

    public void marcarAssentoOcupado(int idAssento) {
        String sql = "UPDATE assentos SET ocupado = true WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAssento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
