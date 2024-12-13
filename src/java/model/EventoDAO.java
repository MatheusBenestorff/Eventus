package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private Connection conn;

    public EventoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Evento> listarPorTipo(String tipo) {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos WHERE tipo = ?";  

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo); 
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNome(rs.getString("nome"));
                    evento.setDescricao(rs.getString("descricao"));
                    evento.setTipo(rs.getString("tipo"));
                    eventos.add(evento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
    public Evento buscarPorId(int id) {
        Evento evento = null;
        String sql = "SELECT * FROM eventos WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setNome(rs.getString("nome"));
                    evento.setDescricao(rs.getString("descricao"));
                    evento.setTipo(rs.getString("tipo"));
                    evento.setCartaz(rs.getString("cartaz"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return evento;
    }
}
