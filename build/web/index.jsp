<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Filme" %>
<%@ page import="model.Evento" %>
<%@ page import="model.Cinema" %>
<%@ include file="public/header.jsp" %>

<style>

.parallax {
    height: 60vh;
    background-image: url('img/banner16.jpg'); 
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
<%
    // Obter os filmes e exposições da requisição
    List<Filme> filmesPreview = (List<Filme>) request.getAttribute("filmesPreview");
    List<Evento> exposicoesPreview = (List<Evento>) request.getAttribute("exposicoesPreview");
    if (filmesPreview == null) {
        response.sendRedirect("ListarPreviaServlet");
        return;
    }
    if (exposicoesPreview == null) {
        response.sendRedirect("ListarPreviaServlet");
        return;
    }
    
%>

<main>
    <!-- Carousel -->
    <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/banner5.jpg" class="d-block w-100" alt="Imagem 1" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));"/>
                <div class="carousel-caption d-none d-md-block">
                <h2 style="font-size: 70px; margin-bottom: 60px;">Escolha o cinema de sua preferência</h2>
      </div>
            </div>
            <div class="carousel-item">
                <img src="img/banner6.jpg" class="d-block w-100" alt="Imagem 2" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));" />
                <div class="carousel-caption d-none d-md-block">
                <h2 style="font-size: 70px; margin-bottom: 60px;">Compartilhe experiências incríveis</h2>
      </div>
            </div>
            <div class="carousel-item">
                <img src="img/banner4.jpg" class="d-block w-100" alt="Imagem 3" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));"/>
                <div class="carousel-caption d-none d-md-block">
                <h2 style="font-size: 60px; margin-bottom: 40px;">Assista aos principais sucessos da indústria</h2>
      </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <!-- Seção de Filmes em Destaque -->
    <section class="container my-4">
        <h1 class="text-start mb-4" style="color: #c9d1d9;">Filmes em Destaque</h1>
        <div class="row">
            <% 
                if (!filmesPreview.isEmpty()) {
                    for (Filme filme : filmesPreview) {
            %>
                <div class="col-6 col-md-4 col-lg-2 mb-4">
                    <div class="card h-100" style="background-color: #0d1117; border: none;">
                        <a href="<%= request.getContextPath() %>/DetalhesFilmeServlet?id=<%= filme.getId() %>" style="text-decoration: none;">
                            <img src="<%= filme.getCartaz() %>" class="card-img-top" alt="<%= filme.getNome() %> " 
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
                    <p class="text-center" style="color: #c9d1d9;">Nenhum filme em destaque no momento.</p>
                </div>
            <% 
                }
            %>
        </div>
    </section>

    <!-- Seção de Exposições em Destaque -->
    <section class="container my-4">
        <h1 class="text-start mb-4" style="color: #c9d1d9;">Exposições em Destaque</h1>
        <div class="row">
            <% 
                if (!exposicoesPreview.isEmpty()) {
                    for (Evento exposicao : exposicoesPreview) {
            %>
                <div class="col-6 col-md-4 col-lg-3 mb-4">
                    <div class="card h-100" style="background-color: #0d1117; border: none;">
                        <a href="<%= request.getContextPath() %>/DetalhesEventoServlet?id=<%= exposicao.getId() %>" style="text-decoration: none;">
                            <img src="<%= exposicao.getCartaz() %>" class="card-img-top" alt="<%= exposicao.getNome() %>" 
                                 style="height: 280px; width: 190px; object-fit: cover; margin: auto;">
                            <div class="card-body">
                                <h5 class="card-title" style="color: #c9d1d9; font-size: 1rem; text-align: left;"><%= exposicao.getNome() %></h5>
                            </div>
                        </a>
                    </div>
                </div>
            <% 
                    }
                } else {
            %>
                <div class="col-12">
                    <p class="text-center" style="color: #c9d1d9;">Nenhuma exposição em destaque no momento.</p>
                </div>
            <% 
                }
            %>
        </div>
    </section>

<section class="container my-4">
    <h1 class="text-start mb-4" style="color: #c9d1d9;"> Principais Notícias</h1>
    <div class="row text-white">
        <!-- Primeira Notícia - Ocupa 2 colunas -->
        <div class="col-12 col-md-6 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <img src="img/noticia1.jpg" class="card-img-top" alt="Notícia 1" style="height: 250px; object-fit: cover;">
                <div class="card-img-overlay d-flex flex-column justify-content-end" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));">
                    <h5 class="card-title text-white">Produtor confirma cena pós-créditos de 'Sonic 3'...</h5>
                    <p class="card-text text-white">Pré-venda de ingressos para o longa já está aberta.</p>
                </div>
            </div>
        </div>

        <!-- Notícias em 2 colunas - Primeira coluna -->
        <div class="col-12 col-md-3 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <img src="https://ingresso-a.akamaihd.net/b2b/production/uploads/article/image/2816/74e21c32d7a797a4e63ca08460727728.jpg" class="card-img-top" alt="Notícia 2" style="height: 250px; object-fit: cover;">
                <div class="card-img-overlay d-flex flex-column justify-content-end" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));">
                    <h5 class="card-title text-white">Continuação do live-action do Chico Bento...</h5>
                    <p class="card-text text-white">Primeiro filme do personagem estreando...</p>
                </div>
            </div>
        </div>

        <div class="col-12 col-md-3 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <img src="img/noticia3.jpg" class="card-img-top" alt="Notícia 3" style="height: 250px; object-fit: cover;">
                <div class="card-img-overlay d-flex flex-column justify-content-end" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));">
                    <h5 class="card-title text-white">Exibição de 'O Auto da Compadecida 2' na CCXP24...</h5>
                    <p class="card-text text-white">A nova aventura de Chicó e João Grilo...</p>
                </div>
            </div>
        </div>

        <div class="col-12 col-md-6 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <img src="img/noticia2.jpg" class="card-img-top" alt="Notícia 1" style="height: 250px; object-fit: cover;">
                <div class="card-img-overlay d-flex flex-column justify-content-end" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));">
                    <h5 class="card-title text-white">Começou! A pré-venda de 'Sonic 3'...</h5>
                    <p class="card-text text-white">>Novo longa do Ouriço azul estreia no cinema...</p>
                </div>
            </div>
        </div>

        <!-- Notícias em 2 colunas - Primeira coluna -->
        <div class="col-12 col-md-3 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <img src="https://ingresso-a.akamaihd.net/b2b/production/uploads/article/image/2818/a1dc5d6e3e7c738f13ac7676d952e7c3.jpg" class="card-img-top" alt="Notícia 2" style="height: 250px; object-fit: cover;">
                <div class="card-img-overlay d-flex flex-column justify-content-end" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));">
                    <h5 class="card-title text-white">Homenageado na CCXP24, Wagner Moura conta...</h5>
                    <p class="card-text text-white">Homenageado na CCXP24, Wagner Moura conta...</p>
                </div>
            </div>
        </div>

        <div class="col-12 col-md-3 mb-4">
            <div class="card h-100" style="background-color: #0d1117; border: none;">
                <img src="https://ingresso-a.akamaihd.net/b2b/production/uploads/article/image/2817/96766078078bfe25411feccf7c20c9bd.jpg" class="card-img-top" alt="Notícia 3" style="height: 250px; object-fit: cover;">
                <div class="card-img-overlay d-flex flex-column justify-content-end" style="background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0));">
                    <h5 class="card-title text-white">Maurício de Sousa confirma...</h5>
                    <p class="card-text text-white">Carlos Saldanha, diretor de 'Rio' e 'A...</p>
                </div>
            </div>
        </div>
    </div>
</section>
        <!-- Seção Parallax -->

<section class="parallax d-flex align-items-center justify-content-center">
    <section class="container my-4">
    <h1 class="text-start my-4" style="color: #fff;">Cinemas próximos a você</h1>
    <div class="row">
        <% 
            List<Cinema> cinemas = (List<Cinema>) request.getAttribute("cinemas");
            if (cinemas != null && !cinemas.isEmpty()) {
                for (Cinema cinema : cinemas) {
        %>
            <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="card h-100" style="background-color: rgba(13, 25, 43, 0.9); border: 1px solid #30363d; border-radius: 8px; padding: 1rem;">
                    <!-- Ícone de Cinema -->
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
                <p class="text-center" style="color: #c9d1d9;">Nenhum cinema em destaque no momento.</p>
            </div>
        <% 
            }
        %>
    </div>
</section>

</section>

        
        
<!-- Seção de Cinemas em Destaque -->







</main>

<%@ include file="public/footer.jsp" %> 