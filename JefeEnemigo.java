public abstract class JefeEnemigo extends Enemigo {
    protected int bonusVida;
    protected int bonusAtaque;

    public JefeEnemigo(String nombre, int vida, int ataque, String tipo, int bonusVida, int bonusAtaque) {
        super(nombre, vida + bonusVida, ataque + bonusAtaque, tipo + " Jefe");
        this.bonusVida = bonusVida;
        this.bonusAtaque = bonusAtaque;
    }

    public abstract void usarHabilidadExtra(Combatiente objetivo, Batalla batalla);

    @Override
    public void tomarTurno(Batalla batalla) {
        if (!isVivo()) return;
        int r = rnd.nextInt(100);
        if (r < 50) {
            Combatiente objetivo = batalla.seleccionarObjetivoJugador();
            if (objetivo != null) usarHabilidadExtra(objetivo, batalla);
        } else {
            super.tomarTurno(batalla);
        }
    }
}

