package net.minecraft.src;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.src.NodePack.Shared.Node_OptionsFilter;
import net.minecraft.src.NodePack.Shared.Node_Pattern;
import net.minecraft.src.NodePack.Shared.Node_ValueSet;

public class mod_Node_PackLoader extends BaseMod
{
	
    @Override
	public String getVersion() {
    	return "3.0 for Minecraft 1.0.0";
	}
    
    @Override
    public String getName() {
    	return "Node Pack Loader";
    }

    public mod_Node_PackLoader()
    {
        
    }
    
    @Override
	public void load() {
    	String s;
    	
		ModLoader.getMinecraftInstance();
        File file = Minecraft.getMinecraftDir();
		s = file.getAbsolutePath()+"/mods/NodePack/";
        File file1 = new File(file.getAbsolutePath()+"/mods/");
        File file2 = new File(file1.getAbsolutePath()+"/NodePack/");
        nodePackLogFile = new File(s+"NodePack_log.txt");
        trace("NodePack log file path: "+nodePackLogFile.getAbsolutePath());
        try
        {
            file1.mkdir();
            boolean folderWasCreated = file2.mkdir();
            
            if(folderWasCreated) {
	            //copy default options files
        		File defaultOptionsFolder = getDefaultOptions(file2);
        		if(defaultOptionsFolder != null) {
        			trace("Default options file: "+defaultOptionsFolder.getAbsolutePath()+" exists? "+defaultOptionsFolder.exists());
        			copyFiles(defaultOptionsFolder, file2);
        		}
            } else {
            	long modifiedTime = file.lastModified();
            	Calendar cal = Calendar.getInstance();
            	cal.clear();
            	cal.set(2000, 11, 13);
            	long lastUpdate = cal.getTimeInMillis();
            	if(modifiedTime < lastUpdate) {
            		//remove old options files
            		deleteAllFilesInDirectory(file2);
            		//copy default options files
            		File defaultOptionsFolder = getDefaultOptions(file2);
            		if(defaultOptionsFolder != null) {
            			trace("Default options file: "+defaultOptionsFolder.getAbsolutePath()+" exists? "+defaultOptionsFolder.exists());
            			copyFiles(defaultOptionsFolder, file2);
            		}
            	}
            }
            nodePackLogFile.createNewFile();
        }
        catch(Exception exception)
        {
            trace("unable to load the nodepack files - abort"); 
            exception.printStackTrace();
        }
        try
        {
            logPrintStream = new PrintStream(nodePackLogFile);
        }
        catch(Exception exception1)
        {
            trace("Could not open NodePack Log PrintStream");
        	System.err.println(exception1);
            System.exit(1);
        }
        File file3 = new File(s);
        if(file3.isDirectory())
        {
            Node_OptionsFilter optionsfilter = new Node_OptionsFilter();
            trace("searching in directory: "+file3.getAbsolutePath());
            File afile1[] = file3.listFiles(optionsfilter);
            trace("There are "+afile1.length+" files that match.");
            for(int j = 0; j < afile1.length; j++)
            {
                try
                {
                    parseFileForOptions(afile1[j]);
                    continue;
                }
                catch(Exception exception3)
                {
                    trace("Error parsing in options file: "+afile1[j].getAbsolutePath());
                    trace("Reason: IO Error");
                }
            }
        }
        
        loaded = true;
        trace("ID's: "+ids);
        trace("Int options: "+integerOptions);
        trace("Float options: "+floatOptions);
        trace("boolean options: "+booleanOptions);
        trace("string options: "+stringOptions);
        trace("patterns: "+patterns);
        trace("sequences: "+sequences);
	}
    
    @Override
    public void modsLoaded()
	{
    	//preload the textures used
        
	}
    
