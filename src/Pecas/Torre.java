package Pecas;

import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;
import XadrezCamada.Cor;
import XadrezCamada.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//Acima ↑
				p.setValores(posicao.getLinha() - 1, posicao.getColuna());
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() - 1);
				}
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		
		//Esquerda ←
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() - 1);
				}
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		//Direita →
				p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() + 1);
				}
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		//Abaixo ↓
				p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() + 1);
				}
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		return mat;
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
