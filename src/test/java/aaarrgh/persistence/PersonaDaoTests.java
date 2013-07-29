package aaarrgh.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aaarrgh.model.Persona;

public class PersonaDaoTests {

	PersonaDao dao = DaoFactory.getPersonaDao();

	Persona MatiasCabanias;
	Persona LucasQuevedo;
	Persona CarolinaVera;
	Persona JoseQuintana;

	@Before
	public void setUp() throws PersistenceException {
		// se borran todas las personas, para iniciar con base vacía
		for (Persona cadaPersona : dao.findAll()) {
			dao.delete(cadaPersona);
		}

		// se inserta a Cosme Fulanito
		MatiasCabanias = buildPersona(1, "Matias", "Cabanias", 26, "576534");
		dao.insert(MatiasCabanias);

		// Chuck Norris decide insertarse
		LucasQuevedo = buildPersona(2, "Lucas", "Quevedo", 20, "hola2");
		dao.insert(LucasQuevedo);
		
		CarolinaVera = buildPersona(3, "Carolina", "Vera", 20, "123");
		dao.insert(CarolinaVera);
		
		JoseQuintana = buildPersona(4, "Jose", "Quinta", 25, "456");
		dao.insert(JoseQuintana);
	}

	private Persona buildPersona(Integer id, String nombre, String apellido, Integer edad, String password) {
		Persona persona = new Persona();
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setEdad(edad);
		persona.setPassword(password);

		return persona;
	}

	@Test
	public void testQueSePuedeBuscarUnaPersona() throws PersistenceException {

		Persona personaEncontrada = dao.findById(MatiasCabanias.getId());

		assertNotNull("la persona con id 1 debe existir", personaEncontrada);
		assertEquals("la persona 1 tiene nombre: Cosme", "Matias", personaEncontrada.getNombre());
		assertEquals("la persona 1 tiene apellido: Fulanito", "Cabanias", personaEncontrada.getApellido());
		assertEquals("la persona 1 tiene edad: 26", 26, (int)personaEncontrada.getEdad());

	}
	
	@Test
	public void testApellido() throws PersistenceException{
		Persona login = dao.authenticate("Matias", "576534");
		assertEquals("login 576534", "576534", login.getPassword());
		
	}
	
	
	
	
//	@After
//	public void tearDown() throws PersistenceException {
//		// se borran todas las personas
//
//		dao.delete(cosmeFulanito);
//		dao.delete(chuckNorris);
//
//	}
//
//	@Test
//	public void testQueSePuedeBuscarUnaPersona() throws PersistenceException {
//
//		Persona personaEncontrada = dao.findById(cosmeFulanito.getId());
//
//		assertNotNull("la persona con id 1 debe existir", personaEncontrada);
//		assertEquals("la persona 1 tiene nombre: Cosme", "Cosme", personaEncontrada.getNombre());
//		assertEquals("la persona 1 tiene apellido: Fulanito", "Fulanito", personaEncontrada.getApellido());
//		assertEquals("la persona 1 tiene edad: 42", 42, (int)personaEncontrada.getEdad());
//
//	}
//
//	@Test
//	public void testQueSePuedeInsertarUnaPersona() throws PersistenceException {
//
//		Persona maxPower = buildPersona(3, "Max", "Power", 37);
//		assertEquals("antes de insertar hay 2 personas", 2, dao.findAll().size());
//
//		dao.insert(maxPower);
//		assertEquals("luego de insertar hay 3 personas", 3, dao.findAll().size());
//		assertNotNull("puede encontrarse a la persona con id 3", dao.findById(maxPower.getId()));
//
//	}
//
//	@Test
//	public void testQueSePuedeBorrarUnaPersona() throws PersistenceException {
//
//		Persona personaEncontrada = dao.findById(cosmeFulanito.getId());
//		dao.delete(personaEncontrada);
//
//		personaEncontrada = dao.findById(1);
//		assertNull("la persona con id 1 no debe existir", personaEncontrada);
//
//	}
//
//	@Test
//	public void testQueSePuedeActualizarUnaPersona() throws PersistenceException {
//
//		Persona personaEncontrada = dao.findById(cosmeFulanito.getId());
//		assertEquals("la persona con id 1 se llama Cosme", "Cosme", personaEncontrada.getNombre());
//		personaEncontrada.setNombre("Gertrudis");
//		dao.update(personaEncontrada);
//		assertEquals("la persona con id 1 ahora se llama Gertrudis", "Gertrudis", personaEncontrada.getNombre());
//
//	}
//
//	@Test
//	public void testQueSePuedenBuscarTodasLasPersonas() throws PersistenceException {
//
//		List<Persona> todasLasPersonas = dao.findAll();
//		assertEquals("se espera que haya dos personas en la base", 2, todasLasPersonas.size());
//
//	}

}