    /**
     * Causes s to be printed to the NodePack Log.
     * @param s the value that you want printed
     */
    public static void trace(String s)
    {
    	if(loaded && (booleanOptions.size() <= 0 || !getBooleanOption("enableLog"))) {
    		//ModLoader.getLogger().log(ModLoader.cfgLoggingLevel, "<NodePack>: "+s);
    		return;
    	}
    	
    	if(logPrintStream != null) {
	        logPrintStream.println("<NodePack>: "+s);
	        logPrintStream.flush();
    	} else {
    		ModLoader.getLogger().log(java.util.logging.Level.CONFIG, "<NodePack>: "+s);
    		//System.out.println("<NodePack>: "+s);
    	}
    }

    /**
     * Get the block or item ID based on an identifying string
     * @param s the identifier
     * @return the item or block id for that identifier. Or -1 if none exists.
     */
    public static int getID(String s)
    {
        Integer integer = ids.get(s);
        if(integer == null)
        {
            trace("No match found for key: "+s+" id: "+integer);
            return -1;
        }
        return integer.intValue();
    }

    /**
     * Get the integer value of an option based on an identifying string
     * @param s the identifier
     * @return the option value for that identifier. Or -1 if none exists.
     */
    public static int getIntOption(String s)
    {
        Integer integer = integerOptions.get(s);
        if(integer == null)
        {
            trace("No match found for key: "+s+" id: "+integer);
            return -1;
        }
        return integer.intValue();
    }

    /**
     * Get the float value of an option based on an identifying string
     * @param s the identifier
     * @return the option value for that identifier. Or -1 if none exists.
     */
    public static float getFloatOption(String s)
    {
        Float float1 = floatOptions.get(s);
        if(float1 == null)
        {
            trace("No match found for key: "+s+" id: "+float1);
            return -1;
        }
        return float1.floatValue();
    }

    /**
     * Get the boolean value of an option based on an identifying string
     * @param s the identifier
     * @return the option value for that identifier. Or false if none exists.
     */
    public static boolean getBooleanOption(String s)
    {
        Boolean boolean1 = booleanOptions.get(s);
        if(boolean1 == null)
        {
            trace("No match found for key: "+s+" id: "+boolean1);
            return false;
        }
        return boolean1.booleanValue();
    }
    
    /**
     * Get the string value of an option based on an identifying string
     * @param s the identifier
     * @return the option value for that identifier. Or null if none exists.
     */
    public static String getStringOption(String s)
    {
        String str = stringOptions.get(s);
        if(str == null)
        {
            trace("No match found for key: "+s+" id: "+str);
        }
        return str;
    }
    
    /**
     * Get a node pattern based on an identifying string
     * @param s the identifier
     * @return the node pattern. Or null if none exists.
     */
    public static Node_Pattern getPattern(String s)
    {
    	Node_Pattern pattern = patterns.get(s);
    	return pattern;
    }
    
    /**
     * Get a node sequence based on an identifying string
     * @param s the identifier
     * @return the node sequence. Or null if none exists.
     */
    public static Node_ValueSet getSequence(String s)
    {
    	Node_ValueSet sequence = sequences.get(s);
    	return sequence;
    }
    
    /**
     * prints a string to the in-game chat.
     * @param s the string to print
     */
    public static void printToChat(String s)
    {
        ModLoader.getMinecraftInstance().ingameGUI.getChatGUI().printChatMessage(s);
    }
    
    /**
     * Parses the supplied file for item and block Id's
     * @param file the file to parse
     * @throws Exception if something unexpected happens.
     */
    public static void parseFileForIDs(File file)
        throws Exception
    {
        BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
        for(String s = bufferedreader.readLine(); s != null; s = bufferedreader.readLine())
        {
            if(s.matches("[\\s]*") || s.startsWith("#"))
            {
                continue;
            }
            String as[] = s.split("[\\s][\\s]*");
            if(as.length != 2)
            {
                trace("Error parsing in Id in file: "+file.getAbsolutePath()+" for line: "+s);
                trace("Reason: Too many tokens on line "+as.length);
                continue;
            }
            int i = -1;
            try
            {
                i = Integer.parseInt(as[1]);
            }
            catch(NumberFormatException numberformatexception)
            {
                trace("Error parsing in Id in file: "+file.getAbsolutePath()+" for line: "+s);
                trace("Reason: Could not convert ID token to integer");
            }
            ids.put(as[0], Integer.valueOf(i));
        }
        bufferedreader.close();

    }

