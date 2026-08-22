package modelo;

public class Audiovisual extends Material {
    private int duracion;
    private String formato;

    public Audiovisual(String codigo, String titulo, int diasBasePrestamo, NivelComplejidad nivel, String rutaImagen,
            int duracion, String formato) {
        super(codigo, titulo, diasBasePrestamo, nivel, rutaImagen);
        this.duracion = duracion;
        this.formato = formato;
    }

    public String getDescripcion() {
        return "Audiovisual: " + titulo + " - " + duracion + " min (" + formato + ")";
    }

    public int calcularDiasPrestamo() {
        int extra=1;
        if(duracion >=120)
            extra=3;

        return diasM + extra + nivelC.getDiasExtra();
    }
}
