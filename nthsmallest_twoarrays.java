// =======================================================================
// Author: Danyal Isran
// Email: isran01@email.franklin.edu
// Title: Find the nth element of two sorted lists each of length n
// Course: COMP620 FALL 2014
// Project: 2
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
public class COMP620Project2 {

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
			
			
			boolean debugMode = true;
			if (debugMode)
			{
				//arrayA = new int[] {1, 12, 15, 20, 26, 32, 50};
				//arrayB = new int[] {2, 4, 7, 22, 25, 28, 30};
				arrayA = new int[] {2, 4, 7, 10, 13};
				arrayB = new int[] { 15, 20, 26, 32, 50};
				System.out.format("A: %s", Arrays.toString(arrayA));
				System.out.format("\nB: %s\n", Arrays.toString(arrayB));
			}
			
			
			while((arrayA == null || arrayB == null) || arrayA.length != arrayB.length)
			{
				System.out.println("** Both lists must be of the same length(n) **");
				System.out.println("Enter integers for Array A");				
				arrayA = fillArray();
				System.out.println("Enter integers for Array B");
				arrayB = fillArray();
			} 		
			defineKthElement(arrayA, arrayB, 3);
			//System.out.format("%d", problem2(arrayA, arrayB, 13, 14));
	}
	
	/**
	 * k > n + 1
	 * @param A
	 * @param B
	 * @param k
	 */
	private static void defineKthProblem2(int[] A, int[] B, int k)
	{
		int kthSmallest = -1;
		int n = A.length; // len(A) = len(B), doesn't matter which one we use.
		if(A.length == B.length && A.length > 0 && k > A.length + 1)
		{			 
			if(B[0] > A[n - 1])
				kthSmallest =  B[k - n - 1];
			else if(A[0] > B[n - 1])
				kthSmallest = A[k - n - 1];
			else			
				kthSmallest = findKth2(A, B, k);
		}
		if(kthSmallest == -1)
			System.out.println("Wrong Input: Both lists have to be same length AND k > n + 1");
		else
			System.out.printf("kth largest(%d): %d", (2*n) - k + 1, kthSmallest);
	}
	
	
	private static int kth_number(int[] A, int[] B, int k)
	{
	   assert(A.length + B.length >= k); // Fail if no enough elements 
	   int left = 0; int right = A.length < k ? A.length : k;
	   // {loop invariant: the number of elements chosen from a is in [left, right]}
	   while (left <= right)
	   {
	      if (left == right) 
	      {  if (left > 0)
	           return Math.max(A[left-1], A[k-left-1]);
	         else 
	           return B[k-1];
	      }
	      int m = (left+right)/2;
	      if (k - m > B.length) left = m+1;
	      else if (A[m] < B[k-m-1]);
	      else if (k-m < B.length && B[k-m] < A[m-1]) right = m-1;
	      else return Math.max(A[m-1], B[k-m-1]);
	   }
	   return -1;
	} 
	

	
	private static int findKth2(int[] A, int[] B, int k)
	{			
		int i = (k - 1) / 2;
		int j = (k - 1) - i;
			
		if(A[i] < B[j])
		{
			if(B[j - 1] < A[i])
				return A[i];
			else
				return findKth2(sliceArray(A, i + 1, A.length - 1), sliceArray(B, 0, j - 1), k - i -1);
		}
		else
		{
			if(A[i - 1] < B[j])
				return B[j];
			else
				return findKth2(sliceArray(A, 0, i - 1), sliceArray(B, j + 1, B.length - 1), k - j -1);
		}
	}
	
	private static int[] sliceArray(int[] array, int i, int j){
		int[] temp = new int[j - i + 1];
		for(int k = 0; i <= j; k++) {
			temp[k] = array[i];
			i++;
		}
		return temp;
	}
	
	/**
	 * k < n
	 * @param A
	 * @param B
	 * @param k
	 */
	private static void defineKthElement(int[] A, int[] B, int k)
	{
		int kthSmallest = -1;
		if(A.length == B.length && A.length > 0 && k < A.length)
		{
			int n = A.length; // len(A) = len(B), doesn't matter which one we use.
			if(B[0] > A[n - 1])
				kthSmallest =  A[k -1];
			else if(A[0] > B[n - 1])
				kthSmallest = B[k - 1];
			else			
				kthSmallest = findKth(A, B, 0, k - 1, k);
		}
		if(kthSmallest == -1)
			System.out.println("Wrong Input: Both lists have to be same length AND k < n");
		else
			System.out.printf("kth smallest(%d): %d", k, kthSmallest);
	}
	
	/**
	 * k < n
	 * if n = 5
	 * k = 1, 2, 3, 4
	 * @param A
	 * @param B
	 * @param start
	 * @param end
	 * @param n
	 * @return
	 */
	private static int findKth(int[] A, int[] B, int start, int end, int k)
	{			
		int i = ((start + 1 + end + 1) / 2) - 1;
		int j = (k - (i + 1)) - 1;
		
		if(k == 1)
			return A[0] < B[0] ? A[0] : B[0];
		
		if(A[i] < B[j])
		{
			if(A[i + 1] > B[j])
				return B[j];
			else
				return findKth(A, B, i + 1, end, k);
		}
		else
		{
			if(B[j + 1] > A[i])
				return A[i];
			else
				return findKth(A, B, start, i - 1, k);
		}
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
