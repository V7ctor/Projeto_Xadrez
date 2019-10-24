package Aplicacao;

import XadrezCamada.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {

		PartidaXadrez partida = new PartidaXadrez();
		
		UI.printarTabuleiro(partida.getPecas());
		
	}

}
