package testbed;

import java.util.Random;

public class ArraySum 
{
	public static Random rng = new Random();
	
	public static void main(String[] args)
	{
		testHeapBinarySum(200, 100, true);
	}
		
	/*
	 * PART A - SOLUTION #1
	 */
	public static int[] binarySum(int[] S, int sum)
	{
		int[] valuePointers = new int[2];
		valuePointers[0] = -1; 
		valuePointers[1] = -1;
		
		int i, target, previousValue = S[0] - 1;
		for(i = 0; i < S.length -1; i++)
		{
			if(S[i] != previousValue)
			{
				target = sum - S[i];
				valuePointers[1] = binarySearch(i + 1, S.length - 1, S, target);
				if(valuePointers[1] > 0)
				{
					valuePointers[0] = i;
					return valuePointers;
				}
				previousValue= S[i];
			}
		}
		return valuePointers;
	}	
	
	public static int binarySearch(int low, int high, int[] S, int target)
	{
		if(low > high)
			return 0;
		int mid = (low + high)/2;
		if(target == S[mid])
			return mid;	
		if(target < S[mid])
			return binarySearch(low, mid - 1, S, target);
		else
			return binarySearch(mid + 1, high, S, target);
	}
	
	public static void testBinarySum(int numberRuns, int n, boolean printArray)
	{
		long totalTime = 0;
		for(int i = 1; i <= numberRuns; i++)
		{
			int[] array = getArray(n, true);
			int sum = getSum();
			
			if(printArray)
				printArray(array);
			long startTime = System.nanoTime();
			int[] pointers = binarySum(array, sum);
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("Finished in " + timeSpent + " nanoseconds.");
			totalTime += timeSpent;
		
			if(pointers[0] != -1)
				System.out.println(array[pointers[0]] + " + " + array[pointers[1]] + " = " + sum);
		}
		System.out.println("Average time spent on " + numberRuns + " runs of size " + n + " is " + (totalTime / numberRuns) + " nanoseconds.");
	}
	
	/*
	 * PART A - SOLUTION #2
	 */
	public static int[] sequentialSum(int[] S, int sum)
	{
		int[] valuePointers = new int[2];
		valuePointers[0] = -1;
		valuePointers[1] = -1;
		
		int i, j, target, previousValue = S[0] - 1;
		for(i = 0; i < S.length; i++)
		{
			if(S[i] != previousValue)
			{
				target = sum - S[i];
				if(target >= 0)
					for(j = S.length - 1; j > i && S[j] >= target; j--)
						if(S[j] == target)
						{
							valuePointers[0] = i;
							valuePointers[1] = j;
							return valuePointers;
						}
				else
					for(j = i + 1; j < S.length && S[j] <= target; j++)
						if(S[j] == target)
						{
							valuePointers[0] = i;
							valuePointers[1] = j;
							return valuePointers;
						}
				previousValue = S[i];			
			}
		}		
		return valuePointers;
	}
	
	public static void testSequentialSum(int numberRuns, int n, boolean printArray)
	{
		long totalTime = 0;
		for(int i = 1; i <= numberRuns; i++)
		{
			int[] array = getArray(n, true);
			int sum = getSum();
		
			if(printArray)
				printArray(array);
			long startTime = System.nanoTime();
			int[] pointers = sequentialSum(array, sum);
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("Finished in " + timeSpent + " nanoseconds.");
			totalTime += timeSpent;
		
			if(pointers[0] != -1)
				System.out.println(array[pointers[0]] + " + " + array[pointers[1]] + " = " + sum);
		}
		System.out.println("Average time spent on " + numberRuns + " runs of size " + n + " is " + (totalTime / numberRuns) + " nanoseconds.");
	}
	
