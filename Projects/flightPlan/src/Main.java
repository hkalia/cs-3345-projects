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
            flightGraph.print();

            sc = new Scanner(requests);

            while (sc.hasNextLine()) {
            	System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
