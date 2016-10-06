package tipo;

import parte2.Movie;
import parte2.Rental;

public class Childrens {

	private static final double price = 1.5;

	public double getPrice(Rental rental) {
		int days = rental.getDays();
		double total = 0;
		total += 1.5;
		if (days > 3) {
			total += (days - 3) * price;
		}
		return total;
	}
}
