package Pecas;

import TabuleiroCamada.Tabuleiro;
import XadrezCamada.Cor;
import XadrezCamada.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}
}
