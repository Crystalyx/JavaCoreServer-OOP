package com.faceless;

import com.faceless.vmservice.containers.PropertyContainer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.stream.Collectors;


public class Utilities
{
	Document readDocument(String fileName)
	{
		InputStream inputStream = getClass().getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String contents = reader.lines()
				.collect(Collectors.joining(System.lineSeparator()));

		return insertButtons(parseDocument(contents));
	}

	private static Document insertButtons(Document document)
	{
		for (Element input : document.select("fbutton"))
		{
			String buttonName = input.attr("name");
			String method     = input.attr("method");
			String path       = input.attr("path");
			String text       = input.attr("text");
			input.append(createButton(buttonName, method, path, text));
		}

		return document;
	}

	private static String createButton(String name, String method, String path, String text)
	{
		return "<button class=\"" + name + "\" >" + text + "</button>" +
			   "<script>" +
			   "$('." + name + "').click(function ()" +
			   "{$.ajax({url: \"" + path + "\",type: \"" + method + "\"," +
			   "success: function (result) {location.reload();console.log(result)}," +
			   "error: function (error) {console.log(`Error ${error}`)}})})" +
			   "</script>";
	}

	private static String readFile(String fileName)
	{

		try
		{
			FileReader fileStream     = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileStream);
			return bufferedReader.lines().reduce("", (a, b) -> a + "\n" + b);//aligning all lines of the file
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.println("Err");
		return "";
	}

	private static Document parseDocument(String htmlFile)
	{
		return Jsoup.parse(htmlFile);
	}

	public static void applyPropertyContainer(Document document, PropertyContainer container)
	{
		if (container == null)
			return;
		for (Element input : document.select("property"))
		{
			String propertyName = input.attr("name");
			input.text(container.getProperty(propertyName));
		}
	}


}