    /**
     * Parses the supplied file for options
     * @param file the file to parse
     * @throws Exception if something unexpected happens.
     */
    public static void parseFileForOptions(File file)
        throws Exception
    {	
        BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
        for(String s = bufferedreader.readLine(); s != null; s = bufferedreader.readLine())
        {
            if(s.matches("[\\s]*") || s.startsWith("#"))
            {
                continue;
            }
            String as[] = s.split("[\\s][\\s]*");
            if(as.length != 3)
            {
                trace("Error parsing in options in file: "+file.getAbsolutePath()+" for line: "+s);
                trace("Reason: Too many tokens on line "+as.length);
                continue;
            }
            if(as[0].equalsIgnoreCase("int"))
            {
                int i = -1;
                try
                {
                    i = Integer.parseInt(as[2]);
                }
                catch(NumberFormatException numberformatexception)
                {
                    trace("Error parsing in options file in file: "+file.getAbsolutePath()+" for line: "+s);
                    trace("Reason: Could not convert value token to an integer");
                }
                integerOptions.put(as[1], Integer.valueOf(i));
                continue;
            } else
            if(as[0].equalsIgnoreCase("float"))
            {
                float f = -1F;
                try
                {
                    f = Float.parseFloat(as[2]);
                }
                catch(NumberFormatException numberformatexception1)
                {
                    trace("Error parsing in options file in file: "+file.getAbsolutePath()+" for line: "+s);
                    trace("Reason: Could not convert value token to float");
                }
                floatOptions.put(as[1], Float.valueOf(f));
                continue;
            } else
            if(as[0].equalsIgnoreCase("boolean"))
            {
                boolean flag = false;
                try
                {
                    flag = Boolean.parseBoolean(as[2]);
                }
                catch(NumberFormatException numberformatexception2)
                {
                    trace("Error parsing in options file in file: "+file.getAbsolutePath()+" for line: "+s);
                    trace("Reason: Could not convert value token to a boolean");
                }
                booleanOptions.put(as[1], Boolean.valueOf(flag));
            } else
            if(as[0].equalsIgnoreCase("string"))
            {
                String str = null;
                str = as[2];
                stringOptions.put(as[1], str);
            } else
            if(as[0].equalsIgnoreCase("pattern"))
            {
            	Node_Pattern pattern = null;
                try
                {
                    //found a pattern: build the pattern array
                	boolean endOfPattern = false;
                	
                	ArrayList<String> patternArray = new ArrayList<String>();
                	
                	do {
                		s = bufferedreader.readLine();
                		
                		if(s != null) {
                			if(s.startsWith("/")) {
                				endOfPattern = true;
                			}
                			else if(s.matches("[\\s]*") || s.startsWith("#")) {
                				//do not parse empty lines or comments
                			} else {
                				patternArray.add(s);
                			}
                		}
                		
                	} while(s != null && !endOfPattern);
                	
                	pattern = Node_Pattern.parsePattern(patternArray.toArray(new String[0]));
                	endOfPattern = false;
                	
                	Map<Character,Node_ValueSet> validBlockSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> breakSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> chanceToBreakSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> skipSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> chanceToDropSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> replacementChanceSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> replacementIDSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> replacementMetaSets = new HashMap<Character,Node_ValueSet>();
                	Map<Character,Node_ValueSet> replacementIntervalSets = new HashMap<Character,Node_ValueSet>();
                	
                	do {
                		String[] splits = s.split(" ");
                		if(s.matches("[\\s]*") || s.startsWith("#")) {
            				//do not parse empty lines or comments
            			} else if(splits[0].equalsIgnoreCase("/EndPattern")) {
                			endOfPattern = true;
                		} else if(splits[0].equalsIgnoreCase("/uOffset")) {
        					Node_ValueSet set = Node_ValueSet.parseSet(splits[1]);
        					pattern.setUOffsetSet(set);
        				} else if(splits[0].equalsIgnoreCase("/vOffset")) {
        					Node_ValueSet set = Node_ValueSet.parseSet(splits[1]);
        					pattern.setVOffsetSet(set);
        				} else if(splits[0].equalsIgnoreCase("/uMoveInterval")) {
        					Node_ValueSet set = Node_ValueSet.parseSet(splits[1]);
        					pattern.setUMoveIntervalSet(set);
        				} else if(splits[0].equalsIgnoreCase("/vMoveInterval")) {
        					Node_ValueSet set = Node_ValueSet.parseSet(splits[1]);
        					pattern.setVMoveIntervalSet(set);
        				} else if(splits[0].equalsIgnoreCase("/Define")) {
        					//get character being defined
        					char key = splits[1].charAt(0);
        					
        					s = bufferedreader.readLine();
        					while(s != null && !s.equalsIgnoreCase("/EndDefine")) {
        						String[] splits2 = s.split(" ");
        						if(splits2.length == 2) {
	        						if(splits2[0].equalsIgnoreCase("breakableBlocks")) {
	        							validBlockSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("breakSequence")) {
	        							breakSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("skipSequence")) {
	        							skipSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("chanceToBreak")) {
	        							chanceToBreakSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("chanceToDrop")) {
	        							chanceToDropSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("replacementChance")) {
	        							replacementChanceSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("replacementID")) {
	        							replacementIDSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("replacementMeta")) {
	        							replacementMetaSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						} else if(splits2[0].equalsIgnoreCase("replacementInterval")) {
	        							replacementIntervalSets.put(key, Node_ValueSet.parseSet(splits2[1]));
	        						}
        						}
        						
        						s = bufferedreader.readLine();
        					}
        					
        				}
                		
                		s = bufferedreader.readLine();
                	} while (s != null && !endOfPattern);
                	
                	pattern.setValidBlockSets(validBlockSets);
                	pattern.setBreakSets(breakSets);
                	pattern.setSkipSets(skipSets);
                	pattern.setChanceToBreakSets(chanceToBreakSets);
                	pattern.setChanceToDropSets(chanceToDropSets);
                	pattern.setReplacementChanceSets(replacementChanceSets);
                	pattern.setReplacementIDSets(replacementIDSets);
                	pattern.setReplacementMetaSets(replacementMetaSets);
                	pattern.setReplacementIntervalSets(replacementIntervalSets);
                }
                catch(Exception ex1)
                {
                    trace("Error parsing in options file in file: "+file.getAbsolutePath()+" for line: "+s);
                    trace("Reason: Invalid pattern found.");
                    ex1.printStackTrace(logPrintStream);
                }
                if(pattern != null) {
                	patterns.put(as[1], pattern);
                } else {
                	//patterns.put(as[1], Node_Pattern.getDefaultPattern());
                }
                
            } else
            if(as[0].equalsIgnoreCase("seq"))
            {
                Node_ValueSet set = null;
                try
                {
                    set = Node_ValueSet.parseSet(as[2]);
                }
                catch(NumberFormatException numberformatexception2)
                {
                    trace("Error parsing in options file in file: "+file.getAbsolutePath()+" for line: "+s);
                    trace("Reason: Could not convert value token to a boolean");
                }
                sequences.put(as[1], set);
            }
            else
            {
                trace("Error parsing in options file in file: "+file.getAbsolutePath()+" for line: "+s);
                trace("Reason: Could not resolve option type");
            }
        }
        bufferedreader.close();
    }
    
