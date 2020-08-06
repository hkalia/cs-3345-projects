import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		FlightGraph flightGraph = new FlightGraph();
		PrintStream fileOut = null;
		try {
			fileOut = new PrintStream(args[2]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(fileOut);
		try {
			FileReader flightDataReader = new FileReader(args[0]);
			FileReader requests = new FileReader(args[1]);
			Scanner sc = new Scanner(flightDataReader);

			sc.nextLine();
			while (sc.hasNextLine()) {
				flightGraph.addEdge(sc.nextLine().split("\\|", 4));
			}
			sc.close();

			ShortPathFinder pathFinder = new ShortPathFinder();
			sc = new Scanner(requests);
			int requestNum = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < requestNum; i++) {
				String[] request = new String[3];
				request = sc.nextLine().split("\\|", 3);
				Boolean costPath = false;
				System.out.print("Flight " + (i+1) + ": " + request[0] + " to " + request[1]);
				if (request[2].compareTo("T") == 0) {
					costPath = true;
					System.out.println(" (Cost)");
				}
				else {
					System.out.println(" (Time)");
				}
				pathFinder.dijkstra(flightGraph, request[0], request[1], costPath);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
