package Pecas;

import TabuleiroCamada.Tabuleiro;
import XadrezCamada.Cor;
import XadrezCamada.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}

}
