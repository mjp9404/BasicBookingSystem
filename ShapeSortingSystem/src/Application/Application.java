package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;


import Managers.CompareBaseArea;
import Managers.CompareHeight;
import Managers.CompareVolume;
import Managers.Manager;
import problemdomain.Shape;
import utilities.Sort;

/**
 * Main driver for Shape sorting Application
 * 
 *
 */
public class Application {

	public static void main(String[] args) {
		Manager app = new Manager();
		Shape[] shapes = null;

		String filePathString = "";
		String sortType = "";
		String sortParameter = "";
		
		// Figure out which arg is which based on f, s, t
		for(String arg : args) {
			arg = arg.toLowerCase();
			
			switch(arg.charAt(1)) {
			case 'f':
				filePathString = arg.substring(2).replace("\"", ""); // removing " from the string
			break;
			case 's':
				sortType = arg.substring(2);
			break;
			case 't':
				sortParameter = arg.substring(2);
			break;
			}
		}
			// Test sort Parameter to make sure it's valid
			File f = new File(filePathString);
			if (!f.exists()) {
			System.out.println("file not found:" + filePathString);
			return;
			}

			// Test Sort Parameter Input to make sure it's valid
			if (!sortParameter.equals("v") && !sortParameter.equals("a") && !sortParameter.equals("h")) {
			System.out.println(sortParameter + " is not an available sort parameter. Please enter h for Height, v for Volume, or a for Base Area!");
			return;
			}

			// Test Sort Input to make sure it's valid
			if (!sortType.equals("b") && !sortType.equals("s") && !sortType.equals("q")
			&& !sortType.equals("i") && !sortType.equals("m") && !sortType.equals("z")) {
			System.out.println(sortType + " is not an available sort. Please enter s for "
					+ "Selection, i for Insert, or m for Merge, q for Quick, or z for Gnome!");
			return;
			}
			
			// Load Array for txt file
			shapes = app.loadArray(f);
			
			//execute sort based on args
			app.determineSort(sortType, sortParameter, shapes, filePathString);
	}
		
}	
			

		

		

