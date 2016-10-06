package tipo;

public class NewReleases {
	private static final double price = 3;

	public double getPrice(int days) {
		double total = 0;
		total += days * price;
		return total;
	}
}
