package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class List 
{
	private String name;
	private ArrayList<String> words = new ArrayList<String>();
	
	public List(File file) throws IOException
	{
		name = file.getName().replace('_', ' ').replace(".txt", "").trim();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) 
		{
		   if (line.trim().isEmpty()) continue;
		   words.add(line.trim());
		}
		br.close();
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRandomWord()
	{
		Collections.shuffle(words);
		
		return words.get(0);
	}
}
