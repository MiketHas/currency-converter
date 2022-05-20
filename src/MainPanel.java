import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MainPanel extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 400;

    ImageIcon background;
    Image bcgImage;

    JComboBox currency1, currency2;
    JTextField inputField, resultField;
    JButton convertButton;
    double eur = 4.9, usd = 4.5, rub = 0.031, gbp = 5.9;

    DecimalFormat df = new DecimalFormat("0.00");

    MainPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(null);
        this.setOpaque(false);

        background = new ImageIcon(getClass().getClassLoader().getResource("curBcg.png"));
        bcgImage = background.getImage();

        currency1 = new JComboBox();
        currency1.setBounds(10, 10, 80, 30);
        currency1.addItem("PLN");
        currency1.addItem("EUR");
        currency1.addItem("USD");
        currency1.addItem("RUB");
        currency1.addItem("GBP");
        currency1.addActionListener(this);
        this.add(currency1);

        currency2 = new JComboBox();
        currency2.setBounds(100, 10, 80, 30);
        currency2.addItem("PLN");
        currency2.addItem("EUR");
        currency2.addItem("USD");
        currency2.addItem("RUB");
        currency2.addItem("GBP");
        currency2.addActionListener(this);
        this.add(currency2);

        inputField = new JTextField();
        inputField.setBounds(10, 40, 80, 30);
        inputField.addActionListener(this);
        this.add(inputField);

        resultField = new JTextField();
        resultField.setBounds(100, 40, 80, 30);
        resultField.addActionListener(this);
        this.add(resultField);

        convertButton = new JButton("Convert");
        convertButton.setBounds(10, 75, 170, 30);
        convertButton.addActionListener(this);
        this.add(convertButton);

    }

    public String convertToPLN(double value1, double value2) {
        double result = value1 * value2;
        String resultString = String.valueOf((df.format(result)));
        return resultString;
    }

    public String convertFromPLN(double value1, double value2) {
        double result = value1 / value2;
        String resultString = String.valueOf(df.format(result));
        return resultString;
    }

    public String convertForeignCorrency(double money, double value1, double value2) {
        double result = money * (value1 / value2);
        String resultString = String.valueOf(df.format(result));
        return resultString;
    }

    @Override
    public void paintComponent(Graphics g) { // background Image
        super.paintComponent(g);
        g.drawImage(bcgImage, 0,0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == inputField || source == convertButton) {
            String firstSelectedCurrency = currency1.getSelectedItem().toString();
            String secondSelectedCurrency = currency2.getSelectedItem().toString();

            double money = Double.parseDouble(inputField.getText());

            switch (firstSelectedCurrency) {
                case "PLN":
                    switch (secondSelectedCurrency) {
                        case "USD" -> resultField.setText(convertFromPLN(money, usd));
                        case "EUR" -> resultField.setText(convertFromPLN(money, eur));
                        case "GBP" -> resultField.setText(convertFromPLN(money, gbp));
                        case "RUB" -> resultField.setText(convertFromPLN(money, rub));
                    }
                    break;
                case "USD":
                    switch (secondSelectedCurrency) {
                        case "PLN" -> resultField.setText(convertToPLN(money, usd));
                        case "EUR" -> resultField.setText(convertForeignCorrency(money, usd, eur));
                        case "GBP" -> resultField.setText(convertForeignCorrency(money, usd, gbp));
                        case "RUB" -> resultField.setText(convertForeignCorrency(money, usd, rub));
                    }
                    break;
                case "EUR":
                    switch (secondSelectedCurrency) {
                        case "PLN" -> resultField.setText(convertToPLN(money, eur));
                        case "USD" -> resultField.setText(convertForeignCorrency(money, eur, usd));
                        case "GBP" -> resultField.setText(convertForeignCorrency(money, eur, gbp));
                        case "RUB" -> resultField.setText(convertForeignCorrency(money, eur, rub));
                    }
                    break;
                case "GBP":
                    switch (secondSelectedCurrency) {
                        case "PLN" -> resultField.setText(convertToPLN(money, gbp));
                        case "USD" -> resultField.setText(convertForeignCorrency(money, gbp, usd));
                        case "EUR" -> resultField.setText(convertForeignCorrency(money, gbp, eur));
                        case "RUB" -> resultField.setText(convertForeignCorrency(money, gbp, rub));
                    }
                    break;
                case "RUB":
                    switch (secondSelectedCurrency) {
                        case "PLN" -> resultField.setText(convertToPLN(money, rub));
                        case "USD" -> resultField.setText(convertForeignCorrency(money, rub, usd));
                        case "EUR" -> resultField.setText(convertForeignCorrency(money, rub, eur));
                        case "GBP" -> resultField.setText(convertForeignCorrency(money, rub, gbp));
                    }
                    break;
            }
        }
    }
}
