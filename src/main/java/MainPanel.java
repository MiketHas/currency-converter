package main.java;

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

    DecimalFormat df = new DecimalFormat("0.00");

    final String eur = "EUR";
    final String usd = "USD";
    final String hkd = "HKD";
    final String gbp = "GBP";
    final String pln = "PLN";

    double currencyEUR = Currencies.eur;
    double currencyUSD = Currencies.usd;
    double currencyHKD = Currencies.hkd;
    double currencyGBP = Currencies.gbp;

    MainPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(null);
        this.setOpaque(false);

        background = new ImageIcon(getClass().getClassLoader().getResource("curBcg.png"));
        bcgImage = background.getImage();

        currency1 = new JComboBox();
        currency1.setBounds(10, 10, 80, 30);
        currency1.addItem(pln);
        currency1.addItem(eur);
        currency1.addItem(usd);
        currency1.addItem(hkd);
        currency1.addItem(gbp);
        currency1.addActionListener(this);
        this.add(currency1);

        currency2 = new JComboBox();
        currency2.setBounds(100, 10, 80, 30);
        currency2.addItem(pln);
        currency2.addItem(eur);
        currency2.addItem(usd);
        currency2.addItem(hkd);
        currency2.addItem(gbp);
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
                case pln:
                    switch (secondSelectedCurrency) {
                        case usd -> resultField.setText(convertFromPLN(money, currencyUSD));
                        case eur -> resultField.setText(convertFromPLN(money, currencyEUR));
                        case gbp -> resultField.setText(convertFromPLN(money, currencyGBP));
                        case hkd -> resultField.setText(convertFromPLN(money, currencyHKD));
                    }
                    break;
                case usd:
                    switch (secondSelectedCurrency) {
                        case pln -> resultField.setText(convertToPLN(money, currencyUSD));
                        case eur -> resultField.setText(convertForeignCorrency(money, currencyUSD, currencyEUR));
                        case gbp -> resultField.setText(convertForeignCorrency(money, currencyUSD, currencyGBP));
                        case hkd -> resultField.setText(convertForeignCorrency(money, currencyUSD, currencyHKD));
                    }
                    break;
                case eur:
                    switch (secondSelectedCurrency) {
                        case pln -> resultField.setText(convertToPLN(money, currencyEUR));
                        case usd -> resultField.setText(convertForeignCorrency(money, currencyEUR, currencyUSD));
                        case gbp -> resultField.setText(convertForeignCorrency(money, currencyEUR, currencyGBP));
                        case hkd -> resultField.setText(convertForeignCorrency(money, currencyEUR, currencyHKD));
                    }
                    break;
                case gbp:
                    switch (secondSelectedCurrency) {
                        case pln -> resultField.setText(convertToPLN(money, currencyGBP));
                        case usd -> resultField.setText(convertForeignCorrency(money, currencyGBP, currencyUSD));
                        case eur -> resultField.setText(convertForeignCorrency(money, currencyGBP, currencyEUR));
                        case hkd -> resultField.setText(convertForeignCorrency(money, currencyGBP, currencyHKD));
                    }
                    break;
                case hkd:
                    switch (secondSelectedCurrency) {
                        case pln -> resultField.setText(convertToPLN(money, currencyHKD));
                        case usd -> resultField.setText(convertForeignCorrency(money, currencyHKD, currencyUSD));
                        case eur -> resultField.setText(convertForeignCorrency(money, currencyHKD, currencyEUR));
                        case gbp -> resultField.setText(convertForeignCorrency(money, currencyHKD, currencyGBP));
                    }
                    break;
            }
        }
    }
}
