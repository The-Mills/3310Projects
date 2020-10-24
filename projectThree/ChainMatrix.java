package projectThree;
import java.util.List;
import java.util.ArrayList;

public class ChainMatrix 
{
	public static void main(String[] args)
	{
		int[][] matrices = new int[][] {{1, 4}, {4, 7}, {7, 10}, {10, 5}, {5, 2}, {2, 9}, {9, 6}, {6, 11}, {11, 8}, {8, 3}};
		//int[][] matrices = new int[][] {{4, 3}, {3, 1}, {1, 2}};
		
		Polygon p = new Polygon(matrices);
		
		List<BridgeTreeNode> bridgeNodes = new ArrayList<BridgeTreeNode>();	
		for(Edge[] bridge : markBridges(p))
		{
			bridgeNodes.add(new BridgeTreeNode(bridge));
		}
		
			
		
		//printBridges(markBridges(p));
		//p.printPolygon();
		
	}
	
	public static List<Edge[]> markBridges(Polygon p)
	{
		List<Edge[]> bridges = new ArrayList<Edge[]>();
		Stack stack = new Stack();
		Edge minimum = p.getMinimumWeight();
		Edge w = minimum;
		Edge t;
		do
		{
			stack.push(w);
			w = w.clockWiseEdge;
			while(stack.top().weightNumber > w.weightNumber)
			{
				t = stack.pop();
				bridges.add(new Edge[] {stack.top(), t});
				bridges.add(new Edge[] {t, w});
			}
		}
		while(w.weightNumber != minimum.weightNumber);
		
		return bridges;
	}
	
	public static BridgeTreeNode constructBridgeTree(List<Edge[]> bridges)
	{
		//BridgeTreeNode root = new BridgeTreeNode();
		
		return null;
	}
	
	public static void printBridges(List<Edge[]> bridges)
	{
		for(Edge[] bridge : bridges)
		{
			System.out.println(bridge[0].weightNumber + " " + bridge[1].weightNumber);
		}
	}
}
