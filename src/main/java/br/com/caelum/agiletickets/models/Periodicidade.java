package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA(1), SEMANAL(7);
	
	Periodicidade(int incremento){
		this.incremento = incremento;
	}
	
	private int incremento;

	public int getIncremento() {
		return incremento;
	}
	
}
