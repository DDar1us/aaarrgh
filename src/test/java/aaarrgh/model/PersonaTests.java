package aaarrgh.model;

import org.junit.Assert;
import org.junit.Test;

public class PersonaTests {

	@Test
	public void testThatGivesFullName() {
		Persona person = new Persona();
		person.setApellido("sparrow");
		person.setNombre("jack");
		
		Assert.assertEquals("a person named 'jack' and with surname 'sparrow' has 'jack sparrow' as full name",
				"jack sparrow", person.getFullName());
		
	}
	
	@Test
	public void testPasswordUsuario() {
		Persona person = new Persona();
		person.setPassword("sparrow");
		person.setNombre("jack");
		
		Assert.assertEquals("el password del usuario es ",
				"sparrow", person.getPassword());
		
		Assert.assertEquals("el nombre del usuario es ",
				"jack", person.getNombre());
		
	}
	
}
