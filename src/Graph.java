import java.io.*;
import java.util.*;

public class Graph { 
	private int nbSommet;
	private int nbArc;
	private ArrayList<Integer> Sommet_Init = new ArrayList<Integer>();
	private ArrayList <Integer>Sommet_Term = new ArrayList<Integer>();
	private ArrayList<Integer> Arc= new ArrayList<Integer>();
	
	private ArrayList<ArrayList<Boolean>> MatriceAdja = new ArrayList<ArrayList<Boolean>>();
	private ArrayList<ArrayList<String>> MatriceVal = new ArrayList<ArrayList<String>>();
	
	private ArrayList<ArrayList<Integer>> MatriceTest = new ArrayList<ArrayList<Integer>>();
	
	public Graph() {
		File file = new File("D:/Desktop/Projet Théorie des Graphes/Fichier txt/exemple.txt");
		try {
			Scanner scan = new Scanner(file);
			this.nbSommet=scan.nextInt();
			this.nbArc = scan.nextInt();
			for(int i=0;i<nbArc;i++) {
				Sommet_Init.add(scan.nextInt());
				Sommet_Term.add(scan.nextInt());
				Arc.add(scan.nextInt());
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Erreur : Le fichier introuvable");
		}
	
	}
	public void Display() {
		System.out.println(this.nbSommet + " sommets");
		System.out.println(this.nbArc + " arcs");
		for(int j=0;j<nbArc;j++) {
			System.out.println(Sommet_Init.get(j)+" -> "+Sommet_Term.get(j)+" = "+Arc.get(j));
		}
	}
	public void MatriceAdjacente() {
		boolean vrai = true;
		boolean faux = false;

		
		for (int i = 0; i < this.nbSommet; i++) {
			this.MatriceAdja.add(new ArrayList<Boolean>());
			for (int j = 0; j < this.nbSommet; j++) {
				this.MatriceAdja.get(i).add(faux);
			}
		}
		for(int i=0;i<this.nbSommet;++i) {
			for(int j=0;j<this.nbSommet;j++) {
				for(int k=0;k<this.nbArc;k++) {
					if(this.Sommet_Init.get(k)==i && this.Sommet_Term.get(k)==j) {
						this.MatriceAdja.get(i).set(j,vrai);
					}
				}
			}
		}
		
	}
	public void DisplayMatriceAdjacente() {
		System.out.println("Graphe sous forme matricielle");
		System.out.println("Matrice Adjacent:");
		System.out.println();
		System.out.print("  ");
		for(int i =0; i<this.nbSommet;i++) {
			System.out.print(i+" ");
		}
		for(int j = 0;j<this.nbSommet;j++) {
			System.out.println();
			System.out.print(j + " ");
			for(int k = 0;k < this.nbSommet;k++) {
				if (this.MatriceAdja.get(j).get(k) == false) {

					System.out.print("F ");
				} else {
					System.out.print("V ");
				}
			}
		}
	}
	public void MatriceValeur() {
		
		for (int i = 0; i < this.nbSommet; i++) {
			this.MatriceVal.add(new ArrayList<String>());
			for (int j = 0; j < this.nbSommet; j++) {
				this.MatriceVal.get(i).add("*");
			}
		}
		for(int i=0;i<this.nbSommet;++i) {
			for(int j=0;j<this.nbSommet;j++) {
				for(int k=0;k<nbArc;k++) {
					if(this.Sommet_Init.get(k)==i && this.Sommet_Term.get(k)==j) {
						this.MatriceVal.get(i).set(j,String.valueOf(Arc.get(k)));
					}
				}
			}
		}
	}
	public void DisplayMatriceValeur() {
		System.out.println("Graphe sous forme matricielle");
		System.out.println("Matrice Valeur:");
		System.out.println();
		System.out.print("  ");
		for(int i =0; i<this.nbSommet;i++) {
			System.out.print(i+" ");
		}
		for(int j = 0;j<this.nbSommet;j++) {
			System.out.println();
			System.out.print(j + " ");
			for(int k = 0;k < this.nbSommet;k++) {
				System.out.print(this.MatriceVal.get(j).get(k)+" ");
			}
		}
	}
	public void DetectionCircuit() {
		for (int i = 0; i < this.nbSommet; i++) {
			this.MatriceTest.add(new ArrayList<Integer>());
			for (int j = 0; j < this.nbSommet; j++) {
				this.MatriceTest.get(i).add(0);
			}
		}
		for(int i=0;i<this.nbSommet;++i) {
			for(int j=0;j<this.nbSommet;j++) {
				for(int k=0;k<nbArc;k++) {
					if(this.Sommet_Init.get(k)==i && this.Sommet_Term.get(k)==j) {
						this.MatriceTest.get(i).set(j,1);
					}
				}
			}
		}
	}
	public void DisplayTest() {
		System.out.println("Matrice Valeur:");
		System.out.println();
		System.out.print("  ");
		for(int i =0; i<this.nbSommet;i++) {
			System.out.print(i+" ");
		}
		for(int j = 0;j<this.nbSommet;j++) {
			System.out.println();
			System.out.print(j + " ");
			for(int k = 0;k < this.nbSommet;k++) {
				System.out.print(this.MatriceTest.get(j).get(k)+" ");
			}
		}
	}
	
}