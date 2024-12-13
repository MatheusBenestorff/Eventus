<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Evento" %>

<%@ include file="header.jsp" %> 

<main class="container my-4">
  <h1 class="text-start mb-4">Festivais</h1>
  <div class="row">
        <% 
          List<Evento> festivalEventos = (List<Evento>) request.getAttribute("eventos");

          if (festivalEventos != null && !festivalEventos.isEmpty()) {
              for (Evento evento : festivalEventos) { 
        %>
          <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
            <div class="card h-100">
            <img src="<%= evento.getCartaz() %>" class="card-img-top" alt="<%= evento.getNome() %>">
            <div class="card-body text-center">
                    <h5 class="card-title"><%= evento.getNome() %></h5>
                </div>
            </div>
        </div>
    <% 
            }
        } else {
    %>
        <div class="col-12">
            <p class="text-center">Nenhum Festival no momento.</p>
        </div>
    <% 
        }
    %>
  </div>
</main>
<%@ include file="footer.jsp" %> 