<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Eventus</title>
    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- Font Awesome -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Itim&family=Permanent+Marker&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Teko:wght@300..700&display=swap" rel="stylesheet">
<style>
      body {
        background-color: #0d1117;
        color: #c9d1d9;
      }

      header {
            background-color: rgba(13, 25, 43, 0.9);
        border-bottom: 1px solid #30363d;
        height: 80px;
      }

      .dropdown-menu {
        background-color: #161b22;
        border: 1px solid #30363d;
        border-radius: 8px;
        padding: 0.5rem 1rem;
      }

      .dropdown-menu .dropdown-item {
        color: #c9d1d9;
        padding: 0.5rem;
        border-radius: 4px;
        transition: background-color 0.3s ease;
      }

      .dropdown-menu .dropdown-item:hover {
        background-color: #21262d;
        color: #ffffff;
      }

      .dropdown a {
        display: flex;
        align-items: center;
        gap: 0.5rem;
        color: #c9d1d9;
        text-decoration: none;
      }

      .dropdown a:hover {
        color: #58a6ff;
      }
     .btn-primary {
        background-color: #0d1117;
        border: none;
    }

    .btn-primary:hover {
        background-color: #1a1d22;
    }

      header a {
        text-decoration: none;
        font-weight: 500;
        transition: color 0.3s;
      }

      header a:hover {
        color: #ccc;
      }

      a {
        font-size: 20px;
      }

      .logo {
        width: 250px;
        height: auto;
        margin-right: 60px;
      }

      .search-icon {
        margin-right: 20px;
      }

      .profile-icon {
        margin-left: 10px;
      }

      .login-dropdown {
        width: 400px;
        background-color: #1a1a1a;
        color: white;
        border-radius: 8px;
        padding: 16px;
      }

      .login-column {
        flex: 1;
        padding: 0 15px;
      }

      .login-new-client {
        border-right: 1px solid gray;
      }

      .login-new-client h6,
      .login-existing-client h6 {
        margin-bottom: 10px;
      }

      .login-new-client p {
        font-size: 14px;
        color: #b0b0b0;
        margin-bottom: 16px;
      }

      .create-account-btn {
        background-color: #6f2ed6;
        border: none;
      }

      .create-account-btn:hover {
        background-color: #5821a9;
      }

      .login-existing-client a {
        color: white;
      }

      .login-existing-client a:hover {
        text-decoration: underline;
      }

      .carousel-inner img {
        height: 600px;
        object-fit: cover;
      } 
          .form-control {
        background-color: rgba(255, 255, 255, 0.3);
        border: 1px solid rgba(255, 255, 255, 0.5);
        color: #333;
        padding-right: 35px;
        width: 100%;
        padding: 10px 15px;
    }
      h2{
        font-family: "Permanent Marker", cursive;
        font-weight: 600;
        font-style: normal;
      }
       h1{
        font-family: "Bebas Neue", sans-serif;
        font-weight: 400;
        font-style: normal;
      }

      section h1 {
        font-size: 2.5rem;
        font-weight: bold;
      }

      .btn-outline-dark {
        border-color: #000;
        color: #000;
      }

      .btn-outline-dark:hover {
        background-color: #000;
        color: #fff;
      }
      .banner-wrapper {
    background: linear-gradient(
        to left, 
        #0d1117 0%,          
        rgba(13, 25, 43, 0.8) 25%, 
        rgba(13, 25, 43, 0.4) 50%, 
        transparent 70%,          
        rgba(13, 25, 43, 0.4) 80%, 
        rgba(13, 25, 43, 0.8) 90%,
        #0d1117 100%       
    );
}

.banner {
    position: relative;
    background-size: cover;
    margin: 0 auto;
    width: 70%; 
    height: 300px;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
}

.banner .overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.6);
    z-index: 1;
}

.banner .container {
    position: relative;
    z-index: 2;
}

