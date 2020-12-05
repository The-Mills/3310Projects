package finalassignment;
import java.util.Random;

public class Main 
{
	public static Random rng = new Random();
	
	public static void main(String[] args)
	{
		runFindSumSequence();
		// runIsSubtree(); 
		//runBestMatrix(); 
	}
	
	public static void printArray(int[] array)
	{
		for(int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
	
	public static void runFindSumSequence()
	{
		int n = 100000000;
		int[] numbers = new int[n];
		for(int i = 0; i < n; i++)
			numbers[i] = rng.nextInt(21) - 10;
		
		int[] results = findSumSequence(numbers);
		//printArray(numbers);
		printArray(results);
	}
	
	public static int[] findSumSequence(int[] array)
	{
		int[] startEnd = new int[2];
		startEnd[0] = 0;
		startEnd[1] = 0;
		int largestSum = Integer.MIN_VALUE, currentSum = 0, currentStart = 0, currentEnd = 0, currentValue = 0;
		int i;
		
		for(i = 0; i < array.length; i++)
		{
			currentValue = array[i];
			if(currentValue < 0)
			{
				if(currentSum + currentValue < 0)
				{
					if(currentSum + currentValue > largestSum)
					{
						largestSum = currentSum + currentValue;
						startEnd[0] = currentStart;
						startEnd[1] = currentEnd;
					}
					currentSum = 0;
					currentStart = i + 1;
					currentEnd = i+1;
				}
				else
				{
					currentSum += currentValue;
					currentEnd = i;
					if(currentSum > largestSum)
					{
						largestSum = currentSum;
						startEnd[0] = currentStart;
						startEnd[1] = currentEnd;
					}
				}
			}
			else
			{
				currentSum += currentValue;
				currentEnd = i;
				if(currentSum > largestSum)
				{
					largestSum = currentSum;
					startEnd[0] = currentStart;
					startEnd[1] = currentEnd;
				}
			}
		}

		return startEnd;
	}
	
	public static void runIsSubtree()
	{
		Tree t1 = new Tree(2, 1);
		t1.setRightChild(0, 2);
		t1.setLeftChild(2, 3);
		t1.setLeftChild(0, 1);
		//System.out.println(derp.getRightChild(0));
		//System.out.println(derp.getLeftChild(0));
		
		//System.out.println(derp.getMaxDepth());
		
		Tree t2 = new Tree(2, 1);
		t2.setRightChild(0, 2);
		t2.setLeftChild(2, 3);
		System.out.println(isSubtree(t1, t2));
	}
	
	public static boolean isSubtree(Tree t1, Tree t2)
	{
		int nodesToCheck = (int)Math.pow(2, t1.getMaxDepth() - t2.getMaxDepth() + 1) - 1;
		for(int i = 0; i < nodesToCheck; i++)
		{
			if(t1.data[i] == t2.data[0])
			{
				boolean left = true;
				boolean right = true;
				if(t2.getLeftChild(0) != null)
					left = recursiveSubtree(t1, t2, i * 2 + 1, 1);
				if(t2.getRightChild(0)!= null)
					right = recursiveSubtree(t1, t2, i * 2 + 2, 2);
				if(left && right)
					return true;
			}
		}
		return false;
	}
	
	public static boolean recursiveSubtree(Tree t1, Tree t2, int currentT1Index, int currentT2Index)
	{
		if(t1.data[currentT1Index] != t2.data[currentT2Index])
			return false;
		boolean left = true;
		boolean right = true;
		if(t2.getLeftChild(currentT2Index) != null)
			left = recursiveSubtree(t1, t2, currentT1Index * 2 + 1, currentT2Index * 2 + 1);
		if(t2.getRightChild(currentT2Index)!= null)
			right = recursiveSubtree(t1, t2, currentT1Index * 2 + 1, currentT2Index * 2 + 2);
		return left && right;
	}
	
	public static void runBestMatrix()
	{
		int m = 300, n = 300;
		int[][] matrix = new int[m][n];
		
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				matrix[i][j] = rng.nextInt(100) - 50;
		
		//printMatrix(matrix);
		long startTime = System.currentTimeMillis();
		int[][] submatrix = findBestMatrix(matrix);
		System.out.println(System.currentTimeMillis() - startTime);
		//printMatrix(submatrix);
	}
	
	public static int[][] findBestMatrix(int[][] matrix)
	{
		int subRowStart = 0, subRowEnd = 0, subColStart = 0, subColEnd = 0, highestSum = Integer.MIN_VALUE, currentSum = 0;
		
		int[][] colSums = new int[matrix.length + 1][matrix[0].length];
		
		for(int j = 0; j < matrix[0].length; j++)
		{
			colSums[1][j] = matrix[0][j];
			for(int i = 2; i < matrix.length + 1; i++)
			{
				colSums[i][j] = matrix[i - 1][j] + colSums[i-1][j];
			}
		}
		
		//printMatrix(colSums);
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++)
			{
				currentSum = 0;
				for(int k = i; k < matrix.length; k++)
				{
					currentSum = 0;
					for(int l = j; l < matrix[0].length; l++)
					{
						currentSum += colSums[k + 1][l] - colSums[i][l];
						if(currentSum > highestSum)
						{
							highestSum = currentSum;
							subRowStart = i;
							subRowEnd = k;
							subColStart = j;
							subColEnd = l;
						}
					}
				}
			}
		}
		//System.out.println(highestSum);
		int[][] submatrix = new int[subRowEnd - subRowStart + 1][subColEnd - subColStart + 1];
		for(int i = 0; i < submatrix.length; i++)
			for(int j = 0; j < submatrix[0].length; j++)
				submatrix[i][j] = matrix[i + subRowStart][j + subColStart];
		return submatrix;
	}
	
	public static void printMatrix(int[][] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print("| ");
			for(int j = 0; j < a[0].length; j++)
			{
				if(a[i][j] >= 0)
					System.out.print(" " + a[i][j] + " ");
				else
					System.out.print(a[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println();
	}
}