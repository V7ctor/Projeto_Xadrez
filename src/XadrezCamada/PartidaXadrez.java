package XadrezCamada;

import java.util.ArrayList;
import java.util.List;

import Pecas.Rei;
import Pecas.Torre;
import TabuleiroCamada.Peca;
import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;

public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		iniciandoPartida();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.getPeca(posicao).possiveisMovimentos();
	}
	
	public PecaXadrez executarMovimento(PosicaoXadrez PosicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = PosicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMover(origem, destino);
		trocarTurno();
		return (PecaXadrez)pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.pecaExistente(posicao)) {
			throw new ExcecaoXadrez("Nao existe peca na posicao de Origem!");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.getPeca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A peca escolhida nao e sua!");
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
		
		if (pecaCapturada != null) {
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void trocarTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private void colocarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasTabuleiro.add(peca);
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
