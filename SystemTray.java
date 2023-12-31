import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SystemTray {

    private static Image getImage() {
        Icon trayIcon = new ImageIcon("../Images/Tray.jpg");

        Image image = new BufferedImage(trayIcon.getIconWidth(), trayIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        trayIcon.paintIcon(new Panel(), image.getGraphics(), 0, 0);

        return image;
    }

    private static PopupMenu createPopupMenu(JFrame frame, TrayIcon icon) {
        PopupMenu menu = new PopupMenu();
        MenuItem open;
        if (Main.isEnglish)
            open = new MenuItem("Open Wavy Downloader...");
        else open = new MenuItem("Wavy Downloader را باز کن...");
        open.addActionListener(e -> {
            java.awt.SystemTray.getSystemTray().remove(icon);
            frame.setVisible(true);
        });
        menu.add(open);

        MenuItem exit;
        if (Main.isEnglish)
            exit = new MenuItem("Exit");
        else exit = new MenuItem("خروج");
        exit.addActionListener(e -> System.exit(0));
        menu.add(exit);

        return menu;
    }


    public static void goToTray(JFrame frame) {
        frame.setVisible(false);

        TrayIcon icon = new TrayIcon(getImage(),"Wavy Downloader");
        icon.setPopupMenu(createPopupMenu(frame, icon));

        icon.addActionListener(e -> {
            java.awt.SystemTray.getSystemTray().remove(icon);
            frame.setVisible(true);
        });

        try {
            java.awt.SystemTray.getSystemTray().add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

