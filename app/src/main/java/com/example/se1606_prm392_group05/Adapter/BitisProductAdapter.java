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

public class BitisProductAdapter extends RecyclerView.Adapter<BitisProductAdapter.ViewHolder> {
    // Danh sách sản phẩm Bitis
    private List<Product> productList;

    // Môi trường hoạt động (Context)
    private Context context;

    // Constructor để khởi tạo Adapter với danh sách sản phẩm và môi trường hoạt động
    public BitisProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    // Phương thức tạo ViewHolder mới cho mỗi mục sản phẩm
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo một View từ layout XML để hiển thị một mục sản phẩm trong RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bitis_product, parent, false);
        return new ViewHolder(view);
    }

    // Phương thức gán dữ liệu sản phẩm vào ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy sản phẩm ở vị trí hiện tại trong danh sách
        Product product = productList.get(position);

        // Gán tên sản phẩm và giá sản phẩm vào các View trong ViewHolder
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.format("$%.2f", product.getProductPrice()));

        // Sử dụng thư viện Picasso để tải và hiển thị hình ảnh sản phẩm
        Picasso.get().load(product.getProductImage()).into(holder.productImage);
    }

    // Trả về số lượng mục trong danh sách sản phẩm
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;

        // Constructor cho ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Liên kết các thành phần UI với phần tử trong layout XML
            productImage = itemView.findViewById(R.id.imageview_brandproduct2);
            productName = itemView.findViewById(R.id.textview_brandname2);
            productPrice = itemView.findViewById(R.id.textview_brandprice2);

            // Đặt sự kiện onClick cho mỗi mục trong danh sách
            itemView.setOnClickListener(this);
        }

        // Phương thức gán dữ liệu từ đối tượng Product vào các View tương ứng
        public void bind(Product product) {
            Picasso.get().load(product.getProductImage()).into(productImage);
            productName.setText(product.getProductName());
            productPrice.setText(String.valueOf(product.getProductPrice()));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product product = productList.get(position);

                // Tạo Intent để chuyển đến màn hình chi tiết sản phẩm và gửi thông tin sản phẩm dưới dạng extras
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
