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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ProductViewHolder>{
    private List<Product> productList;
    private Context context;



    public HomeAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
//    public void setProducts(List<Product> products) {
//        this.productList = products;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_home, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewName.setText(product.getProductName());
        holder.textViewPrice.setText(String.format("$%.2f", product.getProductPrice()));
        Picasso.get().load(product.getProductImage()).into(holder.imageViewProduct);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageViewProduct;
        private TextView textViewName;
        private TextView textViewPrice;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageview_product);
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewPrice = itemView.findViewById(R.id.textview_price);

            // Set click listener on the item view
            itemView.setOnClickListener(this);
        }

        public void bind(Product product) {
            Picasso.get().load(product.getProductImage()).into(imageViewProduct);
            textViewName.setText(product.getProductName());
            textViewPrice.setText(String.valueOf(product.getProductPrice()));
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product product = productList.get(position);
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
