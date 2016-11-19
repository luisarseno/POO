package br.pucrs.Models;

public class Intervalo {
	private int tempo1, tempo2;

	public Intervalo(int tempo1, int tempo2) {
		this.tempo1 = tempo1;
		this.tempo2 = tempo2;
	}

	public int getTempo1() {
		return tempo1;
	}

	public int getTempo2() {
		return tempo2;
	}
	
	
	/**Somente para testar os bgl
	@Override
	public String toString() {
		return tempo1 + "-"+ tempo2;
	}
	**/
	
}
