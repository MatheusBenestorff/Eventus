<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Evento" %>

<%@ include file="header.jsp" %>

<main class="container my-4">
    <% Evento evento = (Evento) request.getAttribute("evento"); %>
    <% if (evento != null) { %>
        <h1 class="text-start mb-4" style="color: #c9d1d9;"><%= evento.getNome() %></h1>
        <div class="row">
            <div class="col-md-4">
                <img src="<%= evento.getCartaz() %>" class="img-fluid" alt="<%= evento.getNome() %>">
            </div>
            <div class="col-md-8">
                <p style="color: #c9d1d9;"><strong>Descrição:</strong> <%= evento.getDescricao() %></p>
                <p style="color: #c9d1d9;"><strong>Tipo:</strong> <%= evento.getTipo() %></p>
            </div>
        </div>
    <% } else { %>
        <p class="text-center" style="color: #c9d1d9;">Detalhes do evento não encontrados.</p>
    <% } %>
</main>

<%@ include file="footer.jsp" %>
