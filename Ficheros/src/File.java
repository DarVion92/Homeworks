import java.io.*;
import java.util.ArrayList;

public class File {

    public static void ToWrite(Product prod1,Product prod2,Product prod3,Product prod4,Product prod5){
        try {
            ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("c:/prueba.dat"));
            oos.writeObject(prod1);
            oos.writeObject(prod2);
            oos.writeObject(prod3);
            oos.writeObject(prod4);
            oos.writeObject(prod5);
            oos.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public static void ToRead() throws IOException {
        ObjectInputStream ois = null;
        FileWriter fw = new FileWriter("c:/producto.txt");
        fw.write("Código ; Descripción ; Cajero ; Existencias ; Ventas ; Importado ; Efectivo\n");
        try {
            ois = new ObjectInputStream(new FileInputStream("c:/prueba.dat"));
            while (true) {
                Product prod = (Product) ois.readObject();
                fw.write(prod.getCode());
                fw.write("    ; ");
                fw.write(prod.getDescription());
                fw.write(" ; ");
                fw.write(prod.getVendor());
                fw.write(" ; ");
                fw.write(String.valueOf(prod.getExistences()));
                fw.write(" ; ");
                fw.write(String.valueOf(prod.getSales()));
                fw.write(" ; ");
                fw.write(String.valueOf(prod.isImported()));
                fw.write(" ; ");
                fw.write(String.valueOf(prod.getCash()));
                fw.write("\n");

            }
            } catch (IOException | ClassNotFoundException e) {
           System.err.println(e);
            }finally {
                fw.close();
                ois.close();
        }





    }
}
