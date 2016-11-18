package ClassesBasicas;

public class TempoDeSimulacao {
	private int tempo;
	
	public TempoDeSimulacao(int tempo) {
		this.tempo = tempo;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		if(tempo >= 0) this.tempo = tempo;
		else this.tempo = 0;
	}
	
}
