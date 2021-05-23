import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Graph {
	private Map<Vertex, List<Vertex>> adjList = new HashMap<>();
	int n=0;
	private int[][] adjMatrix = new int[n][n];
	private HashMap<String, Integer> listNames = new HashMap<>();
	
	public Graph() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addVertex(String label) {
		adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
		int[][] newMatrix = new int[n+1][n+1];
		listNames.put(label, n);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				newMatrix[i][j] = adjMatrix[i][j];
			}
		}
		
		for(int i=n; i<n+1; i++) {
			for(int j=0; j<n+1; j++) {
				newMatrix[i][j] = 0;
			}
		}
		for(int j=n; j<n+1; j++) {
			for(int i=0; i<n+1; i++) {
				newMatrix[i][j] = 0;
			}
		}
		adjMatrix = newMatrix;
		n=n+1;
	}
	
	public void removeVertex(String label) {
		Vertex v = new Vertex(label);
		adjList.values().stream().forEach(e -> e.remove(v));
		adjList.remove(new Vertex(label));
		
		int location = listNames.get(label);
		for(int i=location; i<location+1; i++) {
			for(int j=0; j<n; j++) {
				adjMatrix[i][j] = 0;
			}
		}
		for(int j=location; j<location+1; j++) {
			for(int i=0; i<n; i++) {
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addEdge(String ver1, String ver2) {
		Vertex vertex1 = new Vertex(ver1);
		Vertex vertex2 = new Vertex(ver2);
		adjList.get(vertex1).add(vertex2);
		adjList.get(vertex2).add(vertex1);
		
		int location = listNames.get(ver1);
		int location2 = listNames.get(ver2);
		adjMatrix[location][location2] = 1;
		adjMatrix[location2][location] = 1;
	}
	
	public void removeEdge(String ver1, String ver2) {
		Vertex vertex1 = new Vertex(ver1);
		Vertex vertex2 = new Vertex(ver2);
		List<Vertex> verOneEdge = adjList.get(vertex1);
		List<Vertex> verTwoEdge = adjList.get(vertex2);
		
		if(verOneEdge != null) {
			verOneEdge.remove(vertex2);
		}
		
		if(verTwoEdge != null) {
			verTwoEdge.remove(vertex1);
		}
		
		int location = listNames.get(ver1);
		int location2 = listNames.get(ver2);
		adjMatrix[location][location2] = 0;
		adjMatrix[location2][location] = 0;
	}
	
	public List<Vertex> allEdges(String vertex){
		return adjList.get(new Vertex(vertex));
	}
	
	public void show() {
		for(Map.Entry<Vertex, List<Vertex>> entry : adjList.entrySet()) {
			System.out.println(entry.getKey().getLabel());
			System.out.print(" ---> ");
			List<Vertex> vertexEdge = entry.getValue();
			for(Vertex printVertex : vertexEdge) {
				System.out.print(printVertex.getLabel() + "-");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("NOW SHOWING ADJACENCY MATRIX");

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
