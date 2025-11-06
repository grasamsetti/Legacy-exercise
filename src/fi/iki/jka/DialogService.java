package fi.iki.jka;

import java.awt.Component;

public interface DialogService {
    void showMessage(Component parent, String message, String title, int messageType);
}
