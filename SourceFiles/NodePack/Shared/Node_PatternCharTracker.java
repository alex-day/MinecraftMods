package net.minecraft.src.NodePack.Shared;

/**
* each character in a pattern requires a pattern tracker.
* it will keep track of that characters progression through it's break/skip/replace/etc sequences.
*/

public class Node_PatternCharTracker {
	
	
	int numStepsTaken; // the number of steps taken so far.
	Node_Pattern pattern; // the pattern that we are tracking
	
	boolean isBreaking;
	int breakSequenceInd;
	int breakSequenceStep;
	int skipSequenceInd;
	int skipSequenceStep;
	
	int chanceToBreakInd;
	int chanceToDropInd;
	
	int replacementIDInd;
	int replacementMetaInd;
	int replacementIntervalInd;
	int replacementIntervalStep;
	int replacementChanceInd;
	
	char key;
	
	public Node_PatternCharTracker(Node_Pattern pat, int stepsTaken, char key) {
		pattern = pat;
		numStepsTaken = stepsTaken;
		
		this.key = key;
		
		//calculate fields based on steps taken
		initialiseTracking();
	}
	
	/**
	* If the number of steps taken is not equal to zero,
	* this method will sync the tracker so it's in the right position.
	*/
	private void initialiseTracking() {
		isBreaking = false;
		breakSequenceInd = 0;
		breakSequenceStep = 0;
		skipSequenceInd = 0;
		skipSequenceStep = 0;
		
		chanceToBreakInd = 0;
		chanceToDropInd = 0;
		
		replacementIDInd = 0;
		replacementMetaInd = 0;
		replacementIntervalInd = 0;
		replacementIntervalStep = 0;
		replacementChanceInd = 0;
		
		for(int i=0; i<numStepsTaken; i++) {
			takeStep();
		}
	}
	
	/**
	 * increments all of the counters as well as updating the index's so that they don't go over bounds.
	 */
	public void takeStep() {
		//increment all counters.
		if(isBreaking) {
			Node_ValueSet breakSet = pattern.getBreakSet(key);
			
			if(breakSet != null) {
				int desiredStep = breakSet.getIntValueAtIndex(breakSequenceInd);
				breakSequenceStep++;
				if(breakSequenceStep >= desiredStep) {
					breakSequenceInd++;
					breakSequenceStep = 0;
					isBreaking = false;
					if(breakSequenceInd >= pattern.getBreakSet(key).getLength()) {
						breakSequenceInd = 0;
					}
				}
			}
			
			/*
			* only perform some increments if we are in the process of breaking blocks
			*/
			Node_ValueSet chanceToBreakSet = pattern.getChanceToBreakSet(key);
			if(chanceToBreakSet != null) {
				chanceToBreakInd++;
				if(chanceToBreakInd >= chanceToBreakSet.getLength()) {
					chanceToBreakInd = 0;
				}
			}
			
			Node_ValueSet chanceToDropSet = pattern.getChanceToDropSet(key);
			if(chanceToDropSet != null) {
				chanceToDropInd++;
				if(chanceToDropInd >= chanceToDropSet.getLength()) {
					chanceToDropInd = 0;
				}
			}
			
			Node_ValueSet replacementChanceSet = pattern.getReplacementChanceSet(key);
			if(replacementChanceSet != null) {
				replacementChanceInd++;
				if(replacementChanceInd >= replacementChanceSet.getLength()) {
					replacementChanceInd = 0;
				}
			}
			
			Node_ValueSet replacementIDSet = pattern.getReplacementIDSet(key);
			if(replacementIDSet != null) {
				replacementIDInd++;
				if(replacementIDInd >= replacementIDSet.getLength()) {
					replacementIDInd = 0;
				}
			}
			
			Node_ValueSet replacementMetaSet = pattern.getReplacementMetaSet(key);
			if(replacementMetaSet != null) {
				replacementMetaInd++;
				if(replacementMetaInd >= replacementMetaSet.getLength()) {
					replacementMetaInd = 0;
				}
			}
			
			Node_ValueSet replacementIntervalSet = pattern.getReplaceIntervalSet(key);
			if(replacementIntervalSet != null) {
				int desiredStep = replacementIntervalSet.getIntValueAtIndex(replacementIntervalInd);
				replacementIntervalStep++;
				if(replacementIntervalStep >= desiredStep) {
					replacementIntervalInd++;
					replacementIntervalStep = 0;
					if(replacementIntervalInd >= pattern.getReplaceIntervalSet(key).getLength()) {
						replacementIntervalInd = 0;
					}
				}
			}
			
		} else {
			Node_ValueSet skipSet = pattern.getSkipSet(key);
			if(skipSet != null) {
				int desiredStep = pattern.getSkipSet(key).getIntValueAtIndex(skipSequenceInd);
				skipSequenceStep++;
				if(skipSequenceStep >= desiredStep) {
					skipSequenceInd++;
					skipSequenceStep = 0;
					isBreaking = true;
					if(skipSequenceInd >= pattern.getSkipSet(key).getLength()) {
						skipSequenceInd = 0;
					}
				}
			} else {
				isBreaking = true;
			}
		}
	}
	
	/**
	* determines if at the current step a block should be broken
	* this is based on the build/skip sequence as well as the probability to build.
	* if no replacementIDSet is defined, defaults to 100%
	*/
	public boolean shouldBreak() {
		//determine if we are skipping
		if(isBreaking) {
			Node_ValueSet set = pattern.getChanceToBreakSet(key);
			if(set == null) {
				return true;
			}
			double chance = set.getDoubleValueAtIndex(chanceToBreakInd);
			if(Node_PatternTracker.randGen.nextDouble() < chance) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* determines if at the current step a block should be broken
	* this is based on the build/skip sequence as well as the probabillity to build.
	* if no chanceToDrop set is defined, defaults to 0% chance
	*/
	public double shouldDrop() {
		//determine if we are skipping
		if(isBreaking) {
			Node_ValueSet set = pattern.getChanceToDropSet(key);
			if(set == null) {
				return 0.0;
			}
			double chance = set.getDoubleValueAtIndex(chanceToDropInd);
			return chance;
		}
		return 0.0;
	}
	
	/**
	* determines if at the current step a block should be placed
	* this is based on the replacement block chance as well as the replacement interval.
	* if no replacementChanceSet is defined, defaults to 0% chance
	*/
	public boolean shouldReplace() {
		if(isBreaking && replacementIntervalStep == 0) {
			Node_ValueSet set = pattern.getReplacementChanceSet(key);
			if(set == null) {
				return false;
			}
			double chance = set.getDoubleValueAtIndex(replacementChanceInd);
			if(Node_PatternTracker.randGen.nextDouble() < chance) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * if no replacementIDSet is defined, defaults to 0 (air)
	 * @return the block ID that should replace the block that was just broken
	 */
	public int getReplacementBlockID() {
		Node_ValueSet set = pattern.getReplacementIDSet(key);
		if(set == null) {
			return 0;
		}
		return set.getIntValueAtIndex(replacementIDInd);
	}
	
	/**
	 * if no replacementMetaSet is defined, defaults to 0
	 * @return the meta data that should be given to the block that is being placed as a replacement.
	 */
	public int getReplacementMeta() {
		Node_ValueSet set = pattern.getReplacementMetaSet(key);
		if(set == null) {
			return 0;
		}
		return set.getIntValueAtIndex(replacementMetaInd);
	}
}