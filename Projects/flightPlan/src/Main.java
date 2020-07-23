import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FlightGraph flightGraph = new FlightGraph();

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
