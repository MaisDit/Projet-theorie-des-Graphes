import java.util.*;
/*la Detection de rang ne fonctionne pas a cause de "Exception in thread "main" java.lang.StackOverflowError" */

public class Rang  {
	private Map<Integer,Integer> Sommet = new HashMap<Integer,Integer>();
	private ArrayList<Integer> Racine = new ArrayList<Integer>();
	private ArrayList<Integer> Rang = new ArrayList<Integer>();
	//rang de chaque sommet
	
	
	
	
	public Rang(Graph graphe) {}
	
	public void CalculRang(Graph graphe) {
		int k=0;
		for(int i=0;i<graphe.getNbSommet();i++) {
			for(int j=0;j<graphe.getSommet_Term().size();j++) {
				if (!graphe.getSommet_Term().contains(i)) {
					Sommet.put(i,k);
					graphe.getSommet_Term().removeAll(Arrays.asList(graphe.getSommet_Init().get(i))); /* enleve tous les elements avec la valeur de Sommet_term(i)*/
					k=k+1;
				}
				
			}
			if(graphe.getSommet_Init().size()!=0) {
				this.CalculRang(graphe);}
			
		}
			
	}
	public void DisplayRang(Graph graphe) {
		for(int i=0;i<graphe.getNbSommet();i++) {
			System.out.println("le sommet "+i+"est de rang "+Sommet.get(i));
		}
	}
}

