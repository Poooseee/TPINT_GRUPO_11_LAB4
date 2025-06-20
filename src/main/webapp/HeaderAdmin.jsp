<style>
  header {
    width: 100%;
    margin: 0;
    background-color: rgb(219, 220, 221);
    font-family: system-ui;
    height: 7em;
    display: flex;
    flex-direction: column;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1000;
  }

  #div-h2 {
    margin: 0 auto;
    height: 100%;
    place-content: center;
  }

  #nav-header {
    width: 100%;
    display: flex;
    justify-content: space-around;
    margin-bottom: 0;
    margin-top: auto;
  }

  header a {
    text-decoration: none;
    width: 100%;
    text-align: center;
  }

  #div-h2 h2 {
    text-align: center;
    margin: 0;
    color: rgba(49, 49, 49, 0.527);
    transition: all 0.15s ease-in-out;
    font-size: xx-large;
  }

  .a-nav-header {
    color: rgba(49, 49, 49, 0.76);
    background-color: rgb(219, 220, 221);
    transition: all 0.2s ease-in-out;
    padding: 0.7em;
    border-right: 1px solid rgb(190, 187, 187);
    font-weight: 600;
    font-size: medium;
  }

  .a-nav-header:last-child {
    border-right: none;
  }

  .a-nav-header:hover {
    color: white;
    background-color: rgba(77, 180, 187, 0.637);
  }

  #div-h2 h2:hover {
    color: rgba(77, 180, 187, 0.637);
  }
</style>

<header>
  <div id="div-h2">
    <a href="menuAdmin.jsp"><h2>Administrador</h2></a>
  </div>
  <nav id="nav-header">
    <a class="a-nav-header" href="abmlClientes.jsp">Clientes</a>
    <a class="a-nav-header" href="abmlCuentas.jsp">Cuentas</a>
    <a class="a-nav-header" href="autorizacionPrest.jsp">Préstamos</a>
    <a class="a-nav-header" href="reporte.jsp">Reportes</a>
  </nav>
</header>
