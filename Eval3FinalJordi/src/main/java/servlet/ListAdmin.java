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
public class ListAdmin extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		/* Web title and content */
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
		      for (Admin admin: listAdmin){
		    	  message = "<input type=radio name=admId value=" + admin.getId() +" >"+ admin.getName() +"<br>";
		    	  builder.append(message);
		      }
	      } 	      	      
	      
	      
	      
	      
	      
	      
	      
	      

	      
	      
	      
	      
	      	      
	      
	      
	      
	      /* Template Web - Do not edit*/
	      
	      out.println(
  		  		builder.toString()	      
	    		);
	     	
	}
}