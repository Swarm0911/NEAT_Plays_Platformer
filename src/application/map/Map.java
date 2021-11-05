package application.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	public String[] map;
	boolean validMap = false;
	public String[] getMap() {
		return map;
	}
	public Map() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("map.txt"));
			String line;
			map = new String[13];
			int row = 0;
			while((line = br.readLine()) != null) {
				map[row] = line;
				row++;
			}
		} catch (FileNotFoundException e) {
			map = new String[]{
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000000000000000000000000000000000",
					"0000008000000000000000000000000000",
					"0000088000000000000000000000000000",
					"0p008880m00800800000000s0008000000",
					"8888888888880088888888888888888888",
					"ssssssssssssssssssssssssssssssssss",
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		validateMap();
	}
	
	public void addColumn() {
		System.out.println();
		print();
		for(int i = 0; i < map.length; i++) {
			if(i + 1 == map.length )
				map[i] = map[i] + "s";
			else
				map[i] = map[i] + "0";
		}
		System.out.println();
		print();
		
		
	}
	
	public void removeColumn() {
		System.out.println();
		print();
		if(map[0].length() > 1)
			for(int i = 0; i < map.length; i++) {
				map[i] = map[i].substring(0, map[i].length() - 1);
			}
		System.out.println();
		print();
		
		
	}
	
	public void validateMap() {
		int pCount = 0;
		for(int i = 0; i < map.length; i++) {
			String line = map[i];
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) == 'p') {
					pCount++;
				}
			}
			if(pCount == 1)
				validMap = true;
		}
	}
	
	public boolean isValid() {
		return validMap;
	}
	public void print() {
		for(int i = 0; i < map.length; i++) {
			String line = map[i];
			for(int j = 0; j < line.length(); j++) {
				System.out.print(line.charAt(j));
			}
			System.out.println();
		}
	}
}
