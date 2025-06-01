package util;

import javax.swing.*;
import javax.swing.text.*;

public class TextFieldNumerico extends JTextField {
    
    public TextFieldNumerico(int columns) {
        super(columns);
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    private static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d+") && (fb.getDocument().getLength() + string.length() <= 11)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
            if (string.matches("\\d+") && (fb.getDocument().getLength() - length + string.length() <= 11)) {
                super.replace(fb, offset, length, string, attrs);
            }
        }
    }
}