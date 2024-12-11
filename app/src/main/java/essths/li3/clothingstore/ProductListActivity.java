package essths.li3.clothingstore;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements Adapter.OnProductClickListener {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Product> products;
    private EditText searchEditText;
    private LinearLayout emptyStateLayout;
    private String currentCategory;
    private TextView titleText;
    private MaterialButton faceCategoryBtn, bodyCategoryBtn, hairCategoryBtn;
    private MaterialButton currentSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Get category from intent if available
        currentCategory = getIntent().getStringExtra("category");

        initializeViews();
        setupListeners();

        // Initialize with all products or filtered by category
        updateProductsList(searchEditText.getText().toString());

        // If category was passed via intent, select the appropriate button
        if (currentCategory != null) {
            selectCategoryButton(currentCategory);
        }
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.productsRecyclerView);
        searchEditText = findViewById(R.id.searchEditText);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);
        titleText = findViewById(R.id.titleText);
        faceCategoryBtn = findViewById(R.id.faceCategoryBtn);
        bodyCategoryBtn = findViewById(R.id.bodyCategoryBtn);
        hairCategoryBtn = findViewById(R.id.hairCategoryBtn);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        titleText.setText("Products");
    }

    private void setupListeners() {
        // Setup search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateProductsList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup category buttons
        View.OnClickListener categoryClickListener = v -> {
            MaterialButton clickedButton = (MaterialButton) v;
            String category = clickedButton.getText().toString();

            if (currentSelectedButton == clickedButton) {
                // Deselect if clicking the same category
                resetButtonStyle(clickedButton);
                currentSelectedButton = null;
                currentCategory = null;
            } else {
                // Select new category
                if (currentSelectedButton != null) {
                    resetButtonStyle(currentSelectedButton);
                }
                setSelectedButtonStyle(clickedButton);
                currentSelectedButton = clickedButton;
                currentCategory = category;
            }

            updateProductsList(searchEditText.getText().toString());
        };

        faceCategoryBtn.setOnClickListener(categoryClickListener);
        bodyCategoryBtn.setOnClickListener(categoryClickListener);
        hairCategoryBtn.setOnClickListener(categoryClickListener);

        // Setup back button
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }

    private void resetButtonStyle(MaterialButton button) {
        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.transparent)));
        button.setTextColor(ContextCompat.getColor(this, android.R.color.black));
    }

    private void setSelectedButtonStyle(MaterialButton button) {
        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green)));
        button.setTextColor(ContextCompat.getColor(this, android.R.color.white));
    }

    private void selectCategoryButton(String category) {
        MaterialButton buttonToSelect = null;
        switch (category.toLowerCase()) {
            case "face":
                buttonToSelect = faceCategoryBtn;
                break;
            case "body":
                buttonToSelect = bodyCategoryBtn;
                break;
            case "hair":
                buttonToSelect = hairCategoryBtn;
                break;
        }

        if (buttonToSelect != null) {
            buttonToSelect.performClick();
        }
    }

    private void updateProductsList(String query) {
        // Search products with category filter if available
        products = Products.searchProducts(query, currentCategory);

        // Show/hide empty state
        emptyStateLayout.setVisibility(products.isEmpty() ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(products.isEmpty() ? View.GONE : View.VISIBLE);

        // Update RecyclerView
        if (adapter == null) {
            adapter = new Adapter(products, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateProducts(products);
        }
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