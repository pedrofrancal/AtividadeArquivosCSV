package view;

import java.io.IOException;

import controller.ControllerArquivo;

public class Principal {
	public static void main(String[] args) {
		ControllerArquivo ca = new ControllerArquivo();
		try {
			ca.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
