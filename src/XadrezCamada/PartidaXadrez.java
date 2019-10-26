package XadrezCamada;

import Pecas.Rei;
import Pecas.Torre;
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
	
	private void colocarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	private void iniciandoPartida() {
		
		colocarPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        colocarPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
