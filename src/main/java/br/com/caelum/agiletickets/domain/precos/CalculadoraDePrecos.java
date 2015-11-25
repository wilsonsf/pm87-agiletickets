package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		if(verificaTipoDaSessao(sessao, TipoDeEspetaculo.CINEMA) || verificaTipoDaSessao(sessao, TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if((sessao.getIngressosDisponiveis()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = sessao.getPreco();
			}
		} else if(verificaTipoDaSessao(sessao, TipoDeEspetaculo.BALLET)) {
			if((sessao.getIngressosDisponiveis()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(verificaTipoDaSessao(sessao, TipoDeEspetaculo.ORQUESTRA)) {
			if((sessao.getIngressosDisponiveis()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private static boolean verificaTipoDaSessao(Sessao sessao, TipoDeEspetaculo tipo) {
		return sessao.getEspetaculo().getTipo().equals(tipo);
	}
}