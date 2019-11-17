package XadrezCamada;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Pecas.Bispo;
import Pecas.Cavalo;
import Pecas.Peao;
import Pecas.Rainha;
import Pecas.Rei;
import Pecas.Torre;
import TabuleiroCamada.Peca;
import TabuleiroCamada.Posicao;
import TabuleiroCamada.Tabuleiro;

public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassantVunerabilidade;
	
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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PecaXadrez getEnPassantVunerabilidade() {
		return enPassantVunerabilidade;
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
		
		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Voce nao pode se colocar em Check!");
		}
		
		PecaXadrez pecaMoveu = (PecaXadrez) tabuleiro.getPeca(destino);
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}else {
			trocarTurno();
		}
		
		// #Movimento Especial -> En Passant
		
		if (pecaMoveu instanceof Peao && (destino.getLinha() == origem.getLinha() - 2) || 
				(destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVunerabilidade = pecaMoveu;
		}else {
			enPassantVunerabilidade = null;
		}
		
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
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
		p.incrementarContadorMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		// #Movimento Especial -> Roque Pequeno
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, destinoT);
			torre.incrementarContadorMovimento();
		}
		
		// #Movimento Especial -> Roque Grande
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, destinoT);
			torre.incrementarContadorMovimento();
		}
		
		// #Movimento Especial -> En Passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}else {
					peaoPosicao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(peaoPosicao);
				pecasCapturadas.add(pecaCapturada);
				pecasTabuleiro.remove(pecaCapturada);
			}
		}
				
		return pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
		p.DecrementarContadorMovimento();
		tabuleiro.colocarPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
		
		// #Movimento Especial -> Roque Pequeno
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.DecrementarContadorMovimento();
		}
		
		// #Movimento Especial -> Roque Grande
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.DecrementarContadorMovimento();
		}
		
		// #Movimento Especial -> En Passant
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVunerabilidade) {
				PecaXadrez peao = (PecaXadrez) tabuleiro.removerPeca(destino);
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(3, destino.getColuna());
				}else {
					peaoPosicao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.colocarPeca(peao, peaoPosicao);
			}
		}
	}
	
	private void trocarTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Nao existe "+ cor + " no tabuleiro!");
	}
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		
		List<Peca> lista =  pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : lista) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMover(origem, destino);
						boolean testCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true; 
		
		
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasOponentes = pecasTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
				                    .collect(Collectors.toList());
		
		for (Peca p : pecasOponentes) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private void colocarPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasTabuleiro.add(peca);
	}
	
	private void iniciandoPartida() {
		
			colocarPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
			colocarPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
			colocarPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
			colocarPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
			colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
			colocarPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
			colocarPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
			colocarPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
			colocarPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
	        colocarPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		
	        colocarPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
	        colocarPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
			colocarPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
			colocarPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
	        colocarPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
			colocarPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
			colocarPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
	        colocarPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
	        colocarPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
	        colocarPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
       
	}
}
