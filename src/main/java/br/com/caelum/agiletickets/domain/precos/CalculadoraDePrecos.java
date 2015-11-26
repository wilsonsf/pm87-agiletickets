package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		if(verificaTipoDaSessao(sessao, TipoDeEspetaculo.CINEMA) || verificaTipoDaSessao(sessao, TipoDeEspetaculo.SHOW)) {
			preco = atualizaPrecoDaSessao(sessao);
		} else if(verificaTipoDaSessao(sessao, TipoDeEspetaculo.BALLET)) {
			preco = atualizaPrecoDaSessao(sessao);
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(verificaTipoDaSessao(sessao, TipoDeEspetaculo.ORQUESTRA)) {
			preco = atualizaPrecoDaSessao(sessao);

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal atualizaPrecoDaSessao(Sessao sessao) {
		return (sessao.deveAplicarAcrescimoDeVagas() ? sessao.aplicaAcrescimoNaSessao() : sessao.getPreco());
	}
	
	private static boolean verificaTipoDaSessao(Sessao sessao, TipoDeEspetaculo tipo) {
		return sessao.getEspetaculo().getTipo().equals(tipo);
	}
}