package logica;

public class Disco extends Elemento{
    private float aSize;
    public Disco(String pNombre, float pSize) {
        super(pNombre);
        this.aSize = pSize;
    }

    public float getaSize() {
        return aSize;
    }

    public void setaSize(float aSize) {
        this.aSize = aSize;
    }
}
