package br.pucrs.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TelefoneTest {
	private Telefone c1;
	
	@Before
	public void setUp() throws Exception{
		c1 = new Telefone("C1", new Antena("A1", 4, new Intervalo(1,1)));
	}

	@Test
	public void testStringDouble() {
		assertNotNull(c1);
	}
	
	@Test
	public void testGetIdent() {
		assertEquals(c1.getIdent(), "C1");
	}
	
	@Test
	public void testGetAntenaAssociada() {
		System.out.println("AntenaAssociada: " + c1.getAntenaAssociada());
	}
	
}
