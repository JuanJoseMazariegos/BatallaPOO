public abstract class Combatiente {
    protected String nombre;
    protected int maxVida;
    protected int vida;
    protected int ataque;

    public Combatiente(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.maxVida = vida;
        this.vida = vida;
        this.ataque = ataque;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public boolean isVivo() { return vida > 0; }

    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > maxVida) vida = maxVida;
    }

    public void atacar(Combatiente objetivo) {
        objetivo.recibirDanio(this.ataque);
    }

    public abstract void tomarTurno(Batalla batalla);

    public void mensajeInicio() {
        System.out.println(nombre + ": ¡Listo para la batalla!"); 
    }
    public void mensajeDerrota() {
        System.out.println(nombre + ": He caído..."); 
    }
    public void mensajeVictoria() {
        System.out.println(nombre + ": He ganado la batalla!"); 
    }
    @Override
    public String toString() {
        return String.format("%s (HP: %d/%d, ATK: %d)", nombre, vida, maxVida, ataque);
    }
}

