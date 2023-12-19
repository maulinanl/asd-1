import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.text.NumberFormat;

public class Input extends PlainDocument {
    JFormattedTextField textField;
    private int limit;

    Input(int limit) {
        super();
        this.limit = limit;
        this.textField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        textField.setDocument(this); // Set document for the text field
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if (str.matches("\\d+")) {
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 9", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
