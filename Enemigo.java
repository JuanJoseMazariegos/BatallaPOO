import java.util.Random;

public abstract class Enemigo extends Combatiente {
    protected String tipo;
    protected Random rnd = new Random();

    public Enemigo(String nombre, int vida, int ataque, String tipo) {
        super(nombre, vida, ataque);
        this.tipo = tipo;
    }

    public abstract void usarHabilidad(Combatiente objetivo, Batalla batalla);

    @Override
    public void tomarTurno(Batalla batalla) {
        if (!isVivo()) return;
        int r = rnd.nextInt(100);
        if (r < 60) {
            Combatiente objetivo = batalla.seleccionarObjetivoJugador();
            if (objetivo != null) {
                int danio = this.ataque + batalla.getBuffAtaque(this);
                // aplicar escudo si tiene
                int shield = batalla.getShieldValue(objetivo);
                int danioFinal = Math.max(0, danio - shield);
                objetivo.recibirDanio(danioFinal);
                batalla.registrar(String.format("%s ataca a %s por %d (escudo %d)", nombre, objetivo.getNombre(), danioFinal, shield));
                batalla.decrementarBuffTurnos(this);
            }
        } else if (r < 90) {
            Combatiente objetivo = batalla.seleccionarObjetivoJugador();
            if (objetivo != null) usarHabilidad(objetivo, batalla);
        } else {
             String[] excusas = {
        nombre + " se distrajo mirando un pajarito y perdió el turno.",
        nombre + " titubea... parece estar analizando la situación.",
        nombre + " tropieza torpemente y pierde la oportunidad de atacar.",
        nombre + " se ríe con arrogancia, dándote una ligera ventaja.",
        nombre + " susurra un hechizo pero se le olvida la última palabra... nada sucede.",
        nombre + " duda por un momento... y deja pasar su turno.",
        nombre + " parece estar provocándote intencionalmente.",
        nombre + " retrocede para recuperar energía, sin atacar esta vez."
             };
    }
}
}


