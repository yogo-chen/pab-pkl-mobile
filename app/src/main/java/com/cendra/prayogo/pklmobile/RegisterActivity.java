package com.cendra.prayogo.pklmobile;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cendra.prayogo.pklmobile.service.PklAccountManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText birthdayEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout birthdayTextInputLayout;
    private TextInputLayout addressTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout emailTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.nameEditText = (EditText) findViewById(R.id.register_nameEditText);
        this.birthdayEditText = (EditText) findViewById(R.id.register_birthdayEditText);
        this.addressEditText = (EditText) findViewById(R.id.register_addressEditText);
        this.phoneEditText = (EditText) findViewById(R.id.register_phoneEditText);
        this.emailEditText = (EditText) findViewById(R.id.register_emailEditText);
        this.nameTextInputLayout = (TextInputLayout) findViewById(R.id.register_nameTextInputLayout);
        this.birthdayTextInputLayout = (TextInputLayout) findViewById(R.id.register_birthdayTextInputLayout);
        this.addressTextInputLayout = (TextInputLayout) findViewById(R.id.register_addressTextInputLayout);
        this.phoneTextInputLayout = (TextInputLayout) findViewById(R.id.register_phoneTextInputLayout);
        this.emailTextInputLayout = (TextInputLayout) findViewById(R.id.register_emailTextInputLayout);

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

        final Calendar birthdayCalendar = Calendar.getInstance();

        final DatePickerDialog birthdayDatePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthdayCalendar.set(Calendar.YEAR, year);
                        birthdayCalendar.set(Calendar.MONTH, monthOfYear);
                        birthdayCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH);
                        birthdayEditText.setText(dateFormat.format(birthdayCalendar.getTime()));

                        if (birthdayTextInputLayout.isErrorEnabled()) {
                            birthdayTextInputLayout.setErrorEnabled(false);
                            birthdayTextInputLayout.setError(null);
                        }
                    }
                },
                birthdayCalendar.get(Calendar.YEAR),
                birthdayCalendar.get(Calendar.MONTH),
                birthdayCalendar.get(Calendar.DAY_OF_MONTH)
        );

        birthdayDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        this.birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (birthdayTextInputLayout.isErrorEnabled()) {
                    birthdayTextInputLayout.setErrorEnabled(false);
                    birthdayTextInputLayout.setError(null);
                }
                if (!birthdayDatePickerDialog.isShowing()) {
                    birthdayDatePickerDialog.show();
                }
            }
        });

        this.addressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (addressTextInputLayout.isErrorEnabled()) {
                    addressTextInputLayout.setErrorEnabled(false);
                    addressTextInputLayout.setError(null);
                }
            }
        });

        this.phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (phoneTextInputLayout.isErrorEnabled()) {
                    phoneTextInputLayout.setErrorEnabled(false);
                    phoneTextInputLayout.setError(null);
                }
            }
        });

        this.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (emailTextInputLayout.isErrorEnabled()) {
                    emailTextInputLayout.setErrorEnabled(false);
                    emailTextInputLayout.setError(null);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private boolean checkNameField() {
        String s = getNameField();
        if (s.equals("")) {
            this.nameTextInputLayout.setErrorEnabled(true);
            this.nameTextInputLayout.setError(getText(R.string.activity_register_nameEditTextErrorBlank));
            return false;
        } else {
            return true;
        }
    }

    private boolean checkBirthdayField() {
        String s = getBirthdayField();
        if (s.equals("")) {
            this.birthdayTextInputLayout.setErrorEnabled(true);
            this.birthdayTextInputLayout.setError(getText(R.string.activity_register_birthdayEditTextBlank));
            return false;
        } else {
            return true;
        }
    }

    private boolean checkAddressField() {
        String s = getAddressField();
        if (s.equals("")) {
            this.addressTextInputLayout.setErrorEnabled(true);
            this.addressTextInputLayout.setError(getText(R.string.activity_register_addressEditTextBlank));
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPhoneField() {
        String s = getPhoneField();
        if (s.equals("")) {
            this.phoneTextInputLayout.setErrorEnabled(true);
            this.phoneTextInputLayout.setError(getText(R.string.activity_register_phoneEditTextErrorBlank));
            return false;
        } else if (!Patterns.PHONE.matcher(s).matches()) {
            this.phoneTextInputLayout.setErrorEnabled(true);
            this.phoneTextInputLayout.setError(getText(R.string.activity_register_phoneEditTextErrorPattern));
            return false;
        } else {
            return true;
        }
    }

    private boolean checkEmailField() {
        String s = getEmailField();
        if (s.equals("")) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.activity_register_emailEditTextErrorBlank));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.activity_register_emailEditTextErrorPattern));
            return false;
        } else {
            return true;
        }
    }

    private String getEmailField() {
        return this.emailEditText.getText().toString().trim().toLowerCase();
    }

    private String getBirthdayField() {
        return this.birthdayEditText.getText().toString().trim();
    }

    private String getNameField() {
        return this.nameEditText.getText().toString().trim();
    }

    private String getAddressField() {
        return this.addressEditText.getText().toString().trim();
    }

    private String getPhoneField() {
        return this.phoneEditText.getText().toString().trim();
    }

    public void registerButtonOnClick(View view) {
        if (checkEmailField() && checkBirthdayField() && checkNameField() && checkAddressField() && checkPhoneField()) {
            boolean canRegister = PklAccountManager.register(RegisterActivity.this, getEmailField(), getNameField(), getAddressField(), getPhoneField(), getBirthdayField());
            if (!canRegister) {
                Toast.makeText(RegisterActivity.this, "Registrasi gagal, alamat email " + getEmailField() + " telah digunakan!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Selamat anda telah terdaftar, silakan login untuk menggunakan aplikasi ini! Saat login, gunakan alamat email yang anda telah daftarkan sebagai " + getEmailField() + "!", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        }
    }
}