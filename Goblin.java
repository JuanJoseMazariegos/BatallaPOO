public class Goblin extends Enemigo {
    public Goblin(String nombre) {
        super(nombre, 60, 10, "Goblin");
    }

    @Override
    public void usarHabilidad(Combatiente objetivo, Batalla batalla) {
        objetivo.recibirDanio(5);
        batalla.aplicarBleed(objetivo, 3, 2);
        batalla.registrar(String.format("%s usa Veneno sobre %s (3 dmg x2)", nombre, objetivo.getNombre()));
    }

    @Override 
    public void mensajeInicio() { 
        System.out.println(nombre + ": Gruñido goblin!"); 
    }
}
