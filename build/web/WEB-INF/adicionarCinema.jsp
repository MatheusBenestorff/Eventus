<%@ page import="model.Cinema" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="sidebar.jsp" %>
<style>
    body {
        background-image: url('img/banner12.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        color: #c9d1d9;
        min-height: 100vh;
    }
</style>

<main>
    <div class="card">
        <h1>Adicionar Novo Cinema</h1>

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

        <form action="<%= request.getContextPath() %>/adicionarCinema" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label for="nome">Nome do Cinema:</label>
                    <input type="text" id="nome" name="nome" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="rua">Rua:</label>
                    <input type="text" id="rua" name="rua" class="form-control" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="numero">Número:</label>
                    <input type="number" id="numero" name="numero" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="cidade">Cidade:</label>
                    <input type="text" id="cidade" name="cidade" class="form-control" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group" style="width: 100%;">
                    <label for="estado">Estado:</label>
                    <input type="text" id="estado" name="estado" class="form-control" required>
                </div>
            </div>

            <button type="submit" class="btn btn-primary mt-3">Adicionar Cinema</button>
        </form>
    </div>
</main>
