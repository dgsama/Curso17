package tipo;

public class Regular {
	private static final double price = 1.5;

	public double getPrice(int days) {
		double total = 0;
		total += 2;
		if (days > 2) {
			total += (days - 2) * price;
		}
		return total;
	}
}