package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssentoDAO {
    private final Connection conn;

    public AssentoDAO(Connection conn) {
        this.conn = conn;
    }

   
    public List<Assento> listarAssentosOcupadosPorSessao(int idSessao) throws Exception {
        List<Assento> assentosOcupados = new ArrayList<>();
        String sql = "SELECT * FROM assentos WHERE id_sessao = ? AND ocupado = TRUE";  

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSessao);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Assento assento = new Assento();
                    assento.setId(rs.getInt("id"));
                    assento.setLinha(rs.getInt("linha"));
                    assento.setColuna(rs.getInt("coluna"));
                    assento.setOcupado(rs.getBoolean("ocupado"));
                    assento.setIdSessao(rs.getInt("id_sessao")); 
                    assentosOcupados.add(assento);
                }
            }
        }
        return assentosOcupados;
    }

    
    public boolean atualizarStatusAssento(int id, boolean ocupado) throws Exception {
        String sql = "UPDATE assentos SET ocupado = ? WHERE id = ?";  

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, ocupado);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        }
    }

  
    public boolean inserirAssento(Assento assento) throws Exception {

        String sql = "INSERT INTO assentos (linha, coluna, ocupado, id_sessao) VALUES (?, ?, ?, ?)";  

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assento.getLinha());
            stmt.setInt(2, assento.getColuna());
            stmt.setBoolean(3, assento.isOcupado());
            stmt.setInt(4, assento.getIdSessao());
            return stmt.executeUpdate() > 0;
        }
    }

    
    public List<Assento> listarAssentosPorSessao(int idSessao) throws Exception {
        List<Assento> assentos = new ArrayList<>();
        String sql = "SELECT * FROM assentos WHERE id_sessao = ?";  

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSessao);
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
        }
        return assentos;
    }
    public boolean adicionarAssento(Assento assento) throws Exception {
    String sql = "INSERT INTO assentos (linha, coluna, ocupado, id_sessao) VALUES (?, ?, ?, ?)";  

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, assento.getLinha());    
        stmt.setInt(2, assento.getColuna());   
        stmt.setBoolean(3, assento.isOcupado());  
        stmt.setInt(4, assento.getIdSessao()); 

     
        return stmt.executeUpdate() > 0;
    }
}
}
