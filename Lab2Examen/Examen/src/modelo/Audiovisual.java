package modelo;

public class Audiovisual extends Material {
    private int duracion;
    private String formato;

<<<<<<< HEAD
    public Audiovisual(String codigo, String titulo, int diasBasePrestamo, NivelComplejidad nivel, String rutaI,
            int duracion, String formato) {
        super(codigo, titulo, diasBasePrestamo, nivel, rutaI);
=======
    public Audiovisual(String codigo, String titulo, int diasBasePrestamo, NivelComplejidad nivel, String rutaImagen,
            int duracion, String formato) {
        super(codigo, titulo, diasBasePrestamo, nivel, rutaImagen);
>>>>>>> 584eb2e7507cd556eb3f39567e3f968d42b38fc7
        this.duracion = duracion;
        this.formato = formato;
    }

    public String getDescripcion() {
<<<<<<< HEAD
        return "Contenido audiovisual: " + titulo + " Duracion:" + duracion + " \n"+
                "Formato:"+ formato + ")";
=======
        return "Audiovisual: " + titulo + " - " + duracion + " min (" + formato + ")";
>>>>>>> 584eb2e7507cd556eb3f39567e3f968d42b38fc7
    }

    public int calcularDiasPrestamo() {
        int extra=1;
        if(duracion >=120)
            extra=3;

        return diasM + extra + nivelC.getDiasExtra();
    }
}
