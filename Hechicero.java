public class Hechicero extends Enemigo {
    public Hechicero(String nombre) {
        super(nombre, 70, 8, "Hechicero");
    }

    @Override
    public void usarHabilidad(Combatiente objetivo, Batalla batalla) {
        int cur = 12;
        this.curar(cur);
        batalla.registrar(String.format("%s se cura %d puntos", nombre, cur));
    }
    
    private void lanzarBolaDeFuego(Combatiente objetivo, Batalla batalla) {
        int danio = 12 + rnd.nextInt(8);
        objetivo.recibirDanio(danio);
        batalla.registrar(String.format("%s lanza una bola de fuego sobre %s por %d de daño (fuego)", nombre, objetivo.getNombre(), danio));
        batalla.aplicarBleed(objetivo, 3, 2);
    }

    @Override 
    public void mensajeInicio() { 
        System.out.println(nombre + ": Sentir la magia..."); 
    }
}

