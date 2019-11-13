package Pecas;

import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;
import XadrezCamada.Cor;
import XadrezCamada.PartidaXadrez;
import XadrezCamada.PecaXadrez;

public class Rei extends PecaXadrez {

	private PartidaXadrez partidaXadrex;
	
	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrex) {
		super(tabuleiro, cor);
		this.partidaXadrex = partidaXadrex;
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testarRoqueTorre(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// Acima ↑
				p.setValores(posicao.getLinha() - 1, posicao.getColuna());
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			
		// Esquerda ←
				p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		
		// Direita →
				p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// Abaixo ↓
				p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// Noroeste 
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// Nordeste 
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// Sudoeste 
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// Sudeste 
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
		// #Movimento Especial -> Roque
				
				if (getContadorMovimento() == 0 && !partidaXadrex.getCheck()) {
					// Roque Pequeno
					Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
					if (testarRoqueTorre(posT1)) {
						Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
						Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
						if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null) {
							mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
									
						}
					}
					// Roque Grande (Rainha)
					Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
					if (testarRoqueTorre(posT2)) {
						Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
						Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
						Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
						if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null 
								&& getTabuleiro().getPeca(p3) == null) {
							mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
									
						}
					}
				}
						
				
		return mat;
	}
	
	@Override
	public String toString() {
		return "K";
	}

}
