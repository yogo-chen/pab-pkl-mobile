package com.cendra.prayogo.pklmobile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cendra.prayogo.pklmobile.adapter.CatalogAdapter;
import com.cendra.prayogo.pklmobile.model.Product;
import com.cendra.prayogo.pklmobile.service.PklServiceHelper;

public class CatalogActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!PklServiceHelper.isLoggedIn(CatalogActivity.this)) {
            Intent intent = new Intent(CatalogActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_catalog);

        this.recyclerView = (RecyclerView) findViewById(R.id.catalog_recyclerView);
        this.layoutManager = new LinearLayoutManager(CatalogActivity.this);
        this.recyclerView.setLayoutManager(this.layoutManager);

        PklServiceHelper.getCatalog(CatalogActivity.this, new PklServiceHelper.OnEventListener() {
            @Override
            public void onResultSuccess(Object result) {
                adapter = new CatalogAdapter((String[]) result);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onResultFailed(int statusCode) {
                Toast.makeText(CatalogActivity.this, "Catalog fetch failed with code " + statusCode, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.catalog_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.catalog_menu_add_product: {
                startAddProductActivity();
                return true;
            }
            case R.id.catalog_menu_about: {
                Toast.makeText(CatalogActivity.this, "PKL Mobile - Created by Prayogo Cendra", Toast.LENGTH_LONG).show();
                return true;
            }
            case R.id.catalog_menu_logout: {
                PklServiceHelper.logout(CatalogActivity.this);
                Intent intent = new Intent(CatalogActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private void startAddProductActivity() {
        Intent intent = new Intent(CatalogActivity.this, AddProductActivity.class);
        startActivity(intent);
    }

    public void addProduct(View view) {
        Toast.makeText(CatalogActivity.this, "Add Product", Toast.LENGTH_LONG).show();
    }

    public void productDetail(View view) {
        TextView cardViewProductNameTextView = (TextView) view.findViewById(R.id.product_card_view_product_name_text_view);
        PklServiceHelper.getProduct(CatalogActivity.this, new PklServiceHelper.OnEventListener() {
            @Override
            public void onResultSuccess(Object result) {
                Product product = (Product) result;

                final Dialog productDetailDialog = new Dialog(CatalogActivity.this);
                productDetailDialog.setContentView(R.layout.dialog_product_detail);
                TextView dialogProductNameTextView = (TextView) productDetailDialog.findViewById(R.id.dialog_product_detail_product_name);
                EditText dialogBasePriceEditText = (EditText) productDetailDialog.findViewById(R.id.dialog_product_detail_base_price);
                EditText dialogSellPriceEditText = (EditText) productDetailDialog.findViewById(R.id.dialog_product_detail_sell_price);
                Button dialogSaveButton = (Button) productDetailDialog.findViewById(R.id.dialog_product_detail_save_button);

                dialogProductNameTextView.setText(product.name);
                dialogBasePriceEditText.setText(product.basePrice + "");
                dialogSellPriceEditText.setText(product.sellPrice + "");
                dialogSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO onProductSaved
                        productDetailDialog.dismiss();
                    }
                });
                productDetailDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                productDetailDialog.show();
            }

            @Override
            public void onResultFailed(int statusCode) {
                Toast.makeText(CatalogActivity.this, "Product detail fetch failed with code " + statusCode, Toast.LENGTH_LONG).show();
            }
        }, cardViewProductNameTextView.getText().toString());
    }
}
