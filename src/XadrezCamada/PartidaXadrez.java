package XadrezCamada;

import Pecas.Rei;
import Pecas.Torre;
import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciandoPartida();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.getPeca(i, j);
			}
		}
		return mat;
	}
	
	private void iniciandoPartida() {
		
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 1));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 1));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(7, 4));
	}
}
