package sirius.views.auth;

import sirius.utils.Constant;

import sirius.controllers.LoginController;

import sirius.views.Frame;
import sirius.views.components.RoundedPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginView extends Frame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel registerLink, emailLabel, passwordLabel;

    public LoginView() {
        super("Login");
        setContentPane(createPanel());

        addFieldFocusListener();

        getRootPane().setDefaultButton(loginButton);

        new LoginController(this);
    }

    private JPanel createPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Constant.PRIMARY_COLOR);

        mainPanel.add(createLeftPanel(), BorderLayout.WEST);

        mainPanel.add(createRightPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Constant.PRIMARY_COLOR);
        leftPanel.setPreferredSize(new Dimension(Constant.APP_WIDTH / 2, Constant.APP_HEIGHT));

        JLabel logoLabel = new JLabel("SIRIUS");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setForeground(Constant.SNOW_COLOR);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel.setLayout(new GridBagLayout());
        leftPanel.add(logoLabel);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        RoundedPanel rightPanel = new RoundedPanel(20, true);
        rightPanel.setBackground(Constant.SNOW_COLOR);
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 40, 0);
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Constant.PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 5, 0);
        emailLabel = new JLabel("Email");
        emailLabel.setFont(Constant.FIELD_LABEL_FONT);
        emailLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        rightPanel.add(emailLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);

        emailField = new JTextField();
        emailField.setFont(Constant.FIELD_FONT);
        emailField.setPreferredSize(new Dimension(300, 40));

        rightPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 5, 0);
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(Constant.FIELD_LABEL_FONT);
        passwordLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        rightPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        JPanel passwordPanel = createPasswordPanel();
        rightPanel.add(passwordPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setForeground(Constant.SNOW_COLOR);
        loginButton.setBackground(Constant.PRIMARY_COLOR);
        loginButton.setBorder(null);
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        JPanel registerPanel = createRegisterPanel();
        rightPanel.add(registerPanel, gbc);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        
        wrapperPanel.setBackground(Constant.PRIMARY_COLOR);
        
        wrapperPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        wrapperPanel.add(rightPanel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(Constant.SNOW_COLOR);

        passwordField = new JPasswordField();
        passwordField.setFont(Constant.FIELD_LABEL_FONT);
        passwordField.setPreferredSize(new Dimension(250, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constant.NOTFOCUS_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(passwordField, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setBackground(Constant.SNOW_COLOR );

        JLabel textLabel = new JLabel("Belum punya akun?");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        panel.add(textLabel);

        registerLink = new JLabel("Register");
        registerLink.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLink.setForeground(Constant.PRIMARY_COLOR);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(registerLink);

        return panel;
    }

    public void addFieldFocusListener() {
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                Constant.onFocus(emailField, emailLabel);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                Constant.onFocusLost(emailField, emailLabel);
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                Constant.onFocus(passwordField, passwordLabel);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                Constant.onFocusLost(passwordField, passwordLabel);
            }
        });
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getRegisterLink() {
        return registerLink;
    }

}