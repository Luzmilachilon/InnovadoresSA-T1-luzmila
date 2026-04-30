package innovadoressa;

public class Trabajador {

    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private String regimen;
    private String fondoPension;
    private double sueldoBase;
    private boolean tieneHijos;
    private boolean trabajaNocturno;
    private boolean pactoColectivo;

    public Trabajador(String nombre, String tipoDocumento, String numeroDocumento,
                      String regimen, String fondoPension, double sueldoBase,
                      boolean tieneHijos, boolean trabajaNocturno, boolean pactoColectivo)
            throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
        this.tipoDocumento = tipoDocumento.toUpperCase();
        this.regimen = regimen.toUpperCase();
        this.fondoPension = fondoPension.toUpperCase();
        this.sueldoBase = sueldoBase;
        this.tieneHijos = tieneHijos;
        this.trabajaNocturno = trabajaNocturno;
        this.pactoColectivo = pactoColectivo;

        if (!validarDocumento(tipoDocumento, numeroDocumento)) {
            throw new Exception("DNI debe tener 8 digitos, Residencia Temporal 11 digitos.");
        }
        this.numeroDocumento = numeroDocumento;
    }

    private boolean validarDocumento(String tipo, String numero) {
        if (numero == null || !numero.matches("\\d+")) return false;
        if (tipo.equalsIgnoreCase("DNI")) {
            return numero.length() == 8;
        } else if (tipo.equalsIgnoreCase("RESIDENCIA")) {
            return numero.length() == 11;
        }
        return false;
    }
}