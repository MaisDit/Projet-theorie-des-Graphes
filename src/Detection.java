import java.util.*;

public class Detection extends Graph {
	private ArrayList<ArrayList<Boolean>> Matrice = new ArrayList<ArrayList<Boolean>>();
	
	public Detection() {
		Graph circuit = new Graph();
		circuit.MatriceAdjacente();
		circuit.MatriceValeur();
		
		boolean vrai = true;
		boolean faux = false;
		
		for (int i = 0; i < circuit.getNbSommet(); i++) {
			
			this.Matrice.add(new ArrayList<Boolean>());
			for (int j = 0; j < circuit.getNbSommet(); j++) {

				this.Matrice.get(i).add(faux); 

			}
		}
		int nb = circuit.getNbSommet();
		Boolean ttest = false;
		for (int i = 0; i < nb; i++) {
			for (int j = 0; j < nb; j++) {
				// A[i][j] = C[i][j];
				this.Matrice.get(i).set(j, circuit.getMatriceAdja().get(i).get(j));
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

		this.printMatrice();
	}
	public Boolean Detec1Diago() { // On teste pour les 1 en diagonales : rappel (1 = true)
		Boolean res = false;
		Graph circuit = new Graph(); // relecture de circuit pour éviter le problème de mémoire
		circuit.MatriceAdjacente();
		circuit.MatriceValeur();
		for (int i = 0; i < circuit.getNbSommet(); i++) {
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
	public void printMatrice() {

		Graph circuit = new Graph(); // relecture de graph pour éviter le problème de mémoire

		System.out.println("* Détection de circuit : Algorithme de Roy-Warshall");
		System.out.println("Matrice d'adjacence");
		// Ligne du haut
		System.out.print("  ");
		for (int i = 0; i < circuit.getNbSommet(); i++) {
			System.out.print(i + " ");

		}
		// On saute de ligne
		System.out.println();
		for (int j = 0; j < circuit.getNbSommet(); j++) { // j = colonne de gauche
			System.out.println();
			System.out.print(j + " ");
			for (int k = 0; k < circuit.getNbSommet(); k++) { // k c'est les trucs successifs
				if (this.Matrice.get(j).get(k) == false) {

					System.out.print("F ");
				} else {
					System.out.print("V ");
				}

			}

		}

	}

}
	

