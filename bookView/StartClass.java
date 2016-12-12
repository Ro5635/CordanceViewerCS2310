package bookView;

import java.io.FileNotFoundException;

import CordanceParser.ParsedCordance;

public class StartClass {
	/**
	 * sets up the program by instanciating the 2 objects tui and cordance 
	 * @param args
	 */
	
	public static void main(String[] args) {
		DirectoryPusher directoryPusher;
		try {
			directoryPusher = new DirectoryPusher(args[0]);
			ParsedCordance pc = directoryPusher.getParsedCordance();
			Cordance cordance = new Cordance(pc);
			TUI tui = new TUI(cordance);
		} catch (FileNotFoundException e) {
			System.out.println("file flocation not valid");
			e.printStackTrace();
		}

	 }
}
