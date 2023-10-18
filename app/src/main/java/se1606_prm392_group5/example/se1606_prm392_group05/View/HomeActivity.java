package se1606_prm392_group5.example.se1606_prm392_group05.View;

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

import se1606_prm392_group5.example.se1606_prm392_group05.Adapter.ChatAdapter;
import se1606_prm392_group5.example.se1606_prm392_group05.Adapter.HomeAdapter;
import se1606_prm392_group5.example.se1606_prm392_group05.Data.ProductsDBHelper;
import se1606_prm392_group5.example.se1606_prm392_group05.Data.ProductsData;
import se1606_prm392_group5.example.se1606_prm392_group05.Model.Product;
//import com.example.se1603_prm392_shoestoreapp_group05.Model.Utils;
import com.example.se1606_prm392_group05.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

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
        newProduct = dbHelper.getAllProducts();

        List<Product> sampleProducts = ProductsData.getSampleProducts();
        newProduct.addAll(sampleProducts);

        HomeAdapter adapter = new HomeAdapter(this, newProduct);
        recyclerView.setAdapter(adapter);

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
        Advertisement.add("https://ananas.vn/wp-content/uploads/Desktop_Homepage_Banner.jpg");
        Advertisement.add("https://file.hstatic.net/1000230642/file/vansukhoidaunice__1__9e02ab5f0c1d47d1b31040dbf45c6bbe_master.jpg");
        Advertisement.add("https://file.hstatic.net/1000230642/file/1920x750__1__ababa5e5dd20461aab9cbf65d9277383_master.jpg");
        Advertisement.add("https://ananas.vn/wp-content/uploads/Web1920-1.jpeg");
//        Advertisement.add("https://i.imgur.com/59hVrow.png");
        for (int i=0; i<Advertisement.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(Advertisement.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_sidebar_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sidebar_in_right);
        Animation animation_sidebar_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sidebar_out_right);
        viewFlipper.setInAnimation(animation_sidebar_in);
        viewFlipper.setOutAnimation(animation_sidebar_out);
    }
    private void Assignviews(){
        toolbar = findViewById(R.id.toolbarHome);
        viewFlipper = findViewById(R.id.viewFlipperHome);
        navigationView = findViewById(R.id.navigationViewHome);
        recyclerView = findViewById(R.id.recyclerviewHome);

        linearLayout1 = findViewById(R.id.linerboderlogo1);
        linearLayout2 = findViewById(R.id.linerboderlogo2);
        drawerLayout = findViewById(R.id.drawerlayoutHome);



        TextView HometextView = findViewById(R.id.nav_home);
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
                Intent intent = new Intent(HomeActivity.this, ChatAdapter.class);
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
                Intent intent = new Intent(HomeActivity.this, NikeProduct.class);
                startActivity(intent);
            }
        });
        PumaLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PumaProduct.class);
                startActivity(intent);
            }
        });

        CartImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
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