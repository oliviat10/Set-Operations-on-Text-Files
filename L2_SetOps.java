import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );


		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length)
					set = doubleLength( set );
				set[ count++ ] = infile.readLine();
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/

	// Helper method that searches array for an element
	public static boolean contains(String[] set, String s)
	{
		// Iterate through String values in the array
		for(String setString : set)
		{
			// Check to see if array String matches the element of interest
			if(setString.equals(s))
			{
				return true;
			}
		}
		return false;
	}

	// Union method (set with all elements in both arrays (no dupes))
	static String[] union( String[] set1, String[] set2 )
	{
		// New String array set to max possible length
		String[] unionArray= new String[set1.length+set2.length];

		// Initialize counter to be used in trimArray method
		int count=0;

		// Add all of set 1 to new array
		for(String s : set1)
		{
			unionArray[count++]=s;
		}
		// Iterate through set 2 and add values that aren't already in the set
		for(String s : set2)
		{
			if(contains(set1, s)==false)
			{
				unionArray[count++]=s;
			}
		}
		// Return array trimmed to length of count variable
		 return trimArray(unionArray,count);
	}

	// Intersection method (set 1 and 2 shared elements)
	static String[] intersection( String[] set1, String[] set2 )
	{
		// New String array for intersection
		String[] intersectArray;

		// Max size of intersection array = size of smaller array
		if (set1.length>set2.length)
		{
			intersectArray= new String[set2.length];
		}else
		{
			intersectArray= new String[set1.length];
		}

		// New count variable for trimArray method
		int count=0;

		// Go through set 1, check if value is in set 2
		// if so, add it to the new array
		for (String s : set1)
		{
			if(contains(set2, s))
			{
				intersectArray[count++]=s;
			}
		}
		// Return array trimmed to length of count variable
		return trimArray(intersectArray,count);
	}

	// Difference method (set1 - set2)
	static String[] difference( String[] set1, String[] set2 )
	{
		// New String array of max possible length
		String[] difArray= new String[set1.length];

		// New count variable for trimArray method
		int count=0;

		// Add to new array: only values that exist in set 1 and not set 2
		for(String s : set1)
		{
			if(contains(set2, s)==false)
			{
				difArray[count++]=s;
			}
		}
		// Return trimmed array the length of count variable
		return trimArray(difArray,count);
	}

	// Exclusive or method (elements existing in one of the sets but not both)
	static String[] xor( String[] set1, String[] set2 )
	{
		// Return difference of unionArray - intersectArray
		return difference(union(set1,set2),intersection(set1,set2));
	}

	// Return an array of length 2x containing all old array data
	static String[] doubleLength( String[] old )
	{
		// New array of twice old array's length
		String[] doubledArray= new String[2*old.length];

		// Iterate through old array and fill new array with its elements
		for (int i=0;i<old.length;i++)
		{
			doubledArray[i]=old[i];
		}
		return doubledArray;
	}

	// Return new array of length==count containing all the old array's data
	static String[] trimArray( String[] old, int count )
	{
		// New count variable for new array index value
		int newCount=0;

		// New String array of same length as old array
		String[] trimmedArray= new String[count];

		// Iterate through old array's elements and add to new trimmedArray
		for (String s : old)
		{
			// while index value of new array doesn't exceed length of old array
			while(newCount<count)
			{
				trimmedArray[newCount++]=s;
				break;
			}
		}
		return trimmedArray;
	}

} // END CLASS
