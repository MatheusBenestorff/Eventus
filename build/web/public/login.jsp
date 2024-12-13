<%@ include file="header.jsp" %>

<style>
    body {
        background-image: url('img/banner2.jpg');
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
        height: 800px;
    }

    .card {
        box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
        border-radius: 15px;
        background-color: rgba(255, 255, 255, 0.2);
        backdrop-filter: blur(10px);
        width: 100%;
        max-width: 500px;
        height: 400px;
    }

    .card .form-label {
        color: #fff;
        font-weight: 600;
        text-align: left;
        display: block;
        margin-bottom: 5px;
    }

    .form-control {
        background-color: rgba(255, 255, 255, 0.3);
        border: 1px solid rgba(255, 255, 255, 0.5);
        color: #333;
        padding-right: 35px;
        width: 100%;
        padding: 10px 15px;
    }

    .form-control-icon {
        position: absolute;
        right: 10px;
        top: 50%;
        transform: translateY(-50%);
        color: #333;
    }

    .btn-primary {
        background-color: #0d1117;
        border: none;
    }

    .btn-primary:hover {
        background-color: #1a1d22;
    }

    .alert {
        display: none;
    }

    .input-group {
        position: relative;
        margin-bottom: 15px;
    }

    .card-body {
        padding: 40px;
    }
</style>

<main>
    <div class="card p-2">
        <div class="card-body text-center">
            <h1 class=" text-white" style="font-size: 70px;">Entrar</h1>   
            <form method="POST" action="login">
                <label for="email" class="form-label">E-mail</label>
                <div class="mb-3 input-group">
                    <input 
                      type="email" 
                      id="email" 
                      name="email" 
                      class="form-control" 
                      placeholder="Digite seu e-mail" 
                      required 
                    />
                    <i class="fas fa-envelope form-control-icon"></i>
                </div>
                <label for="senha" class="form-label">Senha</label>
                <div class="mb-3 input-group">
                    <input 
                      type="password" 
                      id="senha" 
                      name="senha" 
                      class="form-control" 
                      placeholder="Digite sua senha" 
                      required 
                    />
                    <i class="fas fa-lock form-control-icon"></i>
                </div>
                <button type="submit" class="btn btn-primary w-100">Entrar</button>
            </form>

            <p style="color: red;">
                <%= request.getAttribute("mensagem") != null ? request.getAttribute("mensagem") : "" %>
            </p>
        </div>  
    </div>
</main>
