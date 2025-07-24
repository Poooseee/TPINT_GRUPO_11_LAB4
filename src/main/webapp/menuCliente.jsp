<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="entidades.Cliente" %>
    <%@ page import="entidades.Usuario" %>
    <%@ page import="entidades.Cuenta" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%@ page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Menú Cliente - Banco Honse</title>
</head>
   
<style>
    body{
        margin: 0;
        background: #2C90AA;
        background: linear-gradient(0deg,rgba(44, 144, 170, 1) 0%, rgba(255, 252, 253, 1) 90%);
        background-repeat: no-repeat;
        background-size: cover;
        min-height: 100dvh;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    a{
        text-decoration: none;
    }
    h1{
        text-align: center;
    }
    button{
        all: unset;
        cursor: pointer;
    }

    .inicio{
        display: flex;
    }
    .inicio p{
        margin: auto;
    }
    #logoBanco{
        width: 75px;
    }

    .saldo{
        margin: 6% auto 0 auto;
        width: 75%;
        height: 20vh;
        background-color: rgba(255, 252, 253, 1);
        border-radius: 10px;
        display:flex;
        flex-direction:column;
    }
    .saldoSuperior{
        display: flex;
        justify-content: space-between;
        align-items:center;
    }
    .saldoSuperior a{
        margin: 0.5% 1% 0 0;
    }
    .saldoSuperior h2{
        padding-left: 1%;
    }
    .saldoInferior{
      flex-grow: 1;
	  display: flex;
	  align-items: center;
	  justify-content: center;
	  font-size: 2em;
	  overflow: hidden;
    }
    .saldoInferior p{
		
    }
    .btnsAcciones{
        margin: 1% auto 10% auto;
        width: 75%;
        border-radius: 10px;
    }
    .btnsAccionesImgs{
        display: flex;
        justify-content: space-around;
    }
    .btnsAcciones a{
        background-color: rgba(255, 252, 253, 1);
        padding: 1%;
        border-radius: 100%;
    }
    .btnsAcciones img{
        width: 96px;
    }
    .btnsAccionesTxt{
        display: flex;
        justify-content: space-around;
        margin-right: 1%;
    }
    .cuentas{
     display: flex;              
     flex-direction: row;        
     justify-content: center ;
     align-items: center;       
     gap: 1rem;  
     margin: 5% auto 0 auto;
        width: 75%;
        height: 12vh;
        background-color: rgba(255, 252, 253, 1);
        border-radius: 10px;
    }
    select{
        width: 300px;               
  font-size: 18px;            
  padding: 0.5em 1em;         
  box-sizing: border-box; 
    }
</style>
<body>

    <%@ include file="./HeaderCliente.jsp" %>
    
    
    <main>
	    <div class="cuentas">
		<%
		ArrayList <Cuenta> cuentas = new ArrayList<>(); 
		if(request.getAttribute("cuentasCliente") != null)
			 cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentasCliente");
		
		String cbuSeleccionado = (String)request.getAttribute("cbuSeleccionado");
		if (cbuSeleccionado == null && cuentas != null && !cuentas.isEmpty()) {
		    cbuSeleccionado = cuentas.get(0).getCbu();
		}
		
		%>
	     <h2 style=margin-left:15px>Cuentas</h2>
	    
	    <form method="post" action="ServletClientes">
		     <select id="selectCuentas" name="cbuSeleccionado" onchange="this.form.submit()">
		     	<%
		     		if(cuentas != null && !cuentas.isEmpty()){
		     			for(Cuenta cuenta : cuentas){
		     				String cbu = cuenta.getCbu();
		     				int numeroCuenta = cuenta.getNumero();
		                    Boolean seleccionado = false;
		                    if(request.getParameter("cbuSeleccionado") != null && request.getParameter("cbuSeleccionado").equals(cbu)){
		                        seleccionado = true;
		                    }
		     				%>
		     					<option value=<%=cbu%> <%=seleccionado? "selected" : "" %>><%= numeroCuenta +" - "+ cbu %></option>
		     				<%
		     			}
		     		}else{
		     			%>
		     				<option value="-">Aún tiene cuentas</option>
		     			<%
		     		}
		     	%>
		     </select>
		</form>
	    </div>
    
        <div class="saldo">
            <div class="saldoSuperior">
                <h2>Saldo disponible:</h2>
                <a href="${pageContext.request.contextPath}/ServletMovimientos?cbuSeleccionado=<%= cbuSeleccionado%>">Ir a movimientos</a>
            </div>
            <%
				String saldoCuentaSeleccionada =  String.valueOf(request.getAttribute("saldoCuentaSeleccionada")) ;
            	if(saldoCuentaSeleccionada == null || "null".equals(saldoCuentaSeleccionada)) saldoCuentaSeleccionada = "00.0";
            %>
		<div class="saldoInferior">
		    <p>$<%= saldoCuentaSeleccionada %></p>
		</div>
		
        </div>
        
        <%
        if(!cuentas.isEmpty()){
        	%>
        	<div class="btnsAcciones">
            <div class="btnsAccionesImgs">
            	<%
            	String numeroCuenta = String.valueOf(request.getAttribute("numeroCuenta"));
            	if ((numeroCuenta == null || "null".equals(numeroCuenta)) && cuentas != null && !cuentas.isEmpty()) {
            	    numeroCuenta = String.valueOf(cuentas.get(0).getNumero());
            	}
            	%>
                <a href="ServletTransferencias?cbuSeleccionado=<%= cbuSeleccionado %>&numeroCuenta=<%=numeroCuenta%>">
                	<img src="imgs/logoTransferencia.png" alt="logoTransferencia">
                </a>
                <a href="PagoPrestamoServlet?cbuSeleccionado=<%= cbuSeleccionado %>">
                	<img src="imgs/logoPagaPrestamos.png" alt="logoPagaPrestamos" style="transform: scale(0.8);" >
                </a>
                <a href="ServletPrestamos?cbuSeleccionado=<%= cbuSeleccionado %>&saldoCuentaSeleccionada=<%=saldoCuentaSeleccionada%>&numeroCuenta=<%=numeroCuenta%>">
                	<img src="imgs/logoPrestamo.png" alt="logoPrestamo">
                </a>
            </div>
            <div class="btnsAccionesTxt">
                <h3>Transferencia</h3>
                <h3>Pagá tus préstamos</h3>
                <h3>Préstamo</h3>
            </div>
        </div>
        	<%
        }
        %>
        
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
</body>
</html>