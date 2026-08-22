package modelo;

public class Revista extends Material{
    private int numEdicion;
    private Periodicidad periodo;

    public Revista(String codigo, String titulo, int dias, NivelComplejidad nivel, String rutaI, int numeroEdicion, Periodicidad periodo) {
        super(codigo, titulo, dias, nivel, rutaI);
        this.numEdicion = numeroEdicion;
        this.periodo = periodo;
    }

    public String getDescripcion(){
        return "Revista: " + titulo + " Edicion:" + numEdicion + "\n" +
                "Periodicidad:" + periodo;
    }

    public int calcularDiasPrestamo() {
        int extra=0;
        if(periodo==Periodicidad.SEMANAL)
            extra=2;
        if(periodo==Periodicidad.MENSUAL)
            extra=5;
        if(periodo==Periodicidad.ANUAL)
            extra=10;

        return diasM + extra + nivelC.getDiasExtra();
    }
}
