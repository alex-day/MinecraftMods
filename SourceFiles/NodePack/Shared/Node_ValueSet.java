package net.minecraft.src.NodePack.Shared;

import java.util.ArrayList;

import net.minecraft.src.mod_Node_PackLoader;


public class Node_ValueSet {
	
	Node_Value[] values;
	
	public Node_ValueSet(Node_Value[] vals) {
		values = vals;
	}
	
	public double getDoubleValueAtIndex(int index) {
		return values[index].getDoubleValue();
	}
	
	public int getIntValueAtIndex(int index) {
		return values[index].getIntegerValue();
	}
	
	public int getLength() {
		return values.length;
	}

	public boolean contains(int bID) {
		for(int i=0; i<values.length; i++) {
			if(values[i].getIntegerValue() == (bID) || values[i].getIntegerValue() == -1) {
				return true;
			}
		}
		return false;
	}

	public static Node_ValueSet parseSet(String string) {
		String[] values = string.split(",");
		ArrayList<Node_Value> nodeValues = new ArrayList<Node_Value>();
		
		for(int i=0; i< values.length; i++) {
			//check to see if it's a 'wiggle' directive
			if(values[i].startsWith("wiggle")) {
				String numbers = values[i].substring(values[i].indexOf("(")+1, values[i].indexOf(")"));
				int posOfColon = numbers.indexOf(":");
				String minStr = numbers.substring(0,posOfColon);
				String maxStr = numbers.substring(posOfColon+1);
				nodeValues.add(new Node_WiggleValue(Double.parseDouble(minStr), Double.parseDouble(maxStr)));
			}
			// check to see if it matches one of our predefined sets.
			else if(values[i].equalsIgnoreCase("ALL")) {
				nodeValues.add(new Node_Value(-1));
			} else {
				//add the plain value into the value set
				nodeValues.add(new Node_Value(Double.parseDouble(values[i])));
			}
		}
		
		Node_ValueSet valueSet = new Node_ValueSet(nodeValues.toArray(new Node_Value[0]));
		return valueSet;
	}
}