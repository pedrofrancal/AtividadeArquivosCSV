package controller;

import java.io.File;
import java.io.IOException;

public interface IControllerArquivo {
	public void readFile() throws IOException;
	public void openFile(File file) throws IOException;
}
