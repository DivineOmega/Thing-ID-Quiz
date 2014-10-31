package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main 
{
	public static ArrayList<List> lists = new ArrayList<List>();
	
	public static void main(String[] args) 
	{
		File directory = new File("lists");
		
		File files[] = directory.listFiles();
		
		for (File file : files) 
		{
			try 
			{
				lists.add(new List(file));
				System.out.println("Loaded list: "+file.getName());
			} 
			catch (IOException e) 
			{
				System.out.println("Error loading list ("+e+"): "+file.getName());
			}
		}
		
		System.out.println();
		System.out.println("*** Welcome to Thing ID Quiz ***");
		System.out.println();
		
		while(true)
		{
			Collections.shuffle(lists);
			
			List activeList = lists.get(0);
			String activeWord = activeList.getRandomWord();
			
			System.out.println("What is "+activeWord+"?");
						
			ArrayList<List> listOptions = new ArrayList<List>();
			
			while(!listOptions.contains(activeList))
			{
				Collections.shuffle(lists);
				
				listOptions.clear();
				listOptions.add(lists.get(0));
				listOptions.add(lists.get(1));
				listOptions.add(lists.get(2));
			}
			
			System.out.println();
			
			int x = 1;
			for (List list : lists) 
			{
				System.out.println("["+x+"] "+list.getName());
				x++;
				if (x>3) break;
			}
			
			System.out.println();
			
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			
			int choice = 0;
			
			while (choice<1 || choice >3)
			{
				System.out.print("Enter your choice [1-3]: ");
				try
				{
					choice = keyboard.nextInt();
				}
				catch(NumberFormatException e)
				{
					System.out.println();
					System.out.println("Must be a number.");
				}
			}
			
			System.out.println();
			
			List selectedList = listOptions.get(choice-1);
			
			if (selectedList==activeList)
			{
				System.out.println("Correct, "+activeWord+" is a "+activeList.getName()+".");
			}
			else
			{
				System.out.println("Wrong, "+activeWord+" is a "+activeList.getName()+".");
			}
			
			System.out.println();
			System.out.println("Press [ENTER] for the next question.");
			
			keyboard.nextLine();
			keyboard.nextLine();
			
			System.out.println("----------------------------------------------");
			System.out.println();
			
		}
		
	}

}
