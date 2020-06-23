package controller;

import java.awt.Desktop;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*Elaborado dia 23/06/2020
 *Por Pedro França
 */
public class ControllerArquivo implements IControllerArquivo{
	//Ler txt e transformar em .csv

	public ControllerArquivo() {
		super();
	}
	
	//utilizar do JFileChooser pra gerar uma janela
	private File chooseFile() {
		File filePath;
		
		JFileChooser file = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo TXT", "txt");
		file.setFileFilter(filter);
		int returnVal = file.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = file.getSelectedFile();
			return filePath;
		}else {
			return null;
		}
		
	}
	
	//Salvar no desktop
	public void createCsv(String text) throws IOException {
		File dir = new File(System.getProperty("user.home") + "/Desktop");
		File arq = new File(System.getProperty("user.home") + "/Desktop", "relatorio.csv");
		if(dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(arq.exists()) {
				existe = true;
			}
			String conteudo = text;
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		}else {
			throw new IOException("Diretorio invalido");
		}
		openFile(arq);
	}
	
	//Adicionar ;
	public void addSemicolon(String text) throws IOException {
		String newText;
		newText = text.substring(0, 18) + "\n";
		text = text.substring(20);
		newText += text.replaceAll(" ", ";");
		createCsv(newText);
	}
	
	//Adicionar ao buffer o texto
	@Override
	public void readFile() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(chooseFile()));
		StringBuffer buffer = new StringBuffer();
		
		String lido;
		while((lido = read.readLine())!=null) {
			buffer.append(lido);
			buffer.append("\n");
		}
		read.close();
		addSemicolon(buffer.toString());
	}

	//Auto-abrir o csv após o termino
	@Override
	public void openFile(File file) throws IOException {
		
		if (file.exists()&&file.isFile()) {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}else {
			throw new IOException("Diretorio invalido");
		}
	}

}
