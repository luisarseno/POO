package br.pucrs.Model;

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
	
	

	@Override
	public String toString() {
		return "Intervalo: ["+tempo1 + "-"+ tempo2+"]";
	}

	
}
