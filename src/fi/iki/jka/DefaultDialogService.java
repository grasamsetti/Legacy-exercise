package fi.iki.jka;

import java.awt.Component;


public class DefaultDialogService implements DialogService {
    @Override
    public void showMessage(Component parent, String message, String title, int messageType) {
        javax.swing.JOptionPane.showMessageDialog(parent, message, title, messageType);
    }
}

