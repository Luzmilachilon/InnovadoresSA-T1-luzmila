package innovadoressa;

import java.util.ArrayList;

public class ControladorTrabajador {

    private ArrayList<Trabajador> listaTrabajadores;

    public ControladorTrabajador() {
        this.listaTrabajadores = new ArrayList<>();
    }

    public void agregarTrabajador(Trabajador t) {
        listaTrabajadores.add(t);
        System.out.println("Trabajador " + t.getNombre() + " registrado correctamente.");
    }

    public void listarTrabajadores() {
        if (listaTrabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }
        System.out.println("\n=== LISTA DE TRABAJADORES ===");
        for (int i = 0; i < listaTrabajadores.size(); i++) {
            System.out.println("Trabajador #" + (i + 1) + ":" + listaTrabajadores.get(i));
            System.out.println("-----------------------------");
        }
        System.out.println("Total registrados: " + listaTrabajadores.size());
    }
}