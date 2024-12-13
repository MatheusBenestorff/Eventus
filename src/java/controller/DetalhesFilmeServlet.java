package controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import model.FilmeDAO;
import model.Filme;
import model.CinemaDAO;
import model.Cinema;
import model.Sessao;
import model.SessaoDAO;
import utils.DatabaseConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DetalhesFilmeServlet", urlPatterns = {"/DetalhesFilmeServlet"})
public class DetalhesFilmeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                int id = Integer.parseInt(idParam);
                FilmeDAO filmeDAO = new FilmeDAO(conn);
                Filme filme = filmeDAO.buscarPorId(id);

                if (filme != null) {
                   
                    CinemaDAO cinemaDAO = new CinemaDAO(conn);
                    List<Cinema> cinemas = cinemaDAO.listarCinemasPorFilme(id);

                   
                    SessaoDAO sessaoDAO = new SessaoDAO(conn);
                    List<Sessao> sessoes = sessaoDAO.listarSessoesPorFilme(id);

                    
                    Map<String, List<Sessao>> sessoesPorData = new HashMap<>();
                    DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
                    DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM"); 

                    
                    DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm");

                    for (Sessao sessao : sessoes) {
                        
                        String dataOriginal = sessao.getData();
                        LocalDate dataFormatada = LocalDate.parse(dataOriginal, formatoEntrada);
                        String dataReformatada = dataFormatada.format(formatoSaida);

                        
                        String horarioOriginal = sessao.getHorario();
                        LocalTime horarioFormatado = LocalTime.parse(horarioOriginal, DateTimeFormatter.ofPattern("HH:mm:ss"));
                        String horarioFinal = horarioFormatado.format(formatoHorario);
                        
                     
                        sessao.setHorario(horarioFinal);

                      
                        if (cinemas != null) {
                            for (Cinema cinema : cinemas) {
                                
                                if (sessao.getIdCinema() == cinema.getId()) {
                                    sessao.setCinema(cinema); 
                                    break;
                                }
                            }
                        }

                      
                        sessoesPorData.computeIfAbsent(dataReformatada, k -> new ArrayList<>()).add(sessao);
                    }

                   
                    request.setAttribute("filme", filme);
                    request.setAttribute("cinemas", cinemas);
                    request.setAttribute("sessoesPorData", sessoesPorData);

                   
                    request.getRequestDispatcher("public/detalhesFilme.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Filme não encontrado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar detalhes do filme.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do filme não fornecido.");
        }
    }
}
