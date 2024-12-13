<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Filme" %>
<%@ page import="model.Cinema" %>
<%@ page import="model.Sessao" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

    <%
        Filme filme = (Filme) request.getAttribute("filme");
        if (filme != null) {
    %>
<style>

.parallax {
    height: 37vh;
    background-image: url('<%= filme.getBanner() %>');
    background-attachment: fixed;
    background-position: bottom;
    background-repeat: no-repeat;
    background-size: cover;
    position: relative;
}

.parallax .text-center {
    position: relative;
    z-index: 1;
}

.parallax:before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.1); 
    z-index: 0;
}
</style>
<main class="container-fluid p-0">

       
        <section class="parallax">
                <div class="overlay" style="background: rgba(0, 0, 0, 0.4); height:37vh;">
                <div class="container text-white py-5">
                    <div class="row align-items-center">
                        <div class="col-md-3">
                            <img src="<%= filme.getCartaz() %>" alt="Cartaz do filme" class="img-fluid rounded shadow" style="max-width: 180px;">
                        </div>
                        <div class="col-md-9">
                            <h1 class="display-2"><%= filme.getNome() %></h1>
                            <h1 style="font-size: 30px; margin-bottom: 1rem;">Duração: <%= filme.getDuracao() %> minutos</h1>
                        </div>
                    </div>
                </div>
            </div>
         </section>               

        <!-- Sessões por Data -->
        <div class="container my-5">
            <h1 class="mb-4">Sessões</h1>

            <%
                Map<String, List<Sessao>> sessoesPorData = (Map<String, List<Sessao>>) request.getAttribute("sessoesPorData");
                if (sessoesPorData != null && !sessoesPorData.isEmpty()) {
                    for (Map.Entry<String, List<Sessao>> entry : sessoesPorData.entrySet()) {
                        String data = entry.getKey();
                        List<Sessao> sessoesDaData = entry.getValue();
            %>
                <div class="mb-4">
                    <h3 style="color: #c9d1d9;"><%= data %></h3>
                    <div class="row">
                        <% for (Sessao sessao : sessoesDaData) { %>
                            <div class="col-12 col-md-6 col-lg-4 mb-4">
                                <div class="card h-100" style="background-color: #0d1117; border: 1px solid #30363d; border-radius: 8px; padding: 1rem;">
                                    <p style="color: #b0b0b0; font-size: 0.9rem;">
                                        <strong>Horário:</strong> <%= sessao.getHorario() %>
                                        <br>
                                        <strong>Tipo:</strong> <%= sessao.getTipo() %>
                                        <br>
                                        <strong>Cinema:</strong> <%= sessao.getCinema().getNome() %> 
                                    </p>
                                    <a href="CompraIngressoServlet?filmeId=<%= filme.getId() %>&sessaoId=<%= sessao.getId() %>&horario=<%= sessao.getHorario() %>"
                                       class="btn btn-sm" style="font-size: 0.8rem; background-color: #c9d1d9;">
                                        Comprar Ingresso
                                    </a>
                                </div>
                            </div>
                        <% } %>
                    </div>
                </div>
            <%
                    }
                } else {
            %>
                <p class="text-danger">Nenhuma sessão disponível.</p>
            <%
                }
            %>
        </div>
    <%
        } else {
    %>
        <div class="container my-5">
            <p class="text-danger">Filme não encontrado.</p>
        </div>
    <%
        }
    %>

        <section class="container my-5">
            <section class="parallax" style="height: 60vh;">

                <div class="overlay d-flex align-items-center justify-content-center" style="background: rgba(0, 0, 0, 0.6); height: 60vh;">
                    <div class="text-center text-white">
                        <p style="font-size: 2rem; font-weight: bold; margin-bottom: 1rem;">
                        <strong>Gênero:</strong> <%= filme.getGenero() %>
                        </p>
                        <p style="font-size: 2rem; font-weight: bold; margin-bottom: 1rem;">
                        <strong>Diretor:</strong> <%= filme.getDiretor() %>
                        </p>
                        <p style="font-size: 1.5rem; font-weight: 300;">
                        <%= filme.getDescricao() %>
                        </p>
                    </div>
                </div>                
            </section>
        </section>   

</main>
<%@ include file="footer.jsp" %> 
