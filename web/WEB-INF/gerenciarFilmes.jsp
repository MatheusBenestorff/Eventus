<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Filme" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

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


.table {
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    width: 100%;
    margin-top: 20px;
    border-collapse: collapse;
    box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
}

.table th, .table td {
    padding: 15px;
    text-align: left;
}

.table th {
    background-color: #0d1117;
    color: #fff;
}

.table td {
    background-color: #fff;
    color: #333;
}

.table td button {
    margin-right: 10px;
}

.btn-primary {
    background-color: #0d1117;
    border: none;
    width: 100%;
    padding: 10px;
}

.btn-primary:hover {
    background-color: #1a1d22;
}

.btn-danger, .btn-warning {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
}

.btn-danger:hover {
    background-color: #ff0000;
}

.btn-warning:hover {
    background-color: #ffcc00;
}

.alert {
    display: none;
}

.input-group {
    position: relative;
}

.form-control-icon {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    color: #333;
}
</style>
<main>
    <div class="card">
        <form method="get" action="gerenciarFilmes">
            <h1 class="mb-4" style="font-size: 50px;">Gerenciar Filmes</h1>
            <div class="form-group">
                <input type="number" name="id" placeholder="Digite o ID do filme" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Pesquisar</button>
        </form>

        <hr>

        <%
            List<Filme> filmes = (List<Filme>) request.getAttribute("filmes");
            if (filmes != null && !filmes.isEmpty()) {
        %>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Gênero</th>
                    <th>Diretor</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Filme filme : filmes) {
                %>
                <tr>
                    <td><%= filme.getId() %></td>
                    <td><%= filme.getNome() %></td>
                    <td><%= filme.getGenero() %></td>
                    <td><%= filme.getDiretor() %></td>
                    <td>
                        <form method="post" action="gerenciarFilmes" style="display:inline;">
                            <input type="hidden" name="id" value="<%= filme.getId() %>">
                            <input type="hidden" name="acao" value="excluir">
                            <button type="submit" class="btn btn-danger">Excluir</button>
                        </form>
                        <form method="get" action="editarFilme" style="display:inline;">
                            <input type="hidden" name="id" value="<%= filme.getId() %>">
                            <button type="submit" class="btn btn-warning">Editar</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <% } else { %>
        <p class="text-center text-white">Nenhum filme encontrado.</p>
        <% } %>
    </div>
</main>
