package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.se1606_prm392_group05.Adapter.CartAdapter;
import com.example.se1606_prm392_group05.Model.Cart;
import com.example.se1606_prm392_group05.R;

import java.util.List;


public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCart.setLayoutManager(layoutManager);


        Intent intent = getIntent();
        List<Cart> cartList = (List<Cart>) intent.getSerializableExtra("cartItem");
        cartAdapter = new CartAdapter(this, cartList);

        recyclerViewCart.setAdapter(cartAdapter);

    }
}