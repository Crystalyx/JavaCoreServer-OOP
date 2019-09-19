package com.faceless;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Utilities
{

	static String readFile(String fileName)
	{
		try
		{
			var fileStream     = new FileReader(fileName);
			var bufferedReader = new BufferedReader(fileStream);
			return bufferedReader.lines().reduce("", (a, b) -> a + "\n" + b);//aligning all lines of the file
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.println("Err");
		return "";
	}

}
