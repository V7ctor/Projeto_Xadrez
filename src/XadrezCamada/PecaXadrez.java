package XadrezCamada;

import TabuleiroCamada.Peca;
import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Cor cor;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean goPecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
		return p != null && p.getCor() != cor;
	}
	
}
