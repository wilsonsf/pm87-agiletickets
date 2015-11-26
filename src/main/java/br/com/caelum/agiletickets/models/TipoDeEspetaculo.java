package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	CINEMA(0.05,0.10,0,0.), 
	SHOW(0.05,0.10,0,0.), 
	TEATRO(0.,0.,0,0.), 
	BALLET(0.50,0.20,60,0.10), 
	ORQUESTRA(0.50,0.20,60,0.10);
	
	private TipoDeEspetaculo(double limiteVagas, double acrescimoVagas, int limiteTempo, double acrescimoTempo) {
		this.limitePercentualParaAcrescimo = limiteVagas;
		this.acrescimoVagasRestantes = acrescimoVagas;
		this.limiteTempoParaAcrescimo = limiteTempo;
		this.acrescimoDuracao = acrescimoTempo;
	}
	private double acrescimoVagasRestantes;
	private double limitePercentualParaAcrescimo;
	private double acrescimoDuracao;
	private int limiteTempoParaAcrescimo;
	
	public double getAcrescimoVagasRestantes() {
		return acrescimoVagasRestantes;
	}
	public double getLimitePercentualDeVagasParaAcrescimo() {
		return limitePercentualParaAcrescimo;
	}
	public double getAcrescimoDuracao() {
		return acrescimoDuracao;
	}
	public int getLimiteTempoParaAcrescimo() {
		return limiteTempoParaAcrescimo;
	}
}
