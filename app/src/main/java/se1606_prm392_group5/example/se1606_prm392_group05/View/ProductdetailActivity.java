package se1606_prm392_group5.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import se1606_prm392_group5.example.se1606_prm392_group05.Data.CartDBHelper;
import se1606_prm392_group5.example.se1606_prm392_group05.Data.ProductsData;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.Cart;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductdetailActivity extends AppCompatActivity {
    private ImageView imageViewProduct;
    private TextView txtPdName;
    private TextView txtPdColor;
    private TextView txtPdSize;
    private TextView txtPdPrice;
    private TextView txtPdDescribe;
    private Spinner spinnerQuantity;
    private NotificationBadge notificationBadge;
    private CartDBHelper cartDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        imageViewProduct = findViewById(R.id.imageViewProduct);
        txtPdName = findViewById(R.id.txtPdName);
        txtPdColor = findViewById(R.id.txtPdColor);
        txtPdSize = findViewById(R.id.txtPdSize);
        txtPdPrice = findViewById(R.id.txtPdPrice);
        txtPdDescribe = findViewById(R.id.txtPdDescribe);


        notificationBadge = findViewById(R.id.menu_sl);
        List<Product> productList = ProductsData.getSampleProducts();
        // Nhận thông tin sản phẩm từ intent
        Intent intent = getIntent();
        if (intent != null) {
            int productID = intent.getIntExtra("productID", 0);
            String productImage = intent.getStringExtra("productImage");
            String productName = intent.getStringExtra("productName");
            String productPriceStr = intent.getStringExtra("productPrice");
            double productPrice = Double.parseDouble(productPriceStr);
            String productBrand = intent.getStringExtra("productBrand");
            String productDescribe = intent.getStringExtra("productDescribe");
            String productColor = intent.getStringExtra("productColor");
            String productSize = intent.getStringExtra("productSize");

            // Tạo đối tượng Product với các thông tin nhận được
            Product product = new Product(productID, productImage, productName, productPrice, productBrand, productDescribe, productColor, productSize);
            // Hiển thị thông tin sản phẩm
            Picasso.get().load(productImage).into(imageViewProduct);
            txtPdName.setText(productName);
            txtPdColor.setText(productColor);
            txtPdSize.setText(productSize);
            txtPdPrice.setText("$" + String.format("%.2f", productPrice));
            txtPdDescribe.setText(productDescribe);

            ImageView back = findViewById(R.id.backhome);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductdetailActivity.this, HomeActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            Button addToCartButton = findViewById(R.id.addtocart);
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Add the product to the cart
                    Cart cartItem = new Cart(0, product, 1); // Assuming quantity is 1 for simplicity
                    CartDBHelper cartDBHelper = new CartDBHelper(ProductdetailActivity.this);
                    cartDBHelper.insertCart(cartItem);

                    // Update the notification badge
                    int cartItemCount = cartDBHelper.getCartCount();
                    notificationBadge.setNumber(cartItemCount);

                    // Display a toast message
                    Toast.makeText(ProductdetailActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView cartImageView = findViewById(R.id.CartimageView);
            cartImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the CartActivity
                    Intent intent = new Intent(ProductdetailActivity.this, CartActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}