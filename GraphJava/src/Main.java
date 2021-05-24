import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		
	    graph.addVertex("Bob");
	    graph.addVertex("Alice");
	    graph.addVertex("Mark");
	    graph.addVertex("Rob");
	    graph.addVertex("Maria");
	    
	    
	    graph.addEdge("Bob", "Alice", 3);
	    graph.addEdge("Bob", "Rob", 4);
	    graph.addEdge("Alice", "Mark", 7);
	    graph.addEdge("Rob", "Mark", 2);
	    graph.addEdge("Alice", "Maria", 1);
	    graph.addEdge("Rob", "Maria", 9);
	    
	    //graph.show();
	    //DFT(graph, "Bob");
	    //System.out.println("BFT:");
	    //BFT(graph, "Bob");
	    Dijkstra(graph, "Rob", "Mark");
	}
	
	public static void Dijkstra(Graph graph, String startSearch, String endSearch) {
		Set<Vertex> unvisited = graph.getSetofVertices();
		Set<Vertex> visited = new HashSet<>();
		
		HashMap<String, Vertex> finalMap = new HashMap<>();
		
		for(Vertex init : unvisited) {
			if(init.getLabel().equals(startSearch)) {
				finalMap.put(startSearch, new Vertex(startSearch, 0, startSearch));
			}
			else {
				finalMap.put(init.getLabel(), new Vertex(init.getLabel(),1000000000, "X"));

			}
		}
		
		while(!unvisited.isEmpty()) {
			String currentVertex = "";
			int minWeight = 1000000000;
			for(Map.Entry<String, Vertex> entry : finalMap.entrySet()) {
				if(unvisited.contains(entry.getValue())) {
					if(entry.getValue().getWeight() <= minWeight) {
						minWeight = entry.getValue().getWeight();
						currentVertex = entry.getValue().getLabel();
					}
				}
			}
			
			List<Vertex> adjacentEdges = graph.allEdges(currentVertex);

			for(Vertex search : adjacentEdges) {
				
				if(unvisited.contains(search)) {
					
					Vertex fromMap = finalMap.get(search.getLabel());
					if((search.getWeight()+minWeight) < fromMap.getWeight() ) {
						finalMap.put(search.getLabel(), 
								new Vertex(search.getLabel(),
										(search.getWeight()+minWeight),
										currentVertex));
						
					}
					else {
						
						finalMap.put(search.getLabel(), 
								new Vertex(search.getLabel(),
										(fromMap.getWeight()),
										fromMap.getPrevNode()));
					}
				}
			}
			
			unvisited.remove(new Vertex(currentVertex));
			visited.add(new Vertex(currentVertex));
		}
		

		String result = "";
		int total = finalMap.get(endSearch).getWeight();
		while(!endSearch.equals(startSearch)) {
			if(result.equals("")) {
				result = endSearch;
			}
			else {
				result = endSearch + "  --- > " + result;
			}
			endSearch = finalMap.get(endSearch).getPrevNode();
		}
		
		result = startSearch + "  --- > " + result;
		
		System.out.println(result);
		System.out.println("Shortest pathway we yielded: " + total);
		
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
