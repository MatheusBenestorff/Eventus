<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Administração - Eventus</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #0d1117;
            color: #c9d1d9;
        }
        .sidebar {
            height: 100%;
            width: 300px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: rgba(13, 25, 43, 0.9);
            padding-top: 20px;
        }
        .sidebar a {
            padding: 10px 15px;
            text-decoration: none;
            font-size: 18px;
            color: #ffffff;
            display: block;
        }
        .sidebar a:hover {
            background-color: #575d63;
        }
        .content {
            margin-left: 310px;
            padding: 20px;
        }
        .header {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .subheader {
            font-size: 20px;
            margin-bottom: 10px;
        }
        .useful-links a {
            color: #007bff;
        }
        .logo {
            width: 250px;
            height: auto;
            margin-left: 25px;
            margin-bottom: 40px;
        }
        .sidebar form button {
            background: none;
            border: none;
            padding: 10px 15px;
            text-decoration: none;
            font-size: 18px;
            color: #ffffff;
            width: 100%;
            text-align: left;
            cursor: pointer;
        }
        .sidebar form button:hover {
            background-color: #575d63;
        }
        .form-container {
            display: flex;
            justify-content: space-around;
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        label, input, textarea {
            display: block;
            width: 100%;
            margin: 10px 0;
        }
        input[type="submit"] {
            width: auto;
            padding: 10px 20px;
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
            background-color: rgba(13, 17, 23, 0.4);
            backdrop-filter: blur(10px);
            width: 100%;
            max-width: 800px; 
            padding: 35px;
            margin-left: 300px;
        }

        .card h1 {
            color: #fff;
            font-size: 50px;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            color: #fff;
            font-weight: 600;
            text-align: left;
            display: block;
            margin-bottom: 5px;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.9);
            border: 1px solid rgba(0, 0, 0, 0.1);
            color: #333;
            padding: 10px 15px;
            width: 100%;
        }

        .form-row {
            display: flex;
            justify-content: space-between;
        }

        .form-row .form-group {
            width: 48%; 
        }

        .form-check {
            display: inline-block;
            margin-right: 10px;
            margin-bottom: 20px;
        }

        .form-check label {
            margin-left: 5px;
        }
        
               h1{
        font-family: "Bebas Neue", sans-serif;
        font-weight: 400;
        font-style: normal;
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

        
        .sidebar form button {
            font-family: "Bebas Neue", sans-serif;
            text-align: center;
            margin-bottom: 20px;
            border-bottom: 2px solid #575d63; 
            font-size: 30px;
        }

        
        .sidebar form {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="navbar-brand mx-auto">
            <img src="img/logosemfundo.png" alt="Logo" class="logo" />
        </div>
  
        <form action="<%= request.getContextPath() %>/adicionarConteudo" method="POST">
            <input type="hidden" name="tipoConteudo" value="filme"> <!-- Tipo de conteúdo = Filme -->
            <button type="submit">Adicionar Novo Filme</button>
        </form>
        

        <form action="<%= request.getContextPath() %>/adicionarConteudo" method="POST">
            <input type="hidden" name="tipoConteudo" value="evento"> <!-- Tipo de conteúdo = Evento -->
            <button type="submit">Adicionar Novo Evento</button>
        </form>
        
      
        <form action="<%= request.getContextPath() %>/gerenciarFilmes" method="POST">
            <button type="submit">Gerenciar Filmes</button>
        </form>
        
  
        <form action="<%= request.getContextPath() %>/adicionarSessao" method="POST">
            <button type="submit">Adicionar Sessão</button>
        </form>
        
        
        <form action="<%= request.getContextPath() %>/adicionarCinema" method="POST">
            <button type="submit">Adicionar Cinema</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
