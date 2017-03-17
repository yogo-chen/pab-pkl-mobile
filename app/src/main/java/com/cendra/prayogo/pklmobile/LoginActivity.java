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

import com.cendra.prayogo.pklmobile.service.PklServiceHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText birthdayEditText;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout birthdayTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailEditText = (EditText) findViewById(R.id.activity_login_emailEditText);
        this.birthdayEditText = (EditText) findViewById(R.id.activity_login_birthdayEditText);
        this.emailTextInputLayout = (TextInputLayout) findViewById(R.id.activity_login_emailTextInputLayout);
        this.birthdayTextInputLayout = (TextInputLayout) findViewById(R.id.activity_login_birthdayTextInputLayout);

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
                LoginActivity.this,
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
    }

    // TODO onBackPressed

    private boolean checkEmailField() {
        String s = getEmailField();
        if (s.equals("")) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.activity_login_emailEditTextErrorBlank));
            this.emailEditText.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
            this.emailTextInputLayout.setErrorEnabled(true);
            this.emailTextInputLayout.setError(getText(R.string.activity_login_emailEditTextErrorPattern));
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
            this.birthdayTextInputLayout.setError(getText(R.string.activity_login_birthdayEditTextBlank));
            this.birthdayEditText.requestFocus();
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

    public void loginButtonOnClick(final View view) {
        if (checkEmailField() && checkBirthdayField()) {
            view.setEnabled(false);
            PklServiceHelper.login(LoginActivity.this, new PklServiceHelper.OnEventListener() {
                @Override
                public void onResultSuccess(Object result) {
                    Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, CatalogActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onResultFailed(int statusCode) {
                    view.setEnabled(true);
                    switch (statusCode) {
                        case PklServiceHelper.ERROR_REQUEST_TIMEOUT: {
                            Toast.makeText(LoginActivity.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case PklServiceHelper.ERROR_SERVICE_UNAVAILABLE: {
                            Toast.makeText(LoginActivity.this, "Server is unavailable", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case PklServiceHelper.ERROR_UNAUTHORIZED: {
                            Toast.makeText(LoginActivity.this, "Invalid email or birthday", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case PklServiceHelper.ERROR_INTERNAL_SERVER_ERROR: {
                            Toast.makeText(LoginActivity.this, "Internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            }, getEmailField(), getBirthdayField());
        }
    }

    public void registerButtonOnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
