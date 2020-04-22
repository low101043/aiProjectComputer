package com.natlowis.ai.fileHandaling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a file which handles CSV files and can output information from the file.
 * @author low101043
 *
 */
public class CSVFiles {

	private File file; 		//The file to use
	private int lengthOfRow;  //The length each row should be

	/**
	 * This is the basic constructor for a CSV file.  You need the file to use and the amount of data the file should have 
	 * @param fileToChange A <Code> File </Code> type which is the file to edit
	 * @param size A <Code> int </Code> which is how large the file should be 
	 */
	public CSVFiles(File fileToChange, int size) {
		file = fileToChange;
		lengthOfRow = size;

	}

	/**
	 * This will read a CSV file.  It will throw an exception if you pass a file which does not exist, if the length of a row is not correct
	 * @return An <Code> ArrayList </code> which contains an <Code> ArrayList of Strings </Code> 
	 */
	public ArrayList<ArrayList<String>> readCSV() {

		ArrayList<ArrayList<String>> output = null;  //Sets the output to null if it does not work thus passing null object back which can be dealt with other code
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {  //Will try and open the files.  Will close the file if it cannot be opened
			
			output = new ArrayList<ArrayList<String>>();  //Sets up the output
			 
			
			String line = "";  //The output from each line
			
			while ((line = br.readLine()) != null) {  //Reads each line from the file and adds it to the string array
				
				String[] split = line.split(",");
				
				if (split.length != lengthOfRow) {  //If the array is not the correct length it throws an exception
					throw new Exception();
				}

				ArrayList<String> lineList = new ArrayList<String>();  //This is the data in the String array

				for (String item : split) {  //Adds each item in the array split to the arraylist lineList
					lineList.add(item);
				}
				
				output.add(lineList);  //Adds each line to output
			}


		} catch (IOException e) {  //Catches the exceptions thrown //TODO  sort what to do with them
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return output;
	}

}

//Graphs size 3 - origin, destination, weight
//TODO  What the size should be for each input.  Add to GUI.