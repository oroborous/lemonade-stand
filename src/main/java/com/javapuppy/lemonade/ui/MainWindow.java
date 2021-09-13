package com.javapuppy.lemonade.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private final String DECISION = "decision";
    private final String RESULT = "result";

    private JTextField txtGlasses = new JTextField();
    private JTextField txtSigns = new JTextField();
    private JTextField txtPrice = new JTextField();
    private JLabel lblAssets = new JLabel();
    private JLabel lblCostPerGlass = new JLabel();
    private JLabel lblWeatherImage = new JLabel();
    private JTextArea txtSpecialEvents = new JTextArea();
    private CardLayout cardLayout = new CardLayout();

    private JLabel lblDay = new JLabel("Day 1");
    private JLabel lblStand = new JLabel("Stand 1");
    private JLabel lblGlassesMade = new JLabel("20 Glasses Made");
    private JLabel lblGlassesSold = new JLabel("20 Glasses Sold");
    private JLabel lblIncome = new JLabel("Income $1.00");
    private JLabel lblExpPerGlass = new JLabel("$.05 Per Glass");
    private JLabel lblSignsMade = new JLabel("1 Signs Made");
    private JLabel lblTotalExpenses = new JLabel("Expenses $.55");
    private JLabel lblProfit = new JLabel("Profit $.45");
    private JLabel lblNewAssets = new JLabel("Assets $2.45");

    class OpenStandAction extends AbstractAction {

        OpenStandAction() {
            this.putValue(Action.NAME, "Open Stand");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(MainWindow.this.getContentPane(), RESULT);
        }
    }

    class NextDayAction extends AbstractAction {
        NextDayAction() {
            this.putValue(Action.NAME, "Next Day");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(MainWindow.this.getContentPane(), DECISION);
        }
    }

    class ResultPanel extends JPanel {
        ResultPanel() {
            this.setLayout(new GridLayout(8, 2, 10, 10));
            this.add(lblDay);
            this.add(lblStand);
            this.add(lblGlassesSold);
            this.add(new JPanel());
            this.add(lblExpPerGlass);
            this.add(lblIncome);
            this.add(lblGlassesMade);
            this.add(new JPanel());
            this.add(lblSignsMade);
            this.add(lblTotalExpenses);
            this.add(new JPanel());
            this.add(lblProfit);
            this.add(new JPanel());
            this.add(lblNewAssets);
            this.add(new JPanel());
            this.add(new JButton(new NextDayAction()));
        }
    }

    class DecisionPanel extends JPanel {
        DecisionPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 3, 1, 1,
                    GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0);
            this.add(lblWeatherImage, gbc);
            gbc.gridheight = 1;
            gbc.gridx++;
            gbc.weighty = 0;
            gbc.insets.right = 0;
            this.add(new JLabel("Assets"), gbc);
            gbc.gridy++;
            gbc.weighty = 1;
            this.add(lblAssets, gbc);
            gbc.gridy++;
            gbc.weighty = 0;
            this.add(new JLabel("Cost per Glass"), gbc);
            gbc.gridy++;
            gbc.weighty = 1;
            this.add(lblCostPerGlass, gbc);
            gbc.gridy++;
            gbc.gridheight = 2;
            this.add(new DataEntryPanel(), gbc);
            gbc.gridheight = 1;

            gbc.gridx = 0;
            gbc.weighty = 0;
            this.add(new JLabel("Special Events"), gbc);
            gbc.gridy++;
            gbc.weighty = 1;
            gbc.insets.right = 10;
            this.add(txtSpecialEvents, gbc);
        }
    }

    class DataEntryPanel extends JPanel {
        DataEntryPanel() {
            this.setLayout(new GridLayout(4, 2, 5, 10));
            this.add(new JLabel("Glasses to make"));
            this.add(txtGlasses);
            this.add(new JLabel("Signs to make"));
            this.add(txtSigns);
            this.add(new JLabel("Price per glass"));
            this.add(txtPrice);
            this.add(new JPanel());
            this.add(new JButton(new OpenStandAction()));
        }
    }

    public MainWindow() {
        super("Lemonade Stand");
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        this.getContentPane().setLayout(cardLayout);

        this.getContentPane().add(new DecisionPanel(), DECISION);
        this.getContentPane().add(new ResultPanel(), RESULT);
    }
}
