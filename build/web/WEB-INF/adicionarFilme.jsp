<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="sidebar.jsp" %> 
<style>
body {
    background-image: url('img/banner9.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    color: #c9d1d9;
    min-height: 100vh;
}

</style>
<main>
    <div class="card">


    <form action="<%= request.getContextPath() %>/adicionarConteudo" method="post" class="mb-5">
        <h1 class="mb-4">Adicionar Novo Filme</h1>

        <input type="hidden" name="tipoConteudo" value="filme">


        <div class="form-row">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea id="descricao" name="descricao" class="form-control" rows="2" required></textarea>
            </div>
        </div>


        <div class="form-row">
            <div class="form-group">
                <label for="diretor">Diretor:</label>
                <input type="text" id="diretor" name="diretor" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="genero">Gênero:</label>
                <input type="text" id="genero" name="genero" class="form-control" required>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="duracao">Duração (em minutos):</label>
                <input type="number" id="duracao" name="duracao" class="form-control" min="1" required>
            </div>
            <div class="form-group">
                <label for="cartaz">URL do Cartaz:</label>
                <input type="text" id="cartaz" name="cartaz" class="form-control">
            </div>
        </div>


        <div class="form-group">
            <label for="banner">URL do Banner:</label>
            <input type="text" id="banner" name="banner" class="form-control">
        </div>


        <div class="form-group">
            <div class="form-check">
                <label class="form-check-label">Em Cartaz</label>
                <input type="checkbox" class="form-check-input" name="emCartaz">
            </div>
            <div class="form-check">
                <label class="form-check-label">Em Breve</label>                
                <input type="checkbox" class="form-check-input" name="emBreve">
            </div>
            <div class="form-check">
                <label class="form-check-label">Pré-Estreia</label>                
                <input type="checkbox" class="form-check-input" name="preEstreia">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Adicionar Filme</button>
    </form>
</div>
</main>
