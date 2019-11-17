package Pecas;

import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;
import XadrezCamada.Cor;
import XadrezCamada.PartidaXadrez;
import XadrezCamada.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public boolean[][] possiveisMovimentos() {

		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
				if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}

			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
				if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p) && getTabuleiro().posicaoExiste(p2)
						&& !getTabuleiro().pecaExistente(p2) && getContadorMovimento() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
				}

			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// #Movimento Especial -> En Passant (Branco)
				
				if (posicao.getLinha() == 3) {
					Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					if (getTabuleiro().posicaoExiste(esquerda) && goPecaOponente(esquerda)
					&& getTabuleiro().getPeca(esquerda) == partidaXadrez.getEnPassantVunerabilidade()) {
						mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
					}
					Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
					if (getTabuleiro().posicaoExiste(direita) && goPecaOponente(direita)
					&& getTabuleiro().getPeca(direita) == partidaXadrez.getEnPassantVunerabilidade()) {
						mat[direita.getLinha() - 1][direita.getColuna()] = true;
					}
				}

		} else {

			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}

			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExistente(p) && getTabuleiro().posicaoExiste(p2)
						&& !getTabuleiro().pecaExistente(p2) && getContadorMovimento() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
				}

			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && goPecaOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// #Movimento Especial -> En Passant (Preto)
				
				if (posicao.getLinha() == 4) {
					Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					if (getTabuleiro().posicaoExiste(esquerda) && goPecaOponente(esquerda)
					&& getTabuleiro().getPeca(esquerda) == partidaXadrez.getEnPassantVunerabilidade()) {
						mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
					}
					Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
					if (getTabuleiro().posicaoExiste(direita) && goPecaOponente(direita)
					&& getTabuleiro().getPeca(direita) == partidaXadrez.getEnPassantVunerabilidade()) {
						mat[direita.getLinha() + 1][direita.getColuna()] = true;
					}
				}

		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
	
	

}
