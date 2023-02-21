package lesson8_FinalProject;

import lesson8_FinalProject.components.DigitJButton;
import lesson8_FinalProject.components.OperatorJButton;
import lesson8_FinalProject.listener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ApplicationForm extends JFrame {

    private JTextField inputField;

    public ApplicationForm(String title) {
        super(title);
        setBounds(850,300,400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);

        setJMenuBar(createMenu());

        add(createCenterPanel(), BorderLayout.CENTER);
//        add(new Button("Кнопка"), BorderLayout.WEST);

        setVisible(true);
    }



    public JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(new ClearFieldButtonListener(inputField));
        menuFile.add(clear);

        menuFile.add(new JMenuItem("Exit"));
        menuBar.add(menuFile);

        menuBar.add(new JMenuItem("Help"));
        menuBar.add(new JMenuItem("About"));

        JMenuItem exit = new JMenuItem("Exit");
//        exit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
        exit.addActionListener( e -> System.exit(0)); // Взаимозаменяемая запись. Компактная, но чуть сложнее к восприятию :) лямбда-выражение
        menuBar.add(exit);

        return menuBar;
    }
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        ActionListener buttonListener = new ButtonListener(inputField);

        centerPanel.add(createDigitsPanel(buttonListener), BorderLayout.CENTER);
        centerPanel.add(createOperatorsPanel(buttonListener), BorderLayout.EAST);

        return centerPanel;
    }

    private JPanel createTopPanel() {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        inputField = new JTextField();
        inputField.setEditable(false);
        top.add(inputField);
        inputField.setFont(new Font("Arial", Font.PLAIN,25));
        inputField.setMargin(new Insets(8,8,8,0));
        inputField.setBackground(new Color(138, 196, 4, 210));

        inputField.setText("");

        return  top;
    }

    private JPanel createDigitsPanel(ActionListener buttonListener) {
        JPanel digitsPanel = new JPanel();
        digitsPanel.setLayout(new GridLayout(4,3));

  //      JButton btn = new JButton();
 //       digitsPanel.add(btn);
        for (int i = 0; i < 10; i++){
            String buttonTitle = (i == 9) ? "0" : String.valueOf(i+1);
            JButton btn = new DigitJButton(buttonTitle);
//            btn.addActionListener(buttonListener);
            btn.addActionListener(new DigitsListener(inputField));
            digitsPanel.add(btn);
        }

        JButton calc = new OperatorJButton("=");
        calc.addActionListener(new CalcResultButtonListener(inputField));
        digitsPanel.add(calc);
        calc.setEnabled(true);

        JButton clear = new OperatorJButton("c");
        clear.addActionListener(new ClearFieldButtonListener(inputField));
        digitsPanel.add(clear);
        clear.setEnabled(true);

        return digitsPanel;
    }

    private JPanel createOperatorsPanel(ActionListener buttonListener) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        JButton minus = new OperatorJButton("-");
 //       minus.addActionListener(buttonListener);
        minus.addActionListener(new OperatorsListener(inputField));

        panel.add(minus);

        JButton plus = new OperatorJButton("+");
//        plus.addActionListener(buttonListener);
        plus.addActionListener(new OperatorsListener(inputField));

        panel.add(plus);

        JButton multiply = new OperatorJButton("x");
//        multiply.addActionListener(buttonListener);
        multiply.addActionListener(new OperatorsListener(inputField));
        panel.add(multiply);

        JButton divide = new OperatorJButton("/");
//        divide.addActionListener(buttonListener);
        divide.addActionListener(new OperatorsListener(inputField));
        panel.add(divide);

        return panel;
    }

}
