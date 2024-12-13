<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="sidebar.jsp" %> 
<style>
body {
    background-image: url('img/banner10.jpg');
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
        <h1 class="mb-4" style="font-size: 50px;">Adicionar Novo Evento</h1>

        <input type="hidden" name="tipoConteudo" value="evento">


        <div class="form-row">
            <div class="form-group" style="width: 100%;">
                <label for="nomeEvento">Nome do Evento:</label>
                <input type="text" id="nomeEvento" name="nomeEvento" class="form-control"  required>
            </div>
        </div>

        <div class="form-group">
            <label for="descricaoEvento">Descrição:</label>
            <textarea id="descricaoEvento" name="descricaoEvento" class="form-control" rows="4" required></textarea>
        </div>

        <div class="form-group">
            <label for="cartaz">URL do Cartaz:</label>
            <input type="text" id="cartaz" name="cartaz" class="form-control">
        </div>


        <div class="form-group">
        <div class="form-check">
            <label class="form-check-label">Stand-up</label>
                <input type="checkbox" class="form-check-input" name="standUp">
        </div>

        <div class="form-check">
            <label class="form-check-label">Festival</label>
                <input type="checkbox" class="form-check-input" name="festival">
        </div>

        <div class="form-check">
            <label class="form-check-label"> Exposição</label>
                <input type="checkbox" class="form-check-input" name="exposicao">
        </div>
        </div>    

        <button type="submit" class="btn btn-primary">Adicionar Evento</button>
    </form>
</div>
</main>