package modelo;

public enum NivelComplejidad {
<<<<<<< HEAD
    BAJO(1, 0, "Contenido normal"),
=======
    BAJO(1, 0, "Lectura Normal"),
>>>>>>> 584eb2e7507cd556eb3f39567e3f968d42b38fc7
    MEDIO(2, 3, "Requiere conocimiento previo"),
    ALTO(3, 7, "Material especializado");

    private final int orden;
    private final int diaExtras;
    private final String descripcion;

    NivelComplejidad(int orden, int diasE, String descripcion){
        this.orden=orden;
        this.diaExtras=diasE;
        this.descripcion=descripcion;
    }

    public int getOrden(){
        return orden;
    }

    public int getDiasExtra(){
        return diaExtras;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public boolean reqAutorizacion(){
        return this == ALTO;
    }
}
