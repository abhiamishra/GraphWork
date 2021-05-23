import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
	    graph.addVertex("Bob");
	    graph.addVertex("Alice");
	    graph.addVertex("Mark");
	    graph.addVertex("Rob");
	    graph.addVertex("Maria");
	    graph.addEdge("Bob", "Alice");
	    graph.addEdge("Bob", "Rob");
	    graph.addEdge("Alice", "Mark");
	    graph.addEdge("Rob", "Mark");
	    graph.addEdge("Alice", "Maria");
	    graph.addEdge("Rob", "Maria");
	    graph.show();
	    DFT(graph, "Bob");
	    System.out.println("BFT;");
	    BFT(graph, "Bob");
	}
	
	public static void DFT(Graph graph, String startDFT) {
		ArrayList<String> dft = new ArrayList<>();
		Stack<String> dftStack = new Stack<>();
		dftStack.push(startDFT);
		while(!dftStack.isEmpty()) {
			String popped = dftStack.pop();
			
			if(!dft.contains(popped)) {
				dft.add(popped);
				List<Vertex> allEdge = graph.allEdges(popped);
				for(Vertex iterating : allEdge) {
					dftStack.push(iterating.getLabel());
				}
			}
			
		}
		
		for(String printer : dft) {
			System.out.println(printer);
		}
		
	}
	
	public static void BFT(Graph graph, String bftSearch) {
		ArrayList<String> dft = new ArrayList<>();
		Queue<String> dftStack = new LinkedList<>();
		dftStack.add(bftSearch);
		while(!dftStack.isEmpty()) {
			String popped = dftStack.remove();
			if(!dft.contains(popped)) {
				dft.add(popped);
				List<Vertex> allEdge = graph.allEdges(popped);
				for(Vertex iterating : allEdge) {
					dftStack.add(iterating.getLabel());
				}
			}
		}
		
		for(String printer : dft) {
			System.out.println(printer);
		}
	}

}
