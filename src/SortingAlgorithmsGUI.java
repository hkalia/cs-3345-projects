import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class SortingAlgorithmsGUI {


    private JPanel rootPanel = new JPanel(new GridBagLayout(), true);

//    private JButton hamburgerMenu = new JButton("M");
//    private JLabel programmer = new JLabel("Programmer");
//    private JLabel fullExpressionLabel = new JLabel("", SwingConstants.RIGHT);
//    private JLabel hexLabel = new JLabel("0");
//    private JLabel expressionLabel = new JLabel("0", SwingConstants.RIGHT);
//    private JLabel decLabel = new JLabel("0");
//    private JButton hex = new JButton("HEX");
//    private JLabel binLabel = new JLabel("0");
//    private JButton upArrow = new JButton("↑");
//    private JButton mod = new JButton("Mod");
//    private JButton keyCE = new JButton("CE");
//    private JButton keyClear = new JButton("C");
//    private JButton delete = new JButton("⌫");
//    private JButton div = new JButton("÷");
//    private JButton keyA = new JButton("A");
//    private JButton keyB = new JButton("B");
//    private JButton key7 = new JButton("7");
//    private JButton key8 = new JButton("8");
//    private JButton key9 = new JButton("9");
//    private JButton mul = new JButton("✕");
//    private JButton keyC = new JButton("C");
//    private JButton keyD = new JButton("D");
//    private JButton key4 = new JButton("4");
//    private JButton key5 = new JButton("5");
//    private JButton key6 = new JButton("6");
//    private JButton sub = new JButton("-");
//    private JButton keyE = new JButton("E");
//    private JButton keyF = new JButton("F");
//    private JButton key1 = new JButton("1");
//    private JButton key2 = new JButton("2");
//    private JButton key3 = new JButton("3");
//    private JButton add = new JButton("+");
//    private JButton openParen = new JButton("(");
//    private JButton closeParen = new JButton(")");
//    private JButton neg = new JButton("±");
//    private JButton key0 = new JButton("0");
//    private JButton point = new JButton(".");
//    private JButton equal = new JButton("=");
//    private JButton dec = new JButton("DEC");
//    private JButton bin = new JButton("BIN");


//    private JButton[] allButtons = new JButton[]{hamburgerMenu, hex, dec, bin, upArrow, mod, keyCE, keyClear, delete, div, keyA, keyB, key7, key8, key9, mul, keyC, keyD, key4, key5, key6, sub, keyE, keyF, key1, key2, key3, add, openParen, closeParen, neg, key0, point, equal};
//    private JButton[] padButtons = new JButton[]{upArrow, mod, keyCE, keyClear, delete, div, keyA, keyB, key7, key8, key9, mul, keyC, keyD, key4, key5, key6, sub, keyE, keyF, key1, key2, key3, add, openParen, closeParen, neg, key0, point, equal};
//    private JButton[] valueKeys = new JButton[]{key0, key1, key2, key3, key4, key5, key6, key7, key8, key9, keyA, keyB, keyC, keyD, keyE, keyF};
//    private JButton[] numKeys = new JButton[]{key0, key1, key2, key3, key4, key5, key6, key7, key8, key9};
//    private JButton[] letterKeys = new JButton[]{keyA, keyB, keyC, keyD, keyE, keyF};
//    private JButton[] operatorKeys = new JButton[]{mod, div, mul, sub, add, openParen, closeParen, equal};
//    private JButton[] otherKeys = new JButton[]{keyCE, keyClear, delete, neg};

    // current base of calculator
    private enum Base {
        DECIMAL(10), HEX(16), BINARY(2);

        private int value;

        Base(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }
    private Base mode = Base.DECIMAL;

    // main vars for manipulating the mathematical expressions
    private StringBuilder currentExpression = new StringBuilder();
    private double currentResult = 0;
    private ArrayList<String> fullExpression = new ArrayList<String>();

    // flag any clearing that needs to happen on next button press
    private boolean needFullClear = false;
    private boolean needClear = false;

    // main colors
    private Color mainBackground = new Color(242, 242, 242);
    private Color buttonBackground = new Color(230, 230, 230);

    private Calculator() {
        setupUI();

        ActionListener valueKeysListener = event -> {
            JButton src = (JButton) event.getSource();
            checkClears();
            if (src == key0) {
                if (!(currentExpression.length() == 1 && currentExpression.charAt(0) == '0')) {
                    currentExpression.append("0");
                }
            } else {
                currentExpression.append(src.getText());
            }
            setLabels();
        };
        for (JButton valueKey : valueKeys) valueKey.addActionListener(valueKeysListener);

        ActionListener operatorKeysListener = event -> {
            Object src = event.getSource();
            checkClears();
            // add current number to expression
            if (currentExpression.length() > 0) {
                try {
                    fullExpression.add(Long.toString(Long.parseLong(currentExpression.toString(), mode.getValue())));
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            currentExpression.setLength(0);
            String tmp;
            try {
                tmp = Long.toString(Math.round(ExpressionEvaluator
                        .evaluateExpression(fullExpression)), mode.getValue());
            } catch (Exception e) {
                e.printStackTrace();
                tmp = "";
            }
            if (!tmp.equals("") && src != equal) {
                currentExpression.append(tmp);
                needClear = true;
            }
            if (src == mod) fullExpression.add("%");
            else if (src == div) fullExpression.add("/");
            else if (src == mul) fullExpression.add("*");
            else if (src == sub) fullExpression.add("-");
            else if (src == add) fullExpression.add("+");
            else if (src == openParen) fullExpression.add("(");
            else if (src == closeParen) fullExpression.add(")");
            else if (src == equal) {
                try {
                    currentExpression.setLength(0);
                    currentExpression.append(Long.toString(Math.round(ExpressionEvaluator
                            .evaluateExpression(fullExpression)), mode.getValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                    fullExpression.clear();
                }
                fullExpression.clear();
            }
            setLabels();
        };
        for (JButton operatorKey : operatorKeys) operatorKey.addActionListener(operatorKeysListener);

        ActionListener baseChangeListener = event -> {
            Object src = event.getSource();
            checkClears();
            // reset borders
            hex.setBorder(BorderFactory.createEmptyBorder());
            dec.setBorder(BorderFactory.createEmptyBorder());
            bin.setBorder(BorderFactory.createEmptyBorder());
            if (src == hex) {
                currentExpression.setLength(0);
                fullExpression.clear();
                for (JButton valueKey : valueKeys) {
                    valueKey.setEnabled(true);
                }
                hex.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mode = Base.HEX;
            } else if (src == dec) {
                currentExpression.setLength(0);
                fullExpression.clear();
                for (JButton numKey : numKeys) {
                    numKey.setEnabled(true);
                }
                for (JButton letterKey : letterKeys) {
                    letterKey.setEnabled(false);
                }
                dec.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mode = Base.DECIMAL;
            } else if (src == bin) {
                currentExpression.setLength(0);
                fullExpression.clear();
                for (JButton valueKey : valueKeys) {
                    valueKey.setEnabled(false);
                }
                key0.setEnabled(true);
                key1.setEnabled(true);
                bin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mode = Base.BINARY;
            }
            setLabels();
        };
        hex.addActionListener(baseChangeListener);
        dec.addActionListener(baseChangeListener);
        bin.addActionListener(baseChangeListener);

        ActionListener otherKeysListener = event -> {
            Object src = event.getSource();
            checkClears();
            if (src == keyCE) currentExpression.setLength(0);
            else if (src == keyClear) {
                currentExpression.setLength(0);
                fullExpression.clear();
            } else if (src == delete) {
                currentExpression.deleteCharAt(currentExpression.length() - 1);
            } else if (src == neg) {
                if (currentExpression.length() > 0 && currentExpression.charAt(0) == '-')
                    currentExpression.deleteCharAt(0);
                else currentExpression.insert(0, "-");
            }
            setLabels();
        };
        for (JButton otherKey : otherKeys) otherKey.addActionListener(otherKeysListener);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        frame.setContentPane(new Calculator().rootPanel);

        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // get 2/3 of the height, and 1/3 of the width
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width / 3;

        // set the frame height and width
        frame.setSize(new Dimension(width, height));

        //Display the window.
        frame.setVisible(true);
    }

    private void setupUI() {
        // position elements in grid and set appropriate weights
        rootPanel.add(hamburgerMenu, new GridBagConstraints(0, 0, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(programmer, new GridBagConstraints(1, 0, GridBagConstraints.REMAINDER, 1, 0.1, 0.1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(fullExpressionLabel, new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, 1, 0.1, 0.1, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(expressionLabel, new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, 1, 0.1, 0.3, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(hex, new GridBagConstraints(0, 3, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(hexLabel, new GridBagConstraints(1, 3, GridBagConstraints.REMAINDER, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(dec, new GridBagConstraints(0, 4, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(decLabel, new GridBagConstraints(1, 4, GridBagConstraints.REMAINDER, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(bin, new GridBagConstraints(0, 5, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(binLabel, new GridBagConstraints(1, 5, GridBagConstraints.REMAINDER, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        for (int i = 0; i < padButtons.length; i++) {
            rootPanel.add(padButtons[i], new GridBagConstraints((i % 6), (i / 6) + 6, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        // Adjust style of elements (background, border, padding)
        for (JButton button : allButtons) {
            button.setBorder(BorderFactory.createEmptyBorder());
        }
        for (JButton padButton : padButtons) {
            padButton.setBackground(buttonBackground);
            padButton.setOpaque(true);
        }
        rootPanel.setBackground(mainBackground);
        hamburgerMenu.setBackground(mainBackground);
        hex.setBackground(mainBackground);
        dec.setBackground(mainBackground);
        bin.setBackground(mainBackground);

        expressionLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        fullExpressionLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        hexLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        decLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        binLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        dec.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        upArrow.setEnabled(false);
        point.setEnabled(false);
        for (JButton letterKey : letterKeys) {
            letterKey.setEnabled(false);
        }
    }

    private void checkClears() {
        if (needFullClear) {
            needFullClear = false;
            currentExpression.setLength(0);
            fullExpression.clear();
        }
        if (needClear) {
            needClear = false;
            currentExpression.setLength(0);
        }
    }

    private void setLabels() {
        fullExpressionLabel.setText(listToString(fullExpression));
        if (currentExpression.length() == 0) {
            decLabel.setText("0");
            hexLabel.setText("0");
            binLabel.setText("0");
            expressionLabel.setText("0");
            return;
        }
        decLabel.setText(Long.toString(Long.parseLong(currentExpression.toString(), mode.getValue())));
        hexLabel.setText(splitStringAtN(
                Long.toHexString(Long.parseLong(currentExpression.toString(), mode.getValue())), 2));
        binLabel.setText("<html>"
                + splitStringAtN(
                Long.toBinaryString(Long.parseLong(currentExpression.toString(), mode.getValue())), 4)
                + "</html>");
        switch (mode) {
            case DECIMAL:
                expressionLabel.setText(decLabel.getText());
                break;
            case HEX:
                expressionLabel.setText(hexLabel.getText());
                break;
            case BINARY:
                expressionLabel.setText(binLabel.getText());
                break;
        }
    }

    private String splitStringAtN(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i != 0 && (s.length() - i) % n == 0)
                sb.append(" ");
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private String listToString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }
}
