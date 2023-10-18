package se1606_prm392_group5.example.se1606_prm392_group05.Model;

public class    Product {
    private int ProductID;
    private String ProductImage;
    private String ProductName;
    private double ProductPrice;

    private String ProductBrand;

    private String ProductSize;
    private String ProductDescribe;
    private String ProductColor;


    public Product(int ProductID, String ProductImage, String ProductName, double ProductPrice, String ProductBrand, String ProductDescribe, String ProductColor, String ProductSize) {
        this.ProductID = ProductID;
        this.ProductImage = ProductImage;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductBrand = ProductBrand;
        this.ProductDescribe = ProductDescribe;
        this.ProductColor = ProductColor;
        this.ProductSize = ProductSize;

    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ID) {
        this.ProductID = ID;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String Image) {
        this.ProductImage = Image;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String Name) {
        this.ProductName = Name;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double Price) {
        this.ProductPrice = Price;
    }

    public String getBrand() {
        return ProductBrand;
    }

    public void setBrand(String brand) {
        this.ProductBrand = brand;
    }

    public String getProductDescribe() {
        return ProductDescribe;
    }

    public void setProductDescribe(String Describe) {
        this.ProductDescribe = Describe;
    }
    public String getProductColor() {
        return ProductColor;
    }

    public void setProductColor(String Color) {
        this.ProductColor = Color;
    }

    public String getProductSize() {
        return ProductSize;
    }

    public void setProductSize(String size) {
        this.ProductSize = size;
    }


}
