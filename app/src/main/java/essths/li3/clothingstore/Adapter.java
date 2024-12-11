package essths.li3.clothingstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductViewHolder> {
    private List<Product> products;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onLikeClick(int position);
        void onProductClick(int position);
    }

    public Adapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.brandName.setText(product.getBrand());
        holder.price.setText(String.format("%.1f DT", product.getPrice()));
        holder.likeIcon.setImageResource(product.isLiked() ?
                R.drawable.likeselected : R.drawable.like);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, likeIcon;
        TextView productName, brandName, price;
        MaterialCardView likeButton;

        ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            likeIcon = itemView.findViewById(R.id.likeIcon);
            productName = itemView.findViewById(R.id.productName);
            brandName = itemView.findViewById(R.id.brandName);
            price = itemView.findViewById(R.id.price);
            likeButton = itemView.findViewById(R.id.likeButton);

            likeButton.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onLikeClick(getAdapterPosition());
                }
            });

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onProductClick(getAdapterPosition());
                }
            });
        }
    }
}