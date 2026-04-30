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

    public double calcularPension() {
        switch (fondoPension) {
            case "AFP_INTEGRA": return sueldoBase * 0.121;
            case "AFP_PRIMA":   return sueldoBase * 0.125;
            case "AFP_HABITAT": return sueldoBase * 0.127;
            case "ONP":         return sueldoBase * 0.13;
            default:            return 0;
        }
    }

    public double calcularBonos() {
        double total = 0;
        if (pactoColectivo && regimen.equals("728")) {
            total += sueldoBase * 0.10;
        }
        if (tieneHijos) {
            total += 102.50;
        }
        if (trabajaNocturno) {
            total += sueldoBase * 0.35;
        }
        return total;
    }

    public double calcularSueldoNeto() {
        return sueldoBase + calcularBonos() - calcularPension();
    }

    public String getNombre()          { return nombre; }
    public String getTipoDocumento()   { return tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public String getRegimen()         { return regimen; }
    public String getFondoPension()    { return fondoPension; }
    public double getSueldoBase()      { return sueldoBase; }

    @Override
    public String toString() {
        return "\n  Nombre         : " + nombre +
               "\n  Tipo Doc.      : " + tipoDocumento +
               "\n  Nro. Documento : " + numeroDocumento +
               "\n  Regimen        : " + regimen +
               "\n  Fondo Pension  : " + fondoPension +
               "\n  Sueldo Base    : S/ " + String.format("%.2f", sueldoBase) +
               "\n  Bonificaciones : S/ " + String.format("%.2f", calcularBonos()) +
               "\n  Descuento AFP  : S/ " + String.format("%.2f", calcularPension()) +
               "\n  Sueldo Neto    : S/ " + String.format("%.2f", calcularSueldoNeto());
    }
}