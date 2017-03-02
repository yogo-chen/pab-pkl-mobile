package com.cendra.prayogo.pklmobile;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {
//    private ContentValues loggedInPkl;

    private EditText nameEditText;
    private EditText basePriceEditText;
    private EditText sellPriceEditText;

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout basePriceTextInputLayout;
    private TextInputLayout sellPriceTextInputLayout;

    private boolean nameFieldAcceptable;
    private boolean basePriceFieldAcceptable;
    private boolean sellPriceFieldAcceptable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.loggedInPkl = PklAccountManager.getLoggedIn(AddProductActivity.this);
//
//        if (this.loggedInPkl == null) {
//            Intent intent = new Intent(AddProductActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }

        setContentView(R.layout.activity_add_product);

        this.nameEditText = (EditText) findViewById(R.id.activity_add_product_name_edit_text);
        this.basePriceEditText = (EditText) findViewById(R.id.activity_add_product_base_price_edit_text);
        this.sellPriceEditText = (EditText) findViewById(R.id.activity_add_product_sell_price_edit_text);

        this.nameTextInputLayout = (TextInputLayout) findViewById(R.id.activity_add_product_name_text_input_layout);
        this.basePriceTextInputLayout = (TextInputLayout) findViewById(R.id.activity_add_product_base_price_text_input_layout);
        this.sellPriceTextInputLayout = (TextInputLayout) findViewById(R.id.activity_add_product_sell_price_text_input_layout);

        this.nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    checkNameField();
                }
            }
        });
        this.nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (nameTextInputLayout.isErrorEnabled()) {
                    nameTextInputLayout.setErrorEnabled(false);
                    nameTextInputLayout.setError(null);
                }
            }
        });

        this.basePriceEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    checkBasePriceField();
                }
            }
        });
        this.basePriceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (basePriceTextInputLayout.isErrorEnabled()) {
                    basePriceTextInputLayout.setErrorEnabled(false);
                    basePriceTextInputLayout.setError(null);
                }
            }
        });

        this.sellPriceEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    checkSellPriceField();
                }
            }
        });
        this.sellPriceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (sellPriceTextInputLayout.isErrorEnabled()) {
                    sellPriceTextInputLayout.setErrorEnabled(false);
                    sellPriceTextInputLayout.setError(null);
                }
            }
        });
    }

    private void checkNameField() {
        String s = getNameField();
        if (s.equals("")) {
            this.nameTextInputLayout.setErrorEnabled(true);
            this.nameTextInputLayout.setError("Required");
            this.nameFieldAcceptable = false;
        } else {
            this.nameFieldAcceptable = true;
        }
    }

    private void checkBasePriceField() {
        int i = getBasePriceField();
        if (i <= 0) {
            this.basePriceTextInputLayout.setErrorEnabled(true);
            this.basePriceTextInputLayout.setError("Required");
            this.basePriceFieldAcceptable = false;
        } else {
            this.basePriceFieldAcceptable = true;
        }
    }

    private void checkSellPriceField() {
        int i = getSellPriceField();
        if (i <= 0) {
            this.sellPriceTextInputLayout.setErrorEnabled(true);
            this.sellPriceTextInputLayout.setError("Required");
            this.sellPriceFieldAcceptable = false;
        } else {
            this.sellPriceFieldAcceptable = true;
        }
    }

    private String getNameField() {
        return this.nameEditText.getText().toString().trim();
    }

    private int getBasePriceField() {
        int res = 0;
        try {
            res = Integer.parseInt(this.basePriceEditText.getText().toString().trim());
        } catch (NumberFormatException e) {
        }
        return res;
    }

    private int getSellPriceField() {
        int res = 0;
        try {
            res = Integer.parseInt(this.sellPriceEditText.getText().toString().trim());
        } catch (NumberFormatException e) {
        }
        return res;
    }

    private void checkAllField() {
        checkNameField();
        checkBasePriceField();
        checkSellPriceField();
    }

    private boolean isAllFieldAcceptable() {
        return this.nameFieldAcceptable
                && this.basePriceFieldAcceptable
                && this.sellPriceFieldAcceptable;
    }

    private void addProduct() {
//        ProductDbHelper productDbHelper = new ProductDbHelper(AddProductActivity.this);
//        productDbHelper.insertProduct(getNameField(), getBasePriceField(), getSellPriceField(), this.loggedInPkl.getAsString(PklAccountManager.LOGGED_IN_EMAIL));
//        Toast.makeText(AddProductActivity.this, "Product Added", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(AddProductActivity.this, CatalogActivity.class);
//        startActivity(intent);
//        finish();
    }

    public void addProductButtonOnClick(View view) {
//        checkAllField();
//        if (isAllFieldAcceptable()) {
//            addProduct();
//        }
    }
}