    /**
     * copies the default options from the minecraft.jar to the specified folder
     * @param to the destination folder.
     * @return null, however if the game is run via eclipse it will return the location that the default options should be in.
     */
    public static File getDefaultOptions(File to) {
    	try {
	    	byte[] buf = new byte[1024];
	    	File jarRoot = new File((net.minecraft.src.ModLoader.class).getProtectionDomain().getCodeSource().getLocation().toURI());
			//trace("Jar root "+jarRoot.getAbsolutePath()+" exists? "+jarRoot.exists());
	    	
			if(jarRoot.isFile() && jarRoot.getName().endsWith(".jar")) {
				FileInputStream fileinputstream = new FileInputStream(jarRoot);
		        ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
		        Object obj = null;
		        do
		        {
		            ZipEntry zipentry = zipinputstream.getNextEntry();
		            if(zipentry == null)
		            {
		                break;
		            }
		            String s1 = zipentry.getName();
		            //trace("zip entry name: "+s1);
		            if(!zipentry.isDirectory() && (s1.startsWith("Node") && s1.endsWith(".txt")))
		            {
		            	int n;
		                FileOutputStream fileoutputstream;
		                File newFile = new File(to,s1);
		                if (zipentry.isDirectory()) {
		                    if (!newFile.mkdirs()) {
		                        break;
		                    }
		                    zipentry = zipinputstream.getNextEntry();
		                    continue;
		                }
	
		                //trace("path to split"+s1);
		                s1 = s1.replace("\\", "/");
		                String[] nameChunks = s1.split("/");
		                //trace("split size: "+nameChunks.length);
		                String name = nameChunks[nameChunks.length-1];
		                //trace(to.getAbsolutePath()+"/"+name);
		                fileoutputstream = new FileOutputStream(to.getAbsolutePath()+"/"+name);
		                
		                while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
		                    fileoutputstream.write(buf, 0, n);
		                }
	
		                fileoutputstream.close();
		                zipinputstream.closeEntry();
		            }
		        } while(true);
		        zipinputstream.close();
			} else {
				return new File(jarRoot,"NodePack/Resources/Options/");
			}
    	} catch(Exception e) {
    		trace("Error copying object files "+e);
    	}
    	return null;
    }
    
    /**
     * deletes all files in the specified directory
     * @param f the directory
     */
    public static void deleteAllFilesInDirectory(File f) {
    	if(f.isDirectory()) {
    		File[] files = f.listFiles();
    		for(int i=0; i<files.length; i++) {
    			files[i].delete();
    		}
    	}
    }
    
    /**
     * copies all files from the from directory to the to directory.
     * @param from
     * @param to
     */
    public static void copyFiles(File from, File to) {
    	if(from == null || to == null) {
    		trace("Error initialising the options files.");
    		return;
    	}
    	try {
	    	if (from.isDirectory()) {
	            if (!to.exists()) {
	                to.mkdir();
	            }
	            File[] files = from.listFiles();
	            for(File file:files){
	                InputStream in = new FileInputStream(file);
	                OutputStream out = new FileOutputStream(to+"/"+file.getName());
	
	                // Copy the bits from input stream to output stream
	                byte[] buf = new byte[1024];
	                int len;
	                while ((len = in.read(buf)) > 0) {
	                    out.write(buf, 0, len);
	                }
	                in.close();
	                out.close();
	            }            
	        }
    	} catch(IOException ex) {
    		trace("Error initialising the options files.");
    	}
    }

    public static final int field_25104_k = 0;
    public static final int field_25110_e = 1;
    public static final int field_25107_h = 2;
    public static File nodePackLogFile;
    public static PrintStream logPrintStream;
    public static Map<String,Integer> ids = new HashMap<String,Integer>();
    public static Map<String,Integer> integerOptions = new HashMap<String,Integer>();
    public static Map<String,Float> floatOptions = new HashMap<String,Float>();
    public static Map<String,Boolean> booleanOptions = new HashMap<String,Boolean>();
    public static Map<String,String> stringOptions = new HashMap<String,String>();
    public static Map<String,Node_Pattern> patterns = new HashMap<String,Node_Pattern>();
    public static Map<String,Node_ValueSet> sequences = new HashMap<String,Node_ValueSet>();
    public static boolean loaded = false;
}
