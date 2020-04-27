import java.util.*;

public class Detection {
	
	/*fermeture transitive A[][]*/
	private ArrayList<ArrayList<Boolean>> Matrice = new ArrayList<ArrayList<Boolean>>();
	
	/*instence du construteur*/
	public Detection(Graph graphe) {
		
		boolean vrai = true;
		boolean faux = false;
		
		for (int i = 0; i < graphe.getNbSommet(); i++) {
			
			this.Matrice.add(new ArrayList<Boolean>());
			for (int j = 0; j < graphe.getNbSommet(); j++) {

				this.Matrice.get(i).add(faux); 

			}
		}
		int nb = graphe.getNbSommet();
		Boolean ttest = false;
		for (int i = 0; i < nb; i++) {
			for (int j = 0; j < nb; j++) {
				// A[i][j] = C[i][j];
				this.Matrice.get(i).set(j, graphe.getMatriceAdja().get(i).get(j));
			}
		}
		for (int k = 0; k < nb; k++) {
			for (int i = 0; i < nb; i++) {
				for (int j = 0; j < nb; j++) {

					// A[i][j] = A[i][j] || (A[i][k] && A[k][j]);
					// condition
					ttest = this.Matrice.get(i).get(j) || (this.Matrice.get(i).get(k) && this.Matrice.get(k).get(j));
					this.Matrice.get(i).set(j, ttest);
				}

			}

		}

		this.printMatrice(graphe);
	}
	public Boolean Detec1Diago(Graph graphe) { // On teste pour les 1 en diagonales : rappel (1 = true)
		Boolean res = false;
		graphe.MatriceAdjacente(); /*la Matrice d'adjacence = C[][]*/
		graphe.MatriceValeur();
		for (int i = 0; i < graphe.getNbSommet(); i++) {
			if (this.Matrice.get(i).get(i) == true) {
				res = true;
			}

		}

		if (res) {

			System.out.println("Ce graphe est un circuit.");
		} else {

			System.out.println("Ce graphe n'est pas un circuit.");
		}
		return res;
	}
	public void printMatrice(Graph graphe) {


		System.out.println("* Détection de circuit : Algorithme de Roy-Warshall");
		System.out.println("Matrice d'adjacence");
		// Ligne du haut
		System.out.print("  ");
		for (int i = 0; i < graphe.getNbSommet(); i++) {
			System.out.print(i + " ");

		}
		// On saute de ligne
		System.out.println();
		for (int j = 0; j < graphe.getNbSommet(); j++) { // j = colonne de gauche
			System.out.println();
			System.out.print(j + " ");
			for (int k = 0; k < graphe.getNbSommet(); k++) { // k c'est les trucs successifs
				if (this.Matrice.get(j).get(k) == false) {

					System.out.print("F ");
				} else {
					System.out.print("V ");
				}

			}

		}

	}

}
	

