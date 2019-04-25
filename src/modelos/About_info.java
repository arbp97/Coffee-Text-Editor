package modelos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class About_info extends JFrame {

    private JPanel basePanel;
    private JLabel infoLabel;
    private ImageIcon icon;

    public About_info(Editor ed) {

        this.setTitle("About");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(ed);
        setResizable(false);
        setSize(300,300);

        basePanel = new JPanel();
        infoLabel = new JLabel("<html>Coffee Text Editor<br>" +
                                    " 1.0 <br>" +
                                    " Made by Alan Blangille <br>" +
                                    " License: GPL v2.0 <br>" +
                                    "Github: www.github.com/arbp97" + "</html>");

        if(ed.getOsVersion().equals("Windows")) {
            icon = new ImageIcon(this.getClass().getResource("\\resources\\coffee-cup.png"));
        }else {
            icon = new ImageIcon(this.getClass().getResource("/resources/coffee-cup.png"));
        }

        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);  // transform it back

        infoLabel.setIcon(icon);

        this.add(infoLabel);
        this.pack();

    }

    private Image getScaledImage(Image srcImg, int w, int h){//this is used to scale images

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public JPanel getBasePanel() {
        return basePanel;
    }

    public void setBasePanel(JPanel basePanel) {
        this.basePanel = basePanel;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(JLabel infoLabel) {
        this.infoLabel = infoLabel;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
