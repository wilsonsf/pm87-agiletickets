package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test(timeout=1000)
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test(timeout=1000)
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test(timeout=1000)
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test(timeout=1000)
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test(timeout=1000)
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test(timeout=1000)
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test(timeout=1000)
	public void deveCriarSessaoDiariaDeUmDia() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate diaInicio = new LocalDate(2015, 11, 30);
		LocalDate diaFim = diaInicio;
		LocalTime horario = new LocalTime(22, 0, 0);

		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(diaInicio, diaFim, horario, Periodicidade.DIARIA);
		
		assertEquals(1, sessoesCriadas.size());
		assertEquals(new DateTime(2015, 11, 30, 22, 0, 0), sessoesCriadas.get(0).getInicio());
	}

	@Test(timeout=1000)
	public void deveCriarSessoesDiariasEmUmIntervaloDeVariosDias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate diaInicio = new LocalDate(2015, 11, 29);
		LocalDate diaFim = new LocalDate(2015, 12, 2);
		LocalTime horario = new LocalTime(22, 0, 0);
		
		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(diaInicio, diaFim, horario, Periodicidade.DIARIA);
		assertEquals(4, sessoesCriadas.size());
		assertEquals(new DateTime(2015, 11, 29, 22, 0, 0), sessoesCriadas.get(0).getInicio());
		assertEquals(new DateTime(2015, 11, 30, 22, 0, 0), sessoesCriadas.get(1).getInicio());
		assertEquals(new DateTime(2015, 12, 1, 22, 0, 0), sessoesCriadas.get(2).getInicio());
		assertEquals(new DateTime(2015, 12, 2, 22, 0, 0), sessoesCriadas.get(3).getInicio());
	}
	
	@Test(timeout=1000)
	public void deveCriarSessoesSemanaisEmUmIntervaloDeUmDia() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate diaInicio = new LocalDate(2015, 11, 30);
		LocalDate diaFim = diaInicio;
		LocalTime horario = new LocalTime(22, 0, 0);

		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(diaInicio, diaFim, horario, Periodicidade.SEMANAL);
		
		assertEquals(1, sessoesCriadas.size());
		assertEquals(new DateTime(2015, 11, 30, 22, 0, 0), sessoesCriadas.get(0).getInicio());
	}
	
	@Test(timeout=1000)
	public void deveCriarSessoesSemanaisEmUmIntervaloDeVariosDias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate diaInicio = new LocalDate(2015, 11, 30);
		LocalDate diaFim = diaInicio.plusWeeks(2);
		LocalTime horario = new LocalTime(22, 0, 0);

		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(diaInicio, diaFim, horario, Periodicidade.SEMANAL);
		
		assertEquals(3, sessoesCriadas.size());
		assertEquals(new DateTime(2015, 11, 30, 22, 0, 0), sessoesCriadas.get(0).getInicio());
		assertEquals(new DateTime(2015, 12, 7, 22, 0, 0), sessoesCriadas.get(1).getInicio());
		assertEquals(new DateTime(2015, 12, 14, 22, 0, 0), sessoesCriadas.get(2).getInicio());
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
