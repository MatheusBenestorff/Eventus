package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private Connection conn;

    public FilmeDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Filme> listarTodos() {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM filmes";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
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
    public Filme buscarPorId(int id) {
    Filme filme = null;
    String sql = "SELECT * FROM filmes WHERE id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setCartaz(rs.getString("cartaz"));
                filme.setBanner(rs.getString("banner"));
                filme.setDescricao(rs.getString("descricao")); 
                filme.setDiretor(rs.getString("diretor"));     
                filme.setGenero(rs.getString("genero"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setEmCartaz(rs.getBoolean("em_cartaz"));
                filme.setEmBreve(rs.getBoolean("em_breve"));
                filme.setPreEstreia(rs.getBoolean("pre_estreia"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return filme;
}


    public void excluirFilme(int id) {
        String sql = "DELETE FROM filmes WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void atualizarFilme(Filme filme) {
    String sql = "UPDATE filmes SET nome = ?, descricao = ?, diretor = ?, genero = ?, " +
                 "cartaz = ?, banner = ?, em_cartaz = ?, em_breve = ?, pre_estreia = ? " +
                 "WHERE id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, filme.getNome());
        stmt.setString(2, filme.getDescricao());
        stmt.setString(3, filme.getDiretor());
        stmt.setString(4, filme.getGenero());
        stmt.setString(5, filme.getCartaz());
        stmt.setString(6, filme.getBanner());
        stmt.setBoolean(7, filme.isEmCartaz());
        stmt.setBoolean(8, filme.isEmBreve());
        stmt.setBoolean(9, filme.isPreEstreia());
        stmt.setInt(10, filme.getId()); 

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public List<Filme> buscarFilmes(String search) throws SQLException {
    List<Filme> filmes = new ArrayList<>();
    String sql = "SELECT * FROM filmes WHERE nome LIKE ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + search + "%");

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setCartaz(rs.getString("cartaz"));
                filme.setEmCartaz(rs.getBoolean("em_cartaz"));
                filme.setEmBreve(rs.getBoolean("em_breve"));
                filme.setPreEstreia(rs.getBoolean("pre_estreia"));
                
                filmes.add(filme);
            }
        }
    }
    return filmes;
}



}
