import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al simulador de Batalla (consola). Escribe el nombre de tu personaje:");
        String nombre = sc.nextLine();
        System.out.println("Elige rol: 1) Guerrero  2) Explorador");
        String r = sc.nextLine();
        Jugador player;
        if (r.equals("1")) player = new Guerrero(nombre);
        else player = new Explorador(nombre);
        player.mensajeInicio();

        if (player instanceof Guerrero) {
            player.agregarItem(new Item("Poción menor", Item.Tipo.CURA, 40, 2));
            player.agregarItem(new Item("Brebaje de fuerza", Item.Tipo.FUERZA, 10, 1));
        } else {
            player.agregarItem(new Item("Poción menor", Item.Tipo.CURA, 40, 4));
            player.agregarItem(new Item("Elixir de fuerza", Item.Tipo.FUERZA, 15, 2));
        }

        Batalla batalla = new Batalla();
        batalla.agregarCombatiente(player);

        Random rnd = new Random();
        int n = 1 + rnd.nextInt(3);
        for (int i=0;i<n;i++) {
            int t = rnd.nextInt(4);
            Enemigo e;
            if (t==0) e = new Goblin("Goblin_"+(i+1));
            else if (t==1) e = new Hechicero("Hechicero_"+(i+1));
            else if (t==2) e = new JefeGoblin("JefeGoblin_"+(i+1));
            else e = new JefeHechicero("JefeHechicero_"+(i+1));
            e.mensajeInicio();
            batalla.agregarCombatiente(e);
        }
        System.out.println("Se han generado " + n + " enemigos. Iniciando batalla...");
        batalla.ejecutarBatalla();
    }
}

