package com.cendra.prayogo.pklmobile;

import android.app.DatePickerDialog;
import android.content.ContentValues;
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

import com.cendra.prayogo.pklmobile.db.PklDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText birthdayEditText;

    private TextInputLayout emailTextInputLayout;
    private TextInputLayout birthdayTextInputLayout;

    private Calendar birthdayCalendar;
    private DatePickerDialog birthdayDatePickerDialog;

    private boolean emailFieldAcceptable;
    private boolean birthdayFieldAcceptable;

    private PklDbHelper pklDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailEditText = (EditText) findViewById(R.id.login_emailEditText);
        this.birthdayEditText = (EditText) findViewById(R.id.login_birthdayEditText);

        this.emailTextInputLayout = (TextInputLayout) findViewById(R.id.login_emailTextInputLayout);
        this.birthdayTextInputLayout = (TextInputLayout) findViewById(R.id.login_birthdayTextInputLayout);

        this.pklDbHelper = new PklDbHelper(LoginActivity.this);

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

        this.birthdayCalendar = Calendar.getInstance();
        this.birthdayDatePickerDialog = new DatePickerDialog(
                LoginActivity.this,
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
    }

    private void checkEmailField() {
        String s = getEmailField();
        if (s.equals("")) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.login_emailEditTextErrorBlank));
            this.emailFieldAcceptable = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.login_emailEditTextErrorPattern));
            this.emailFieldAcceptable = false;
        } else {
            this.emailFieldAcceptable = true;
        }
    }

    private void checkBirthdayField() {
        String s = getBirthdayField();
        if (s.equals("")) {
            this.birthdayTextInputLayout.setErrorEnabled(true);
            this.birthdayTextInputLayout.setError(getText(R.string.login_birthdayEditTextBlank));
            this.birthdayFieldAcceptable = false;
        } else {
            this.birthdayFieldAcceptable = true;
        }
    }

    private void checkAllField() {
        checkEmailField();
        checkBirthdayField();
    }

    private void showBirthdayDatePickerDialog() {
        if (!this.birthdayDatePickerDialog.isShowing()) {
            this.birthdayDatePickerDialog.show();
        }
    }

    private String getEmailField() {
        return this.emailEditText.getText().toString().trim().toLowerCase();
    }

    private String getBirthdayField() {
        return this.birthdayEditText.getText().toString().trim();
    }

    public void loginButtonOnClick(View view) {
        checkAllField();
        if (this.emailFieldAcceptable && this.birthdayFieldAcceptable) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            String simpleBirthdayDate = dateFormat.format(this.birthdayCalendar.getTime());
            ContentValues pkl = this.pklDbHelper.getPkl(getEmailField(), simpleBirthdayDate);
            if (pkl != null) {
                Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Email or Birthday", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void registerButtonOnClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
