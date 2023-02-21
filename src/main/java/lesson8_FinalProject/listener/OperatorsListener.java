package lesson8_FinalProject.listener;

import lesson8_FinalProject.components.CalcBuffer;
import lesson8_FinalProject.components.MathOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorsListener extends ButtonListener implements ActionListener {
    public OperatorsListener(JTextField inputField) {
        super(inputField);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        getInputField().setText(getInputField().getText()+ " " + btn.getText());

        //inputField.setText(inputField.getText()+ " " + btn.getText());

        MathOperation operationMath = null;
  /*
        for(int i = 0; i < getAllMathOperations().length; i++){
            if ()
        }
        getAllMathOperations();
 */

        switch (btn.getText().intern()){
            case ("+"):
                operationMath = MathOperation.addition;
                break;
            case ("-"):
                operationMath = MathOperation.subtraction;
                break;
            case ("x"):

                operationMath = MathOperation.multiplication;
                break;
            case ("/"):
                operationMath = MathOperation.division;
                break;
        }

        CalcBuffer.addOperationBuffer(operationMath);

        CalcBuffer.transferInputBuffer();
        CalcBuffer.setExpressionStatus(CalcBuffer.ExpressionStatus.newExpression);

    }
}
