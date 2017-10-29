package SD.Ass3;

import java.util.*;
import org.apache.commons.cli.*;

public class BinSearch {
	public static void main(String[] args) throws Exception
	{
		Comparable key = null;
        List<Comparable> aList = new ArrayList<Comparable>();
        
        //Build options.
        Option keyOption = Option.builder("key")
        		.hasArg()
        		.required()
        		.longOpt("key")
        		.desc("key to search for")
        		.build();
        Option typeOption = Option.builder("type")
        		.hasArg()
        		.required()
        		.longOpt("type")
        		.desc("data type of list")
        		.build();
        Option listOption = Option.builder("list")
        		.hasArgs()
        		.required()
        		.longOpt("list")
        		.desc("list of values")
        		.build();
        
        //Add options.
        Options options = new Options();
        options.addOption(keyOption);
        options.addOption(typeOption);
        options.addOption(listOption);
        
        //Parse command line arguments.
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        
    	//If type=i, convert list values to integers and append to arrayList.
        if(cmd.getOptionValue("type").equals("i"))
        {
        	key = Integer.parseInt(cmd.getOptionValue("key"));
        	for(String s : cmd.getOptionValues("list"))
        	{
        		aList.add(Integer.parseInt(s));
        	}
        }
        //Else type=s, append values to arrayList.
        else if (cmd.getOptionValue("type").equals("s"))
        {
        	key = cmd.getOptionValue("key");
        	for(String s : cmd.getOptionValues("list"))
        	{
        		aList.add(s);
        	}
        }
        //Type is something other than i or s.
        else
        {
        	System.out.println("Invalid type.");
        	System.exit(1);
        }   

        //Check if arguments were provided.
        if(aList.isEmpty())
        {
            System.out.println("No arguments provided!");
            System.exit(1);
        }

        //Call binSearch.
        boolean found = (binSearch(aList, key));
        System.out.println(found ? 1 : 0);
		
	}
	
	public static boolean binSearch(List<Comparable> aList, Comparable key)
    {
        int low = 0, mid = 0, high = aList.size()-1;
        do
        {
            //Calculate midpoint.
            mid = (low + high)/2;
            //Return true if key is found.
            if(aList.get(mid).compareTo(key) == 0)
            {
                return true;
            }
            //Key is located in upper half.
            else if(aList.get(mid).compareTo(key) < 0)//key > aList.get(mid))
            {
                low = mid+1;
            }
            else
            //Key is located in lower half.
            {
                high = mid-1;
            }

        }
        while(low <= high);

        //Return false if key not found.
        return false;
    }

}
