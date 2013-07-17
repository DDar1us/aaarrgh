package aaarrgh.model;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aaarrgh.model.Persona;


public class SesionTweet {

	
	public SesionTweet() {
		super();
	}

	public void sesion (Persona persona){	
		this.usuario = persona;
		
	}
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		 
		//creamos nuestra sesion
         HttpSession session = request.getSession(true);

         //Obtenemos los obejtos a guardar en session
         session.setAttribute("nombre", this.usuario.getNombre());
         session.setAttribute("apellido", this.usuario.getApellido());
         session.setAttribute("edad", this.usuario.getEdad());
         
         response.sendRedirect("welcome.jsp");
	 }
	
}
