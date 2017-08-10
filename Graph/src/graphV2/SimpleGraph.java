package graphV2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;



public class SimpleGraph<T> implements Interf1<T>,GraphInterface<T> {
	private final Set<T> nodes;
	private final Set<Edge<T>> edges;
	private boolean found;
	private Set<T> visitedNodes;
	private List<T> path;
	private JFrame frame;
	private Graph<T,Edge<T>> visualGraph;
	private VisualizationImageServer<T, Edge<T>> visualServer;
	
	private SimpleGraph() {
		this.nodes = new HashSet<T>();
		this.edges = new HashSet<Edge<T>>();
		found = false;
		visitedNodes = new HashSet<T>();
		path = new ArrayList<T>();
		
	}
	public static <T> SimpleGraph<T> graphInstance() {
		return new SimpleGraph<T>();
	}
	@Override
	public void addNode(T node) {
		Objects.requireNonNull(node, "Node cannot be null!");
		nodes.add(node);		
	}
	
	public void deleteNode(T node) {
		nodes.remove(node);
		Iterator<Edge<T>> edgeIterator = edges.iterator();
			while(edgeIterator.hasNext())
				if(nodeExists(node,edgeIterator.next())) {
				edgeIterator.remove();
			}
	}

	private boolean nodeExists(T node, Edge<T> edge) {
		return edge.getNodeStart().equals(node) || edge.getNodeEnd().equals(node);
	}

	@Override
	public void addEdge(T nodeStart, T nodeEnd) {
		if(checkNodeExistance(nodeStart, nodeEnd)) {	
			Edge<T> edge = Edge.createInstance(nodeStart, nodeEnd);
				edges.add(edge);
		}
	}

	private boolean checkNodeExistance(T nodeStart, T nodeEnd) {
		return nodes.contains(nodeStart) && nodes.contains(nodeEnd);
	}

	@Override
	public void deleteEdge(T nodeStart, T nodeEnd) {
		Iterator<Edge<T>> edgeIterator = edges.iterator();
			while(edgeIterator.hasNext())
				if(edgeExists(nodeStart,nodeEnd,edgeIterator.next())) {
				edgeIterator.remove();
			}	
		}

	private boolean edgeExists(T nodeStart, T nodeEnd, Edge<T> edge) {
		return edge.getNodeStart().equals(nodeStart) && edge.getNodeEnd().equals(nodeEnd);
	}
	
	@Override
	public List<T> searchPath(T nodeStart, T nodeEnd) {
		searchPathPrim(nodeStart, nodeEnd);
		Collections.reverse(path);
		return path;
	}
		private void searchPathPrim(T nodeStart, T nodeEnd) {
			Set<T> neighbours = getNeighbours(nodeStart);
			if(found) {
				return;
			}
			if(nodeStart.equals(nodeEnd)) {
				 found = true;
				}
			if(visitedNodes.contains(nodeStart)) {
				return;
			} else {
				visitedNodes.add(nodeStart);
				for(T each : neighbours) {
					searchPathPrim(each,nodeEnd);
				}
			}
			if(found) {
				path.add(nodeStart);
			}
	}
		
		public void addSubGraph(SimpleGraph<T> subGraph) {
			Set<T> subGraphNodes = subGraph.getNodes();
			Set<Edge<T>> subGraphEdges = subGraph.getEdges();
			for(T node : subGraphNodes) {
				this.getNodes().add(node);
			}
			for(Edge<T> edge : subGraphEdges) {
				this.getEdges().add(edge);
			}
		}
		
		@Override
		public Set<T> getNodes() {
			return nodes;
		}

		@Override
		public Set<Edge<T>> getEdges() {
			return edges;
		}
	@Override
	public Set<T> getNeighbours(T node) {
		Set<T> neighbours = new HashSet<T>();
		for(Edge<T> edge : edges) {
			if(edge.getNodeStart().equals(node)) {
				neighbours.add(edge.getNodeEnd());
			}
		}
		return neighbours;
	}
	@Override
	public void printNodes() {
		for(T nodes : nodes) {
			System.out.println("Nodes: " + nodes);
		}
	}
	@Override
	public void printEdges() {
		for(Edge<T> edges: edges) {
			System.out.println("Edges: " + edges);
		}
	}
	public int getNumberOfEdges() {
		return edges.size();
	}
	public int getNumberOfNodes() {
		return nodes.size();
	}

	public List<Edge<T>> inIncidentEdge(T node) {
		List<Edge<T>> incidentEdges = new ArrayList<Edge<T>>();
		for(Edge<T> edge : edges) {
			if(edge.getNodeEnd().equals(node)) {
				incidentEdges.add(edge);
			}
		}
		return incidentEdges;
	}
	
	public List<Edge<T>> outIncidentEdge(T node) {
		List<Edge<T>> outcidentEdges = new ArrayList<Edge<T>>();
		for(Edge<T> edge : edges) {
			if(edge.getNodeStart().equals(node)) {
				outcidentEdges.add(edge);
			}
		}
		return outcidentEdges;
	}
	public void saveToFile(String filename) {
			
			try {
					FileWriter fw = new FileWriter(filename);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Nodes: " + this.getNodes());
					bw.newLine();
					bw.write("Edges: " + this.getEdges());
					bw.newLine();
					bw.write("Number of nodes: " + this.getNumberOfNodes());
					bw.newLine();
					bw.write("Number of edges: " + this.getNumberOfEdges());
					bw.close();
					fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	@SuppressWarnings("unchecked")
	public void addNodes(T... args) {
		for(T node : args) {
			nodes.add(node);
		}
	}
	
	public void displayGraph() {
		visualGraph = new  DirectedSparseMultigraph<T, Edge<T>>();
		
			for(T nodes : getNodes()) {
				visualGraph.addVertex(nodes);
			}
			for(Edge<T> edges : getEdges()) {
				visualGraph.addEdge(edges, edges.getNodeStart(),edges.getNodeEnd());
			}
			visualServer = new VisualizationImageServer<T, Edge<T>>(
			        new CircleLayout<T, Edge<T>>(visualGraph), new Dimension(550, 450));
		 	Transformer<T,Paint> vertexPaint = new Transformer<T,Paint>() {
				 public Paint transform(T i) {
				 return Color.GREEN;
				 }
			 }; 
			 visualServer.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
			 visualServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<T>());
			 visualServer.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
			 genFrame();

	}
	private void genFrame() {
		frame = new JFrame();
		frame.getContentPane().add(visualServer);
		frame.pack();
		frame.setVisible(true);
	}
	@Override
	public void doSmth1231() {
		// TODO Auto-generated method stub
		
	}
	
}
