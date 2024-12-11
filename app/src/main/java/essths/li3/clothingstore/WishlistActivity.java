package essths.li3.clothingstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WishlistActivity extends AppCompatActivity implements Adapter.OnProductClickListener {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Product> likedProducts;
    private TextView itemCountText;
    private LinearLayout emptyStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        initializeViews();
        setupNavigation();
        updateWishlist();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.wishlistRecyclerView);
        itemCountText = findViewById(R.id.itemCountText);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    private void setupNavigation() {
        View homeBtn = findViewById(R.id.homebtn);
        View discoverBtn = findViewById(R.id.discoverbtn);
        View profileBtn = findViewById(R.id.profilebtn);

        if (homeBtn != null) {
            homeBtn.setOnClickListener(v -> {
                startActivity(new Intent(WishlistActivity.this, MainActivity.class));
                finish();
            });
        }

        if (discoverBtn != null) {
            discoverBtn.setOnClickListener(v -> {
                startActivity(new Intent(WishlistActivity.this, MainActivity2.class));
                finish();
            });
        }

        if (profileBtn != null) {
            profileBtn.setOnClickListener(v -> {
                startActivity(new Intent(WishlistActivity.this, ProfileActivity.class));
                finish();
            });
        }
    }

    private void updateWishlist() {
        likedProducts = Products.getLikedProducts();

        if (itemCountText != null) {
            itemCountText.setText(likedProducts.size() + " items");
        }

        boolean isEmpty = likedProducts.isEmpty();
        if (emptyStateLayout != null) {
            emptyStateLayout.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        }
        if (recyclerView != null) {
            recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        }

        if (adapter == null && recyclerView != null) {
            adapter = new Adapter(likedProducts, this);
            recyclerView.setAdapter(adapter);
        } else if (adapter != null) {
            adapter.updateProducts(likedProducts);
        }
    }

    @Override
    public void onLikeClick(int position) {
        if (position >= 0 && position < likedProducts.size()) {
            Product product = likedProducts.get(position);
            product.setLiked(false);
            updateWishlist();
        }
    }

    @Override
    public void onProductClick(int position) {
        if (position >= 0 && position < likedProducts.size()) {
            Product product = likedProducts.get(position);
            // Handle product click
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWishlist();
    }
}