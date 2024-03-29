package Pecas;

import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;
import XadrezCamada.Cor;
import XadrezCamada.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// -------
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			
		// -------
				p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		
		// -------
				p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// -------
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// -------
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// -------
				p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// -------
				p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// ------- 
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
						
				
		return mat;
	}
	
	@Override
	public String toString() {
		return "C";
	}

}
