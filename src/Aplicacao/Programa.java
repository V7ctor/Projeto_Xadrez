package Aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import XadrezCamada.ExcecaoXadrez;
import XadrezCamada.PartidaXadrez;
import XadrezCamada.PecaXadrez;
import XadrezCamada.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		
		while (true) {
			try {
				UI.limparTela();
				UI.printarPartida(partida);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean[][] possiveisMovimentos = partida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printarTabuleiro(partida.getPecas(), possiveisMovimentos);
				
				System.out.println();
				
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partida.executarMovimento(origem, destino);
				
			}catch(ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
