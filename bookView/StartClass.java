package bookView;

import java.io.FileNotFoundException;

import CordanceParser.ParsedCordance;

public class StartClass {
	/**
	 * sets up the program by instanciating the 2 objects tui and cordance and passing
	 * the file location through to them
	 * @param args
	 */
	
	public static void main(String[] args) {
		DirectoryPusher directoryPusher;
		try{
			try {
				try {	//set up controller and TUI
					directoryPusher = new DirectoryPusher(args[0]);
					ParsedCordance pc = directoryPusher.getParsedCordance();
					Cordance cordance = new Cordance(pc);
					TUI tui = new TUI(cordance);
				} catch (FileNotFoundException e) {
					System.out.println("file location not valid");
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.exit(001);
				System.out.println("please put in a file location in string args");
			}
		}catch(NullPointerException e){
			System.exit(010);
			System.out.println("please put in a valid file location");
		}
	}
}
