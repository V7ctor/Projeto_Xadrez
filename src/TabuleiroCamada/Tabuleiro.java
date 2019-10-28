package TabuleiroCamada;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro ao criar Tabuleiro: É necessário que haja ao menos 1 linha e 1 coluna!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca getPeca(int linha, int colunas) {
		if (!posicaoExiste(linha, colunas)) {
			throw new ExcecaoTabuleiro("Posição não existe no Tabuleiro!");
		}
		return pecas[linha][colunas];
	}
	
	public Peca getPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Posição não existe no Tabuleiro!");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if (pecaExistente(posicao)) {
			throw new ExcecaoTabuleiro("Já existe uma Peça nessa posição: "+posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Posição não existe no Tabuleiro!");
		}
		if (getPeca(posicao) == null) {
			return null;
		}
		Peca aux = getPeca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	// -> Método Auxíliar //
	private boolean posicaoExiste(int linha, int coluna) { 
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean pecaExistente(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Posição não existe no Tabuleiro!");
		}
		return getPeca(posicao) != null;
	}
}
