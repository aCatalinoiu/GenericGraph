package graphV2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
public class JungGraph implements ActionListener
{
	private JButton pathButton,addNodeButton,addEdgeButton;
	private JButton deleteNodeButton,deleteEdgeButton,showButton,hiddenButton;
	private JFrame frame;
	private JLabel startNodeLabel,endNodeLabel,addNodeLabel,addEdgeLabel;
	private JLabel deleteNodeLabel,deleteEdgeLabel;
	private JTextField startNodeField,endNodeField,addNodeField,deleteNodeField;
	private JTextField deleteEdgeField,addEdgeField,pathField;
	private JPanel panel,utilityPanel,modifyPanel,buttonPanel;
	private Graph<String, Edge<String>> g;
	private VisualizationImageServer<String, Edge<String>> vs;
	private SimpleGraph<String> simpleGraph;
	private JDialog dialog;
	
	
	
	public static void main(String[] args) throws IOException {
		JungGraph jgraph = new JungGraph();
		
		jgraph.createGUI();
		jgraph.loadFile();
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("abs", 12);
		map.put("ddd", 421);
		if(!map.containsKey("abs")) {
			map.put("abs", 15);
		}
		System.out.println(map.keySet());
		System.out.println(map.entrySet());
		String a = "str";
		a += "str" + "str";
		System.out.println(a);
		
	}
	public void createGUI() {
		GUI();
	}
	private void GUI() {
		g = new  DirectedSparseMultigraph<String, Edge<String>>();
		simpleGraph = SimpleGraph.graphInstance();

			createPanels();
			createLabels();
			createButtons();
			createTextFields();
			populatePanels();
		 	genFrame();
		
		}
	private void genFrame() {
		frame = new JFrame();
		frame.setTitle("Simple Graph");
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	private void createPanels() {
		utilityPanel = new JPanel(new GridBagLayout());
		buttonPanel = new JPanel();
		modifyPanel = new JPanel();
		panel = new JPanel(new BorderLayout());
		dialog = new JDialog(frame);
		dialog.setSize(760, 550);
	 	dialog.setTitle("its here");
	 	dialog.setLocation(100, 100);
	}
	private void createLabels() {
		startNodeLabel = new JLabel("Start node");
	 	endNodeLabel = new JLabel("End node");
	 	addNodeLabel = new JLabel("Add node: ");
	 	addEdgeLabel = new JLabel("Add edge: ");
	 	deleteNodeLabel = new JLabel("Delete node: ");
	 	deleteEdgeLabel = new JLabel("Delete edge: ");
	}
	private void createButtons() {
		pathButton =  new JButton("Get Path");
	 	addNodeButton = new JButton("Add node");
	 	addEdgeButton = new JButton("Add edge");
	 	deleteNodeButton = new JButton("Delete node");
	 	deleteEdgeButton = new JButton("Delete edge");
	 	showButton = new JButton("Display Graph");
	 	hiddenButton = new JButton();
	 	addNodeButton.addActionListener(this);
	 	addEdgeButton.addActionListener(this);
	 	deleteEdgeButton.addActionListener(this);
	 	deleteNodeButton.addActionListener(this);
	 	pathButton.addActionListener(this);
	 	showButton.addActionListener(this);
	 	hiddenButton.addActionListener(this);
	}
	private void createTextFields() {
		startNodeField = new JTextField(20);
	 	endNodeField = new JTextField(20);
	 	addNodeField = new JTextField(20);
	 	addEdgeField = new JTextField(20);
	 	deleteNodeField = new JTextField(20);
	 	deleteEdgeField = new JTextField(20);
	 	pathField = new JTextField(20);
	}
	private void populatePanels() {
		buttonPanel.add(addNodeButton);
	 	buttonPanel.add(addEdgeButton);
	 	buttonPanel.add(deleteNodeButton);
	 	buttonPanel.add(deleteEdgeButton);
	 	buttonPanel.add(showButton);
	 	addComp(utilityPanel,pathButton,0,2,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	 	addComp(utilityPanel,startNodeLabel,0,0,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	 	addComp(utilityPanel,startNodeField,1,0,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	 	addComp(utilityPanel,endNodeLabel,0,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);	
	 	addComp(utilityPanel,endNodeField,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	 	addComp(utilityPanel,pathField,1,2,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
	 	modifyPanel.add(addNodeLabel,BorderLayout.WEST);
	 	modifyPanel.add(addNodeField,BorderLayout.EAST);
	 	modifyPanel.add(addEdgeLabel,BorderLayout.WEST);
	 	modifyPanel.add(addEdgeField,BorderLayout.EAST);
	 	modifyPanel.add(deleteNodeLabel,BorderLayout.WEST);
	 	modifyPanel.add(deleteNodeField,BorderLayout.EAST);
	 	modifyPanel.add(deleteEdgeLabel,BorderLayout.WEST);
	 	modifyPanel.add(deleteEdgeField,BorderLayout.EAST);
	 	panel.add(utilityPanel,BorderLayout.NORTH);
	 	panel.add(modifyPanel,BorderLayout.CENTER);
	 	panel.add(buttonPanel,BorderLayout.SOUTH);
	 	
	}
	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch) {	         
		       GridBagConstraints gridConstraints = new GridBagConstraints();
		        	gridConstraints.gridx = xPos;
			        gridConstraints.gridy = yPos;
			        gridConstraints.gridwidth = compWidth;
			        gridConstraints.gridheight = compHeight;
			        gridConstraints.weightx = 50;
			        gridConstraints.weighty = 50;
			        gridConstraints.insets = new Insets(2,2,2,2);
			        gridConstraints.anchor = place;
			        gridConstraints.fill = stretch;
			        thePanel.add(comp, gridConstraints);
			         
			    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pathButton) {
			displayPath();
		} else if(e.getSource() == addNodeButton) {
			addNodeAction();
		} else if(e.getSource() == addEdgeButton) {
			addEdgeAction();
		} else if(e.getSource() == showButton) {
			showAction();
		} else if(e.getSource() == deleteNodeButton) {
			deleteNodeAction();
		} else if(e.getSource() == deleteEdgeButton) {
			deleteEdgeAction();
		} else if(e.getSource() == hiddenButton) {
			showAction();
		}
		
	}
	private void deleteEdgeAction() {
		if(deleteEdgeField.getText().trim().equals("")){
			System.out.println("field is empty");
		}
		else {
			String[] edgeVal = deleteEdgeField.getText().split(",");
			for(Edge<String> edges: simpleGraph.getEdges()) {
				if(checkValidEdgesGUI(edgeVal, edges)) {
					g.removeEdge(edges);
				}
			}
			simpleGraph.deleteEdge(edgeVal[0].toUpperCase(), edgeVal[1].toUpperCase());
		}
		hiddenButton.doClick();
		deleteEdgeField.setText("");
	}
	private void deleteNodeAction() {
		String nodeVal = deleteNodeField.getText().toUpperCase();
		if(!nodeVal.trim().equals("")) {
		g.removeVertex(nodeVal);
		for(Edge<String> edges: simpleGraph.getEdges()) {
			if(checkEdgesGui(nodeVal, edges)) {
				g.removeEdge(edges);
				}
			}
			simpleGraph.deleteNode(nodeVal);
		}
			hiddenButton.doClick();
			deleteNodeField.setText("");
	}
	private void showAction() {
		for(String nodes : simpleGraph.getNodes()) {
			g.addVertex(nodes);
		}
		for(Edge<String> edges : simpleGraph.getEdges()) {
			g.addEdge(edges, edges.getNodeStart(),edges.getNodeEnd());
		}
		createViewer();
		dialog.setVisible(true);
	}
	private void addEdgeAction() {
		String[] edgeVal;

		if(addEdgeField.getText().trim().equals("")){
			System.out.println("field is empty");
		} else {
			edgeVal =addEdgeField.getText().split(",");
			simpleGraph.addEdge(edgeVal[0].toUpperCase(), edgeVal[1].toUpperCase());
		}
		hiddenButton.doClick();
		addEdgeField.setText("");
	}
	private void addNodeAction() {
		String nodeVal = addNodeField.getText();
		//System.out.println(nodeVal);
		if(!nodeVal.trim().equals(""))
		simpleGraph.addNodes(nodeVal.toUpperCase().trim());
		hiddenButton.doClick();
		addNodeField.setText("");
	}
	private void displayPath() {
		String firstNode = startNodeField.getText();
		String endNode = endNodeField.getText();
		List<String> aux = new ArrayList<String>(simpleGraph.searchPath(firstNode.toUpperCase(), endNode.toUpperCase()));
		pathField.setText("Drum: " + aux );
		aux.removeAll(aux);
	}
	private boolean checkValidEdgesGUI(String[] edgeVal, Edge<String> edges) {
		return edges.getNodeStart().equals(edgeVal[0].toUpperCase()) && edges.getNodeEnd().equals(edgeVal[1].toUpperCase());
	}
	
	private boolean checkEdgesGui(String nodeVal, Edge<String> edges) {
		return edges.getNodeStart().equals(nodeVal) || edges.getNodeEnd().equals(nodeVal);
	}
	private void createViewer() {
		
		vs = new VisualizationImageServer<String, Edge<String>>(
			        new KKLayout<String, Edge<String>>(g), new Dimension(650,550));
		 Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
			 public Paint transform(String i) {
			 return Color.GREEN;
			 }
		}; 
		 vs.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		 vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		 vs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		dialog.add(vs);
		dialog.pack();
	}
	private void loadFile() throws IOException {
		List<String> errorList = new ArrayList<String>();
		String str;
		String[] nodeToken,edgeToken;
		try {
			BufferedReader in = new BufferedReader(new FileReader("inFile.txt"));
			while((str = in.readLine()) != null) {

				nodeToken = str.trim().replaceAll(",+", ",").split(","); 
				for(int j = 0; j<nodeToken.length; j++) {
					if(nodeToken[j].contains("-")) {
						if(nodeToken[j].matches("[a-zA-Z0-9]+-[a-zA-Z0-9]+")) {
							edgeToken = nodeToken[j].split("-");
							simpleGraph.addEdge(edgeToken[0].trim().toUpperCase(), edgeToken[1].trim().toUpperCase());
							
						} else {
							errorList.add("Invalid type " + nodeToken[j]);
						}
					} else {
						if(!nodeToken[j].trim().equals("")) {
								simpleGraph.addNode(nodeToken[j].trim().toUpperCase());
						}
					}
				}
			}
			for(String nodes: simpleGraph.getNodes()) {
				g.addVertex(nodes);
			}
			for(Edge<String> edges : simpleGraph.getEdges()) {
				g.addEdge(edges, edges.getNodeStart(),edges.getNodeEnd());
			}
			in.close();
			createViewer();
			
		} catch(IOException e) {
			e.printStackTrace(System.out);
			
		} finally {
			if(!errorList.isEmpty()) {
				System.out.println(errorList);
			}
				
		}
	}
	
}