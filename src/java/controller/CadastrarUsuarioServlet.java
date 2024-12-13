package controller;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DatabaseConnection;

@WebServlet("/cadastrarUsuario")
public class CadastrarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("public/cadastroUsuario.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String cep = request.getParameter("cep");
        String rua = request.getParameter("rua");
        String bairro = request.getParameter("bairro");
        String numero = request.getParameter("numero");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmaSenha = request.getParameter("confirmaSenha");

        String mensagem;
       
        if (!senha.equals(confirmaSenha)) {
            mensagem = "As senhas não coincidem!";
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("public/cadastroUsuario.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO usuario (nome, cpf, telefone, cep, rua, bairro, numero, estado, cidade, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, cep);
            stmt.setString(5, rua);
            stmt.setString(6, bairro);
            stmt.setString(7, numero);
            stmt.setString(8, estado);
            stmt.setString(9, cidade);
            stmt.setString(10, email);
            stmt.setString(11, senha);

            stmt.executeUpdate();
            mensagem = "Usuário cadastrado com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            mensagem = "Erro ao adicionar o usuário: " + e.getMessage();
        }

        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("public/cadastroUsuario.jsp").forward(request, response);
    }
}
