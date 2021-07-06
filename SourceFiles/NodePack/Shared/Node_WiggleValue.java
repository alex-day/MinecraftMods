package net.minecraft.src.NodePack.Shared;


public class Node_WiggleValue extends Node_Value {
	
	double maxBounds;
	
	public Node_WiggleValue(double d, double e) {
		super(d);
		maxBounds = e;
	}
	
	public double getDoubleValue() {
		double minBounds = value;
		double difference = maxBounds - minBounds;
		
		double random = Node_PatternTracker.randGen.nextDouble();
		
		return minBounds + (difference*random);
	}
	
	public boolean equals(int val) {
		if(value <= val && val <= maxBounds) {
			return true;
		}
		return false;
	}
}