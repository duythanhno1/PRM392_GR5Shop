package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.se1606_prm392_group05.Adapter.AnanasProductAdapter;
import com.example.se1606_prm392_group05.Data.ProductsData;
import com.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnanasProduct extends AppCompatActivity {
    private RecyclerView recyclerViewNike;
    private AnanasProductAdapter ananasProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ananas_product);

        recyclerViewNike = findViewById(R.id.recyclerviewNike);

        // Lấy danh sách sản phẩm của thương hiệu Nike
        List<Product> nikeProducts = getNikeProducts();

        // Khởi tạo nikeProductAdapter với danh sách sản phẩm của thương hiệu Nike
        ananasProductAdapter = new AnanasProductAdapter(this,nikeProducts);

        // Thiết lập layout manager cho RecyclerView (GridLayoutManager với spanCount là 2)
        recyclerViewNike.setLayoutManager(new GridLayoutManager(this, 2));

        // Thiết lập adapter cho RecyclerView với danh sách sản phẩm của thương hiệu Nike
        recyclerViewNike.setAdapter(ananasProductAdapter);

        ImageView SearchImageview = findViewById(R.id.SearchimageView);
        SearchImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnanasProduct.this, SearchProduct.class);
                startActivity(intent);
            }
        });

        ImageView back = findViewById(R.id.backhome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnanasProduct.this, HomeActivity.class);
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
                    Collections.sort(nikeProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return p1.getProductName().compareToIgnoreCase(p2.getProductName());
                        }
                    });
                    ascendingOrder = false;
                    sortImageView.setRotation(180); // Đổi hướng của biểu tượng khi sắp xếp giảm dần
                } else {
                    Collections.sort(nikeProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return p2.getProductName().compareToIgnoreCase(p1.getProductName());
                        }
                    });
                    ascendingOrder = true;
                    sortImageView.setRotation(0); // Đặt lại hướng ban đầu của biểu tượng khi sắp xếp tăng dần
                }

                ananasProductAdapter.notifyDataSetChanged();
            }
        });
        ImageView sortImageView2 = findViewById(R.id.sortImageView2);
        sortImageView2.setOnClickListener(new View.OnClickListener() {
            boolean ascendingOrder = true;

            @Override
            public void onClick(View v) {
                if (ascendingOrder) {
                    Collections.sort(nikeProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return Double.compare(p1.getProductPrice(), p2.getProductPrice());
                        }
                    });
                    ascendingOrder = false;
                    sortImageView2.setRotation(180); // Đổi hướng của biểu tượng khi sắp xếp giảm dần
                } else {
                    Collections.sort(nikeProducts, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return Double.compare(p2.getProductPrice(), p1.getProductPrice());
                        }
                    });
                    ascendingOrder = true;
                    sortImageView2.setRotation(0); // Đặt lại hướng ban đầu của biểu tượng khi sắp xếp tăng dần
                }

                ananasProductAdapter.notifyDataSetChanged();
            }
        });
    }
    private List<Product> getNikeProducts() {
        List<Product> nikeProducts = new ArrayList<>();

        // Lấy danh sách sản phẩm từ HomeData
        List<Product> allProducts = ProductsData.getSampleProducts();

        // Lọc danh sách sản phẩm của thương hiệu Nike
        for (Product product : allProducts) {
            if (product.getBrand().equals("Nike")) {
                nikeProducts.add(product);
            }
        }

        return nikeProducts;
    }
}