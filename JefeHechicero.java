public class JefeHechicero extends JefeEnemigo {
    public JefeHechicero(String nombre) {
        super(nombre, 70, 8, "Hechicero", 50, 12);
    }

    @Override
    public void usarHabilidad(Combatiente objetivo, Batalla batalla) {
        super.curar(15);
        batalla.registrar(String.format("%s (jefe) se cura 15.", nombre));
    }

    @Override
    public void usarHabilidadExtra(Combatiente objetivo, Batalla batalla) {
        batalla.aplicarEscudo(this, 8, 2);
        batalla.registrar(String.format("%s invoca Escudo que reduce daño por 2 turnos", nombre));
    }
    
    private void lanzarLlamaArcana(Combatiente objetivo, Batalla batalla) {
        int danio = 18 + rnd.nextInt(8);
        objetivo.recibirDanio(danio);
        batalla.registrar(String.format("%s lanza Llama Arcana sobre %s por %d de daño (fuego)", nombre, objetivo.getNombre(), danio));
        batalla.aplicarBleed(objetivo, 3, 2);
    }

    @Override 
    public void mensajeInicio() { 
        System.out.println(nombre + ": Poder arcano en ti!"); 
    }
}

