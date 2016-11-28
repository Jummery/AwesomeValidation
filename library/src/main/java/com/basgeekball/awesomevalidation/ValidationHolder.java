package com.basgeekball.awesomevalidation;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.model.NumericRange;

import java.util.regex.Pattern;

public class ValidationHolder {

    private EditText mEditText;
    private EditText mConfirmationEditText;
    private TextInputLayout mTextInputLayout;
    private TextInputLayout mConfirmationTextInputLayout;
    private Pattern mPattern;
    private NumericRange mNumericRange;
    private String mErrMsg;

    public ValidationHolder(EditText editText, Pattern pattern, String errMsg) {
        mEditText = editText;
        mPattern = pattern;
        mErrMsg = errMsg;
    }

    public ValidationHolder(EditText editText, NumericRange numericRange, String errMsg) {
        mEditText = editText;
        mNumericRange = numericRange;
        mErrMsg = errMsg;
    }

    public ValidationHolder(EditText confirmationEditText, EditText editText, String errMsg) {
        mConfirmationEditText = confirmationEditText;
        mEditText = editText;
        mErrMsg = errMsg;
    }

    public ValidationHolder(TextInputLayout textInputLayout, Pattern pattern, String errMsg) {
        mTextInputLayout = textInputLayout;
        mPattern = pattern;
        mErrMsg = errMsg;
    }

    public ValidationHolder(TextInputLayout textInputLayout, NumericRange numericRange, String errMsg) {
        mTextInputLayout = textInputLayout;
        mNumericRange = numericRange;
        mErrMsg = errMsg;
    }

    public ValidationHolder(TextInputLayout confirmationTextInputLayout, TextInputLayout textInputLayout, String errMsg) {
        mConfirmationTextInputLayout = confirmationTextInputLayout;
        mTextInputLayout = textInputLayout;
        mErrMsg = errMsg;
    }

    public boolean isRegexType() {
        return mPattern != null;
    }

    public boolean isRangeType() {
        return mNumericRange != null;
    }

    public boolean isConfirmationType() {
        return mConfirmationEditText != null || mConfirmationTextInputLayout != null;
    }

    public boolean isEditTextStyle() {
        return mEditText != null;
    }

    public boolean isTextInputLayoutStyle() {
        return mTextInputLayout != null;
    }

    public Pattern getPattern() {
        return mPattern;
    }

    public NumericRange getNumericRange() {
        return mNumericRange;
    }

    public String getErrMsg() {
        return mErrMsg;
    }

    public String getText() {
        if (mEditText != null) {
            return mEditText.getText().toString();
        } else {
            return mTextInputLayout.getEditText().getText().toString();
        }
    }

    public String getConfirmationText() {
        if (mConfirmationEditText != null) {
            return mConfirmationEditText.getText().toString();
        } else {
            return mConfirmationTextInputLayout.getEditText().getText().toString();
        }
    }

    public EditText getEditText() {
        if (isEditTextStyle()) {
            return isConfirmationType() ? mConfirmationEditText : mEditText;
        } else {
            return isConfirmationType() ? mConfirmationTextInputLayout.getEditText() : mTextInputLayout.getEditText();
        }
    }

    public TextInputLayout getTextInputLayout() {
        return isConfirmationType() ? mConfirmationTextInputLayout : mTextInputLayout;
    }

}
