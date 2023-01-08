package sait.frms.application;

import sait.frms.gui.MainWindow;

import java.io.IOException;

import sait.frms.gui.*;

/**
* calls the Flight and Reservation Manager constructor
* @author Jonathan Ryan Muriel
* @author Minjong Park
* @author Majd Alshahaf
* @version March 28, 2022
*/
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}

