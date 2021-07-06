package net.minecraft.src.NodePack.Shared;

import java.util.Random;

public class Node_PatternTracker {
	public static Random randGen = new Random();
	
	int numStepsTaken; // the number of steps taken so far.
	Node_Pattern pattern; // the pattern that we are tracking
	
	int uOffsetInd;
	int uIntervalInd;
	int uIntervalStep;
	
	int vOffsetInd;
	int vIntervalInd;
	int vIntervalStep;
	
	public Node_PatternTracker(Node_Pattern pat, int stepsTaken) {
		pattern = pat;
		numStepsTaken = stepsTaken;
		
		//calculate fields based on steps taken
		initialiseTracking();
	}
	
	/**
	* If the number of steps taken is not equal to zero,
	* this method will sync the tracker so it's in the right position.
	*/
	private void initialiseTracking() {
		for(int i=0; i<numStepsTaken; i++) {
			takeStep();
		}
	}
	
	public void takeStep() {
		
		Node_ValueSet uIntervalSet = pattern.getUMoveIntervalSet();
		if(uIntervalSet != null) {
			int desiredStep = uIntervalSet.getIntValueAtIndex(uIntervalInd);
			uIntervalStep++;
			if(uIntervalStep > desiredStep) {
				uIntervalInd++;
				uIntervalStep = 0;
				if(uIntervalInd >= pattern.getUMoveIntervalSet().getLength()) {
					uIntervalInd = 0;
					uOffsetInd++;
					
					Node_ValueSet uSet = pattern.getUOffsetSet();
					if(uSet != null) {
						uOffsetInd++;
						if(uOffsetInd >= pattern.getUOffsetSet().getLength()) {
							uOffsetInd = 0;
						}
					}
				}
			}
		}
		
		Node_ValueSet vIntervalSet = pattern.getVMoveIntervalSet();
		if(uIntervalSet != null) {
			int desiredStep = vIntervalSet.getIntValueAtIndex(vIntervalInd);
			vIntervalStep++;
			if(vIntervalStep > desiredStep) {
				vIntervalInd++;
				vIntervalStep = 0;
				if(vIntervalInd >= pattern.getVMoveIntervalSet().getLength()) {
					vIntervalInd = 0;
					vOffsetInd++;
					
					Node_ValueSet vSet = pattern.getVOffsetSet();
					if(vSet != null) {
						vOffsetInd++;
						if(vOffsetInd >= pattern.getVOffsetSet().getLength()) {
							vOffsetInd = 0;
						}
					}
				}
			}
		}
	}
	
	public int getUOffset() {
		if(uIntervalStep == 0) {
			Node_ValueSet set = pattern.getUOffsetSet();
			if(set == null) {
				return 0;
			}
			return set.getIntValueAtIndex(uOffsetInd);
		}
		return 0;
	}
	
	public int getVOffset() {
		if(vIntervalStep == 0) {
			Node_ValueSet set = pattern.getVOffsetSet();
			if(set == null) {
				return 0;
			}
			return set.getIntValueAtIndex(vOffsetInd);
		}
		return 0;
	}
}