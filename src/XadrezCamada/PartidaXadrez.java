package XadrezCamada;

import Pecas.Rei;
import Pecas.Torre;
import TabuleiroCamada.Peca;
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
	
	public PecaXadrez executarMovimento(PosicaoXadrez PosicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = PosicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMover(origem, destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.pecaExistente(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de Origem!");
		}
		if (!tabuleiro.getPeca(posicao).pecaPresa()) {
			throw new ExcecaoXadrez("Nao existe movimentos possiveis para a peca escolhida!");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.getPeca(origem).possoMover(destino)) {
			throw new ExcecaoXadrez("A peca escolhida nao pode se mover para a posicao de destino!");
		}
	}
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return pecaCapturada;
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
