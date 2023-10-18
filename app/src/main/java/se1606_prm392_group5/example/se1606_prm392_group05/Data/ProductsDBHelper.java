package se1606_prm392_group5.example.se1606_prm392_group05.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import se1606_prm392_group5.example.se1606_prm392_group05.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "ProductID";
    private static final String COLUMN_IMAGE = "ProductImage";
    private static final String COLUMN_NAME = "ProductName";
    private static final String COLUMN_PRICE = "ProductPrice";
    private static final String COLUMN_BRAND = "ProductBrand";

    private static final String COLUMN_SIZE = "ProductSize";
    private static final String COLUMN_COLOR = "ProductBrand";
    private static final String COLUMN_DESCRIBE = "ProductDescribe";


    public ProductsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PRICE + " REAL," +
                COLUMN_DESCRIBE + " TEXT," +
                COLUMN_COLOR + " TEXT," +
                COLUMN_SIZE + " TEXT" +

                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, product.getProductImage());
        values.put(COLUMN_NAME, product.getProductName());
        values.put(COLUMN_PRICE, product.getProductPrice());
        values.put(COLUMN_BRAND, product.getBrand());
        values.put(COLUMN_DESCRIBE, product.getProductDescribe());
        values.put(COLUMN_COLOR, product.getProductColor());
        values.put(COLUMN_SIZE, product.getProductSize());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid, * FROM " + TABLE_PRODUCTS, null);
        if (cursor.moveToFirst()) {
            do {
                int ProductID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String ProductImage = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
                String ProductName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                double ProductPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE));
                String ProductBrand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BRAND));
                String ProductDescribe = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIBE));
                String ProductColor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR));
                String ProductSize = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SIZE));

                Product product = new Product(ProductID,ProductImage, ProductName, ProductPrice,ProductBrand,ProductDescribe, ProductColor, ProductSize   );
                product.setProductID(ProductID);
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }

    public Product getProductById(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        if (cursor.moveToFirst()) {
            // Retrieve product details from the cursor
            int ProductID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String ProductImage = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
            String ProductName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            double ProductPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE));
            String ProductBrand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BRAND));
            String ProductDescribe = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIBE));
            String ProductColor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOR));
            String ProductSize = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SIZE));

            // Create and return the product object
            Product product = new Product(ProductID, ProductImage, ProductName, ProductPrice, ProductBrand, ProductDescribe, ProductColor, ProductSize);
            product.setProductID(ProductID);
            return product;
        }
        cursor.close();
        db.close();

        return null; // Return null if product not found
    }
}
