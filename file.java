import java.io.*;

public class file {
    public static void ToWrite(String dato1, String dato2, String dato3){
        try {
            ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("c:/prueba.dat"));
            oos.writeChars(dato1);
            oos.writeChars(dato2);
            oos.writeChars(dato3);
            oos.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public static void Toread(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:/prueba.dat"));
            try{
                FileWriter fw =new FileWriter("c:/prueba.txt");
                String be = ois.readLine();
                String be1 = ois.readLine();
                String be2 = ois.readLine();
                fw.write(be+"\n");
                fw.write(be1+"\n");
                fw.write(be2+"\n");
                fw.close();

            }catch (FileNotFoundException e){
                System.err.println(e);
            };
            ois.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
