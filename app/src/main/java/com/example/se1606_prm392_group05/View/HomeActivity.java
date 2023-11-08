package com.example.se1606_prm392_group05.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.se1606_prm392_group05.Adapter.HomeAdapter;
import com.example.se1606_prm392_group05.Data.ProductsDBHelper;
import com.example.se1606_prm392_group05.Data.ProductsData;
import com.example.se1606_prm392_group05.Model.Cart;
import com.example.se1606_prm392_group05.Model.Product;
//import com.example.se1606_prm392_group05.Model.Utils;
import com.example.se1606_prm392_group05.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    DrawerLayout drawerLayout;
    private List<Product> newProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Assignviews();
        ActionBar();
        ActionViewFlipper();

        String username = getIntent().getStringExtra("USERNAME");
        TextView usernameTextView = findViewById(R.id.nav_login);
        TextView logoutTextView = findViewById(R.id.nav_Logout);

        if (username != null) {
            // Đã đăng nhập, hiển thị tên người dùng và nút "Logout"
            usernameTextView.setText("Welcome, " + username + "!");
            usernameTextView.setOnClickListener(null); // Vô hiệu hóa sự kiện click

            logoutTextView.setVisibility(View.VISIBLE); // Hiển thị nút "Logout"
            logoutTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Thực hiện đăng xuất
                    logout();
                }

                private void logout() {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            // Chưa đăng nhập, hiển thị chữ "Login"
            usernameTextView.setText("Login");

            logoutTextView.setVisibility(View.GONE); // Ẩn nút "Logout"
            usernameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chuyển đến trang Login
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            
        }





        ProductsDBHelper dbHelper = new ProductsDBHelper(this);
        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        newProduct = dbHelper.getAllProducts();

        List<Product> sampleProducts = ProductsData.getSampleProducts();
        // Thêm tất cả sản phẩm mẫu vào danh sách sản phẩm (newProduct)
        newProduct.addAll(sampleProducts);

        HomeAdapter adapter = new HomeAdapter(this, newProduct);
        // Đặt Adapter cho RecyclerView để hiển thị danh sách sản phẩm
        recyclerView.setAdapter(adapter);

        // Tạo một GridLayoutManager với 2 cột cho RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new HomeAdapter(this, newProduct);
        recyclerView.setAdapter(adapter);

    }

    

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void ActionViewFlipper(){
        ArrayList<String> Advertisement = new ArrayList<>();
        Advertisement.add("https://ananas.vn/wp-content/uploads/Mobile_ClearanceSale_Banner.jpg");
        Advertisement.add("https://ananas.vn/wp-content/uploads/Hi-im-Mule_1500x800-Mobile.jpg");
        Advertisement.add("https://bitis.com/cdn/shop/files/MEN_1200x.webp?v=1652154185");
//        Advertisement.add("https://i.imgur.com/zDnJg5x.png");
//        Advertisement.add("https://i.imgur.com/59hVrow.png");
        for (int i=0; i<Advertisement.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(Advertisement.get(i)).into(imageView);
            // Đặt chế độ tỷ lệ của ImageView là FIT_XY để làm cho hình ảnh nằm vừa trong ImageView
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        // Animation sidebar_in: hiệu ứng khi mục mới vào
        Animation animation_sidebar_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sidebar_in_right);
        // Animation sidebar_out: hiệu ứng khi mục hiện tại ra khỏi
        Animation animation_sidebar_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sidebar_out_right);

        // Đặt hoạt ảnh (animation) cho khi một phần tử mới hiển thị trong ViewFlipper
        viewFlipper.setInAnimation(animation_sidebar_in);
        viewFlipper.setOutAnimation(animation_sidebar_out);
    }
    private void Assignviews(){
        // Lấy tham chiếu đến thanh công cụ (toolbar) từ tài nguyên với ID 'toolbarHome'
        toolbar = findViewById(R.id.toolbarHome);
        viewFlipper = findViewById(R.id.viewFlipperHome);
        navigationView = findViewById(R.id.navigationViewHome);
        recyclerView = findViewById(R.id.recyclerviewHome);

        linearLayout1 = findViewById(R.id.linerboderlogo1);
        linearLayout2 = findViewById(R.id.linerboderlogo2);
        drawerLayout = findViewById(R.id.drawerlayoutHome);



        TextView HometextView = findViewById(R.id.nav_home);
        // Lấy tham chiếu đến TextView 'ProductstextView' từ tài nguyên với ID 'nav_products'
        TextView ProductstextView = findViewById(R.id.nav_products);
        TextView CarttextView = findViewById(R.id.nav_cart);
        TextView ChattextView = findViewById(R.id.nav_chat);
        TextView ContacttextView = findViewById(R.id.nav_contact);
        TextView AboutustextView = findViewById(R.id.nav_aboutus);
        TextView LogintextView = findViewById(R.id.nav_login);

        LinearLayout NikeLinearlayout = findViewById(R.id.linerboderlogo1);
        LinearLayout PumaLinearlayout = findViewById(R.id.linerboderlogo2);

        ImageView CartImageview = findViewById(R.id.CartimageView);
        ImageView ChatImageview = findViewById(R.id.ChatimageView);
        ImageView SearchImageview = findViewById(R.id.SearchimageView);

        HometextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });
        ProductstextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });

        CarttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        ChattextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        ContacttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        AboutustextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
        LogintextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        NikeLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AnanasProduct.class);
                startActivity(intent);
            }
        });
        PumaLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BitisProduct.class);
                startActivity(intent);
            }
        });

        // Tạo một đối tượng Intent để chuyển từ HomeActivity sang CartActivity
        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
        Intent intent1 = getIntent();
        CartImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cart> cartList = (List<Cart>) intent1.getSerializableExtra("cartItems");
                // Đặt cartList như một extra Serializable trong intent, để có thể được truyền đến CartActivity.
                intent.putExtra("cartItem", (Serializable) cartList);
                startActivity(intent);
            }
        });
        ChatImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        SearchImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchProduct.class);
                startActivity(intent);
            }
        });
    }


}