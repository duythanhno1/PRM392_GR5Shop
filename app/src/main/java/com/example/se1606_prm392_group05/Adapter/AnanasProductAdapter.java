package com.example.se1606_prm392_group05.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;
import com.example.se1606_prm392_group05.View.ProductdetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnanasProductAdapter extends RecyclerView.Adapter<AnanasProductAdapter.ViewHolder> {
    private List<Product> productList;
    private Context context;
    public AnanasProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
    // Phương thức onCreateViewHolder() tạo ViewHolder mới cho từng item trong RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ananas_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
// Gán tên sản phẩm, giá và hình ảnh từ đối tượng Product vào các View trong ViewHolder
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.format("$%.2f", product.getProductPrice()));
        Picasso.get().load(product.getProductImage()).into(holder.productImage);
    }
    // Phương thức getItemCount() trả về số lượng item trong RecyclerView
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageview_brandproduct);
            productName = itemView.findViewById(R.id.textview_brandname);
            productPrice = itemView.findViewById(R.id.textview_brandprice);
// Đăng ký sự kiện nhấn vào item
            itemView.setOnClickListener(this);
        }
        // Phương thức bind(Product product) để gán dữ liệu từ đối tượng Product vào các View
        public void bind(Product product) {
            // Gán dữ liệu từ đối tượng Product vào các view tương ứng
            Picasso.get().load(product.getProductImage()).into(productImage);
            productName.setText(product.getProductName());
            productPrice.setText(String.valueOf(product.getProductPrice()));
        }
        // Xử lý sự kiện khi item trong RecyclerView được nhấn
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Lấy đối tượng Product tại vị trí được nhấn
                Product product = productList.get(position);
                // Tạo Intent để chuyển đến Activity ProductdetailActivity và gửi dữ liệu sản phẩm
                Intent intent = new Intent(context, ProductdetailActivity.class);
                intent.putExtra("productID", product.getProductID());
                intent.putExtra("productImage", product.getProductImage());
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("productPrice", String.valueOf(product.getProductPrice()));
                intent.putExtra("productBrand", product.getBrand());
                intent.putExtra("productDescribe", product.getProductDescribe());
                intent.putExtra("productColor", product.getProductColor());
                intent.putExtra("productSize", product.getProductSize());
                context.startActivity(intent);
            }
        }
    }
}