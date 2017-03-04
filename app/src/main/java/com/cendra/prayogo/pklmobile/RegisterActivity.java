package com.cendra.prayogo.pklmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cendra.prayogo.pklmobile.model.User;
import com.cendra.prayogo.pklmobile.service.PklServiceHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText birthdayEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout birthdayTextInputLayout;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout addressTextInputLayout;
    private TextInputLayout phoneTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.emailEditText = (EditText) findViewById(R.id.register_emailEditText);
        this.birthdayEditText = (EditText) findViewById(R.id.register_birthdayEditText);
        this.nameEditText = (EditText) findViewById(R.id.register_nameEditText);
        this.addressEditText = (EditText) findViewById(R.id.register_addressEditText);
        this.phoneEditText = (EditText) findViewById(R.id.register_phoneEditText);
        this.emailTextInputLayout = (TextInputLayout) findViewById(R.id.register_emailTextInputLayout);
        this.birthdayTextInputLayout = (TextInputLayout) findViewById(R.id.register_birthdayTextInputLayout);
        this.nameTextInputLayout = (TextInputLayout) findViewById(R.id.register_nameTextInputLayout);
        this.addressTextInputLayout = (TextInputLayout) findViewById(R.id.register_addressTextInputLayout);
        this.phoneTextInputLayout = (TextInputLayout) findViewById(R.id.register_phoneTextInputLayout);

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

        final Calendar birthdayCalendar = Calendar.getInstance();

        final DatePickerDialog birthdayDatePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthdayCalendar.set(Calendar.YEAR, year);
                        birthdayCalendar.set(Calendar.MONTH, monthOfYear);
                        birthdayCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
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

        this.birthdayEditText.setInputType(InputType.TYPE_NULL);

        this.birthdayEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!birthdayDatePickerDialog.isShowing()) {
                    birthdayDatePickerDialog.show();
                }
                return false;
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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private boolean checkEmailField() {
        String s = getEmailField();
        if (s.equals("")) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.activity_register_emailEditTextErrorBlank));
            this.emailEditText.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.activity_register_emailEditTextErrorPattern));
            this.emailEditText.requestFocus();
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
            this.birthdayEditText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkNameField() {
        String s = getNameField();
        if (s.equals("")) {
            this.nameTextInputLayout.setErrorEnabled(true);
            this.nameTextInputLayout.setError(getText(R.string.activity_register_nameEditTextErrorBlank));
            this.nameEditText.requestFocus();
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
            this.addressEditText.requestFocus();
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
            this.phoneEditText.requestFocus();
            return false;
        } else if (!Patterns.PHONE.matcher(s).matches()) {
            this.phoneTextInputLayout.setErrorEnabled(true);
            this.phoneTextInputLayout.setError(getText(R.string.activity_register_phoneEditTextErrorPattern));
            this.phoneEditText.requestFocus();
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

    public void registerButtonOnClick(final View view) {
        if (checkEmailField() && checkBirthdayField() && checkNameField() && checkAddressField() && checkPhoneField()) {
            User user = new User(getEmailField(), getBirthdayField(), getNameField(), getAddressField(), getPhoneField(), "barang");
            PklServiceHelper.register(new PklServiceHelper.OnEventListener() {
                @Override
                public void onPreTask() {
                    view.setEnabled(false);
                }

                @Override
                public void onResultSuccess() {
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    view.setEnabled(true);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onResultFailed() {
                    Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                    view.setEnabled(true);
                }

                @Override
                public void onServerError() {
                    Toast.makeText(RegisterActivity.this, "Server is unavailable", Toast.LENGTH_SHORT).show();
                    view.setEnabled(true);
                }

                @Override
                public void onConnectionError() {
                    Toast.makeText(RegisterActivity.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();
                    view.setEnabled(true);
                }
            }, user);
        }
    }
}