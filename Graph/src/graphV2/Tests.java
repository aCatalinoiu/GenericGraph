package graphV2;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
@Retention(RetentionPolicy.RUNTIME)	
@interface RetentionRuntime {}

public class Tests {
	public static void main(String[] args) {
	}
	
	@Test
	public void testAddEdge() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		Edge<Integer> e1 = Edge.createInstance(1, 4);
		Edge<Integer> e2 = Edge.createInstance(3, 4);
		Edge<Integer> e3 = Edge.createInstance(4, 2);
		Edge<Integer> e4 = Edge.createInstance(2, 2);
		Set<Edge<Integer>> actual = graph.getEdges();
		Set<Edge<Integer>> expected = new HashSet<Edge<Integer>>();
		expected.add(e1);
		expected.add(e3);
		expected.add(e2);
		Assert.assertEquals(expected, actual);
		Set<Edge<Integer>> unexpected = new HashSet<Edge<Integer>>();
		unexpected.addAll(expected);
		unexpected.add(e4);
		
		Assert.assertNotEquals(unexpected, actual);
		
	}


	
	@Test
	public void testDeleteEdge() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		graph.deleteEdge(4, 2);
		Edge<Integer> edge1 = Edge.createInstance(1,4);
		Edge<Integer> edge2 = Edge.createInstance(3,4);
		Edge<Integer> edge3 = Edge.createInstance(4,2);
		Set<Edge<Integer>> actual = graph.getEdges();
		Set<Edge<Integer>> expected = new HashSet<Edge<Integer>>();
		expected.add(edge1);
		expected.add(edge2);
		Assert.assertEquals(expected, actual);
		Set<Edge<Integer>> unexpected = new HashSet<Edge<Integer>>();
		unexpected.addAll(expected);
		unexpected.add(edge3);
		Assert.assertNotEquals(unexpected, actual);
		
	}

	@Test
	public void testSearchPath() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		
		List<Integer> actual = graph.searchPath(1, 2);
		List<Integer> expected = Arrays.asList(1,4,2);
		
		Assert.assertEquals(expected, actual);
		
		List<Integer> unexpected = Arrays.asList(1,3,4,5,6);
		Assert.assertNotEquals(unexpected, actual);
		
	}
	
	@Test
	public void testAddNode() {
		SimpleGraph<String> testString = SimpleGraph.graphInstance();
		testString.addNode("A");
		testString.addNode("B");
		testString.addNode("C");
		List<String> stringList =  Arrays.asList("A","B","C");
		Set<String> expectedString = new HashSet<String>(stringList);
		Set<String> actualString = testString.getNodes();
		
		Assert.assertEquals(actualString, expectedString);
		
		List<String> badStringList = Arrays.asList("A","B","Z","D");
		Set<String> unexpectedString = new HashSet<String>(badStringList);
		Assert.assertNotEquals(unexpectedString, actualString);
		
		SimpleGraph<Integer> testInteger = SimpleGraph.graphInstance();		
		testInteger.addNode(1);
		testInteger.addNode(2);
		testInteger.addNode(3);
		List<Integer> integerList = Arrays.asList(1,2,3);
		Set<Integer> expected = new HashSet<Integer>(integerList);
		Set<Integer> actual = testInteger.getNodes();
		
		Assert.assertEquals(actual, expected);
		List<Integer> badIntegerList = Arrays.asList(1,2,4,0);
		Set<Integer> unexpectedInteger = new HashSet<Integer>(badIntegerList);
		Assert.assertNotEquals(unexpectedInteger, actual);
		
	}
	
	@Test
	public void testDeleteNode() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestNodes(graph);
		graph.deleteNode(2);
		graph.deleteNode(4);
		Set<Integer> actual = graph.getNodes();
		Set<Integer> expected = new HashSet<Integer>();
		expected.add(3);
		expected.add(1);
		Assert.assertEquals(expected, actual);
		List<Integer> unexpected = Arrays.asList(4,3,2,1);
		Assert.assertNotEquals(unexpected, actual);

	}
	
	@Test
	public void testSubGraph() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		SimpleGraph<Integer> subGraph = SimpleGraph.graphInstance();
		subGraph.addNode(10);
		subGraph.addNode(9);
		subGraph.addEdge(10, 9);
		graph.addSubGraph(subGraph);
		
		Set<Integer> actualNodes = graph.getNodes();
		Set<Edge<Integer>> actualEdges = graph.getEdges();
		
		Set<Integer> expectedNodes = new HashSet<Integer>();
		expectedNodes.add(1);
		expectedNodes.add(2);
		expectedNodes.add(3);
		expectedNodes.add(4);
		expectedNodes.add(9);
		expectedNodes.add(10);
		Edge<Integer> edge1 = Edge.createInstance(3, 4);
		Edge<Integer> edge2 = Edge.createInstance(1, 4);
		Edge<Integer> edge3 = Edge.createInstance(4, 2);
		Edge<Integer> edge4 = Edge.createInstance(10, 9);
		Set<Edge<Integer>> expectedEdges = new HashSet<Edge<Integer>>();
		expectedEdges.add(edge4);
		expectedEdges.add(edge3);
		expectedEdges.add(edge2);
		expectedEdges.add(edge1);
		Assert.assertEquals(expectedNodes, actualNodes);
		Assert.assertEquals(expectedEdges, actualEdges);
	}
	@Test
	public void testGetNeighbours() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		
		Set<Integer> actual = graph.getNeighbours(1);
		Set<Integer> expected = new HashSet<Integer>();
		expected.add(4);
		Assert.assertEquals(expected, actual);
		Set<Integer> unexpected = new HashSet<Integer>();
		unexpected.add(3);
		unexpected.add(5);
		Assert.assertNotEquals(unexpected, actual);
	}
	@Test 
	public void testInIncidentEdge() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		List<Edge<Integer>> actual = graph.inIncidentEdge(4);
		Edge<Integer> edge1 = Edge.createInstance(1, 4);
		Edge<Integer> edge2 = Edge.createInstance(1, 1);
		Edge<Integer> edge3 = Edge.createInstance(3, 4);
		List<Edge<Integer>> expected = new ArrayList<Edge<Integer>>();
		expected.add(edge3);
		expected.add(edge1);
		Assert.assertEquals(expected, actual);
		List<Edge<Integer>> unexpected = new ArrayList<Edge<Integer>>();
		unexpected.add(edge1);
		unexpected.add(edge2);
		Assert.assertNotEquals(unexpected, actual);
	}
	
	@Test 
	public void testOutIncidentEdges() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		addTestNodes(graph);
		addTestEdges(graph);
		List<Edge<Integer>> actual = graph.outIncidentEdge(1);
		Edge<Integer> edge1 = Edge.createInstance(1, 4);
		List<Edge<Integer>> expected = new ArrayList<Edge<Integer>>();
		expected.add(edge1);
		
		Assert.assertEquals(expected, actual);

	}
	
	@Test
	public void testSaveFile() {
		SimpleGraph<Integer> graph = SimpleGraph.graphInstance();
		String actual = "";
		Scanner test=null;
		addTestNodes(graph);
		addTestEdges(graph);
		graph.saveToFile("actual.txt");
		try {
			test = new Scanner(new File("actual.txt"));
			while(test.hasNext()) {
				 actual += test.next();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			test.close();
		}
		String expected1 = "Nodes:[1,2,3,4]";
		String expected2 = "Edges:[(3,4),(4,2),(1,4)]";
		String expected3 = "Numberofnodes:4";
		String expected4 = "Numberofedges:3";
		String expected = expected1  + expected2 + expected3 + expected4;
		Assert.assertEquals(expected, actual);
		
	}
	
	private void addTestEdges(SimpleGraph<Integer> graph) {
		graph.addEdge(1, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 2);
	}

	private void addTestNodes(SimpleGraph<Integer> graph) {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
	}
}

	
