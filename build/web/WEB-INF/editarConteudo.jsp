<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Filme" %>

<%@ include file="sidebar.jsp" %> 

<style>
body {
    background-image: url('img/bannerLogin.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    color: #c9d1d9;
    min-height: 100vh;
}
</style>

<main>
    <div class="card">
        <h1 class="mb-4">Editar Filme</h1>
        <form action="<%= request.getContextPath() %>/editarFilme" method="post">
            <% Filme filme = (Filme) request.getAttribute("filme"); %>

            <input type="hidden" name="id" value="<%= filme.getId() %>">
            <div class="form-row">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<%= filme.getNome() %>" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea id="descricao" name="descricao" rows="2" class="form-control" required><%= filme.getDescricao() %></textarea>
            </div>
            </div>
            <div class="form-row">
            <div class="form-group">
                <label for="diretor">Diretor:</label>
                <input type="text" id="diretor" name="diretor" value="<%= filme.getDiretor() %>" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="genero">Gênero:</label>
                <input type="text" id="genero" name="genero" value="<%= filme.getGenero() %>" class="form-control" required>
            </div>
            </div>
            
            <div class="form-group">
                <label for="cartaz">URL do Cartaz:</label>
                <input type="text" id="cartaz" name="cartaz" value="<%= filme.getCartaz() %>" class="form-control">
            </div>

            <div class="form-group">
                <label for="banner">URL do Banner:</label>
                <input type="text" id="banner" name="banner" value="<%= filme.getBanner() %>" class="form-control">
            </div>

            <div class="form-group">
                <div class="form-check">
                <label class="form-check-label">Em Cartaz</label>
                    <input type="checkbox" name="emCartaz" <%= filme.isEmCartaz() ? "checked" : "" %>>
                </div>    
                <div class="form-check">
                <label class="form-check-label">Em Cartaz</label>
                <input type="checkbox" name="emBreve" <%= filme.isEmBreve() ? "checked" : "" %>>
                </div> 
                <div class="form-check">
                <label class="form-check-label">Em Cartaz</label>
                    <input type="checkbox" name="preEstreia" <%= filme.isPreEstreia() ? "checked" : "" %>>
                </div> 
            </div>    
            <input type="submit" value="Salvar Alterações">
        </form>

        <% 
      
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) { 
        %>
            <p class="alert alert-success text-center"><%= mensagem %></p>
        <% 
            } 
        %>
    </div>
</main>
