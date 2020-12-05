package finalassignment;

public class Tree 
{
	public Integer[] data;
	
	public Tree(int maxDepth, int rootValue)
	{
		int maxNodes = (int)Math.pow(2, maxDepth + 1) - 1;
		data = new Integer[maxNodes];
		for(int i = 0; i < data.length; i++)
			data[i] = null;
		
		data[0] = rootValue;	
	}
	
	public boolean setLeftChild(int parentIndex, int value)
	{
		if(data[parentIndex] == null)
			return false;
		else
		{
			data[parentIndex * 2 + 1] = value;
			return true;
		}
	}
	
	public boolean setRightChild(int parentIndex, int value)
	{
		if(data[parentIndex] == null)
			return false;
		else
		{
			data[parentIndex * 2 + 2] = value;
			return true;
		}
	}
	
	public Integer getLeftChild(int parentIndex)
	{
		if(parentIndex * 2 + 1 > data.length - 1)
			return null;
		return data[parentIndex * 2 + 1];
	}
	
	public Integer getRightChild(int parentIndex)
	{
		if(parentIndex * 2 + 2 > data.length - 1)
			return null;
		return data[parentIndex * 2 + 2];
	}
	
	public Integer getMaxDepth()
	{
		return (int)Math.floor(Math.log10(data.length) / Math.log10(2));
	}
}