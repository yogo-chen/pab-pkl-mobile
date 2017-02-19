package com.cendra.prayogo.pklmobile;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cendra.prayogo.pklmobile.adapter.ProductAdapter;
import com.cendra.prayogo.pklmobile.db.ProductDbHelper;
import com.cendra.prayogo.pklmobile.service.PklAccountManager;

public class CatalogActivity extends AppCompatActivity {
    private ContentValues loggedInPkl;

    private ProductDbHelper productDbHelper;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.loggedInPkl = PklAccountManager.getLoggedIn(CatalogActivity.this);

        if (this.loggedInPkl == null) {
            Intent intent = new Intent(CatalogActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_catalog);

        this.productDbHelper = new ProductDbHelper(CatalogActivity.this);

        this.recyclerView = (RecyclerView) findViewById(R.id.catalog_recyclerView);

        this.layoutManager = new LinearLayoutManager(CatalogActivity.this);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.adapter = new ProductAdapter(this.productDbHelper.getAllProduct(this.loggedInPkl.getAsString(PklAccountManager.LOGGED_IN_EMAIL)));
        this.recyclerView.setAdapter(this.adapter);
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
            case R.id.catalog_menu_logout: {
                logout();
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

    private void logout() {
        PklAccountManager.logout(CatalogActivity.this);
        Intent intent = new Intent(CatalogActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
