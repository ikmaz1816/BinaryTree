package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;


public class AllProblems {
	static Scanner sc;
	static class Pair1
	{
		int vertical;
		int level;
		Node node;
		public Pair1(int v,int l,Node node)
		{
			vertical=v;
			level=l;
			this.node=node;
		}
	}
	static class Pair
	{
		int level;
		Node node;
		public Pair(int l,Node n)
		{
			level=l;
			node=n;
		}
	}
	static class D
	{
		int height;
		int diameter;
		public D(int h,int d)
		{
			height=h;
			diameter=d;
		}
	}
	static class Node
	{
		int data;
		Node left;
		Node right;
		public Node(int data)
		{
			this.data=data;
		}
	}
	public static Node buildTree()
	{
		int data=sc.nextInt();
		if(data==-1)
			return null;
		Node n = new Node(data);
		n.left=buildTree();
		n.right=buildTree();
		return n;
	}
	private static void preOrder(Node root) {
		if(root==null)
			return;
		System.out.print(root.data+" ");
		preOrder(root.left);
		preOrder(root.right);
		
	}
	private static void inOrder(Node root)
	{
		if(root==null)
			return;
		inOrder(root.left);
		System.out.print(root.data+" ");
		inOrder(root.right);
	}
	private static void postOrder(Node root)
	{
		if(root==null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data+" ");
	}
	private static void level_order(Node root)
	{
		if(root==null)
			return;
		Queue<Node> q=new LinkedList<>();
		q.add(root);
		q.add(null);
		while(!q.isEmpty())
		{
			Node current=q.poll();
			if(current==null)
			{
				if(q.isEmpty())
					break;
				else
				{
					System.out.println();
					q.add(null);
				}
			}
			else
			{
				System.out.print(current.data+" ");
				if(current.left!=null)
				{
					q.add(current.left);
				}
				if(current.right!=null)
				{
					q.add(current.right);
				}
			}
		}
	}
	private static int count(Node node)
	{
		if(node==null)
			return 0;
		int left=count(node.left);
		int right=count(node.right);
		return 1 + left + right;
	}
	private static int sum(Node node)
	{
		if(node==null)
			return 0;
		int left=sum(node.left);
		int right=sum(node.right);
		return node.data + left + right;
	}
	private static int product(Node node)
	{
		if(node==null)
			return 1;
		int left=product(node.left);
		int right=product(node.right);
		return node.data * left * right;
	}
	private static int height(Node node)
	{
		if(node==null)
			return 0;
		int left=height(node.left);
		int right=height(node.right);
		return 1 + Math.max(left, right);
	}
	private static void preOrderIterative(Node node)
	{
		if(node==null)
			return;
		Stack<Node> s=new Stack<>();
		s.add(node);
		while(!s.isEmpty())
		{
			Node current=s.pop();
			System.out.print(current.data+ " ");
			if(current.right!=null)
			{
				s.add(current.right);
			}
			if(current.left!=null)
			{
				s.add(current.left);
			}
		}
	}
	private static void inOrderIterative(Node node)
	{
		if(node ==null)
			return;
		Stack<Node> s=new Stack<>();
		while(true)
		{
			if(node!=null)
			{
				s.add(node);
				node=node.left;
			}
			else
			{
				if(s.isEmpty())
					break;
				node=s.pop();
				System.out.print(node.data+" ");
				node=node.right;
			}
		}
	}
	private static void postOrderIterative(Node node)
	{
		if(node == null)
			return;
		Stack<Node> s=new Stack<>();
		while(true)
		{
			if(node!=null)
			{
				s.add(node);
				node=node.left;
			}
			else
			{
				if(s.isEmpty())
					break;
				Node temp=s.peek().right;
				if(temp!=null)
					node=temp;
				else
				{
					temp=s.pop();
					System.out.print(temp.data+ " ");
					while(!s.isEmpty() && temp==s.peek().right)
					{
						temp=s.pop();
						System.out.print(temp.data+" ");
					}
				}
			}
		}
	}
	public static int checkBalancedBinary(Node node)
	{
		if(node==null)
			return 0;
		int left=checkBalancedBinary(node.left);
		if(left==-1)
			return -1;
		int right=checkBalancedBinary(node.right);
		if(right==-1)
			return -1;
		if(Math.abs(left-right)>=1)
			return -1;
		return 1 + Math.max(left, right);
	}
	public static int maximum(Node node,int[] max)
	{
		if(node==null)
			return 0;
		int left=maximum(node.left,max);
		int right=maximum(node.right,max);
		max[0] =Math.max(max[0], node.data+left+right);
		return node.data + Math.max(left,right);
	}
	public static D diameter(Node node)
	{
		if(node==null)
			return new D(0,0);
		D left=diameter(node.left);
		D right=diameter(node.right);
		
		int myHeight=1 + Math.max(left.height, right.height);
		
		int diam1=left.diameter;
		int diam2=right.diameter;
		int diam3=1 + left.height + right.height;
		
		int myDiam=Math.max(Math.max(diam2, diam1), diam3);
		return new D(myHeight,myDiam);
	}
	public static void printAllPath(Node node,ArrayList<Integer> arr)
	{
		if(node==null)
			return;
		if(node.left==null && node.right==null)
		{
			arr.add(node.data);
			System.out.println(arr);
			arr.remove(arr.size()-1);
			return;
		}
		
		arr.add(node.data);
		printAllPath(node.left,arr);
		printAllPath(node.right,arr);
		arr.remove(arr.size()-1);
	}
	public static boolean printGivenPath(Node node,ArrayList<Integer> arr,int target)
	{
		if(node==null)
		{
			return false;
		}
		arr.add(node.data);
		if(node.data==target)
		{
			return true;
		}
		if(printGivenPath(node.left,arr,target) || printGivenPath(node.right,arr,target))
			return true;
		arr.remove(arr.size()-1);
		return false;
	}
	public static boolean checkSymmetric(Node p,Node q)
	{
		if(p==null || q==null)
			return p==q;
		return p.data==q.data && checkSymmetric(p.left,q.right) && checkSymmetric(p.right,q.left);
	}
	public static boolean checkIdentical(Node p,Node q)
	{
		if(p==null || q==null)
			return p==q;
		return p.data==q.data && checkIdentical(p.left,q.left) && checkIdentical(p.right,q.right);
	}
	public static boolean subTree(Node node,Node subRoot)
	{
		if(node==null)
			return false;
		if(subRoot==null)
			return true;
		if(node.data==subRoot.data)
		{
			if(checkIdentical(node,subRoot))
				return true;
		}
		if(subTree(node.left,subRoot) || subTree(node.right,subRoot))
			return true;
		return false;
	}
	public static void zigzag(Node node)
	{
		if(node==null)
			return;
		Stack<Node> s=new Stack<>();
		Queue<Node> q=new LinkedList<>();
		s.add(node);
		boolean flag=true;
		while(!s.isEmpty() || !q.isEmpty())
		{
			if(flag)
			{
				Node current=s.pop();
				System.out.print(current.data+" ");
				if(current.right!=null)
					q.add(current.right);
				if(current.left!=null)
					q.add(current.left);
				if(s.isEmpty())
				{
					System.out.println();
					flag=!flag;
				}
			}
			else
			{
				Node current=q.poll();
				System.out.print(current.data+" ");
				if(current.right!=null)
					s.add(current.right);
				if(current.left!=null)
					s.add(current.left);
				if(q.isEmpty())
				{
					System.out.println();
					flag=!flag;
				}
			}
		}
	}
	public static void addLeaves(Node node,ArrayList<Integer> arr)
	{
		if(node.left==null && node.right==null)
		{
			arr.add(node.data);
			return;
		}
		addLeaves(node.right,arr);
		addLeaves(node.left,arr);
	}
	public static void addRight(Node node,ArrayList<Integer> arr)
	{
		if(node==null)
			return;
		while(node!=null)
		{
			if(node.left!=null || node.right!=null)
				arr.add(node.data);
			if(node.right!=null)
				node=node.right;
			else
				node=node.left;
		}
	}
	public static void addLeft(Node node,ArrayList<Integer> arr)
	{
		if(node==null)
			return;
		ArrayList<Integer> temp=new ArrayList<>();
		while(node!=null)
		{
			
			if(node.left!=null || node.right!=null)
				temp.add(node.data);
			if(node.left!=null)
				node=node.left;
			else
				node=node.right;
		}
		for(int i=temp.size()-1;i>=0;i--)
		{
			arr.add(temp.get(i));
		}
	}
	public static void topView(Node node)
	{
		HashMap<Integer,Node> map=new HashMap<>();
		Queue<Pair> q=new LinkedList<>();
		q.add(new Pair(0,node));
		while(!q.isEmpty())
		{
			Node current=q.peek().node;
			int level=q.peek().level;
			q.poll();
			if(map.get(level)==null)
				map.put(level, current);
			if(current.left!=null)
				q.offer(new Pair(level-1,current.left));
			if(current.right!=null)
				q.offer(new Pair(level+1,current.right));
		}
		for(Node i:map.values())
		{
			System.out.print(i.data+" ");
		}
	}
	public static void bottomView(Node node)
	{
		HashMap<Integer,Node> map=new HashMap<>();
		Queue<Pair> q=new LinkedList<>();
		q.add(new Pair(0,node));
		while(!q.isEmpty())
		{
			Node current=q.peek().node;
			int level=q.peek().level;
			q.poll();
			map.put(level, current);
			if(current.left!=null)
				q.offer(new Pair(level-1,current.left));
			if(current.right!=null)
				q.offer(new Pair(level+1,current.right));
		}
		for(Node i:map.values())
		{
			System.out.print(i.data+" ");
		}
	}
	public static void leftView(Node node,Stack<Integer> s,int k)
	{
		if(node==null)
			return;
		if(k==s.size())
			s.add(node.data);
		leftView(node.left,s,k+1);
		leftView(node.right,s,k+1);
	}
	public static void rightView(Node node,Stack<Integer> s,int k)
	{
		if(node==null)
			return;
		if(k==s.size())
			s.add(node.data);
		rightView(node.right,s,k+1);
		rightView(node.left,s,k+1);
	}
	public static void verticalOrderTraversal(Node node)
	{
		TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map=new TreeMap<>();
		Queue<Pair1> q=new LinkedList<>();
		q.add(new Pair1(0,0,node));
		while(!q.isEmpty())
		{
			int x=q.peek().vertical;
			int y=q.peek().level;
			Node current=q.peek().node;
			q.poll();
			if(!map.containsKey(x))
				map.put(x, new TreeMap<>());
			if(!map.get(x).containsKey(y))
				map.get(x).put(y, new PriorityQueue<>());
			map.get(x).get(y).offer(node.data);
			if(current.left!=null)
			{
				q.add(new Pair1(x-1,y+1,current.left));
			}
			if(current.right!=null)
			{
				q.add(new Pair1(x+1,y+1,current.right));
			}
		}
	}
	public static Node LCA(Node node,int a ,int b)
	{
		if(node==null || node.data==a || node.data==b)
			return node;
		Node left=LCA(node.left,a,b);
		Node right=LCA(node.right,a,b);
		if(left==null)
			return right;
		else if(right==null)
			return left;
		else
			return node;
	}
	public static int maximumwidthofbinaryTree(Node node)
	{
		if(node==null)
			return 0;
		Queue<Pair> q=new LinkedList<>();
		q.add(new Pair(0,node));
		int last=0,first=0;
		int ans=0;
		while(!q.isEmpty())
		{
			int min=q.peek().level;
			int size=q.size();
			for(int i=0;i<size;i++)
			{
				int currentmin=q.peek().level-min;
				Node curr=q.peek().node;
				q.poll();
				if(i==0)
					first=currentmin;
				if(i==size-1)
					last=currentmin;
				if(curr.left!=null)
					q.add(new Pair(2*currentmin +1,curr.left));
				if(curr.right!=null)
					q.add(new Pair(2*currentmin +2,curr.right));
			}
			ans=Math.max(ans, last-first+1);
		}
		return ans;
	}
	public static void children_sum(Node node)
	{
		if(node==null)
			return;
		int sum=0;
		if(node.left!=null)
			sum+=node.left.data;
		if(node.right!=null)
			sum+=node.right.data;
		if(sum>node.data)
			node.data=sum;
		else
		{
			if(node.left!=null)
				node.left.data=node.data;
			if(node.right!=null)
				node.right.data=node.data;
		}
		children_sum(node.left);
		children_sum(node.right);
		
		int tot=0;
		if(node.left!=null)
			tot+=node.left.data;
		if(node.right!=null)
			tot+=node.right.data;
		if(node.left!=null && node.right!=null)
			node.data=tot;
	}
	public static void NodesAtDistanceK(Node node,Node target,int k)
	{
		if(node ==null)
			return;
		HashMap<Node,Node> map=new HashMap<>();
		mark_Parent(node,map);
		Queue<Node> q=new LinkedList<>();
		q.add(target);
		HashMap<Node,Boolean> visited=new HashMap<>();
		visited.put(target, true);
		int count=0;
		while(!q.isEmpty())
		{
			if(k==count)
				break;
			int size=q.size();
			count++;
			for(int i=0;i<size;i++)
			{
				Node current=q.poll();
				if(current.left!=null && map.get(current.left)==null)
				{
					visited.put(current.left, true);
					q.add(current.left);
				}
				if(current.right!=null && map.get(current.right)==null)
				{
					visited.put(current.right, true);
					q.add(current.left);
				}
				if(map.get(current)!=null && visited.get(map.get(current))==null)
				{
					visited.put(map.get(current), true);
					q.add(map.get(current));
				}
			}
		}
		System.out.println(q);
	}
	private static void minimumTimeToBurn(Node node,Node target)
	{
		if(node ==null)
			return;
		HashMap<Node,Node> map=new HashMap<>();
		mark_Parent(node,map);
		Queue<Node> q=new LinkedList<>();
		q.add(target);
		HashMap<Node,Boolean> visited=new HashMap<>();
		visited.put(target, true);
		int count=0;
		
		while(!q.isEmpty())
		{
			int size=q.size();
			int f=0;
			for(int i=0;i<size;i++)
			{
				Node current=q.poll();
				if(current.left!=null && map.get(current.left)==null)
				{
					f=1;
					visited.put(current.left, true);
					q.add(current.left);
				}
				if(current.right!=null && map.get(current.right)==null)
				{
					f=1;
					visited.put(current.right, true);
					q.add(current.left);
				}
				if(map.get(current)!=null && visited.get(map.get(current))==null)
				{
					f=1;
					visited.put(map.get(current), true);
					q.add(map.get(current));
				}
			}
			if(f==1)
				count++;
		}
		System.out.println(count);
	}
	private static void mark_Parent(Node node, HashMap<Node, Node> map) {
		Queue<Node> q=new LinkedList<>();
		q.add(node);
		map.put(node, null);
		while(!q.isEmpty())
		{
			Node current=q.poll();
			if(current.left!=null)
			{
				q.add(current.left);
				map.put(current.left,current);
			}
			if(current.right!=null)
			{
				q.add(current.right);
				map.put(current.right,current);
			}
		}
		
	}
	public static void constructInAndPre(int[] inorder,int[] preorder)
	{
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=0;i<inorder.length;i++)
		{
			map.put(inorder[i], i);
		}
		
