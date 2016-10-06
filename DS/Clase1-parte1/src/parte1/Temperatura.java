package parte1;

public class Temperatura {
	private double celsius;
	private double fahrenheit;

	public Temperatura(double celsius) {
		this.celsius = celsius;
		setFahrenheit(celsius);
	}

	public double getCelsius() {
		return celsius;
	}

	public double getFahrenheit() {
		return fahrenheit;
	}

	public void setcelsius(double temp) {
		celsius = temp;
		setFahrenheit(temp);
	}

	public void setFahrenheit(double temp) {
		fahrenheit = temp * 18 + 32;
	}
}
