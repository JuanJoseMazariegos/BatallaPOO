public class Item {
    public enum Tipo { CURA, FUERZA }
    private String nombre;
    private Tipo tipo;
    private int valor;
    private int cantidad;

    public Item(String nombre, Tipo tipo, int valor, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public Tipo getTipo() { return tipo; }
    public int getValor() { return valor; }
    public int getCantidad() { return cantidad; }

    public boolean usar(Combatiente objetivo, Batalla batalla) {
        if (cantidad <= 0) return false;
        if (tipo == Tipo.CURA) {
            objetivo.curar(valor);
            batalla.registrar(String.format("%s usa %s y cura %d a %s", objetivo.getNombre(), nombre, valor, objetivo.getNombre()));
        } else if (tipo == Tipo.FUERZA) {
            batalla.aplicarBuffAtaqueTemporal(objetivo, valor, 1); // 1 turno
            batalla.registrar(String.format("%s usa %s y gana +%d ataque por 1 turno", objetivo.getNombre(), nombre, valor));
        }
        cantidad--;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s x%d (%s %d)", nombre, cantidad, tipo, valor);
    }
}
