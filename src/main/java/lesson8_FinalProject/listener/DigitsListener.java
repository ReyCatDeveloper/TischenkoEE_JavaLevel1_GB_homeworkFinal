package lesson8_FinalProject.listener;

import lesson8_FinalProject.components.CalcBuffer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static lesson8_FinalProject.components.CalcBuffer.ExpressionStatus.endExpression;
import static lesson8_FinalProject.components.CalcBuffer.ExpressionStatus.newExpression;

public class DigitsListener extends ButtonListener implements ActionListener {
    public DigitsListener(JTextField inputField) {
        super(inputField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(CalcBuffer.getExpressionStatus() == endExpression){
            getInputField().setText("");
            CalcBuffer.setInputBuffer("");    //TODO <-- добавил
            CalcBuffer.setExpressionStatus(newExpression);
        }

        JButton btn = (JButton) e.getSource();
        getInputField().setText(getInputField().getText()+ " " + btn.getText());

        //inputField.setText(inputField.getText()+ " " + btn.getText());

        CalcBuffer.addInputBuffer(btn.getText());

    }
}
