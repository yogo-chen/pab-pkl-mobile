package com.cendra.prayogo.pklmobile;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText rePasswordEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText birthdayEditText;
    private EditText featuredProductEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.emailEditText = (EditText) findViewById(R.id.register_emailEditText);
        this.passwordEditText = (EditText) findViewById(R.id.register_passwordEditText);
        this.rePasswordEditText = (EditText) findViewById(R.id.register_rePasswordEditText);
        this.nameEditText = (EditText) findViewById(R.id.register_nameEditText);
        this.addressEditText = (EditText) findViewById(R.id.register_addressEditText);
        this.phoneEditText = (EditText) findViewById(R.id.register_phoneEditText);
        this.birthdayEditText = (EditText) findViewById(R.id.register_birthdayEditText);
        this.featuredProductEditText = (EditText) findViewById(R.id.register_featuredProductEditText);

        this.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBirthdayDate();
            }
        });

    }

    private void setBirthdayDate() {
        final Calendar calendar = Calendar.getInstance(TimeZone.getDefault().getDefault());
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, y");
                birthdayEditText.setText(dateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private String getEmail() {
        return this.emailEditText.getText().toString();
    }

    private String getPassword() {
        return this.passwordEditText.getText().toString();
    }

    private String getRePassword() {
        return this.rePasswordEditText.getText().toString();
    }

    private String getName() {
        return this.nameEditText.getText().toString();
    }

    private String getAddress() {
        return this.addressEditText.getText().toString();
    }

    private String getPhone() {
        return this.phoneEditText.getText().toString();
    }

    private String getBirthday() {
        return this.birthdayEditText.getText().toString();
    }

    private String getFeaturedProduct() {
        return this.featuredProductEditText.getText().toString();
    }
}
