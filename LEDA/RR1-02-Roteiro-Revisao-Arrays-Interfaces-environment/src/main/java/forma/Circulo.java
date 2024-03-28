package forma;

public class Circulo implements Forma {
    
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double area() {
        return Math.PI * this.raio * this.raio;
    }
}
