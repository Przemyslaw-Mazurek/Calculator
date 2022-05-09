import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    JPanel displayPanel;
    JPanel numberPanel;
    JPanel operatorsPanel;
    JPanel clearPanel;
    JTextField displayField;
    JButton[] numberButtons = new JButton[10];
    JButton[] operatorButtons = new JButton[4];
    JButton clearButton;
    JButton equalButton;
    JButton dotButton;
    JButton negativeButton;
    final Font font = new Font("Monospaced", Font.ITALIC, 25);
    String number = "";
    String operator;
    double firstNumber;
    double result;


    Calculator() {
        this.setSize(500, 595);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(null);

        initializingNumberButtons(numberButtons);           //initialize number buttons by numerical values
        initializingOperatorButtons(operatorButtons);       //initialize operator buttons by mathematical operators

        displayField = new JTextField();
        displayField.setPreferredSize(new Dimension(300, 60));
        displayField.setFont(new Font("Monospaced", Font.BOLD, 30));
        displayField.setHorizontalAlignment(JTextField.CENTER);
        displayField.setEditable(false);
        displayField.setBackground(Color.white);
        displayField.addActionListener(this);

        displayPanel = new JPanel();                                //display panel
        displayPanel.setBounds(0, 0, 500, 80);
        displayPanel.setBackground(Color.lightGray);
        displayPanel.add(displayField);
        this.add(displayPanel);

        numberPanel = new JPanel();                                 //number panel
        numberPanel.setBounds(0, 80, 500, 300);
        numberPanel.setBackground(Color.lightGray);
        numberPanel.setLayout(new GridLayout(3, 4, 3, 3));

        for (int i = numberButtons.length - 1; i >= 0; i--) {
            numberPanel.add(numberButtons[i]);
        }


        dotButton = new JButton(".");
        dotButton.setFont(font);
        dotButton.addActionListener(this);
        dotButton.setFocusable(false);
        numberPanel.add(dotButton);

        negativeButton = new JButton("(-)");
        negativeButton.setFont(font);
        negativeButton.addActionListener(this);
        negativeButton.setFocusable(false);
        numberPanel.add(negativeButton);
        this.add(numberPanel);

        equalButton = new JButton("=");
        equalButton.setFont(font);
        equalButton.addActionListener(this);
        equalButton.setFocusable(false);

        operatorsPanel = new JPanel();                              //operator panel
        operatorsPanel.setBounds(0, 380, 500, 100);
        operatorsPanel.setBackground(Color.lightGray);
        operatorsPanel.setLayout(new GridLayout(1, 5, 1, 1));

        for (int i = 0; i < operatorButtons.length; i++) {
            operatorsPanel.add(operatorButtons[i]);
        }
        operatorsPanel.add(equalButton);

        this.add(operatorsPanel);

        clearButton = new JButton("Clear");
        clearButton.setFont(font);
        clearButton.addActionListener(this);
        clearButton.setFocusable(false);
        clearButton.setPreferredSize(new Dimension(500, 80));

        clearPanel = new JPanel();                                      //clear panel
        clearPanel.setBounds(0, 480, 500, 80);
        clearPanel.setLayout(new BorderLayout());
        clearPanel.setBackground(Color.lightGray);
        clearPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1, true));
        clearPanel.add(clearButton);
        this.add(clearPanel);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton instanceOfChoosenButton = (JButton) e.getSource();
        String buttonValue = instanceOfChoosenButton.getText();

        if (buttonValue.equals(negativeButton.getText())) {
            number = number.concat("-");
            displayField.setText(number);
        }

        for (int i = 0; i < numberButtons.length; i++) {
            if (numberButtons[i].getText().equals(buttonValue)){
                number = number.concat(buttonValue);
                displayField.setText(number);
            }
        }

        if (buttonValue.equals(".")){
            number = number.concat(buttonValue);
            displayField.setText(number );
        }


        for (int i = 0; i < operatorButtons.length; i++) {
            if (operatorButtons[i].getText().equals(buttonValue)){
                displayField.setText(buttonValue);
                operator = buttonValue;
                firstNumber = Double.parseDouble(number);
                number = "";
            }
        }

        if (buttonValue.equals("=")){

            switch (operator){
                case "+":
                    result = firstNumber + Double.parseDouble(displayField.getText());
                    break;
                case "-":
                    result = firstNumber - Double.parseDouble(displayField.getText());
                    break;
                case "*":
                    result = firstNumber * Double.parseDouble(displayField.getText());
                    break;
                case "/":
                    result = firstNumber / Double.parseDouble(displayField.getText());
            }

            if (result % 1 ==0){                          //if the result has no decimal part
                int intResult = (int) result;
                displayField.setText("" + intResult);
                number = "";
                firstNumber = 0;
            }else {
                displayField.setText("" + result);
                number = "";
                firstNumber = 0;
            }




        }

        if (buttonValue.equals("Clear")) {
            displayField.setText("");
            number = "";
            firstNumber = 0;
        }
    }

    public void initializingNumberButtons(JButton[] numberButtons) {

        for (int i = 0; i < numberButtons.length; i++) {
            JButton button = new JButton("" + i);
            button.setFocusable(false);
            button.setFont(font);
            button.addActionListener(this);
            numberButtons[i] = button;
        }


    }

    public void initializingOperatorButtons(JButton[] operatorButtons) {
        operatorButtons[0] = new JButton("+");
        operatorButtons[0].addActionListener(this);
        operatorButtons[0].setFocusable(false);
        operatorButtons[0].setFont(font);
        operatorButtons[1] = new JButton("-");
        operatorButtons[1].addActionListener(this);
        operatorButtons[1].setFocusable(false);
        operatorButtons[1].setFont(font);
        operatorButtons[2] = new JButton("*");
        operatorButtons[2].addActionListener(this);
        operatorButtons[2].setFocusable(false);
        operatorButtons[2].setFont(font);
        operatorButtons[3] = new JButton("/");
        operatorButtons[3].addActionListener(this);
        operatorButtons[3].setFocusable(false);
        operatorButtons[3].setFont(font);
    }

    public static boolean isNumber(String number) {
        boolean isNumber = true;

        try {
            Double.parseDouble(number);
        } catch (NumberFormatException e) {
            isNumber = false;
        }

        return isNumber;
    }

}
