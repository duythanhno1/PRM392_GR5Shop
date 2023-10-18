package se1606_prm392_group5.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import se1606_prm392_group5.example.se1606_prm392_group05.Adapter.NikeProductAdapter;
import se1606_prm392_group5.example.se1606_prm392_group05.Data.ProductsData;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PumaProduct extends AppCompatActivity {
    private RecyclerView recyclerViewPuma;
    private NikeProductAdapter pumaProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puma_product);

        recyclerViewPuma = findViewById(R.id.recyclerviewPuma);

        // Lấy danh sách sản phẩm của thương hiệu Nike
        List<Product> pumaProducts = getNikeProducts();

        // Khởi tạo nikeProductAdapter với danh sách sản phẩm của thương hiệu Nike
        pumaProductAdapter = new NikeProductAdapter(this,pumaProducts);

        // Thiết lập layout manager cho RecyclerView (GridLayoutManager với spanCount là 2)
        recyclerViewPuma.setLayoutManager(new GridLayoutManager(this, 2));

        // Thiết lập adapter cho RecyclerView với danh sách sản phẩm của thương hiệu Nike
        recyclerViewPuma.setAdapter(pumaProductAdapter);

        ImageView SearchImageview = findViewById(R.id.SearchimageView);
        SearchImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PumaProduct.this, SearchProduct.class);
                startActivity(intent);
            }
        });

        ImageView back = findViewById(R.id.backhome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PumaProduct.this, HomeActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        ImageView sortImageView = findViewById(R.id.sortImageView);
        sortImageView.setOnClickListener(new View.OnClickListener() {
            boolean ascendingOrder = true;

            @Override
            public void onClick(View v) {
                if (ascendingOrder) {
                    Collections.sort(pumaProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return p1.getProductName().compareToIgnoreCase(p2.getProductName());
                        }
                    });
                    ascendingOrder = false;
                    sortImageView.setRotation(180); // Đổi hướng của biểu tượng khi sắp xếp giảm dần
                } else {
                    Collections.sort(pumaProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return p2.getProductName().compareToIgnoreCase(p1.getProductName());
                        }
                    });
                    ascendingOrder = true;
                    sortImageView.setRotation(0); // Đặt lại hướng ban đầu của biểu tượng khi sắp xếp tăng dần
                }

                pumaProductAdapter.notifyDataSetChanged();
            }
        });
        ImageView sortImageView2 = findViewById(R.id.sortImageView2);
        sortImageView2.setOnClickListener(new View.OnClickListener() {
            boolean ascendingOrder = true;

            @Override
            public void onClick(View v) {
                if (ascendingOrder) {
                    Collections.sort(pumaProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return Double.compare(p1.getProductPrice(), p2.getProductPrice());
                        }
                    });
                    ascendingOrder = false;
                    sortImageView2.setRotation(180); // Đổi hướng của biểu tượng khi sắp xếp giảm dần
                } else {
                    Collections.sort(pumaProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return Double.compare(p2.getProductPrice(), p1.getProductPrice());
                        }
                    });
                    ascendingOrder = true;
                    sortImageView2.setRotation(0); // Đặt lại hướng ban đầu của biểu tượng khi sắp xếp tăng dần
                }

                pumaProductAdapter.notifyDataSetChanged();
            }
        });
    }
    private List<Product> getNikeProducts() {
        List<Product> pumaProducts = new ArrayList<>();

        // Lấy danh sách sản phẩm từ HomeData
        List<Product> allProducts = ProductsData.getSampleProducts();

        // Lọc danh sách sản phẩm của thương hiệu Nike
        for (Product product : allProducts) {
            if (product.getBrand().equals("Puma")) {
                pumaProducts.add(product);
            }
        }

        return pumaProducts;
    }
}