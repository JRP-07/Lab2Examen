package modelo;

public class Libro extends Material{
    private String autor;
    private int nPaginas;
    private String ISBN;

    public Libro(String codigo, String titulo, int dia, NivelComplejidad nivel, String ruta, String autor, int numeroP, String ISBN){
        super(codigo, titulo, dia, nivel, ruta);
        this.autor=autor;
        this.nPaginas=numeroP;
        this.ISBN=ISBN;
    }
    

    public String getDescripcion(){
        return "Libro: "+ titulo + " del autor: " + autor + "\n" +
                "Numero de Paginas: "+ nPaginas +" ISBN:" + ISBN + ""; 
    }

    public int calcularDiasPrestamo(){
        int extra = 0;
        if(nPaginas>300){
            extra=5;
        }
        return diasM + extra + nivelC.getDiasExtra();
    }
}
