package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.se1606_prm392_group05.Adapter.ListProductAdapter;
import com.example.se1606_prm392_group05.Data.ProductsData;
import com.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    private List<Product> productList;
    private boolean isAscendingOrder = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        // Gọi phương thức để lấy danh sách sản phẩm từ HomeData
        productList = ProductsData.getSampleProducts();

        // Gọi phương thức để hiển thị danh sách sản phẩm trong RecyclerView
        setupRecyclerView();
        ImageView SearchImageview = findViewById(R.id.SearchimageView);
        SearchImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProductActivity.this, SearchProduct.class); // Thiết lập sự kiện click cho button Back để quay lại HomeActivity
                startActivity(intent);
            }
        });

        ImageView back = findViewById(R.id.backhome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProductActivity.this, HomeActivity.class); // Thiết lập sự kiện click cho button Search để mở SearchProductActivity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerviewProduct); // Khởi tạo adapter và truyền vào danh sách sản phẩm
        ListProductAdapter adapter = new ListProductAdapter(this,productList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Khởi tạo LayoutManager cho RecyclerView
        recyclerView.setAdapter(adapter); // Set adapter và layout manager cho RecyclerView

        ImageView sortImageView = findViewById(R.id.sortImageView);
        sortImageView.setOnClickListener(new View.OnClickListener() {
            boolean ascendingOrder = true;

            @Override
            public void onClick(View v) {
                if (ascendingOrder) {
                    Collections.sort(productList, new Comparator<Product>() { //Sử dụng Collections.sort() để sắp xếp lại danh sách theo tên
                        @Override
                        public int compare(Product p1, Product p2) {
                            return p1.getProductName().compareToIgnoreCase(p2.getProductName());
                        }
                    });
                    ascendingOrder = false;
                    sortImageView.setRotation(180); // Đổi hướng của biểu tượng khi sắp xếp giảm dần
                } else {
                    Collections.sort(productList, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return p2.getProductName().compareToIgnoreCase(p1.getProductName());
                        }
                    });
                    ascendingOrder = true;
                    sortImageView.setRotation(0); // Đặt lại hướng ban đầu của biểu tượng khi sắp xếp tăng dần
                }
                adapter.notifyDataSetChanged(); //Gọi notifyDataSetChanged() để update lại RecyclerView
            }
        });

        ImageView sortImageView2 = findViewById(R.id.sortImageView2);
        sortImageView2.setOnClickListener(new View.OnClickListener() {
            boolean ascendingOrder = true;

            @Override
            public void onClick(View v) {
                if (ascendingOrder) {
                    Collections.sort(productList, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return Double.compare(p1.getProductPrice(), p2.getProductPrice());
                        }
                    });
                    ascendingOrder = false;
                    sortImageView2.setRotation(180); // Đổi hướng của biểu tượng khi sắp xếp giảm dần
                } else {
                    Collections.sort(productList, new Comparator<Product>() {
                        @Override
                        public int compare(Product p1, Product p2) {
                            return Double.compare(p2.getProductPrice(), p1.getProductPrice());
                        }
                    });
                    ascendingOrder = true;
                    sortImageView2.setRotation(0); // Đặt lại hướng ban đầu của biểu tượng khi sắp xếp tăng dần
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}