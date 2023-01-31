package Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import problemdomain.Shape;
import utilities.Sort;

/**
 * Manager Class containing application controls
 * 
 *
 */
public class Manager {

	/**
	 * no arg Manager Object Constructor
	 */
	public Manager() {
		super();
	}

	/**
	 * Method used to print out final sorted Array of Objects
	 * @param array List of Shapes
	 * @param time Time taken to sort Array
	 * @param sort Type of Sort used
	 * @param parameter Based upon what Shape variable
	 * @param filePathString File where Shapes are loaded from
	 */
	public void output(Shape[] array, int time, String sort, String parameter, String filePathString) {
		System.out.println( sort + " sort by " + parameter + " using file " + filePathString + " (" + array.length + "):");
		System.out.println();
		System.out.println("First element: " + array[0]);
		
		for(int i=999; i < array.length; i = i + 1000) {
			System.out.println("Element " + String.format("%5d%s",i+1,": ")  + array[i]); 
		}
		
		System.out.println("Last element:  " + array[array.length-1]);
		System.out.println();
		System.out.println("Time elapsed = " + time + " ms");
	}
	
	/**
	 * Method used to read txt file of Objects and load array
	 * @param fileName File where Shapes are loaded from
	 * @return shapes Loaded unsorted Array of Shapes
	 */
	public Shape[] loadArray(File f) {
		String shape;
		Shape[] shapes = null;
		int size;
		
		try {
			Scanner inFile = new Scanner(f);
			size = inFile.nextInt();
			shapes = new Shape[size];
			int i = 0;

			while (inFile.hasNext()) {
				shape = "problemdomain." + inFile.next();

				Class cls = Class.forName(shape);
				Constructor constructor = cls.getConstructor(Double.TYPE, Double.TYPE);
				shapes[i] = (Shape) constructor.newInstance(inFile.nextDouble(), inFile.nextDouble());
				i++;
			}
			
			inFile.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("That File doesn't exist");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return shapes;
	}
	
	/**
	 * Method used to determine and run sort determined by user
	 * @param sortType Type of Sort used
	 * @param sortParameter Variable sort is based upon
	 * @param shapes Array of Shapes
	 * @param filePathString File where Shapes are loaded from
	 */
	public void determineSort(String sortType, String sortParameter, Shape[] shapes, String filePathString) {
		Long start;
		Long stop;
		Shape sortedShapes[];
		Sort s = new Sort();
		String b = "Bubble";
		String i = "Insertion";
		String sel = "Selection";
		String m = "Merge";
		String q = "Quick";
		String z = "Gnome";
		String v = "volume";
		String h = "height";
		String ba = "base area";
		
		switch (sortType) {
		//Bubble Sort Case
		case "b": 
				switch(sortParameter) {
				//Bubble Sort by Volume
				case "v":
					start = System.currentTimeMillis();
					sortedShapes = (Shape[]) s.bubbleSort(shapes, new CompareVolume());
					stop = System.currentTimeMillis();
					output(sortedShapes, (int) (stop - start), b, v, filePathString);
					break;
				//Bubble Sort by Height
				case "h": 
					start = System.currentTimeMillis();
					sortedShapes = (Shape[]) s.bubbleSort(shapes, new CompareHeight());
					stop = System.currentTimeMillis();
					output(sortedShapes, (int) (stop - start), b, h, filePathString);
					break;
				//Bubble Sort by Base Area
				case "a": 
					start = System.currentTimeMillis();
					sortedShapes = (Shape[]) s.bubbleSort(shapes, new CompareBaseArea());
					stop = System.currentTimeMillis();
					output(sortedShapes, (int) (stop - start), b, ba, filePathString);
					break;
				}
			break;
		//Insertion Sort Case
		case "i": 
			switch(sortParameter) {
			//Insertion Sort by Volume
			case "v":
				start = System.currentTimeMillis();
				sortedShapes = (Shape[]) s.insertSort(shapes, new CompareVolume());
				stop = System.currentTimeMillis();
				output(sortedShapes, (int) (stop - start), i, v, filePathString);
				break;
				//Insertion Sort by Volume
			case "h": 
				start = System.currentTimeMillis();
				sortedShapes = (Shape[]) s.insertSort(shapes, new CompareHeight());
				stop = System.currentTimeMillis();
				output(sortedShapes, (int) (stop - start), i, h, filePathString);
				break;
				//Insertion Sort by Base Area
			case "a": 
				start = System.currentTimeMillis();
				sortedShapes = (Shape[]) s.insertSort(shapes, new CompareBaseArea());
				stop = System.currentTimeMillis();
				output(sortedShapes, (int) (stop - start), i, ba, filePathString);
				break;
			}
			break;
		//Merge Sort Case
		case "m": 
			switch(sortParameter) {
			//Merge Sort by Volume
			case "v":
				start = System.currentTimeMillis();
				s.mergeSort(shapes, 0, shapes.length-1, new CompareVolume());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), m, v, filePathString);
				break;
			//Merge Sort by Height
			case "h": 
				start = System.currentTimeMillis();
				s.mergeSort(shapes, 0, shapes.length-1, new CompareHeight());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), m, h, filePathString);
				break;
			//Merge Sort by Base Area
			case "a": 
				start = System.currentTimeMillis();
				s.mergeSort(shapes, 0, shapes.length-1, new CompareBaseArea());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), m, ba, filePathString);
				break;
			}
			break;
		//Quick Sort Case
		case "q": 
			switch(sortParameter) {
			//Quick Sort by Volume
			case "v":
				start = System.currentTimeMillis();
				s.quickSort(shapes, 0, shapes.length-1, new CompareVolume());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), q, v, filePathString);
				break;
			//Quick Sort by Height 
			case "h": 
				start = System.currentTimeMillis();
				s.quickSort(shapes, 0, shapes.length-1, new CompareHeight());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), q, h, filePathString);
				break;
			//Quick Sort by Base Area
			case "a": 
				start = System.currentTimeMillis();
				s.quickSort(shapes, 0, shapes.length-1, new CompareBaseArea());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), q, ba, filePathString);
				break;
			}
			break;
		//Selection Sort Case
		case "s": 
			switch(sortParameter) {
			//Selection Sort by Volume
			case "v":
				start = System.currentTimeMillis();
				sortedShapes = (Shape[]) s.selectionSort(shapes, new CompareVolume());
				stop = System.currentTimeMillis();
				output(sortedShapes, (int) (stop - start), sel, v, filePathString);
				break;
				//Selection Sort by Height
			case "h": 
				start = System.currentTimeMillis();
				sortedShapes = (Shape[]) s.selectionSort(shapes, new CompareHeight());
				stop = System.currentTimeMillis();
				output(sortedShapes, (int) (stop - start), sel, h, filePathString);
				break;
				//Selection Sort by Base Area
			case "a": 
				start = System.currentTimeMillis();
				sortedShapes = (Shape[]) s.selectionSort(shapes, new CompareBaseArea());
				stop = System.currentTimeMillis();
				output(sortedShapes, (int) (stop - start), sel, ba, filePathString);
				break;
			}
			break;
		//Gnome Sort Case
		case "z": 
			switch(sortParameter) {
			//Gnome Sort by Volume
			case "v":
				start = System.currentTimeMillis();
				s.gnomeSort(shapes, shapes.length, new CompareVolume());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), z, v, filePathString);
				break;
				//Gnome Sort by Height
			case "h": 
				start = System.currentTimeMillis();
				s.gnomeSort(shapes, shapes.length, new CompareHeight());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), z, h, filePathString);
				break;
				//Gnome Sort by Base Area
			case "a": 
				start = System.currentTimeMillis();
				s.gnomeSort(shapes, shapes.length, new CompareBaseArea());
				stop = System.currentTimeMillis();
				output(shapes, (int) (stop - start), z, ba, filePathString);
				break;
			}
		break;
		}
	}
}
