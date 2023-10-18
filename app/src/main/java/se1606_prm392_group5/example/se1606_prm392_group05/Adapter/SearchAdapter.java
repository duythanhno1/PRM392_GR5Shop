package se1606_prm392_group5.example.se1606_prm392_group05.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se1606_prm392_group5.example.se1606_prm392_group05.Model.Product;
import com.example.se1606_prm392_group05.R;
import se1606_prm392_group5.example.se1606_prm392_group05.View.ProductdetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Product> searchResults;
    private Context context;

    public SearchAdapter(Context context, List<Product> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo ViewHolder từ layout của một item trong RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Hiển thị thông tin sản phẩm trong ViewHolder
        Product product = searchResults.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView productNameTextView;
        private ImageView productImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.textview_productName);
            productImageView = itemView.findViewById(R.id.imageSearch_product);
            itemView.setOnClickListener(this);
        }

        public void bind(Product product) {
            // Hiển thị thông tin sản phẩm trong ViewHolder
            productNameTextView.setText(product.getProductName());
            Picasso.get().load(product.getProductImage()).into(productImageView);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product product = searchResults.get(position);
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

    public void setSearchResults(List<Product> searchResults) {
        this.searchResults = searchResults;
    }
}
