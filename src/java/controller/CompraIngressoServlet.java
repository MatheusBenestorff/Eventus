package controller;

import model.FilmeDAO;
import model.Filme;
import model.CinemaDAO;
import model.Cinema;
import model.Sessao;
import model.Assento;
import model.SessaoDAO;
import utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "CompraIngressoServlet", urlPatterns = {"/CompraIngressoServlet"})
public class CompraIngressoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessaoIdParam = request.getParameter("sessaoId");
        if (sessaoIdParam != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                int sessaoId = Integer.parseInt(sessaoIdParam);

                
                SessaoDAO sessaoDAO = new SessaoDAO(conn);
                Sessao sessao = sessaoDAO.buscarPorId(sessaoId);

                if (sessao != null) {
                    
                    FilmeDAO filmeDAO = new FilmeDAO(conn);
                    Filme filme = filmeDAO.buscarPorId(sessao.getIdFilme());

                  
                    CinemaDAO cinemaDAO = new CinemaDAO(conn);
                    Cinema cinema = cinemaDAO.buscarPorId(sessao.getIdCinema());

                    
                    List<Assento> assentos = sessaoDAO.listarAssentosPorSessao(sessaoId);

                   
                    DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    DateTimeFormatter formatoSaidaData = DateTimeFormatter.ofPattern("dd/MM");
                    LocalDate dataSessao = LocalDate.parse(sessao.getData(), formatoEntrada);
                    String dataFormatada = dataSessao.format(formatoSaidaData);

                   
                    DateTimeFormatter formatoSaidaHora = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horarioSessao = LocalTime.parse(sessao.getHorario(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String horarioFormatado = horarioSessao.format(formatoSaidaHora);

                    
                    sessao.setData(dataFormatada);
                    sessao.setHorario(horarioFormatado);

                   
                    request.setAttribute("filme", filme);
                    request.setAttribute("sessao", sessao);
                    request.setAttribute("cinema", cinema);
                    request.setAttribute("assentos", assentos);

                    
                    request.getRequestDispatcher("public/compraIngresso.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sessão não encontrada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar dados para compra de assento.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da sessão não fornecido.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessaoIdParam = request.getParameter("sessaoId");
        String[] assentosSelecionados = request.getParameterValues("assento");

        if (sessaoIdParam != null && assentosSelecionados != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                int sessaoId = Integer.parseInt(sessaoIdParam);

               
                SessaoDAO sessaoDAO = new SessaoDAO(conn);
                for (String assentoId : assentosSelecionados) {
                    int idAssento = Integer.parseInt(assentoId);
                    sessaoDAO.marcarAssentoOcupado(idAssento);
                }

           
                response.sendRedirect("compraConfirmada.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a compra do assento.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltam parâmetros para a compra.");
        }
    }
}