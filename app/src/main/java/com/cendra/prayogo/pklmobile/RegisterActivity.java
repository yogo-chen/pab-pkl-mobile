package com.cendra.prayogo.pklmobile;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cendra.prayogo.pklmobile.db.PklDbHelper;

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

    private Calendar birthdayCalendar;
    private DatePickerDialog birthdayDatePickerDialog;

    private boolean nameFieldAcceptable;
    private boolean birthdayFieldAcceptable;
    private boolean addressFieldAcceptable;
    private boolean phoneFieldAcceptable;
    private boolean emailFieldAcceptable;

    private PklDbHelper pklDbHelper;

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

        this.pklDbHelper = new PklDbHelper(RegisterActivity.this);

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

        this.birthdayCalendar = Calendar.getInstance();
        this.birthdayDatePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthdayCalendar.set(Calendar.YEAR, year);
                        birthdayCalendar.set(Calendar.MONTH, monthOfYear);
                        birthdayCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
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
        this.birthdayDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        this.birthdayDatePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                checkBirthdayField();
            }
        });
        this.birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBirthdayDatePickerDialog();
            }
        });

        this.addressEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    checkAddressField();
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

        this.phoneEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    checkPhoneField();
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

        this.emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    checkEmailField();
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

    private void checkNameField() {
        String s = getNameField();
        if (s.equals("")) {
            this.nameTextInputLayout.setErrorEnabled(true);
            this.nameTextInputLayout.setError(getText(R.string.register_nameEditTextErrorBlank));
            this.nameFieldAcceptable = false;
        } else {
            this.nameFieldAcceptable = true;
        }
    }

    private void checkBirthdayField() {
        String s = getBirthdayField();
        if (s.equals("")) {
            this.birthdayTextInputLayout.setErrorEnabled(true);
            this.birthdayTextInputLayout.setError(getText(R.string.register_birthdayEditTextBlank));
            this.birthdayFieldAcceptable = false;
        } else {
            this.birthdayFieldAcceptable = true;
        }
    }

    private void checkAddressField() {
        String s = getAddressField();
        if (s.equals("")) {
            this.addressTextInputLayout.setErrorEnabled(true);
            this.addressTextInputLayout.setError(getText(R.string.register_addressEditTextBlank));
            this.addressFieldAcceptable = false;
        } else {
            this.addressFieldAcceptable = true;
        }
    }

    private void checkPhoneField() {
        String s = getPhoneField();
        if (s.equals("")) {
            this.phoneTextInputLayout.setErrorEnabled(true);
            this.phoneTextInputLayout.setError(getText(R.string.register_phoneEditTextErrorBlank));
            this.phoneFieldAcceptable = false;
        } else if (!Patterns.PHONE.matcher(s).matches()) {
            this.phoneTextInputLayout.setErrorEnabled(true);
            this.phoneTextInputLayout.setError(getText(R.string.register_phoneEditTextErrorPattern));
            this.phoneFieldAcceptable = false;
        } else {
            this.phoneFieldAcceptable = true;
        }
    }

    private void checkEmailField() {
        String s = getEmailField();
        if (s.equals("")) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.register_emailEditTextErrorBlank));
            this.emailFieldAcceptable = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.register_emailEditTextErrorPattern));
            this.emailFieldAcceptable = false;
        } else {
            if (this.pklDbHelper.getPkl(s) != null) {
                this.emailTextInputLayout.setErrorEnabled(true);
                this.emailTextInputLayout.setError(getText(R.string.register_emailEditTextErrorTaken));
                this.emailFieldAcceptable = false;
            } else {
                this.emailFieldAcceptable = true;
            }
        }
    }

    private void checkAllField() {
        checkNameField();
        checkBirthdayField();
        checkAddressField();
        checkPhoneField();
        checkEmailField();
    }

    private void showBirthdayDatePickerDialog() {
        if (!this.birthdayDatePickerDialog.isShowing()) {
            this.birthdayDatePickerDialog.show();
        }
    }

    private String getEmailField() {
        return this.emailEditText.getText().toString().trim();
    }

    private String getBirthdayField() {
        return this.birthdayEditText.getText().toString().trim().toLowerCase();
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
        checkAllField();
        if (this.nameFieldAcceptable
                && this.birthdayFieldAcceptable
                && this.addressFieldAcceptable
                && this.phoneFieldAcceptable
                && this.emailFieldAcceptable) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            String simpleBirthdayDate = dateFormat.format(this.birthdayCalendar.getTime());
            this.pklDbHelper.insertPkl(getEmailField(), getNameField(), getAddressField(), getPhoneField(), simpleBirthdayDate);
            Log.d("RegisterActivity", "Register Success [" + this.pklDbHelper.getPkl(getEmailField()).toString() + "]");
            Toast.makeText(RegisterActivity.this, "Your Account Created", Toast.LENGTH_LONG).show();
        }
    }
}