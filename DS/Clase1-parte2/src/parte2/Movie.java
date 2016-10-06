package parte2;

// Pel�cula

public class Movie {
	public static final int CHILDRENS = 2;
	public static final int NEW_RELEASE = 1;
	public static final int REGULAR = 0;

	private String title;

	private int category;

	public Movie(String title, int category) {
		this.title = title;
		this.category = category;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice(Rental rental) {
		// 1) Calcula el importe de cada alquiler
		double price = 0;
		switch (rental.getMovie().getCategory()) {
		case Movie.NEW_RELEASE:
			price += rental.getDays() * 3;
			break;
		case Movie.REGULAR:
			price += 2;
			if (rental.getDays() > 2)
				price += (rental.getDays() - 2) * 1.5;
			break;
		case Movie.CHILDRENS:
			price += 1.5;
			if (rental.getDays() > 3)
				price += (rental.getDays() - 3) * 1.5;
			break;
		}
		return price;
	}

	int getPoints(Rental rental) {
		// 2) Cada alquiler da 1 punto. Punto extra para novedades alquiladas 2+
		// d�as
		int points = 1;
		if ((rental.getMovie().getCategory() == Movie.NEW_RELEASE)
				&& rental.getDays() > 1)
			points = 2;
		return points;
	}

}