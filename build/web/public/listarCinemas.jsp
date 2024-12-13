<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Cinema" %>

<%@ include file="header.jsp" %>

<main class="container my-4">
  <h1 class="text-start mb-4" style="color: #c9d1d9;">Cinemas</h1>

   
    <form method="GET" action="ListarCinemasServlet" class="mb-4">
        <div class="input-group">
            <input 
                type="text" 
                class="form-control" 
                name="search" 
                placeholder="Procure por um cinema..." 
                value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
            <button class="btn btn-outline-light" type="submit">Buscar</button>
        </div>
    </form>

   
    <div class="row">
        <% 
            List<Cinema> cinemas = (List<Cinema>) request.getAttribute("cinemas");

            if (cinemas != null && !cinemas.isEmpty()) {
                for (Cinema cinema : cinemas) { 
        %>
                <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="card h-100" style="background-color: #0d1117; border: 1px solid #30363d; border-radius: 8px; padding: 1rem;">
    
                    <div class="d-flex align-items-center mb-2">
                        <i class="fa fa-film" style="color: #c9d1d9; font-size: 1.5rem; margin-right: 8px;"></i>
                        <h5 style="color: #c9d1d9; font-size: 1.2rem; font-weight: bold;">
                            <%= cinema.getNome() %>
                        </h5>
                    </div>
                    <p style="color: #b0b0b0; font-size: 0.9rem;">
                        <%= cinema.getRua() %>, <%= cinema.getNumero() %>, 
                        <%= cinema.getCidade() %> - <%= cinema.getEstado() %>
                    </p>
                </div>
            </div>
        <% 
                }
            } else { 
        %>
            <div class="col-12">
                <p class="text-center" style="color: #c9d1d9;">Nenhum cinema encontrado.</p>
            </div>
        <% 
            } 
        %>
    </div>
</main>
<%@ include file="footer.jsp" %> 