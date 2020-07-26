public class ConnectionData {
	String cityName;
    int pennyCost;
    int minuteCost;

    ConnectionData(String cityName, double dollarCost, int minuteCost) {
        this.cityName = cityName;
        this.pennyCost = (int) dollarCost * 100;
        this.minuteCost = minuteCost;
    }

    @Override
    public String toString() {
        return "ConnectionData{" + this.cityName +
                ", " + (double) this.pennyCost/100 +
                ", " + this.minuteCost +
                "}";
    }
}
