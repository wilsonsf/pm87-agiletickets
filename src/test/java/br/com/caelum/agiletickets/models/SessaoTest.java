package br.com.caelum.agiletickets.models;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	
	@Test
	public void deveVenderIngressosSeAQuantidadeEhMenorQueAsVagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);
        
        Assert.assertEquals(true, sessao.podeReservar(1));
	}

	@Test
	public void naoDeveVenderIngressosSeAQuantidadeEhMaiorQueAsVagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertEquals(false, sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	@Test
	public void deveReservarTodosIngressosDisponiveis(){
		Espetaculo espetaculo = new Espetaculo();
		
		espetaculo.setTipo(TipoDeEspetaculo.SHOW);
		
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);
		
		Assert.assertTrue(sessao.podeReservar(5));
	}
}
