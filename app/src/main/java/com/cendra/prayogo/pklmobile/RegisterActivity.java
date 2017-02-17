package com.cendra.prayogo.pklmobile;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private DatePickerDialog.OnDateSetListener birthdayOnDateSetListener;

    private boolean nameFieldAcceptable;
    private boolean birthdayFieldAcceptable;
    private boolean addressFieldAcceptable;
    private boolean phoneFieldAcceptable;
    private boolean emailFieldAcceptable;

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
                checkNameField();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.birthdayCalendar = Calendar.getInstance();
        this.birthdayOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthdayCalendar.set(Calendar.YEAR, year);
                birthdayCalendar.set(Calendar.MONTH, monthOfYear);
                birthdayCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, y");
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMYYYY");
                birthdayEditText.setText(dateFormat.format(birthdayCalendar.getTime()));
                checkBirthdayField();
            }
        };
        this.birthdayDatePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                birthdayOnDateSetListener,
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
                checkAddressField();
            }

            @Override
            public void afterTextChanged(Editable s) {
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
                checkPhoneField();
            }

            @Override
            public void afterTextChanged(Editable s) {
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
                checkEmailField();
            }

            @Override
            public void afterTextChanged(Editable s) {
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
            this.nameTextInputLayout.setErrorEnabled(false);
            this.nameTextInputLayout.setError(null);
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
            this.birthdayTextInputLayout.setErrorEnabled(false);
            this.birthdayTextInputLayout.setError(null);
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
            this.addressTextInputLayout.setErrorEnabled(false);
            this.addressTextInputLayout.setError(null);
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
            this.phoneTextInputLayout.setErrorEnabled(false);
            this.phoneTextInputLayout.setError(null);
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
            this.emailTextInputLayout.setErrorEnabled(false);
            this.emailTextInputLayout.setError(null);
            this.emailFieldAcceptable = true;
        }
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

    public void register(View view) {
        if (this.nameFieldAcceptable
                && this.birthdayFieldAcceptable
                && this.addressFieldAcceptable
                && this.phoneFieldAcceptable
                && this.emailFieldAcceptable) {
            Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
        }
    }
}