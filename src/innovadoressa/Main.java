package innovadoressa;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControladorTrabajador controlador = new ControladorTrabajador();
        int opcion = 0;

        System.out.println("=== SISTEMA DE GESTION - INNOVADORES SA ===");

        do {
            System.out.println("\n1. Registrar trabajador");
            System.out.println("2. Ver trabajadores");
            System.out.println("0. Salir");
            System.out.print("Ingrese opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        registrar(sc, controlador);
                        break;
                    case 2:
                        controlador.listarTrabajadores();
                        break;
                    case 0:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opcion no existe.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Solo numeros por favor.");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static void registrar(Scanner sc, ControladorTrabajador controlador) {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Tipo de documento (1=DNI / 2=RESIDENCIA): ");
            int tdoc = Integer.parseInt(sc.nextLine());
            String tipoDoc = (tdoc == 1) ? "DNI" : "RESIDENCIA";

            System.out.print("Numero de documento: ");
            String nroDoc = sc.nextLine();

            System.out.print("Regimen (1=728 / 2=LOCACION): ");
            int reg = Integer.parseInt(sc.nextLine());
            String regimen = (reg == 1) ? "728" : "LOCACION";

            System.out.println("Fondo de pension:");
            System.out.println("  1) AFP INTEGRA   2) AFP PRIMA");
            System.out.println("  3) AFP HABITAT   4) ONP");
            System.out.print("Opcion: ");
            int fop = Integer.parseInt(sc.nextLine());
            String[] fondos = {"AFP_INTEGRA", "AFP_PRIMA", "AFP_HABITAT", "ONP"};
            if (fop < 1 || fop > 4) {
                System.out.println("Opcion de fondo no valida.");
                return;
            }
            String fondo = fondos[fop - 1];

            System.out.print("Sueldo base: ");
            double sueldo = Double.parseDouble(sc.nextLine());
            if (sueldo <= 0) {
                System.out.println("El sueldo tiene que ser mayor a 0.");
                return;
            }

            System.out.print("Tiene hijos? (s/n): ");
            boolean hijos = sc.nextLine().equalsIgnoreCase("s");

            System.out.print("Trabaja de noche? (s/n): ");
            boolean nocturno = sc.nextLine().equalsIgnoreCase("s");

            System.out.print("Tiene pacto colectivo? (s/n): ");
            boolean pacto = sc.nextLine().equalsIgnoreCase("s");

            Trabajador t = new Trabajador(nombre, tipoDoc, nroDoc, regimen,
                                          fondo, sueldo, hijos, nocturno, pacto);
            controlador.agregarTrabajador(t);

        } catch (NumberFormatException e) {
            System.out.println("Dato numerico incorrecto, intente de nuevo.");
        } catch (Exception e) {
            System.out.println("No se pudo registrar: " + e.getMessage());
        }
    }
}