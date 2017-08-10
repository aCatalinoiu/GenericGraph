package graphV2;

public class Client {
	public static void main(String[] args) {
		SimpleGraph<String> graph = SimpleGraph.graphInstance();
		graph.addNodes("A","B","C","N","V","B","R","O");
		graph.addNode("D");
		graph.addEdge("A", "B");
		graph.addEdge("B", "A");
		graph.addEdge("C", "A");
		graph.addEdge("A", "D");
		graph.addEdge("A", "C");
		graph.addEdge("O", "V");
		graph.addEdge("N", "R");
		graph.addEdge("R", "V");
		graph.addEdge("R", "C");
		graph.addEdge("V", "N");
		graph.addEdge("A", "B");
		//graph.deleteEdge("A","B");
		graph.printEdges();
		graph.deleteNode("C");
		graph.printNodes();
		graph.displayGraph();
		graph.saveToFile("file.txt");
		System.out.println("VECINII" + " " + graph.getNeighbours("A"));
		System.out.println(graph.searchPath("R", "N"));
		
		SimpleGraph<Integer> mainGraph = SimpleGraph.graphInstance();
		SimpleGraph<Integer> subGraph = SimpleGraph.graphInstance();
		subGraph.addNode(100);
		subGraph.addNode(200);
		subGraph.addEdge(100, 200);
		mainGraph.addNodes(1,2,3,4,5,6);
		mainGraph.addNode(500);
		mainGraph.addEdge(1, 2);
		mainGraph.addEdge(2, 3);
		mainGraph.addEdge(3, 4);
		mainGraph.addEdge(2, 4);
		mainGraph.addEdge(1, 4);
		mainGraph.addEdge(4, 3);
		mainGraph.addEdge(4, 6);
		mainGraph.addEdge(4, 5);
		//mainGraph.deleteNode(4);
		mainGraph.addSubGraph(subGraph);
		mainGraph.printNodes();
		mainGraph.printEdges();
		System.out.println("Path: " + mainGraph.searchPath(2, 5));
		System.out.println("Neighbours: " + mainGraph.getNeighbours(4));
		System.out.println("Number of edges: "+ mainGraph.getNumberOfEdges());
		System.out.println("Number of Nodes: "+ mainGraph.getNumberOfNodes());
		System.out.println("List of in edges for the selected node are: " + mainGraph.inIncidentEdge(4));
		System.out.println("List of out edges for the selected node are: " + mainGraph.outIncidentEdge(4));
		mainGraph.printNodes();
		mainGraph.displayGraph();
	}
}