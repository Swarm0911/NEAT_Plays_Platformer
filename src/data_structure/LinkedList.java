package data_structure;

import javafx.scene.paint.Color;

public class LinkedList {
	private char type;
	private Color color;
	private LinkedList previous, next;
	
	public LinkedList(char type, Color color) {
		this.type = type;
		this.color = color;
		 previous = next = null;
	}
	
	public char getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

	public void add(LinkedList ll) {
		LinkedList current = this;
		while(current.next != null) {
			current = current.next;
		}
		
		current.next = ll;
		ll.previous = current;
	}
	
	public LinkedList next() {
		if(next != null)
			return next;
		else {
			LinkedList current = this;
			while(current.previous != null) {
				current = current.previous;
			}
			return current;
		}
	}
	
	public LinkedList previous() {
		if(previous != null)
			return previous;
		else {
			LinkedList current = this;
			while(current.next != null) {
				current = current.next;
			}
			return current;
		}
	}
	
}
