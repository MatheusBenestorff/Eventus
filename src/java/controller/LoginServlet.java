package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DatabaseConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        request.getRequestDispatcher("public/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String mensagem = "";

        try (Connection conn = DatabaseConnection.getConnection()) {
       
            String adminQuery = "SELECT * FROM administrador WHERE email = ? AND senha = ?";
            try (PreparedStatement adminStmt = conn.prepareStatement(adminQuery)) {
                adminStmt.setString(1, email);
                adminStmt.setString(2, senha);
                ResultSet adminRs = adminStmt.executeQuery();

                if (adminRs.next()) {
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("logado", true);
                    session.setAttribute("tipoUsuario", "administrador");
                    request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                    return;
                }
            }

 
            String userQuery = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            try (PreparedStatement userStmt = conn.prepareStatement(userQuery)) {
                userStmt.setString(1, email);
                userStmt.setString(2, senha);
                ResultSet userRs = userStmt.executeQuery();

                if (userRs.next()) {
                   
                    HttpSession session = request.getSession();
                    session.setAttribute("logado", true);
                    session.setAttribute("tipoUsuario", "usuario");
                    response.sendRedirect("index.html");
                    return;
                }
            }

           
            mensagem = "E-mail ou senha inválidos!";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao conectar ao banco de dados.";
        }

      
        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("public/login.jsp").forward(request, response);
    }
}
