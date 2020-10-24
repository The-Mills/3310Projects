package projectThree;

public class BridgeTreeNode 
{
	public Edge[] bridge = null;
	public BridgeTreeNode minSon = null;
	public BridgeTreeNode maxSon = null;
	
	public BridgeTreeNode(Edge[] b)
	{
		bridge = b;
	}
	
	public boolean isLeaf()
	{
		return minSon == null && maxSon == null;
	}
}