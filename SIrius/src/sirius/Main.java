/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sirius;


import sirius.utils.Session;
import sirius.views.DashboardView;
import sirius.views.auth.LoginView;
import sirius.views.auth.RegisterView;

import javax.swing.SwingUtilities;
import java.sql.SQLException;

/**
 *
 * @author afrizza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if(Session.isLoggedIn()) {
                    new DashboardView(Session.getCurrentUser()).setVisible(true);
                } else {
                    new LoginView().setVisible(true);
                }
            }
        });

//        Session.clearSession();
    }

}
