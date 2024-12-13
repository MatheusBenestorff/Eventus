<%@ page import="model.Filme" %>
<%@ page import="model.Cinema" %>
<%@ page import="model.Sessao" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="sidebar.jsp" %> 
<style>
    body {
        background-image: url('img/banner7.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        color: #c9d1d9;
        min-height: 100vh;

</style>

<main>
    <div class="card">
        <h1 style="font-size: 50px;">Adicionar Nova Sessão ao filme</h1>


        <form action="<%= request.getContextPath() %>/adicionarSessao" method="post">
            <div class="form-group">
                <label for="filme" class="form-label">Escolha o Filme:</label>
                <select name="idFilme" id="filme" class="form-control" required>
                    <%
                        List<Filme> filmes = (List<Filme>) request.getAttribute("filmes");
                        for (Filme f : filmes) {
                    %>
                        <option value="<%= f.getId() %>"><%= f.getNome() %></option>
                    <% 
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="cinema" class="form-label">Escolha o Cinema:</label>
                <select name="idCinema" id="cinema" class="form-control" required>
                    <%
                        List<Cinema> cinemas = (List<Cinema>) request.getAttribute("cinemas");
                        for (Cinema c : cinemas) {
                    %>
                        <option value="<%= c.getId() %>"><%= c.getNome() %></option>
                    <% 
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="data" class="form-label">Data da Sessão:</label>
                <input type="date" name="data" id="data" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="horario" class="form-label">Horário da Sessão:</label>
                <input type="time" name="horario" id="horario" class="form-control" value="00:00" required>
            </div>

            <div class="form-group">
                <label for="tipo" class="form-label">Tipo de Sessão:</label>
                <select name="tipo" id="tipo" class="form-control" required>
                    <option value="Normal">Normal</option>
                    <option value="3D">3D</option>
                    <option value="IMAX">IMAX</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Adicionar Sessão</button>
        </form>
    </div>
</main>
        <% 
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null && !mensagem.isEmpty()) {
        %>
            <div class="alert alert-info">
                <%= mensagem %>
            </div>
        <% 
            } 
        %>
