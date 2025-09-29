public class Explorador extends Jugador {
    public Explorador(String nombre) {
        super(nombre, 100, 15, "Explorador");
    }
    @Override
    public void mensajeInicio() { System.out.println(nombre + ": Soy un Explorador, listo para usar mis ítems!"); }
    @Override
    public void tomarTurno(Batalla batalla) { super.tomarTurno(batalla); }
}
