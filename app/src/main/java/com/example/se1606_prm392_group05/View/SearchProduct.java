package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.se1606_prm392_group05.Adapter.SearchAdapter;
import com.example.se1606_prm392_group05.Data.ProductsData;
import com.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;

import java.util.ArrayList;
import java.util.List;

public class SearchProduct extends AppCompatActivity {
    private EditText searchEditText;
    private RecyclerView searchRecyclerView;
    private SearchAdapter searchAdapter;
    private List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        searchEditText = findViewById(R.id.searchEditText);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);

        productList = ProductsData.getSampleProducts();
        searchAdapter = new SearchAdapter(this,productList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.setAdapter(searchAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchTerm = s.toString();
                performSearch(searchTerm);
            }
        });
    }

    private void performSearch(String searchTerm) {
        List<Product> searchResults = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(product);
            }
        }

        searchAdapter.setSearchResults(searchResults);
        searchAdapter.notifyDataSetChanged();
    }
}