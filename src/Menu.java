import java.io.*;
public class Menu {
String path;
	

	
	public Menu() throws FileNotFoundException {
		Graph graphe;
		System.out.println("Les graphes vont défiler petit à petit et tout sera stocké sur un fichier");
		String DesktopPath = System.getProperty("user.home") + "/Desktop/Projet Théorie des Graphes/Fichier txt/";/*chemin des fichiers*/
		String OutputPath = DesktopPath;
		this.path = DesktopPath;
		String FullFilePathTemp = DesktopPath;
		
		
		
		
		
		PrintStream o = new PrintStream(new File(path + "output.txt"));
		PrintStream console = System.out; 
		System.setOut(o);
		
		
		
		for(int i = 1; i < 13; i++) {
			System.out.println("Test du graphe " + i);
			
			FullFilePathTemp = DesktopPath + i+".txt";
			graphe = new Graph(FullFilePathTemp);
			System.out.println("Lecture d’un graphe donné dans un fichier texte");
			graphe.Display();
			System.out.println();
			graphe.MatriceAdjacente();
			System.out.println();
			graphe.MatriceValeur();
			System.out.println();
			graphe.DisplayMatriceAdjacente();
			System.out.println();
			graphe.DisplayMatriceValeur();
			System.out.println();
			graphe.DetectionCircuit();
			/*if(graphe.getIsCircuit()==false) {
			//graphe.CalculRang();
			}*/
			
			
			
		}
		
	}
}
