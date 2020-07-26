// =======================================================================
//
// 							Instructions: 
// ----------------------------------------------------------------------
// Enter the numbers of both lists separated by a comma.
// For example 1,5,7 as our first input (first array) and 2,4,6 as our second input (second array).
// The output is the nth smallest element from the two arrays.
// =======================================================================

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ArrayProblem {

	//Util Function
	public static int[] listToArray(List<Integer> list)
	{
		int[] tempArray = new int[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			tempArray[i] = list.get(i);
		}
		return tempArray;
	}
	
	//Util Function
	private static int[] fillArray()
	{			
		List<Integer> arrayValues;
		Scanner s = new Scanner(System.in);
		do
		{
			arrayValues = new ArrayList<Integer>();

			String strInput = s.nextLine();
			String[] tempValues = strInput.split(",");
			for(int i = 0; i < tempValues.length; i++)
			{
				try
				{
					arrayValues.add(Integer.parseInt(tempValues[i].trim()));
				}
				catch(Exception ex0){}
			}
		}
		while(arrayValues.size() <= 0); //number of integers must be > 0
		//s.close();
		return listToArray(arrayValues);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("** Please enter numbers in the array separated by a comma. **");
		System.out.println("** For example 1,5,7 as our first input (first array) and 2,4,6 as our second input (second array). **");
		System.out.println("** The output is the nth smallest element from the two arrays. **");
		
			int[] arrayA = null;
			int[] arrayB = null;
			
			/*
			boolean debugMode = true;
			if (debugMode)
			{
				arrayA = new int[] {2, 5, 8, 13};
				arrayB = new int[] {1, 9, 10, 15};
				System.out.format("A: %s", Arrays.toString(arrayA));
				System.out.format("\nB: %s\n", Arrays.toString(arrayB));
			}
			*/
			
			while((arrayA == null || arrayB == null) || arrayA.length != arrayB.length)
			{
				System.out.println("** Both lists must be of the same length(n) **");
				System.out.println("Enter integers for Array A");				
				arrayA = fillArray();
				System.out.println("Enter integers for Array B");
				arrayB = fillArray();
			} 		
			defineNthElement(arrayA, arrayB);
	}
	
	private static void defineNthElement(int[] A, int[] B)
	{
		int nthSmallest = -1;
		if(A.length == B.length && A.length > 0)
		{
			int n = A.length; // len(A) = len(B), doesn't matter which one we use.

			if(B[0] > A[n - 1])
				nthSmallest =  A[n - 1];
			else if(A[0] > B[n - 1])
				nthSmallest = B[n - 1];
			else			
				nthSmallest = findNth(A, B, 0, n - 1, n);
		}
		if(nthSmallest == -1)
			System.out.println("Wrong Input");
		else
			System.out.printf("nth smallest(%d): %d", A.length, nthSmallest);
	}
	
	private static int findNth(int[] A, int[] B, int start, int end, int n)
	{
		int i = (start + end) / 2;
		int j = (n - (i + 1)) - 1;
		
		if(A[i] < B[j])
		{
			if(A[i + 1] > B[j])
				return B[j];
			else
				return findNth(A, B, i + 1, end, n);
		}
		else
		{
			if(B[j + 1] > A[i])
				return A[i];
			else
				return findNth(A, B, start, i - 1, n);
		}
	}
}
