package Aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import XadrezCamada.Cor;
import XadrezCamada.PartidaXadrez;
import XadrezCamada.PecaXadrez;
import XadrezCamada.PosicaoXadrez;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String leitura = sc.nextLine();
			char coluna = leitura.charAt(0);
			int linha = Integer.parseInt(leitura.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler Posicao de Xadrez: Formatos validos sao de a1 ate h8!");
			// InputMismatchException -> Trata possíveis erros de entrada de dados.
		}
	}
	
	public static void printarPartida (PartidaXadrez partida, List<PecaXadrez> pecasCapturadas) {
		printarTabuleiro(partida.getPecas());
		System.out.println();
		printPecasCapturada(pecasCapturadas);
		System.out.println();
		System.out.println("Turno: "+partida.getTurno());
		if (!partida.getCheckMate()) {
		System.out.println("Esperando o jogador "+partida.getJogadorAtual()+"!");
		if (partida.getCheck()) {
			System.out.println("CHECK!");
		}
	}else {
		System.out.println("CHECK MATE!!!");
		System.out.println("Vencedor: "+partida.getJogadorAtual());
	}
		
	}
	
	public static void printarTabuleiro(PecaXadrez[][] pecas) {
		
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printarPosicao(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printarTabuleiro(PecaXadrez[][] pecas, boolean[][] possiveisMovimentos) {
		
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printarPosicao(pecas[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printarPosicao(PecaXadrez peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printPecasCapturada(List<PecaXadrez> capturadas) {
		List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		System.out.println("Pecas Capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Pretas:  ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(ANSI_RESET);
	}
}
