package Aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import XadrezCamada.ExcecaoXadrez;
import XadrezCamada.PartidaXadrez;
import XadrezCamada.PecaXadrez;
import XadrezCamada.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		List<PecaXadrez> capturadas = new ArrayList<>();
		
		while (!partida.getCheckMate()) {
		   try {
				UI.limparTela();
				UI.printarPartida(partida, capturadas);
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
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
			
				}
			
			}catch(ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		UI.limparTela();
		UI.printarPartida(partida, capturadas);
	}
}
