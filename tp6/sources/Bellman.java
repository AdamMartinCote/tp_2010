import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable;
	private List<Vector<Integer>> rTable;
	
	public Bellman (Graph g) {
		this.graph = g;
		piTable = new LinkedList<Vector<Double>>();
		rTable = new LinkedList<Vector<Integer>>();
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		boolean flag = false;
		int compteur = 0;
		
		//On crée deux nouveaux vecteurs
		Vector<Double> vectPi = new Vector<Double>();
		Vector<Integer> vectR = new Vector<Integer>();
		
		//On popule les vecteurs avec infini et null
		for(int i = 0; i < graph.getNodes().size(); i++){
			vectPi.add(Graph.inf);
			vectR.add(null);
		}
		
		//On ajoute les vecteurs
		piTable.add(vectPi);
		rTable.add(vectR);
		
		//Si ce n'est pas la première fois qu'on ajoute un vecteur
		if(piTable.size() != 1){
			//On évalue combien de valeurs sont égales dans les 2 derniers
			//vecteurs de piTable
			for(int i = 0; i < piTable.get(piTable.size()-1).size(); i++){
				if(piTable.get(piTable.size()-1).get(i) == piTable.get(piTable.size()-2).get(i))
					compteur++;
			}
			
			//Si on a pas atteint le nombre de nodes ou que les deux dernières lignes de piTable ne sont pas égales
			if(piTable.size() != graph.getNodes().size() && compteur != piTable.size()){
				//On vérifie quelles nodes
				for(int i = 0; i < graph.getNodes().size(); i++){
					for(int j = 0; j < graph.getInEdges(graph.getNodes().get(i)).size(); j++)
						//Si la node a une edge qui est connecté à sourceNode
						if(graph.getOutEdges(graph.getNodes().get(i)).get(j).getSource() == sourceNode){
							piTable.get(piTable.size()).set(i, graph.getOutEdges(graph.getNodes().get(i)).get(j).getDistance());
							rTable.get(rTable.size()).set(i, graph.getOutEdges(graph.getNodes().get(i)).get(j).getSource().getId());
					}
				}
			}
		}
		else{
			piTable.get(0).set(0, 0.0);
		}
		for(int i = 0; i < rTable.get(rTable.size() - 1).size(); i++)
			if(rTable.get(rTable.size() - 1).get(i) != null)
				shortestPath();	
	}
	
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		// A completer	
		
		
	}

	public void displayTables() {
		// A completer
		System.out.println("<< PITable >>");
		System.out.println("k  :  S  B  C  D  E  F");
		for(int i = 0; i < piTable.size(); i++){
			System.out.print(i+ "  :  ");
			for(int j = 0; j < piTable.get(i).size(); j++){
				System.out.print(piTable.get(i).get(j) + "  ");
			}
			System.out.println();
		}
		
		System.out.println("<< RTable >>");
		System.out.println("k  :  S  B  C  D  E  F");
		for(int i = 0; i < rTable.size(); i++){
			System.out.print(i+ "  :  ");
			for(int j = 0; j < rTable.get(i).size(); j++){
				if(rTable.get(i).get(j) != null)
					System.out.print(rTable.get(i).get(j) + "  ");
				else
					System.out.print("-  ");
			}
			System.out.println();
		}
	}
}


























