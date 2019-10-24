package XadrezCamada;

import TabuleiroCamada.Peca;
import TabuleiroCamada.Tabuleiro;

public class PecaXadrez extends Peca {

	private Cor cor;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
}
