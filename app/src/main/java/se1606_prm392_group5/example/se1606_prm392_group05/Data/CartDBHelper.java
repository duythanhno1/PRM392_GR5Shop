package se1606_prm392_group5.example.se1606_prm392_group05.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import se1606_prm392_group5.example.se1606_prm392_group05.Model.Cart;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cart.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CART = "cart";
    private static final String COLUMN_ID = "CartId";
    private static final String COLUMN_PRODUCT_ID = "ProductId";
    private static final String COLUMN_QUANTITY = "Quantity";

    private Context context;

    public CartDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_CART + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCT_ID + " INTEGER," +
                COLUMN_QUANTITY + " INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public void insertCart(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, cart.getProduct().getProductID());
        values.put(COLUMN_QUANTITY, cart.getQuantity());
        db.insert(TABLE_CART, null, values);
        db.close();
    }

    public List<Cart> getAllCarts() {
        List<Cart> cartList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART, null);
        if (cursor.moveToFirst()) {
            do {
                int cartId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                int productId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));

                // Retrieve the corresponding product for the cart
                ProductsDBHelper productDBHelper = new ProductsDBHelper(context);
                Product product = productDBHelper.getProductById(productId);

                Cart cart = new Cart(cartId, product, quantity);
                cartList.add(cart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartList;
    }

    public void updateCart(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, cart.getProduct().getProductID());
        values.put(COLUMN_QUANTITY, cart.getQuantity());
        db.update(TABLE_CART, values, COLUMN_ID + " = ?", new String[]{String.valueOf(cart.getCartId())});
        db.close();
    }

    public void deleteCart(int cartId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_ID + " = ?", new String[]{String.valueOf(cartId)});
        db.close();
    }

    public int getCartCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_CART, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }
}