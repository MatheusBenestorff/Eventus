<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Filme" %>

<%@ include file="header.jsp" %> 

<main class="container my-4">
  <h1 class="text-start mb-4" style="color: #c9d1d9;">Filmes em Cartaz</h1>
    <form method="GET" action="ListarEmCartazServlet" class="mb-4">
        <div class="input-group">
            <input 
                type="text" 
                class="form-control" 
                name="search" 
                placeholder="Procure por um filme em cartaz..." 
                value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
            <button class="btn btn-outline-light" type="submit">Buscar</button>
        </div>
    </form>
  <div class="row">
    <% 
        List<Filme> filmesEmCartaz = (List<Filme>) request.getAttribute("filmes");
        
        if (filmesEmCartaz != null && !filmesEmCartaz.isEmpty()) {
            for (Filme filme : filmesEmCartaz) { 
    %>
        <div class="col-6 col-md-4 col-lg-2 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <a href="<%= request.getContextPath() %>/DetalhesFilmeServlet?id=<%= filme.getId() %>" style="text-decoration: none;">
                    <img src="<%= filme.getCartaz() %>" class="card-img-top" alt="<%= filme.getNome() %>" 
                         style="height: 280px; width: 190px; object-fit: cover; margin: auto;">
                    <div class="card-body">
                        <h5 class="card-title" style="color: #c9d1d9; font-size: 1rem; text-align: left;"><%= filme.getNome() %></h5>
                    </div>
                </a>
            </div>
        </div>
    <% 
            }
        } else {
    %>
        <div class="col-12">
            <p class="text-center" style="color: #c9d1d9;">Nenhum filme em cartaz no momento.</p>
        </div>
    <% 
        }
    %>
  </div>
</main>
<%@ include file="footer.jsp" %> 

