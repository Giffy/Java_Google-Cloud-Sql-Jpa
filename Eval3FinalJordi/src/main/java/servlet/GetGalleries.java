package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import model.Gallery;
import dao.DBAdmin;


@SuppressWarnings("serial")
public class GetGalleries extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		/* Web title and content */
	      String title = "Listado de galerias";
	      String content = "Lista las galerias de un administrador";
	      StringBuilder builder = new StringBuilder();
	      String message="";
	
		// Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      
	      String admId = req.getParameter("admId");
	      int adminId = 0;
	      try{
	    	  adminId = Integer.valueOf(admId);	      
	      } catch (RuntimeException e ){	    	  
	    	  adminId  = 0;
	      }

		// servlet action
	      DBAdmin dbhelper = new DBAdmin();

	      
	      Set<Gallery> listGallery = new HashSet ();
	      boolean checkOK = true;	      
	      try{
	    	  listGallery = dbhelper.getGalleries(adminId);
	      }catch (RuntimeException e ){	    	  
	    	  checkOK = false;
	      }
	      
	      
	      if (listGallery.size()==0)  checkOK = false;
	     
	      
	      
	      // servlet reply	      
	      
	      if(checkOK!=true){
	    	  message= "&nbsp &nbsp" + "El ID: " + adminId + " introducido no es valido o no existe en la base de datos.";
	    	  builder.append(message);
	      }else{
	    	  message = 
				  "<tr><td colspan=4><font color=white>&nbsp &nbsp Lista de galerias pertenecientes a " + dbhelper.getAdmin(adminId).getName() + "</font></td></tr>"+
				  "<tr>"+
				  "<td colspan=2 style=font-weight:bold; ><font color=white>&nbsp &nbsp ID</font></td>"+
				  "<td colspan=2 style=font-weight:bold; ><font color=white>&nbsp &nbsp Nombre</font></td>"+
				  "<td colspan=2 style=font-weight:bold; ><font color=white>&nbsp &nbsp Descripcion</font></td>"+
			  	  "</tr>";
	    	  builder.append(message);
		        
		      for (Gallery gallery: listGallery){
		    	  	message=  "<tr>"+
		    			  		"<td colspan=2><font color=white>&nbsp &nbsp "+
		    			  		gallery.getId() +
		    			  		"</font></td>"+
		    			  		"<td colspan=2><font color=white>&nbsp &nbsp "+
		    			  		gallery.getName() +
		    			  		"</font></td>"+
		    			  		"<td colspan=2><font color=white>&nbsp &nbsp "+
		    			  		gallery.getDescription() +
		    			  		"</font></td>"+
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