package isae.kaunetem.launcher;

import java.io.IOException;
import com.beust.jcommander.*;
import isae.kaunetem.CLI.MainCLIParameters;
import isae.kaunetem.function.UnixCommandExecutor;

public class Launcher {

	final MainCLIParameters mainArgs = new MainCLIParameters();

	/*
	 * Used to manage the various input arguments entered by the user
	 */
	void handleInputArgs(String args[]){
		JCommander jcommander = new JCommander(mainArgs);
		jcommander.setProgramName("mark");
		UnixCommandExecutor cmd = new UnixCommandExecutor();
		
		try{
			jcommander.parse(args);
			cmd.setMark(mainArgs.getPacket(), mainArgs.getSrc(), mainArgs.getDest(), mainArgs.getValue(), mainArgs.presPortSrc(), mainArgs.presPortDest());
			
		} catch (ParameterException | NullPointerException e1){
			if(mainArgs.showPacket()){
				try {
					cmd.showMark();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println(e1.getMessage());
				System.out.println("Try mark -h for display the help");
			}	
		}
		
		if (mainArgs.isHelp()){
			showHelp(jcommander);
		}
	}
	
	/*
	 * Used to display help 
	 */
	void showHelp(JCommander jcommander){
		jcommander.usage();
		System.exit(0);
	}
	
	/*
	 * Used to launch the programm
	 */
	void run(){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Launcher launch = new Launcher();
		launch.handleInputArgs(args);
		launch.run();

	}

}