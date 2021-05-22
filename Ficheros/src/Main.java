import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        Product prod1 = new Product("001","Manzana","Daniel",500,200,true,2550); //precio $12.75
        Product prod2 = new Product("002","Cerveza","Carlos",2400,1200,false,28800); //precio $24
        Product prod3 = new Product("003","Vino","Pedro",500,150,false,30000);//precio $200
        Product prod4 = new Product("004","Bombón","Daniel",5000,3750,true,131250);//precio $35
        Product prod5 = new Product("005","Refresco","María",2400,2099,false,31485);//precio $15
        File.ToWrite(prod1,prod2,prod3,prod4,prod5);
        File.ToRead();

    }


}