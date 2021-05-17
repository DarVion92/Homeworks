package logic;
import java.io.Serializable;

public class Product implements Serializable {
    private String code, description, vendor;
    private int existences, sales;
    private boolean imported;
    private float cash;

    public Product(String code, String description, String vendor, int existences, int sales, boolean imported, float cash) {
        this.code = code;
        this.description = description;
        this.vendor = vendor;
        this.existences = existences;
        this.sales = sales;
        this.imported = imported;
        this.cash = cash;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getExistences() {
        return existences;
    }

    public void setExistences(int existences) {
        this.existences = existences;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }
}
