package com.cendra.prayogo.pklmobile;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class TransactionListActivity extends AppCompatActivity {
    private ContentValues loggedInPkl;

//    private TransactionDbHelper transactionDbHelper;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.loggedInPkl = PklAccountManager.getLoggedIn(TransactionListActivity.this);

//        if (this.loggedInPkl == null) {
//            Intent intent = new Intent(TransactionListActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }
        setContentView(R.layout.activity_transaction_list);

//        this.transactionDbHelper = new TransactionDbHelper(TransactionListActivity.this);

//        this.recyclerView = (RecyclerView) findViewById(R.id.asdasdasdasdasd);

        this.layoutManager = new LinearLayoutManager(TransactionListActivity.this);
        this.recyclerView.setLayoutManager(this.layoutManager);

//        this.adapter = new TransactionListAdapter(this.transactionDbHelper.getAllTransaction(this.loggedInPkl.getAsString(PklAccountManager.LOGGED_IN_EMAIL)));
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
//                startAddProductActivity();
                return true;
            }
            case R.id.catalog_menu_about: {
                Toast.makeText(TransactionListActivity.this, "PKL Mobile - Created by Prayogo Cendra", Toast.LENGTH_LONG).show();
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

    private void logout() {
//        PklAccountManager.logout(TransactionListActivity.this);
//        Intent intent = new Intent(TransactionListActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }
}
