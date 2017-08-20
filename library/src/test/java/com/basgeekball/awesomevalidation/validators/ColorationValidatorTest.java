package com.basgeekball.awesomevalidation.validators;

import android.text.SpannableStringBuilder;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.helper.SpanHelper;
import com.basgeekball.awesomevalidation.utility.ValidationCallback;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.regex.Matcher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ColorationValidator.class, SpanHelper.class})
public class ColorationValidatorTest extends TestCase {

    private ColorationValidator mSpiedColorationValidator;
    private ValidationHolder mMockValidationHolder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mSpiedColorationValidator = spy(ColorationValidator.class);
        mMockValidationHolder = mock(ValidationHolder.class);
        mSpiedColorationValidator.mValidationHolderList.add(mMockValidationHolder);
    }

    public void testValidationCallbackExecute() {
        ValidationCallback validationCallback = Whitebox.getInternalState(mSpiedColorationValidator, "mValidationCallback");
        Matcher mockMatcher = PowerMockito.mock(Matcher.class);
        EditText mockEditText = mock(EditText.class);
        String mockErrMsg = PowerMockito.mock(String.class);
        SpannableStringBuilder mockEditable = PowerMockito.mock(SpannableStringBuilder.class);
        when(mMockValidationHolder.getEditText()).thenReturn(mockEditText);
        when(mMockValidationHolder.getErrMsg()).thenReturn(mockErrMsg);
        when(mockEditText.getText()).thenReturn(mockEditable);
        when(mockEditable.length()).thenReturn(PowerMockito.mock(Integer.class));
        PowerMockito.mockStatic(SpanHelper.class);
        validationCallback.execute(mMockValidationHolder, mockMatcher);
        verify(mockEditText).setError(mockErrMsg);
        PowerMockito.verifyStatic(SpanHelper.class);
    }

    public void testHalt() {
        EditText mockEditText = mock(EditText.class);
        when(mMockValidationHolder.getEditText()).thenReturn(mockEditText);
        PowerMockito.mockStatic(SpanHelper.class);
        mSpiedColorationValidator.halt();
        verify(mockEditText).setError(null);
        PowerMockito.verifyStatic(SpanHelper.class);
    }

}
