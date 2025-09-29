import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Batalla {
    private List<Combatiente> combatientes = new ArrayList<>();
    private LinkedList<String> registro = new LinkedList<>(); 
    private Map<Combatiente, Integer> buffAtaque = new HashMap<>();
    private Map<Combatiente, Integer> buffTurnos = new HashMap<>();
    private Map<Combatiente, Integer> bleedValor = new HashMap<>();
    private Map<Combatiente, Integer> bleedTurnos = new HashMap<>();
    private Map<Combatiente, Integer> shieldValor = new HashMap<>();
    private Map<Combatiente, Integer> shieldTurnos = new HashMap<>();
    private Random rnd = new Random();
    private Scanner sc = new Scanner(System.in);

    public void agregarCombatiente(Combatiente c) { combatientes.add(c); }

    public void registrar(String s) {
        registro.addFirst(s);
        if (registro.size() > 3) registro.removeLast();
        System.out.println("[Acción] " + s);
    }

    public void mostrarRegistro() {
        System.out.println("--- Últimas acciones ---");
        for (String s : registro) System.out.println(s);
    }

    public void mostrarEstado() {
        System.out.println("--- Estado de combatientes ---");
        for (Combatiente c : combatientes) System.out.println(c);
    }

    public Combatiente seleccionarObjetivoEnemigo() {
        for (Combatiente c : combatientes) {
            if (c instanceof Enemigo && c.isVivo()) return c;
        }
        return null;
    }

    public Combatiente seleccionarObjetivoJugador() {
        for (Combatiente c : combatientes) {
            if (c instanceof Jugador && c.isVivo()) return c;
        }
        return null;
    }

    public Combatiente seleccionarCualquierCombatiente() {
        mostrarEstado();
        System.out.println("Escribe el nombre del objetivo:");
        String n = sc.nextLine();
        for (Combatiente c : combatientes) 
            if (c.getNombre().equalsIgnoreCase(n) && c.isVivo()) return c;
        return null;
    }

    public int getBuffAtaque(Combatiente c) { return buffAtaque.getOrDefault(c, 0); }

    public void aplicarBuffAtaqueTemporal(Combatiente c, int valor, int turnos) {
        buffAtaque.put(c, buffAtaque.getOrDefault(c,0) + valor);
        buffTurnos.put(c, turnos);
    }

    public void decrementarBuffTurnos(Combatiente c) {
        if (buffTurnos.containsKey(c)) {
            int t = buffTurnos.get(c)-1;
            if (t <= 0) {
                buffTurnos.remove(c);
                buffAtaque.remove(c);
            } else buffTurnos.put(c, t);
        }
    }

    public void aplicarBleed(Combatiente c, int valor, int turnos) {
        bleedValor.put(c, bleedValor.getOrDefault(c,0) + valor);
        bleedTurnos.put(c, bleedTurnos.getOrDefault(c,0) + turnos);
    }

    public void aplicarEscudo(Combatiente c, int valor, int turnos) {
        shieldValor.put(c, shieldValor.getOrDefault(c,0) + valor);
        shieldTurnos.put(c, shieldTurnos.getOrDefault(c,0) + turnos);
    }

    public int getShieldValue(Combatiente c) { return shieldValor.getOrDefault(c, 0); }

    public void aplicarEfectosInicioTurno(Combatiente c) {
        if (bleedTurnos.containsKey(c)) {
            int t = bleedTurnos.get(c);
            int v = bleedValor.getOrDefault(c,0);
            c.recibirDanio(v);
            registrar(String.format("%s recibe %d por veneno", c.getNombre(), v));
            t--; if (t<=0) { bleedTurnos.remove(c); bleedValor.remove(c); }
            else bleedTurnos.put(c, t);
        }
        if (shieldTurnos.containsKey(c)) {
            int t = shieldTurnos.get(c)-1;
            if (t<=0) { shieldTurnos.remove(c); shieldValor.remove(c); }
            else shieldTurnos.put(c, t);
        }
    }

    public void ejecutarBatalla() {
        while (!verificarFin()) {
            for (Combatiente c : new ArrayList<>(combatientes)) {
                if (!c.isVivo()) continue;
                aplicarEfectosInicioTurno(c);
                c.tomarTurno(this);
                if (verificarFin()) break;
            }
            mostrarRegistro();
            mostrarEstado();
        }
        boolean jugadorVive = false;
        for (Combatiente c : combatientes) 
            if (c instanceof Jugador && c.isVivo()) jugadorVive = true;
        if (jugadorVive) 
            System.out.println("¡Victoria del jugador!");
        else 
            System.out.println("El jugador ha sido derrotado.");
    }

    public boolean verificarFin() {
        boolean jugadorVivo=false, enemigoVivo=false;
        for (Combatiente c : combatientes) {
            if (c instanceof Jugador && c.isVivo()) jugadorVivo=true;
            if (c instanceof Enemigo && c.isVivo()) enemigoVivo=true;
        }
        return !jugadorVivo || !enemigoVivo;
    }
}
