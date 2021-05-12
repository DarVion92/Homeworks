package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class File {

    static ArrayList<Object> objectsList;

    public static void Towrite(Product product1, Product product2, Product product3, Product product4, Product product5){
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream( "c:/productos.bin"));
            file.writeObject(product1);
            file.writeChars("\r\n");
            file.writeObject(product2);
            file.writeChars("\r\n");
            file.writeObject(product3);
            file.writeChars("\r\n");
            file.writeObject(product4);
            file.writeChars("\r\n");
            file.writeObject(product5);
            file.writeChars("\r\n");
            file.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static  void Toread(){
        FileInputStream input;
        ObjectInputStream salida;
        LinkedList<Product> list = null;
        List lista = null;

        try {
            input = new FileInputStream("c:/productos.bin");
            salida = new ObjectInputStream(input);


            do {
                Object data = salida.readObject();
                LinkedList<Product> producto = (LinkedList<Product>) data;
                lista.add(producto);
                System.out.println(lista);
            } while (true);
        }catch(Exception e){

        }
    }

    public static void Totxt(Product product){
        try {
            PrintWriter writer = new PrintWriter("c:/productos.txt", "UTF-8");
            writer.println(product.getCode()+", "+product.getDescription()+", "+product.getVendor()+", "+product.getExistences()+", "+product.getSales()+", "+product.isImported()+", "+product.getCash());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println(e);
        }
    }

}
