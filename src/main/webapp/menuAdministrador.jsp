<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Menú Admin - Banco Honse</title>

  
  <style>
  
	body {
      margin: 0;
      background: linear-gradient(
        0deg,
        rgba(44, 144, 170, 1) 0%,
        rgba(113, 190, 196, 0.616) 70%
      );
      font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
        Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
        sans-serif;
        min-height: 100dvh;
    }

    main {
      width: 100%;
      margin: 3em auto;   
    }

    #content {
      width: 80%;
      margin: 0 auto;
      display: flex;
      flex-direction: column;
      gap: 2em;
    }

    .post {
      background-color: white;
      padding: 1em;
      box-shadow: 15px 20px 10px rgba(32, 32, 32, 0.212);
      border: 1px solid rgba(77, 180, 187, 0.637);
    }
  </style>
</head>

<body>
  <%@ include file="./HeaderAdmin.jsp" %> 
  <main>
    <div id="content">
      <div class="post">
        <h2>¿Qué es un Administrador?</h2>
        <p>El administrador es un tipo de usuario especialmente designado por el banco para llevar a cabo tareas de carácter técnico y 
            organizativo dentro del sistema. Su rol no se limita a operar con datos, sino que implica una visión integral del funcionamiento
            de la plataforma...</p>
      </div>
      <div class="post">
        <h2>Responsabilidades</h2>
        <ul>
          <li>Velar por la integridad de los datos del banco.</li>
          <li>Supervisar la actividad de otros usuarios.</li>
          <li>Garantizar el cumplimiento de las políticas internas.</li>
          <li>Reportar actividades inusuales o sospechosas.</li>
        </ul>
      </div>
      <div class="post">
        <h2>Permisos</h2>
        <ul>
          <li>ABML Clientes: Agregar, borrar, modificar y listar los clientes de la BD.</li>
          <li>ABML Cuentas: Agregar, borrar, modificar y listar las cuentas de la BD.</li>
          <li>Autorización de Préstamos: Autorizar o rechazar préstamos solicitados.</li>
          <li>Reportes: Ver estadísticas del banco.</li>
        </ul>
      </div>
    </div>
  </main>
</body>
</html>