		Node root=constructPre(preorder,0,preorder.length-1,inorder,0,inorder.length-1,map);
		postOrder(root);
	}
	private static Node constructPre(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend,
			HashMap<Integer, Integer> map) {
		if(prestart>preend || instart>inend)
			return null;
		Node n=new Node(preorder[prestart]);
		int inRoot=map.get(n.data);
		int numsleft=inRoot-instart;
		n.left=constructPre(preorder,prestart+1,prestart+numsleft,inorder,instart,inRoot-1,map);
		n.right=constructPre(preorder,prestart+numsleft+1,preend,inorder,inRoot+1,inend,map);
		return n;
	}
	private static Node constructPost(int[] postorder, int poststart, int postend, int[] inorder, int instart, int inend,
			HashMap<Integer, Integer> map) {
		if(poststart>postend || instart>inend)
			return null;
		Node n=new Node(postorder[postend]);
		int inRoot=map.get(n.data);
		int numsleft=inRoot-instart;
		n.left=constructPost(postorder,poststart,poststart+numsleft-1,inorder,instart,inRoot-1,map);
		n.right=constructPost(postorder,poststart+numsleft,postend-1,inorder,inRoot+1,inend,map);
		return n;
	}
	public static void constructInAndPost(int[] inorder,int[] postorder)
	{
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=0;i<inorder.length;i++)
		{
			map.put(inorder[i], i);
		}
		
		Node root=constructPost(postorder,0,postorder.length-1,inorder,0,inorder.length-1,map);
		preOrder(root);
	}
	public static String serialize(Node node)
	{
		if(node==null)
			return " ";
		StringBuilder sb=new StringBuilder();
		Queue<Node> q=new LinkedList<>();
		q.add(node);
		while(!q.isEmpty())
		{
			Node current=q.poll();
			if(current==null)
			{
				sb.append("n ");
			}
			else
			{
				sb.append(current.data+" ");
			}
			if(current!=null)
			{
			q.add(current.left);
			q.add(current.right);
			}
		}
		return sb.toString();
	}
	private static Node build(String s) {
		String[] values=s.split(" ");
		Node root=new Node(Integer.parseInt(values[0]));
		Queue<Node> q=new LinkedList<>();
		q.add(root);
		for(int i=1;i<values.length;i++)
		{
			Node current=q.poll();
			if(!values[i].equals("n"))
			{
				Node n=new Node(Integer.parseInt(values[i]));
				current.left=n;
				q.add(n);
			}
			if(!values[++i].equals("n"))
			{
				Node n=new Node(Integer.parseInt(values[i]));
				current.right=n;
				q.add(n);
			}
		}
		return root;
	}
	private static void morrisInTraversal(Node node)
	{
		if(node==null)
			return;
		while(node!=null)
		{
			if(node.left==null)
			{
				System.out.print(node.data+" ");
				node=node.right;
			}
			else
			{
			Node prev=node.left;
			while(prev.right!=null && prev.right!=node)
			{
				prev=prev.right;
			}
			if(prev.right==null )
			{
				prev.right=node;
				node=node.left;
			}
			else
			{
				prev.right=null;
				System.out.print(node.data+" ");
				node=node.right;
			}
		}
		}
	}
	private static void morrisPreTraversal(Node node)
	{
		if(node==null)
			return;
		while(node!=null)
		{
			if(node.left==null)
			{
				System.out.print(node.data+" ");
				node=node.right;
			}
			else
			{
			Node prev=node.left;
			while(prev.right!=null && prev.right!=node)
			{
				prev=prev.right;
			}
			if(prev.right==null )
			{
				prev.right=node;
				System.out.print(node.data+" ");
				node=node.left;
			}
			else
			{
				prev.right=null;
				node=node.right;
			}
		}
		}
	}
	private static void change(Node node)
	{
		if(node==null)
			return;
		while(node!=null)
		{
			if(node.left!=null)
			{
			Node prev=node.left;
			while(prev.right!=null)
				prev=prev.right;
			prev.right=node.right;
			node.right=node.left;
			}
			node=node.right;
		}
	}
	public static void chageWithDepth(Node node,int k)
	{
		if(node==null)
			return;
		node.data=k;
		chageWithDepth(node.left,k+1);
		chageWithDepth(node.right,k+1);
	}
	public static void mirror(Node node)
	{
		if(node==null)
			return;
		mirror(node.left);
		mirror(node.right);
		Node n=new Node(node.data);
		Node temp=node.left;
		node.left=n;
		n.left=temp;
	}
	public static void main(String[] args) { 
		sc=new Scanner(System.in);
		Node root = buildTree();
		mirror(root);
		preOrder(root);
	}
	
}
	