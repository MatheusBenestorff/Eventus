package model;

import java.sql.*;


public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    
    public boolean cadastrarUsuario(Usuario usuario) throws SQLException {
        String insertQuery = "INSERT INTO usuario (nome, cpf, telefone, cep, rua, bairro, numero, estado, cidade, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getCep());
            stmt.setString(5, usuario.getRua());
            stmt.setString(6, usuario.getBairro());
            stmt.setString(7, usuario.getNumero());
            stmt.setString(8, usuario.getEstado());
            stmt.setString(9, usuario.getCidade());
            stmt.setString(10, usuario.getEmail());
            stmt.setString(11, usuario.getSenha());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

   
}
