public class Guerrero extends Jugador {
    public Guerrero(String nombre) {
        super(nombre, 150, 25, "Guerrero");
    }
    @Override
    public void mensajeInicio() { System.out.println(nombre + ": Soy un Guerrero, preparado para el combate!"); }
    @Override
    public void tomarTurno(Batalla batalla) { super.tomarTurno(batalla); }
}

