package net.minecraft.src.NodePack.Shared;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Node_Pattern {
	private static char[][] defaultString = {{' ','X',' '},{'X','X','X'},{' ','X',' '}};
	private static Node_Pattern defaultPattern = new Node_Pattern(defaultString,1,1);
	
	char[][] pattern;
	int centreU;
	int centreV;
	
	Node_ValueSet uOffsetSet;
	Node_ValueSet vOffsetSet;
	Node_ValueSet vMoveIntervalSet;
	Node_ValueSet uMoveIntervalSet;
	
	
	Map<Character,Node_ValueSet> validBlockSets;
	Map<Character,Node_ValueSet> breakSets;
	Map<Character,Node_ValueSet> chanceToBreakSets;
	Map<Character,Node_ValueSet> skipSets;
	Map<Character,Node_ValueSet> chanceToDropSets;
	Map<Character,Node_ValueSet> replacementChanceSets;
	Map<Character,Node_ValueSet> replacementIDSets;
	Map<Character,Node_ValueSet> replacementMetaSets;
	Map<Character,Node_ValueSet> replacementIntervalSets;
	
	
	private Node_Pattern(char[][] pattern, int cenU, int cenV) {
		this.pattern = pattern;
		centreU = cenU;
		centreV = cenV;
	}
	
	/**
	 * parses and constructs a new pattern based on the input string
	 * @param pattern the string pattern to match
	 * @return a new Node_Pattern
	 * 
	 */
	public static Node_Pattern parsePattern(String[] pattern) {
		
		//identify the centre of the pattern.
		int centreU = 0;
		int centreV = 0;
		
		//figure out the max length of the rows
		int maxColLength = 0;
		for(int i=0; i<pattern.length; i++) {
			if(pattern[i].length() > maxColLength) {
				maxColLength = pattern[i].length();
			}
		}
		
		char[][] charPattern = new char[pattern.length][maxColLength];
		
		for(int i=0; i<pattern.length; i++) {
			for(int j=0; j<pattern[i].length() || j<maxColLength; j++) {
				if(j< pattern[i].length()) {
					charPattern[i][j] = pattern[i].charAt(j);
					if(pattern[i].charAt(j) == 'O' || pattern[i].charAt(j) == 'o') {
						centreU = i;
						centreV = j;
					}
				} else {
					charPattern[i][j] = ' ';
				}
			}
		}
		
		Node_Pattern nodePattern = new Node_Pattern(charPattern,centreU,centreV);
		
		return nodePattern;
	}
	
	public Node_PatternTracker initialisePatternTracker(int spacesMoved) {
		Node_PatternTracker patternTracker = new Node_PatternTracker(this, spacesMoved);
		return patternTracker;
	}
	
	public Map<Character,Node_PatternCharTracker> initialiseCharPatternTrackers(int spacesMoved) {
		
		Map<Character,Node_PatternCharTracker> map = new HashMap<Character,Node_PatternCharTracker>();
		Set<Character> keySet = getCharSet();
		for(Iterator<Character> it = keySet.iterator(); it.hasNext();) {
			char key = it.next();
			Node_PatternCharTracker patternCharTracker = new Node_PatternCharTracker(this, spacesMoved, key);
			map.put(key,patternCharTracker);
		}
		return map;
	}

	/**
	 * @return the default pattern to be used if a pattern could not load correctly
	 */
	public static Node_Pattern getDefaultPattern() {
		return defaultPattern;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		ret += "PATTERN{ ";
		
		ret += "centre ("+centreU+","+centreV+")";
		
		ret += "value (";
		
		for(int i=0; i<pattern.length; i++) {
			ret += "(";
			for(int j=0; j<pattern[i].length; j++) {
				ret += pattern[i][j];
				ret += ",";
			}
			ret += "),";
		}
		
		ret += ") ";
		
		ret += "}";
		return ret;
	}
	
	public int getMinUOffset() {
		return - centreU;
	}
	public int getMinVOffset() {
		return - centreV;
	}
	public int getMaxUOffset() {
		return pattern.length - centreU;
	}
	public int getMaxVOffset() {
		return pattern[0].length - centreV;
	}

	public boolean removeAtOffsetPosition(Map<Character,Node_PatternCharTracker> charTrackers, int uOff, int vOff) {
		if(pattern[centreU+uOff][centreV+vOff] == ' ') {
			return false;
		}
		Node_PatternCharTracker tracker = charTrackers.get(pattern[centreU+uOff][centreV+vOff]);
		if(tracker == null) {
			return true;
		}
		if(tracker.shouldBreak()) {
			return true;
		}
		return false;
	}
	
	public double dropAtOffsetPosition(Map<Character,Node_PatternCharTracker> charTrackers, int uOff, int vOff) {
		Node_PatternCharTracker tracker = charTrackers.get(pattern[centreU+uOff][centreV+vOff]);
		if(tracker == null) {
			return 0;
		}
		return tracker.shouldDrop();
	}
	
	public boolean replaceAtOffsetPosition(Map<Character,Node_PatternCharTracker> charTrackers, int uOff, int vOff) {
		Node_PatternCharTracker tracker = charTrackers.get(pattern[centreU+uOff][centreV+vOff]);
		if(tracker == null) {
			return false;
		}
		return tracker.shouldReplace();
	}
	
	public int replaceIDAtOffsetPosition(Map<Character,Node_PatternCharTracker> charTrackers, int uOff, int vOff) {
		Node_PatternCharTracker tracker = charTrackers.get(pattern[centreU+uOff][centreV+vOff]);
		if(tracker == null) {
			return 0;
		}
		return tracker.getReplacementBlockID();
	}
	
	public int replaceMetaAtOffsetPosition(Map<Character,Node_PatternCharTracker> charTrackers, int uOff, int vOff) {
		Node_PatternCharTracker tracker = charTrackers.get(pattern[centreU+uOff][centreV+vOff]);
		if(tracker == null) {
			return 0;
		}
		return tracker.getReplacementMeta();
	}
	
	public int getUOffset(Node_PatternTracker tracker) {
		return tracker.getUOffset();
	}
	public int getVOffset(Node_PatternTracker tracker) {
		return tracker.getVOffset();
	}
	
	public boolean isBlockBreakable(int uOff, int vOff, int bID) {
		if(validBlockSets.get(pattern[centreU+uOff][centreV+vOff]) == null) {
			return true;
		}
		return validBlockSets.get(pattern[centreU+uOff][centreV+vOff]).contains(bID);
	}
	
	public Node_ValueSet getUOffsetSet() {
		return uOffsetSet;
	}

	public Node_ValueSet getVOffsetSet() {
		return vOffsetSet;
	}
	
	public Node_ValueSet getUMoveIntervalSet() {
		return uMoveIntervalSet;
	}

	public Node_ValueSet getVMoveIntervalSet() {
		return vMoveIntervalSet;
	}
	
	public Node_ValueSet getBreakSet(char val) {
		return breakSets.get(val);
	}

	public Node_ValueSet getChanceToBreakSet(char val) {
		return chanceToBreakSets.get(val);
	}

	public Node_ValueSet getSkipSet(char val) {
		return skipSets.get(val);
	}

	public Node_ValueSet getChanceToDropSet(char val) {
		return chanceToDropSets.get(val);
	}

	public Node_ValueSet getReplacementChanceSet(char val) {
		return replacementChanceSets.get(val);
	}

	public Node_ValueSet getReplacementIDSet(char val) {
		return replacementIDSets.get(val);
	}

	public Node_ValueSet getReplacementMetaSet(char val) {
		return replacementMetaSets.get(val);
	}

	public Node_ValueSet getReplaceIntervalSet(char val) {
		return replacementIntervalSets.get(val);
	}
	
	public Set<Character> getCharSet() {
		Set<Character> keySet = new HashSet<Character>();
		
		if(validBlockSets != null) {
			Set<Character> tmpSet = validBlockSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		if(breakSets != null) {
			Set<Character> tmpSet = breakSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(chanceToBreakSets != null) {
			Set<Character> tmpSet = chanceToBreakSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(skipSets != null) {
			Set<Character> tmpSet = skipSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(chanceToDropSets != null) {
			Set<Character> tmpSet = chanceToDropSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(replacementChanceSets != null) {
			Set<Character> tmpSet = replacementChanceSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(replacementIDSets != null) {
			Set<Character> tmpSet = replacementIDSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(replacementMetaSets != null) {
			Set<Character> tmpSet = replacementMetaSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		if(replacementIntervalSets != null) {
			Set<Character> tmpSet = replacementIntervalSets.keySet();
			for ( Iterator<Character> it = tmpSet.iterator(); it.hasNext(); ) {
				char next = it.next();
				if(!keySet.contains(next)) {
					keySet.add(next);
				}
			}
		}
		
		return keySet;
	}

	public void setUOffsetSet(Node_ValueSet set) {
		uOffsetSet = set;
	}

	public void setVOffsetSet(Node_ValueSet set) {
		vOffsetSet = set;
	}
	
	public void setUMoveIntervalSet(Node_ValueSet set) {
		uMoveIntervalSet = set;
	}

	public void setVMoveIntervalSet(Node_ValueSet set) {
		vMoveIntervalSet = set;
	}
	
	public void setValidBlockSets(Map<Character, Node_ValueSet> validBlockSets2) {
		validBlockSets = validBlockSets2;
		
	}

	public void setBreakSets(Map<Character, Node_ValueSet> breakSets2) {
		breakSets = breakSets2;
	}

	public void setSkipSets(Map<Character, Node_ValueSet> skipSets2) {
		skipSets = skipSets2;
	}

	public void setChanceToBreakSets(Map<Character, Node_ValueSet> chanceToBreakSets2) {
		chanceToBreakSets = chanceToBreakSets2;
	}

	public void setChanceToDropSets(Map<Character, Node_ValueSet> chanceToDropSets2) {
		chanceToDropSets = chanceToDropSets2;
		
	}

	public void setReplacementChanceSets(Map<Character, Node_ValueSet> replacementChanceSets2) {
		replacementChanceSets = replacementChanceSets2;
	}

	public void setReplacementIDSets(Map<Character, Node_ValueSet> replacementIDSets2) {
		replacementIDSets = replacementIDSets2;
	}

	public void setReplacementMetaSets(Map<Character, Node_ValueSet> replacementMetaSets2) {
		replacementMetaSets = replacementMetaSets2;
	}

	public void setReplacementIntervalSets(Map<Character, Node_ValueSet> replacementIntervalSets2) {
		replacementIntervalSets = replacementIntervalSets2;
	}
	
}
