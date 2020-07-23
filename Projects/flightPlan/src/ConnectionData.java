public class ConnectionData {
    String cityName;
    double dollarCost;
    int minuteCost;
    Integer cost;
    boolean known;

    ConnectionData(String cityName, double dollarCost, int minuteCost) {
        this.cityName = cityName;
        this.dollarCost = dollarCost;
        this.minuteCost = minuteCost;
        this.cost = null;
        this.known = false;
    }

    @Override
    public String toString() {
        return "ConnectionData{" + this.cityName +
                ", " + this.dollarCost +
                ", " + this.minuteCost +
                ", " + this.cost +
                ", " + this.known + "}";
    }
}
