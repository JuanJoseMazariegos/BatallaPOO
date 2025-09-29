import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Jugador extends Combatiente {
    protected List<Item> inventario = new ArrayList<>();
    protected String rol;

    public Jugador(String nombre, int vida, int ataque, String rol) {
        super(nombre, vida, ataque);
        this.rol = rol;
    }

    public void agregarItem(Item item) {
        inventario.add(item);
    }

    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("Inventario vacío."); return;
        }
        for (int i=0;i<inventario.size();i++) {
            System.out.println((i+1)+") " + inventario.get(i));
        }
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nTurno de " + nombre);
            System.out.println("Estado:"); batalla.mostrarEstado();
            System.out.println("Elige acción: 1) Atacar  2) Usar ítem  3) Pasar  4) Salir"); 
            String op = sc.nextLine();
            if (op.equals("1")) {
                Combatiente objetivo = batalla.seleccionarObjetivoEnemigo();
                if (objetivo != null) {
                    int danioBase = this.ataque + batalla.getBuffAtaque(this);
                    objetivo.recibirDanio(danioBase);
                    batalla.registrar(String.format("%s ataca a %s por %d", nombre, objetivo.getNombre(), danioBase));
                    batalla.decrementarBuffTurnos(this);
                    break;
                } else {
                    System.out.println("No hay enemigos vivos para atacar."); 
                }
            } else if (op.equals("2")) {
                if (inventario.isEmpty()) { System.out.println("No tienes ítems."); continue; }
                System.out.println("Selecciona ítem:"); mostrarInventario();
                String s = sc.nextLine();
                try {
                    int idx = Integer.parseInt(s)-1;
                    if (idx < 0 || idx >= inventario.size()) { System.out.println("Ítem inválido."); continue; }
                    Item it = inventario.get(idx);
                    System.out.println("Selecciona objetivo: 1) Yo mismo  2) Otro combatiente"); 
                    String t = sc.nextLine();
                    Combatiente objetivo = this;
                    if (t.equals("2")) {
                        objetivo = batalla.seleccionarCualquierCombatiente();
                        if (objetivo == null) { System.out.println("Objetivo inválido."); continue; }
                    }
                    boolean usado = it.usar(objetivo, batalla);
                    if (!usado) { System.out.println("No queda cantidad de ese ítem."); }
                    if (it.getCantidad() <= 0) inventario.remove(idx);
                    break;
                } catch (Exception e) {
                    System.out.println("Entrada inválida."); continue;
                }
            } else if (op.equals("3")) {
                batalla.registrar(nombre + " pasa el turno.");
                batalla.decrementarBuffTurnos(this);
                break;
            } else if (op.equals("4")) {
                System.out.println("Has salido de la batalla."); 
                System.exit(0);
            } else {
                System.out.println("Opción inválida."); 
            }
        }
    }
}

