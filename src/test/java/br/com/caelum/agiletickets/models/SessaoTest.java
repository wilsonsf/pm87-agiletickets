package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.agiletickets.domain.precos.SessaoTestDataBuilder;

public class SessaoTest {

	@Test
	public void deveVenderIngressosSeAQuantidadeEhMenorQueAsVagas() throws Exception {
		Sessao sessao = new SessaoTestDataBuilder()
			.comTotalIngressos(2)
			.build();
        
        assertEquals(true, sessao.podeReservar(1));
	}

	@Test
	public void naoDeveVenderIngressosSeAQuantidadeEhMaiorQueAsVagas() throws Exception {
		Sessao sessao = new SessaoTestDataBuilder()
			.comTotalIngressos(2)
			.build();

		assertEquals(false, sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new SessaoTestDataBuilder()
			.comTotalIngressos(5)
			.build();

		sessao.reserva(3);
		assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	@Test
	public void deveReservarTodosIngressosDisponiveis(){
		Sessao sessao = new SessaoTestDataBuilder()
			.comTotalIngressos(5)
			.build();
		
		assertTrue(sessao.podeReservar(5));
		
		sessao.reserva(5);
		assertTrue(sessao.getIngressosReservados().intValue() == 5);
		assertTrue(sessao.getIngressosDisponiveis().intValue() == 0);
	}
	
}
