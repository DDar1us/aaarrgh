package aaarrgh.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import aaarrgh.model.Tweet;

public class TweetDaoTest {

	TweetDao dao = DaoFactory.getTweetDao();

	Tweet mensaje_1;
	Tweet mensaje_2;

	@Before
	public void setUp() throws PersistenceException {
		// se borran todos los tweets de la base de datos
		for (Tweet cadaTweet : dao.findAll()) {
			dao.delete(cadaTweet);
		}

		// se agregan tweets:
		mensaje_1 = buildTweet(1, "Probando tp Tweet 1", 1);
		dao.insert(mensaje_1);
		mensaje_2 = buildTweet(2, "Probando tp Tweet 2", 1);
		dao.insert(mensaje_2);

	}

	private Tweet buildTweet(Integer idTweet, String tweets, Integer Idusuario) {
		Tweet tweet = new Tweet();
		tweet.setIdTweet(idTweet);
		tweet.setTweet(tweets);
		tweet.setIdusuario(Idusuario);

		return tweet;
	}

	// Borro los tweets
	@After
	public void tearDown() throws PersistenceException {
		dao.delete(mensaje_1);
		dao.delete(mensaje_2);

	}

	@Test
	public void testQueSePuedeBuscarUnTweet() throws PersistenceException {

		Tweet tweetEncontrado = dao.findById(mensaje_1.getIdTweet());

		assertNotNull("El tweet con id 1 debe existir", tweetEncontrado);
		assertEquals("El tweet 1 debe contener: Probando tp Tweet 1", "Probando tp Tweet 1",
				tweetEncontrado.getTweet());
		assertEquals("El tweet 1 es del usuario: Cosme", 1,
				(int) tweetEncontrado.getIdusuario());// modificar
	}

	@Test
	public void testQueSePuedeInsertarUnTweet() throws PersistenceException {

		Tweet mensaje_3 = buildTweet(3, "Probando Tp Tweet 3", 1);
		assertEquals("antes de insertar hay 2 tweet", 2, dao.findAll().size());

		dao.insert(mensaje_3);
		assertEquals("luego de insertar hay 3 tweet", 3, dao.findAll().size());
		assertNotNull("puede encontrarse al tweet con id 3",
				dao.findById(mensaje_3.getIdTweet()));

	}

	@Test
	public void testQueSePuedeBorrarUnTweet() throws PersistenceException {

		Tweet tweetEncontrado = dao.findById(mensaje_2.getIdTweet());
		dao.delete(tweetEncontrado);

		tweetEncontrado = dao.findById(2);
		assertNull("El tweet con id 2 no debe existir", tweetEncontrado);

	}

	@Test
	public void testQueSePuedenBuscarTodosLosTweets()
			throws PersistenceException {

		List<Tweet> todosLosTweets = dao.findAll();
		assertEquals("se espera que haya 2 tweets en la base", 2,
				todosLosTweets.size());

	}
	
	
}
