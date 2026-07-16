package sirius.views.auth;

import sirius.utils.Constant;
import sirius.controllers.RegisterController;
import sirius.views.Frame;
import sirius.views.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RegisterView extends Frame {

    private JTextField namaField, emailField, nimField;
    private JPasswordField passwordField, rePasswordField;
    private JButton registerButton;
    private JLabel loginLink, namaLabel, emailLabel, passwordLabel, rePasswordLabel, nimLabel;

    public RegisterView() {
        super("Register");
        setContentPane(createPanel());

        addFieldFocusListener();

        getRootPane().setDefaultButton(registerButton);

        new RegisterController(this);
    }

    private JPanel createPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Constant.PRIMARY_COLOR);

        mainPanel.add(createLeftPanel(), BorderLayout.CENTER);
        
        mainPanel.add(createRightPanel(), BorderLayout.EAST);

        return mainPanel;
    }

    private JPanel createRightPanel() {
        JPanel logoPanel = new JPanel(); 
        logoPanel.setBackground(Constant.PRIMARY_COLOR);
        logoPanel.setPreferredSize(new Dimension(Constant.APP_WIDTH / 2, Constant.APP_HEIGHT));

        JLabel logoLabel = new JLabel("SIRIUS");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setForeground(Constant.SNOW_COLOR);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        logoPanel.setLayout(new GridBagLayout());
        logoPanel.add(logoLabel);

        return logoPanel;
    }

    private JPanel createLeftPanel() {
        RoundedPanel formPanel = new RoundedPanel(20, true);
        formPanel.setBackground(Constant.SNOW_COLOR);
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0); // Jarak default antar elemen vertikal

        // TITLE
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span full width
        gbc.insets = new Insets(0, 0, 20, 0);
        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Constant.PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(titleLabel, gbc);

        JPanel row1Panel = new JPanel(new GridLayout(1, 2, 12, 0)); // 15 = gap horizontal
        row1Panel.setBackground(Constant.SNOW_COLOR);

        JPanel namaContainer = new JPanel(new BorderLayout(0, 5));
        namaContainer.setBackground(Constant.SNOW_COLOR);
        namaLabel = new JLabel("Nama");
        namaLabel.setFont(Constant.FIELD_LABEL_FONT);
        namaLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        namaField = new JTextField();
        namaField.setFont(Constant.FIELD_FONT);
        namaField.setPreferredSize(new Dimension(200, 40));
        namaContainer.add(namaLabel, BorderLayout.NORTH);
        namaContainer.add(namaField, BorderLayout.CENTER);

        JPanel nimContainer = new JPanel(new BorderLayout(0, 5));
        nimContainer.setBackground(Constant.SNOW_COLOR);
        nimLabel = new JLabel("Nim");
        nimLabel.setFont(Constant.FIELD_LABEL_FONT);
        nimLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        nimField = new JTextField();
        nimField.setFont(Constant.FIELD_FONT);
        nimField.setPreferredSize(new Dimension(200, 40));
        nimContainer.add(nimLabel, BorderLayout.NORTH);
        nimContainer.add(nimField, BorderLayout.CENTER);

        row1Panel.add(namaContainer);
        row1Panel.add(nimContainer);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        formPanel.add(row1Panel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        emailLabel = new JLabel("Email");
        emailLabel.setFont(Constant.FIELD_LABEL_FONT);
        emailLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        emailField = new JTextField();
        emailField.setFont(Constant.FIELD_FONT);
        emailField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(Constant.FIELD_LABEL_FONT);
        passwordLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        passwordField = new JPasswordField();
        passwordField.setFont(Constant.FIELD_FONT);
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constant.NOTFOCUS_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        rePasswordLabel = new JLabel("Confirm Password");
        rePasswordLabel.setFont(Constant.FIELD_LABEL_FONT);
        rePasswordLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        formPanel.add(rePasswordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        rePasswordField = new JPasswordField();
        rePasswordField.setFont(Constant.FIELD_FONT);
        rePasswordField.setPreferredSize(new Dimension(300, 40));
        rePasswordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constant.NOTFOCUS_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(rePasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 10, 0);
        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setForeground(Constant.SNOW_COLOR);
        registerButton.setBackground(Constant.PRIMARY_COLOR);
        registerButton.setPreferredSize(new Dimension(300, 40));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBorder(BorderFactory.createLineBorder(Constant.PRIMARY_COLOR));
        formPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        JPanel loginPanel = createLoginLinkPanel();
        formPanel.add(loginPanel, gbc);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(Constant.PRIMARY_COLOR);
        wrapperPanel.setBorder(new EmptyBorder(25, 30, 25, 30)); 
        wrapperPanel.add(formPanel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private JPanel createLoginLinkPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setBackground(Constant.SNOW_COLOR);

        JLabel textLabel = new JLabel("Sudah punya akun?");
        textLabel.setFont(Constant.FIELD_LABEL_FONT);
        textLabel.setForeground(Constant.FOREGROUND_GRAY_COLOR);
        panel.add(textLabel);

        loginLink = new JLabel("Login");
        loginLink.setFont(Constant.FIELD_LABEL_FONT);
        loginLink.setForeground(Constant.PRIMARY_COLOR);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginLink);

        return panel;
    }

    public void addFieldFocusListener() {
        
        namaField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                Constant.onFocus(namaField, namaLabel);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                Constant.onFocusLost(namaField, namaLabel);
            }
        });

        nimField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                Constant.onFocus(nimField, nimLabel);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                Constant.onFocusLost(nimField, nimLabel);
            }
        });

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

        if(rePasswordField != null) {
            rePasswordField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent focusEvent) {
                    Constant.onFocus(rePasswordField, rePasswordLabel);
                }

                @Override
                public void focusLost(FocusEvent focusEvent) {
                   Constant.onFocusLost(rePasswordField, rePasswordLabel);
                }
            });
        }
    }

    public JTextField getNamaField() {
        return namaField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getNimField() {return nimField;}

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getRePasswordField() {
        return rePasswordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JLabel getLoginLink() {
        return loginLink;
    }

}