<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Filme" %>
<%@ page import="model.Cinema" %>
<%@ page import="model.Sessao" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<main class="container-fluid p-0">
    <%
        Filme filme = (Filme) request.getAttribute("filme");
        Sessao sessao = (Sessao) request.getAttribute("sessao");
        Cinema cinema = (Cinema) request.getAttribute("cinema");

        if (filme != null && sessao != null && cinema != null) {
    %>
        <div class="container my-5">
            <div class="row">
              
                <div class="col-md-8">
                    <div class="d-flex flex-wrap rounded justify-content-center" style="max-width: 100%; background-color: #0d192b;">
                        <%
                            int totalAssentos = 30; 
                            for (int i = 1; i <= totalAssentos; i++) {
   
                                String classeAssento = "fas fa-chair text-white fa-4x";  
                        %>
                                <div class="p-2">
                                    <i class="<%= classeAssento %>"></i>
                                    <label><%= + i %></label>
                                </div>
                        <%
                            }
                        %>
                    </div>
                </div>

           
                <div class="col-md-4">
                    <div class="p-3 rounded" style="background-color: #0d192b;">
                        <h1><%= filme.getNome() %></h1>
                       
                        <img src="<%= filme.getCartaz() %>" alt="Cartaz do filme" class="img-fluid rounded mb-3" style="max-height: 200px;">
                        <p><strong>Duração:</strong> <%= filme.getDuracao() %> minutos</p>
                        <p><strong>Descrição:</strong> <%= filme.getDescricao() %></p>
                        <h4>Detalhes da Sessão</h4>
                        <p><strong>Data:</strong> <%= sessao.getData() %></p>
                        <p><strong>Horário:</strong> <%= sessao.getHorario() %></p>
                        <p><strong>Cinema:</strong> <%= cinema.getNome() %></p>

                        <form action="CompraAssentoServlet" method="post">
                            <input type="hidden" name="sessaoId" value="<%= sessao.getId() %>">
                            <button type="submit" class="btn btn-primary btn-block mt-3">Confirmar Compra</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <%
        } else {
    %>
        <div class="container my-5">
            <p class="text-danger">Erro ao carregar informações do filme, sessão ou assentos.</p>
        </div>
    <%
        }
    %>
</main>
