<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Evento" %>

<%@ include file="header.jsp" %>

<main class="container my-4">
  <h1 class="text-start mb-4">Exposições</h1>
  <div class="row">
        <% 
          List<Evento> exposicaoEventos = (List<Evento>) request.getAttribute("eventos");

          if (exposicaoEventos != null && !exposicaoEventos.isEmpty()) {
              for (Evento evento : exposicaoEventos) { 
        %>
        <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <a href="<%= request.getContextPath() %>/DetalhesEventoServlet?id=<%= evento.getId() %>" style="text-decoration: none;">
                <img src="<%= evento.getCartaz() %>" class="card-img-top" alt="<%= evento.getNome() %>">
                <div class="card-body">
                    <h5 class="card-title" style="color: #c9d1d9; font-size: 1rem; text-align: left;"><%= evento.getNome() %></h5>
                </div>
                </a>    
            </div>
        </div>
    <% 
            }
        } else {
    %>
        <div class="col-12">
            <p class="text-center">Nenhuma Exposição no momento.</p>
        </div>
    <% 
        }
    %>
  </div>
</main>
<%@ include file="footer.jsp" %> 