package essths.li3.clothingstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnProductClickListener {

    private List<Product> products;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        products = Products.getProducts();
        RecyclerView recyclerView = findViewById(R.id.productsRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new Adapter(products, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.searchInput).setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                startActivity(new Intent(getApplicationContext(), ProductListActivity.class));

            }
        });
        findViewById(R.id.searchInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductListActivity.class));

            }
        });
        findViewById(R.id.seeAllText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductListActivity.class));

            }
        });
        findViewById(R.id.seeAllIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductListActivity.class));

            }
        });
        findViewById(R.id.discoverbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));

            }
        });
        findViewById(R.id.profilebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        findViewById(R.id.wishlistbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WishlistActivity.class));
            }
        });

    }

    @Override
    public void onLikeClick(int position) {
        products.get(position).setLiked(!products.get(position).isLiked());
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onProductClick(int position) {
        // Not implemented
    }
}