	/*
	 * PART B - SOLUTION #1
	 */
	public static int[] booleanSum(int[] S, int sum)
	{
		int[] values = new int[2];
		values[0] = sum;
		values[1] = sum;
		boolean[] inArray = new boolean[201];
		int target;
		for(int i = 0; i < S.length; i++)
		{
			inArray[S[i] + 100] = true;
		}
		
		for(int i = 0; i < inArray.length; i++)
		{
			if(inArray[i])
			{
				target = (sum - (i - 100));
				if(target > 100 || target < -100)
					continue;
				if(inArray[target + 100])
				{
					values[0] = i - 100;
					values[1] = target;
				}
			}
		}
		
		return values;
	}
	
	public static void testBooleanSum(int numberRuns, int n, boolean printArray)
	{
		long totalTime = 0;
		for(int i = 1; i <= numberRuns; i++)
		{
			int[] array = getArray(n, true);
			int sum = getSum();
		
			if(printArray)
				printArray(array);
			long startTime = System.nanoTime();
			int[] values = booleanSum(array, sum);
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("Finished in " + timeSpent + " nanoseconds.");
			totalTime += timeSpent;
		
			if(values[0] != sum && values[1] != sum)
				System.out.println(values[0] + " + " + values[1] + " = " + sum);
		}
		System.out.println("Average time spent on " + numberRuns + " runs of size " + n + " is " + (totalTime / numberRuns) + " nanoseconds.");
	}
	
	/*
	 * PART B - SOLUTION #2
	 */
	public static int[] heapBinarySum(int[] S, int sum)
	{
		heapSort(S);
		return binarySum(S, sum);
	}
	
	public static int[] heapSort(int[] arr) 
	{
		int n = arr.length;

		// heapify entire tree
		for (int i = n / 2 - 1; i >= 0; i--) 
		{
			heapify(arr, n, i);
		}

		for (int i = n - 1; i >= 0; i--) 
		{
			int swap = arr[0];
			arr[0] = arr[i];
			arr[i] = swap;
			// heapify after swap
			heapify(arr, i, 0);
		}
		
		return arr;
	}

	public static void heapify(int[] arr, int length, int root) 
	{
		int largest = root;
		int left = root * 2 + 1;
		int right = root * 2 + 2;

		if (left < length && arr[left] > arr[largest])
			largest = left;
		if (right < length && arr[right] > arr[largest])
			largest = right;

		// check if switch happened, if so, then swap root and largest element
		if (largest != root) 
		{
			int temp = arr[root];
			arr[root] = arr[largest];
			arr[largest] = temp;

			heapify(arr, length, largest);
		}
	}
	
	public static void testHeapBinarySum(int numberRuns, int n, boolean printArray)
	{
		long totalTime = 0;
		for(int i = 1; i <= numberRuns; i++)
		{
			int[] array = getArray(n, true);
			int sum = getSum();
			
			if(printArray)
				printArray(array);
			long startTime = System.nanoTime();
			int[] pointers = heapBinarySum(array, sum);
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("Finished in " + timeSpent + " nanoseconds.");
			totalTime += timeSpent;
		
			if(pointers[0] != -1)
				System.out.println(array[pointers[0]] + " + " + array[pointers[1]] + " = " + sum);
		}
		System.out.println("Average time spent on " + numberRuns + " runs of size " + n + " is " + (totalTime / numberRuns) + " nanoseconds.");
	}
	
	/*
	 * AUXILIARY METHODS FOR TESTING PURPOSES
	 */
	public static int[] getArray(int n, boolean sort)
	{
		int[] array = new int[n];
		
		int number;
		for(int i = 0; i < n; i++)
		{
			number = rng.nextInt(201);
			if(number > 100)
				number = number * -1 + 100;
			
			array[i] = number;
		}
		
		if(sort)
			heapSort(array);
		
		return array;
	}
	
	public static int getSum()
	{
		int sum = rng.nextInt(401);
		if(sum > 200)
			sum = sum * -1 + 200;
		
		return sum;
	}
	
	public static void printArray(int[] array)
	{
		for(int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}