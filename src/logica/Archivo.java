package logica;

public class Archivo extends Elemento {
    private String aExtension, aFecha;
    private float aSize;

    public Archivo(String pNombre, String pFecha, String pExtension, float pSize) {
        super(pNombre);
        this.aFecha = pFecha;
        this.aExtension = pExtension;
        this.aSize = pSize;
    }
    public Archivo(){
        super();
    }

    public String getaFecha() {
        return aFecha;
    }

    public void setaFecha(String aFecha) {
        this.aFecha = aFecha;
    }

    public String getaExtension() {
        return aExtension;
    }

    public void setaExtension(String aExtension) {
        this.aExtension = aExtension;
    }

    public float getaSize() {
        return aSize;
    }

    public void setaSize(float aSize) {
        this.aSize = aSize;
    }
}
