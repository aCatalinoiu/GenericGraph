package graphV2;

import java.util.List;
import java.util.Set;

public interface GraphInterface<T> {
	
	 void addNode(T node);
	 void deleteNode(T node);
	 void addEdge(T nodeStart, T nodeEnd);
	 void deleteEdge(T nodeStart, T nodeEnd);
	 void printNodes();
	 void printEdges();
	 void displayGraph();
	 void saveToFile(String filename);
	 int getNumberOfEdges();
	 int getNumberOfNodes();
	 Set<T> getNodes();
	 Set<T> getNeighbours(T node);
	 Set<Edge<T>> getEdges();
	 List<Edge<T>> inIncidentEdge(T node);
	 List<Edge<T>> outIncidentEdge(T node);
	 List<T> searchPath(T start , T end);
	 
}
