package bookView;

public class startclass {
	/**
	 * sets up the program by instanciating the 2 objects tui and cordance 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Cordance cordance = new Cordance(args[0]);
		TUI tui = new TUI(cordance);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
	 }
}