h1.display-4 {
    font-size: 2rem;
    font-weight: bold;
}
    
    label {
        display: block;
        text-align: center;
    }

      footer {
        background-color: #0d192b;
      }
      footer img{
          width: 45px;
          height: 40px;
          border-radius: 3px;
      }
    
    </style>
  </head>
  <body>
    <header class="text-white py-2 sticky-top">
      <div class="container d-flex justify-content-between align-items-center">
        <!-- Navigation Left -->
        <nav class="d-flex align-items-center">
          <!-- Dropdown Filmes -->
          <div class="dropdown me-3">
            <a
              href="#"
              class="text-white"
              id="filmesDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Filmes
            </a>
            <ul class="dropdown-menu" aria-labelledby="filmesDropdown">
              <li>
                <a class="dropdown-item" href="ListarEmCartazServlet"
                  ><i class="fas fa-film me-2"></i>Em Cartaz</a
                >
              </li>
              <li>
                <a class="dropdown-item" href="ListarEmBreveServlet"
                  ><i class="fas fa-clock me-2"></i>Em Breve</a
                >
              </li>
              <li>
                <a class="dropdown-item" href="ListarPreEstreiaServlet"
                  ><i class="fas fa-star me-2"></i>Pré Estreia</a
                >
              </li>
            </ul>
          </div>

          <!-- Dropdown Eventos -->
          <div class="dropdown me-3">
            <a
              href="#"
              class="text-white"
              id="eventosDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Eventos
            </a>
            <ul class="dropdown-menu" aria-labelledby="eventosDropdown">
              <li>
                <a class="dropdown-item" href="ListarStandUpServlet">
                  <i class="fas fa-microphone-alt me-2"></i> Stand-up
                </a>
              </li>
              <li>
                <a class="dropdown-item" href="ListarFestivalServlet">
                  <i class="fas fa-music me-2"></i> Festivais
                </a>
              </li>
              <li>
                <a class="dropdown-item" href="ListarExposicaoServlet">
                  <i class="fas fa-paint-brush me-2"></i> Exposições
                </a>
              </li>
            </ul>
          </div>


          <!-- Dropdown Cinemas -->
          <div class=" me-3">
            <a
              href="ListarCinemasServlet"
              class="text-white"
              id="cinemasDropdown"
            >
              Cinemas
            </a>
          </div>

          <!-- Dropdown Sobre -->
            <div class="dropdown">
                <a
                  href="#"
                  class="text-white"
                  id="marcaDropdown"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Sobre
                </a>
                <ul class="dropdown-menu" aria-labelledby="sobreDropdown">
                  <li>
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-envelope me-2"></i> Contato
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      <i class="fas fa-book me-2"></i> História
                    </a>
                  </li>
                </ul>
              </div>
        </nav>

        <!-- Logo -->
       <div class="navbar-brand mx-auto">
        <a href="index.jsp">
          <img src="img/logosemfundo.png" alt="Logo" class="logo" />
        </a>
      </div>


        <!-- Navigation Right -->
        <div class="d-flex align-items-center">
          <div class="dropdown me-3 search-icon">
            <a
              href="#"
              class="text-white"
              id="searchDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="fas fa-search"></i>
            </a>
            <div
              class="dropdown-menu dropdown-menu-end p-3"
              style="min-width: 300px"
            >
              <form>
                <div class="mb-3">
                  <label for="searchInput" class="form-label text-white"
                    >Resultados da sua busca</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="searchInput"
                    placeholder="Digite aqui..."
                  />
                </div>
                <button type="submit" class="btn btn-primary w-100">
                  Pesquisar
                </button>
              </form>
            </div>
          </div>
          <!-- Ícone de Perfil -->
           <div class="dropdown profile-icon">
            <a
              href="#"
              class="text-white"
              id="loginDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="fas fa-user me-2"></i> Entre ou Cadastre-se
            </a>
            <div class="dropdown-menu dropdown-menu-end login-dropdown">
              <div class="d-flex justify-content-between">
                <div class="login-column login-new-client">
                  <h6 class="fw-bold">Ainda não é cliente?</h6>
                  <a
                    href="cadastrarUsuario"
                    class="btn btn-primary w-100 text-white"
                  >
                    Criar Conta
                  </a>
                </div>
                <div class="login-column login-existing-client">
                  <h6 class="fw-bold">Já sou cliente</h6>
                  <a href="login" class="text-white text-decoration-none btn btn-primary w-100"
                    >Entrar</a
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
