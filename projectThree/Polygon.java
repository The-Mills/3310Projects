package projectThree;

public class Polygon 
{
	public Edge firstEdge = new Edge();
	public Edge lastEdge = new Edge();
	
	public Polygon(int[][] matrices)
	{
		// Initialize polygon with weights = -1
		int numberEdges = matrices.length + 1;
		firstEdge.counterClockEdge = lastEdge;
		firstEdge.value = matrices[0][0];
		firstEdge.index = 0;
		firstEdge.weightNumber = -1;
		lastEdge.clockWiseEdge = firstEdge;
		lastEdge.value = matrices[matrices.length - 1][1];
		lastEdge.index = numberEdges - 1;
		lastEdge.weightNumber = -1;
		
		Edge currentEdge = firstEdge;
		for(int i = 1; i < numberEdges - 1; i++)
		{
			Edge nextEdge = new Edge();
			nextEdge.value = matrices[i][0];
			currentEdge.clockWiseEdge = nextEdge;
			nextEdge.counterClockEdge = currentEdge;
			nextEdge.index = i;
			nextEdge.weightNumber = -1;
			currentEdge = nextEdge;
		}
		
		lastEdge.counterClockEdge = currentEdge;
		currentEdge.clockWiseEdge = lastEdge;
		
		
		// Find Weights
		int unweightedEdges = numberEdges;
		Edge largestValueEdge, dummyEdge = new Edge();
		dummyEdge.value = -1;
		int j;
		while(unweightedEdges != 0)
		{
			j = 1;
			currentEdge = firstEdge;
			largestValueEdge = dummyEdge;
			while(j == 1 || currentEdge.index != firstEdge.index)
			{
				j--;
				if(currentEdge.value > largestValueEdge.value && currentEdge.weightNumber == -1)
					largestValueEdge = currentEdge;
				currentEdge = currentEdge.clockWiseEdge;
			}
			largestValueEdge.weightNumber = unweightedEdges;
			unweightedEdges--;
			j = 1;
		}
	}
	
	public boolean isSubPolygon(Edge outerLeft, Edge outerRight, Edge innerLeft, Edge innerRight)
	{
		Edge currentEdge = outerLeft;
		boolean passedInnerLeft = false;

		do
		{
			if(!passedInnerLeft && currentEdge.weightNumber == innerLeft.weightNumber)
				passedInnerLeft = true;
			
			if(passedInnerLeft && currentEdge.weightNumber == innerRight.weightNumber)
				return true;
			currentEdge = currentEdge.clockWiseEdge;
		}
		while(currentEdge.weightNumber != outerRight.clockWiseEdge.weightNumber);
		
		return false;
	}
	
	public Edge getMinimumWeight()
	{
		Edge minimum = firstEdge, currentEdge = firstEdge.clockWiseEdge;
		while(currentEdge.index != firstEdge.index)
		{
			if(currentEdge.weightNumber < minimum.weightNumber)
				minimum = currentEdge;
			currentEdge = currentEdge.clockWiseEdge;
		}
		return minimum;
	}
	
	
	public void printPolygon()
	{
		int i = 1;
		System.out.println("Edge #" + i);
		i++;
		System.out.println("Index: " + firstEdge.index + " Value: " + firstEdge.value + " Weight: " + firstEdge.weightNumber);
		Edge currentEdge = firstEdge.clockWiseEdge;
		while(currentEdge.index != 0)
		{
			System.out.println("Edge #" + i);
			i++;
			System.out.println("Index: " + currentEdge.index + " Value: " + currentEdge.value + " Weight: " + currentEdge.weightNumber);
			currentEdge = currentEdge.clockWiseEdge;
		}
	}
}