package sirius.views;

import sirius.utils.Constant;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(String title) {
        super(title);

        setSize(Constant.APP_WIDTH, Constant.APP_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setLocationRelativeTo(null);

        setResizable(false);
    }
}
