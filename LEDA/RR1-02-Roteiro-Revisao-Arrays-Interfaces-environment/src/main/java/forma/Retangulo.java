package forma;

public class Retangulo implements Forma {

    private double comprimento;
	private double altura;

	public Retangulo(double comprimento, double altura) {
		this.comprimento = comprimento;
		this.altura = altura;
	}

	@Override
	public double area() {
		return this.comprimento * this.altura;
	}
}
