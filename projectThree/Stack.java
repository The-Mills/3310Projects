package projectThree;

import java.util.List;
import java.util.ArrayList;

public class Stack 
{
	private List<Edge> stack = new ArrayList<Edge>();
	
	public void push(Edge i)
	{
		stack.add(i);
	}
	
	public Edge pop()
	{
		Edge i = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		return i;
	}
	
	public Edge top()
	{
		return stack.get(stack.size() - 1);
	}
}