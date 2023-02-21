package lesson8_FinalProject.listener;

import lesson8_FinalProject.components.CalcBuffer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcResultButtonListener implements ActionListener {

    private JTextField inputField;

    public CalcResultButtonListener(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CalcBuffer.transferInputBuffer();
        CalcBuffer.calculateResult();
        inputField.setText(CalcBuffer.getResult());
        CalcBuffer.setExpressionStatus(CalcBuffer.ExpressionStatus.endExpression);
    }
}
