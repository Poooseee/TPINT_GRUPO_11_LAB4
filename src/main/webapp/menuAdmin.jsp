<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menú Admin - Banco Honse</title>
</head>
<style>
    body{
        margin: 0;
        background-color: rgb(219, 220, 221);
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    a{
        text-decoration: none;
    }
    h1{
        text-align: center;
        color: rgba(49, 49, 49, 0.527);
    }
    nav{
        display: flex;
        text-align: center;
        justify-content: space-between;
        padding: 1% 2.5%;
        margin: 0 1%;
        background-color: rgb(255, 255, 255);
    }
    nav a{
        color: black;
        transition: transform 0.3s ease-in-out;
    }
    main{
        background-color: rgb(255, 255, 255);
        margin: 3.5% 1%;
        
    }
    main p{
        padding-left: 1%;
    }
    main h2{
        padding-left: 1%;
    }
    .queEsAdmin{
        padding: 2% 0;
    }
    .responsabilidadesAdmin{
        padding-bottom: 2%;
    }
    .permisosAdmin{
        padding-bottom: 2%;
    }
    nav a:hover{
        transform: scale(1.2);
        color: #007BFF;
    }
</style>
<body>
    <header>
        <a href="menuAdmin.jsp"><h1>Administrador</h1></a>
        <nav>
            <a href="abmlClientes.jsp">Clientes</a>
            <a href="abmlCuentas.jsp">Cuentas</a>
            <a href="autorizacionPrest.jsp">Préstamos</a>
            <a href="reporte.jsp">Reporte</a>
        </nav>
    </header>
    <main>
        <div class="queEsAdmin">
            <h2>¿Qué es un Administrador?</h2>
            <p>El administrador es un tipo de usuario especialmente designado por el banco para llevar a cabo tareas de carácter técnico y 
                organizativo dentro del sistema. Su rol no se limita a operar con datos, sino que implica una visión integral del funcionamiento
                de la plataforma, interviniendo en procesos clave para asegurar su correcto desempeño. Como superusuario, posee privilegios 
                extendidos que le permiten gestionar la información registrada, coordinar operaciones sensibles y supervisar el trabajo de otros
                perfiles de usuario. Su participación es fundamental para mantener el orden, la coherencia y la eficiencia de la gestión interna.</p>
        </div>
        <div class="responsabilidadesAdmin">
            <h2>Responsabilidades</h2>
            <ul>
                <li>Velar por la integridad de los datos del banco.</li>
                <li>Supervisar la actividad de otros usuarios.</li>
                <li>Garantizar el cumplimiento de las políticas internas.</li>
                <li>Reportar actividades inusuales o sospechosas.</li>
            </ul>
            </div>
        <div class="permisosAdmin">
            <h2>Permisos</h2>
            <ul>
                <li>ABML Clientes: Agregar, borrar, modificar y listar los clientes de la BD.</li>
                <li>ABML Cuentas: Agregar, borrar, modificar y listar las cuentas de la BD.</li>
                <li>Autorización de Préstamos: Autorizar o rechazar los préstamos solicitados por el cliente</li>
                <li>Reporte: Acceso a una sección de reportes, donde se muestran datos estadísticos generales del banco.</li>
            </ul>
        </div>
    </main>
    <footer>

    </footer>
</body>
</html>