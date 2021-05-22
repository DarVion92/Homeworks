package logica;
import logica.tree.*;

public class Directorio {
    class Suma {
        private float suma;

        Suma() {
            this.suma = 0;
        }

        public float getSuma() {
            return suma;
        }

        public void setSuma(float suma) {
            this.suma = suma;
        }

        void Incremento(float inc) {
            suma += inc;
        }
    }

    private IGeneralTree<Elemento> arbol;

    public Directorio() {
        arbol = new BinaryTree<Elemento>();

    }

    public float EspacioUsado() {
        Daemon<Elemento> SumaSize = new Daemon<Elemento>() {
            public void AccionparaCada(TreeNode<Elemento> cursor, Object referencia) {
                Suma sum = (Suma) referencia;
                if (cursor.getInfo() instanceof Archivo)
                    sum.Incremento(((Archivo) cursor.getInfo()).getaSize());
            }
        };
        Suma sum = new Suma();
        arbol.preOrderForEach(arbol.getRoot(), SumaSize, sum);
        return sum.getSuma();
    }

    public float EspacioLibre() {
        if (!arbol.isEmpty()) {
            float Sizetotal = ((Disco) arbol.getRoot().getInfo()).getaSize();
            return Sizetotal - EspacioUsado();
        }
        return 0;
    }

    public String MayorArchivo(String pCarpeta) {
        Archivo temp = new Archivo();
        temp.setaNombre("No Existe");
        temp.setaSize(-1);

        Daemon<Elemento> DemEncCarp = new Daemon<Elemento>() {
            public boolean AccionAntesde(TreeNode<Elemento> cursor, Object referencia) {
                return cursor.getInfo().getaNombre().equals((String) referencia);
            }
        };

        TreeNode<Elemento> folderNode = arbol.preOrderFirstThat(arbol.getRoot(), DemEncCarp, pCarpeta);
        if (folderNode != null) {
            Daemon<Elemento> BiggestSize = new Daemon<Elemento>() {
                public void actionForEach(TreeNode<Elemento> cursor, Object referencia) {
                    Archivo aux = (Archivo) referencia;
                    if (cursor.getInfo() instanceof Archivo && ((Archivo) cursor.getInfo()).getaSize() > aux.getaSize())
                        referencia = cursor.getInfo();
                }
            };
            arbol.preOrderForEach(folderNode, BiggestSize, temp);
        }
        return temp.getaNombre();
    }
}