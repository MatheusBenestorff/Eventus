<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<style>
    body {
        background-image: url('img/banner13.jpg');
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        color: #c9d1d9;
        min-height: 100vh;
    }

    header {
        background: transparent;
        height: 80px;
        border: none;
    }

    main {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .card {
        box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
        border-radius: 15px;
        background-color: rgba(255, 255, 255, 0.2);
        backdrop-filter: blur(10px);
        width: 100%;
        max-width: 700px;
        padding: 40px;
    }

    .card h1 {
        color: #fff;
        font-size: 36px;
        margin-bottom: 20px;
        text-align: center;
    }

    .form-row {
        display: flex;
        justify-content: space-between;
        gap: 20px;
    }

    .form-group {
        flex: 1;
    }

    .form-label {
        color: #fff;
        font-weight: 600;
        margin-bottom: 5px;
        display: block;
    }

    .form-control {
        background-color: rgba(255, 255, 255, 0.3);
        border: 1px solid rgba(255, 255, 255, 0.5);
        color: #333;
        padding: 10px 15px;
        width: 100%;
    }

    .btn-primary {
        background-color: #0d1117;
        border: none;
        padding: 10px;
        width: 100%;
    }

    .btn-primary:hover {
        background-color: #1a1d22;
    }

    .message {
        text-align: center;
        color: green;
        margin-top: 20px;
    }
</style>

<main>
    <div class="card">
        <h1 style="font-size: 50px;">Cadastro de Novo Usuário</h1>
        <form action="<%= request.getContextPath() %>/cadastrarUsuario" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label for="nome" class="form-label">Nome:</label>
                    <input type="text" id="nome" name="nome" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="cpf" class="form-label">CPF:</label>
                    <input type="text" id="cpf" name="cpf" class="form-control" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="telefone" class="form-label">Telefone:</label>
                    <input type="text" id="telefone" name="telefone" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="cep" class="form-label">CEP:</label>
                    <input type="text" id="cep" name="cep" class="form-control" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="rua" class="form-label">Rua:</label>
                    <input type="text" id="rua" name="rua" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="bairro" class="form-label">Bairro:</label>
                    <input type="text" id="bairro" name="bairro" class="form-control" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="numero" class="form-label">Número:</label>
                    <input type="text" id="numero" name="numero" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="estado" class="form-label">Estado:</label>
                    <input type="text" id="estado" name="estado" class="form-control" required>
                </div>
            </div>
            <div class="form-row mb-2">
                <div class="form-group">
                    <label for="cidade" class="form-label">Cidade:</label>
                    <input type="text" id="cidade" name="cidade" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="email" class="form-label">E-mail:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
            </div>
                <div class="form-group mb-2">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" id="senha" name="senha" class="form-control" required>
                </div>
                <div class="form-group mb-4">
                    <label for="confirmaSenha" class="form-label">Confirmar Senha:</label>
                    <input type="password" id="confirmaSenha" name="confirmaSenha" class="form-control" required>
                </div>
            <button type="submit" class="btn btn-primary">Cadastrar</button>
        </form>
        <p class="message">
            ${mensagem != null ? mensagem : ""}
        </p>
    </div>
</main>
