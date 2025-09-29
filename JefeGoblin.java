public class JefeGoblin extends JefeEnemigo {
    public JefeGoblin(String nombre) {
        super(nombre, 60, 10, "Goblin", 40, 10);
    }

    @Override
    public void usarHabilidad(Combatiente objetivo, Batalla batalla) {
        objetivo.recibirDanio(5);
        batalla.aplicarBleed(objetivo, 4, 3);
        batalla.registrar(String.format("%s usa Veneno mayor sobre %s", nombre, objetivo.getNombre()));
    }

    @Override
    public void usarHabilidadExtra(Combatiente objetivo, Batalla batalla) {
        int d1 = (int)(this.ataque * 0.7);
        int d2 = (int)(this.ataque * 0.7);
        objetivo.recibirDanio(d1);
        objetivo.recibirDanio(d2);
        batalla.registrar(String.format("%s realiza Ataque Múltiple a %s por %d + %d", nombre, objetivo.getNombre(), d1, d2));
    }

    @Override 
    public void mensajeInicio() { 
        System.out.println(nombre + ": ¡Soy el Jefe Goblin!"); 
    }
}
