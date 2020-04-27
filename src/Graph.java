import java.io.*;
import java.util.*;

public class Graph { 
	/*nombre de Sommet du graphe*/
	private int nbSommet;
	/*nombre d'Arc du graphe*/
	private int nbArc;
	
	public String chemin;
	/* liste des Sommet initiale du fichier txt*/
	private ArrayList<Integer> Sommet_Init = new ArrayList<Integer>();
	
	/*liste des Sommet terminaux du fichier txt*/
	private ArrayList <Integer>Sommet_Term = new ArrayList<Integer>();
	
	/*nombre de Sommet du graphe*/
	private ArrayList<Integer> Arc= new ArrayList<Integer>();
	
	/*Matrice d'Adjacence*/
	private ArrayList<ArrayList<Boolean>> MatriceAdja = new ArrayList<ArrayList<Boolean>>();
	
	/*Matrice de Valeur*/
	private ArrayList<ArrayList<String>> MatriceVal = new ArrayList<ArrayList<String>>();
	
	/*appelle le constructeur de Detection de circuit*/
	private Detection Detection;
	
	/*appelle le constructeur Rang*/
	private Rang Rang;
	
	/*variable de verification si c'est un circuit*/
	private Boolean isCircuit;
	
	public void setIsCircuit(Boolean isCircuit) {
		this.isCircuit = isCircuit;
	}
	
	public Boolean getIsCircuit() {
		return isCircuit;
	}
	public ArrayList<Integer> getArc() {
		return Arc;
	}
	
	public int getNbSommet() {
		return nbSommet;
	}
	public int getNbArc() {
		return nbArc;
	}
	public ArrayList<ArrayList<Boolean>> getMatriceAdja() {
		return MatriceAdja;
	}
	public ArrayList<Integer> getSommet_Init() {
		return Sommet_Init;
	}
	public ArrayList<Integer> getSommet_Term() {
		return Sommet_Term;
	}
	public String getChemin() {
		return chemin;
	}
	
	public Graph(String Path) {

		File file = new File(Path); /*initialise le fichier qu'on veut parcourir*/
		try {
			Scanner scan = new Scanner(file); 
			this.nbSommet=scan.nextInt();/*on lie la première valeur et on le stocke dans nbSommet*/
			this.nbArc = scan.nextInt();/*on lie la première valeur et on le stocke dans nbArc*/
			for(int i=0;i<nbArc;i++) {
				Sommet_Init.add(scan.nextInt());/*on lie les valeurs et on les stocke dans la liste Sommet_Init*/
				Sommet_Term.add(scan.nextInt());/*on lie les valeurs et on les stocke dans la liste Sommet_Term*/
				Arc.add(scan.nextInt());
			}
			scan.close();
		}catch(FileNotFoundException e) {
			System.out.println("Erreur : Le fichier introuvable");/*si pas de fichier on affiche un message d'erreur*/
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
				this.MatriceAdja.get(i).add(faux);/*on met la matrice a faux*/
			}
		}
		for(int i=0;i<this.nbSommet;++i) {/* corespond aux ligne*/
			for(int j=0;j<this.nbSommet;j++) {/*nbSommet corespond aux colone*/
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

		this.Detection = new Detection(this);
		// this.FermetureTransitive.Detec1Diago();

		if (this.Detection.Detec1Diago(this)) {
			this.isCircuit = true;
		} else {
			this.isCircuit = false;
		}

	}
	
	public void CalculRang() {
		Graph sGraph = this.shallowCopy();
		 this.Rang= new Rang(sGraph);
		 this.Rang.CalculRang(sGraph);
		 this.Rang.DisplayRang(sGraph);
		
	}
	public Graph shallowCopy() {
	    Graph newFoo = new Graph();
	    newFoo.nbSommet =this.nbSommet;
	    newFoo.nbArc = this.nbArc;
	    newFoo.chemin =this.chemin;
	    newFoo.Sommet_Init = this.Sommet_Init;
	    newFoo.Sommet_Term = this.Sommet_Term;
	    newFoo.Arc = this.Arc;
	    newFoo.MatriceAdja =this.MatriceAdja;
	    newFoo.MatriceVal = this.MatriceVal;
	    newFoo.Detection=this.Detection;
	    newFoo.isCircuit=this.isCircuit;
	    return newFoo;
	  }
	public Graph() {
		
	}

}

		

	
