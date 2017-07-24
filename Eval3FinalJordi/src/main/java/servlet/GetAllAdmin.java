package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import dao.DBAdmin;



@SuppressWarnings("serial")
public class GetAllAdmin extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		/* Web title and content */
	      String title = "Listado de administradores";
	      String content = "Listar los Administradores en la base de datos";
	      StringBuilder builder = new StringBuilder();
	      String message="";
		
		// Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();


		// servlet action
	      DBAdmin dbhelper = new DBAdmin();
	      Set<Admin> listAdmin = dbhelper.getAllAdmin();
	      
	      
	      if (listAdmin.size()==0){
	    	  
	    	  message = "<tr><td colspan=2 style=font-weight:bold > No hay administradores en la base de datos</td></tr>";
	    	  builder.append(message);
	    	  
	      }else{
	    	  message = "<tr>"+
	  			  	  	"<td colspan=2 style=font-weight:bold; ><font color=white>&nbsp &nbsp ID</font></td>"+
	  			  	  	"<td colspan=2 style=font-weight:bold; ><font color=white>&nbsp &nbsp Nombre</font></td>"+
	  			  	  	"</tr>";
	    	  builder.append(message);
		        
		      for (Admin admin: listAdmin){
		    	  message= "<tr>"+
		    			   "<td colspan=2><font color=white>&nbsp &nbsp "+ admin.getId()+ "</font></td>"+
		    			   "<td colspan=2><font color=white>&nbsp &nbsp "+	admin.getName() + "</font></td>"+
		    			   "</tr>";	    	  
		    	  builder.append(message);
		      }
	      } 	      	      
	      
	      
	      
	      
	      
	      
	      
	      

	      
	      
	      
	      
	      	      
	      
	      
	      
	      /* Template Web - Do not edit*/
	      
	      out.println(
	    		  "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n"+
	    		  "<html>"+
	    		  "<head>"+
	    		  "    <meta charset=utf-8>"+
	    		  "    <meta http-equiv=X-UA-Compatible content=IE=edge>"+
	    		  "    <meta name=viewport content=width=device-width, initial-scale=1, shrink-to-fit=no>"+
	    		  "    <meta name=description >"+
	    		  "    <meta name=author >"+
	    		  "	  <title>"+ title +"</title>"+
	    		  "   <!-- Bootstrap core CSS --><link href=vendor/bootstrap/css/bootstrap.min.css rel=stylesheet>"+
	    		  "   <!-- Custom fonts for this template --><link href=vendor/font-awesome/css/font-awesome.min.css rel=stylesheet type=text/css>"+
	    		  "   <!-- Plugin CSS --><link href=vendor/datatables/dataTables.bootstrap4.css rel=stylesheet>"+
	    		  "   <!-- Custom styles for this template --><link href=css/sb-admin.css rel=stylesheet>"+
	    		  "	  </head>"+
	    		  "   <body bgcolor = #292B2C  id=page-top >"+
	    		  "   <!-- Navigation -->"+
	    		  "     <nav id=mainNav class=navbar static-top navbar-toggleable-md navbar-inverse bg-inverse >"+
	    		  "     <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarExample\" aria-controls=\"navbarExample\" "
	    		  		+ "aria-expanded=\"false\" aria-label=\"Toggle navigation\">"+
	    		  "          <span class=navbar-toggler-icon></span>"+
	    		  "		</button>"+
	    		  "		<a class=navbar-brand href=index.htm><font color=white>" + content + "</font></a>"+
	    		  "     </nav>"+
	    		  "   <table>"+
	    		  "     <tr>"+
	    		  "       <td><br></br>"+
	    		  "<table> <br><font color=white>"+
  		  		builder.toString()+	      
  		          "</font></table>"+
	    		  "<br><br>&nbsp &nbsp <a href=/index.htm><font color=white size=2>Volver al menu principal</font></a>"+
	    		  "</body>"+
	    		  "</html>"
	    		);
	     	
	}
}