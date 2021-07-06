package net.minecraft.src.NodePack.Shared;

public class Node_Value {
	
	double value;
	
	public Node_Value(double val) {
		value = val;
	}
	
	public double getDoubleValue() {
		return value;
	}
	
	public int getIntegerValue() {
		return (new Double(getDoubleValue())).intValue();
	}
	
	public boolean equals(int val) {
		if(getIntegerValue() == val) {
			return true;
		}
		return false;
	}
}