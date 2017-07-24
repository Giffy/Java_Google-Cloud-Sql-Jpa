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
import model.Item;
import dao.DBAdmin;



@SuppressWarnings("serial")
public class CreateItem extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		/* Web title and content */
	      String title = "Dar de alta un item";
	      String content = "Crear un nuevo item";
	      StringBuilder builder = new StringBuilder();
	      String message="";
		
		// Set response content type
	      response.setContentType("text/html");
	      PrintWriter out= response.getWriter();
	      	      
	      String galId = req.getParameter("galId");
	      String name= req.getParameter("name");
	      String description= req.getParameter("description");
	      String strPrice= req.getParameter("price");
	      
	      boolean checkOK= true;
	      int galleryId = 0;
	      try{
	    	  galleryId = Integer.valueOf(galId);	      
	      } catch (RuntimeException e ){	    	  
	    	  galleryId  = 0;
		      checkOK= false;
	      }

	      float price= 0;
	      try{
	    	  price = Float.valueOf(strPrice);	      
	      } catch (RuntimeException e ){	    	  
	    	  price  = 0;		      
	      }
	      	            
	   
		// servlet action
	      if (checkOK){
		      DBAdmin dbhelper= new DBAdmin();
		      Item newItem= new Item();
		      Set<Item> listItems = new HashSet();
		      		      
		      try{
		      listItems= dbhelper.getItems(galleryId);
		      }catch (RuntimeException e){
		    	  checkOK=false;
		      }
		      if (checkOK){
			
			      int size= listItems.size();
			      
			      if (size!=0 && checkOK){
				      for (Item item: listItems){
				    	  if (item.getDescription().contains(description))
				    		  checkOK = false;
				      }
			      } 	      	      
		
			      if (checkOK){
		    		  Gallery gallery= new Gallery();
		    		  gallery.setId(galleryId);
		    		  newItem.setDescription(description);
		    		  newItem.setName(name);	    		  	    		  
		    		  newItem.setPrice(price);
			    	  dbhelper.createItem(gallery, newItem);
		    	  }	
		      }
	      }
	  	// servlet reply	      
	      
	      if(checkOK){
	    	  message="<tr><td colspan=2><font color=white>&nbsp &nbsp El Item se ha dado de alta correctamente. </font></td></tr>";
	      }else{
	    	  message="<tr><td colspan=2><font color=white>&nbsp &nbsp ID de la galeria no existe.</font></td></tr>";
	      }

	      builder.append(message);	      
	      
	      
	      	      
	      
	      
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