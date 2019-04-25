package modelos;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {

    private JMenu menu1,menu2,menu3;
    private JMenuItem open,save,saveAs,font,about;

    public Menu(Editor ed) {

        if(ed.getOsVersion().contains("Windows")) {

            this.setBackground(new Color(237, 234, 229));
            menu1 = new JMenu("File");menu1.setForeground(Color.BLACK);menu1.setBackground(new Color(237, 234, 229));
            menu2 = new JMenu("Preferences");menu2.setForeground(Color.BLACK);menu2.setBackground(new Color(237, 234, 229));
            menu3 = new JMenu("Help");menu3.setForeground(Color.BLACK);menu3.setBackground(new Color(237, 234, 229));

            open = new JMenuItem("Open");open.setBackground(new Color(237, 234, 229));
            save = new JMenuItem("Save");save.setBackground(new Color(237, 234, 229));
            saveAs = new JMenuItem("Save as");saveAs.setBackground(new Color(237, 234, 229));
            font = new JMenuItem("Font");font.setBackground(new Color(237, 234, 229));
            about = new JMenuItem("About");about.setBackground(new Color(237, 234, 229));

            open.setForeground(Color.BLACK);
            save.setForeground(Color.BLACK);
            saveAs.setForeground(Color.BLACK);
            font.setForeground(Color.BLACK);
            about.setForeground(Color.BLACK);

        }else {

            this.setBackground(new Color(55, 52, 48));
            menu1 = new JMenu("File");menu1.setForeground(Color.white);menu1.setBackground(new Color(55, 52, 48));
            menu2 = new JMenu("Preferences");menu2.setForeground(Color.white);menu2.setBackground(new Color(55, 52, 48));
            menu3 = new JMenu("Help");menu3.setForeground(Color.white);menu3.setBackground(new Color(55, 52, 48));

            open = new JMenuItem("Open");open.setBackground(new Color(55, 52, 48));
            save = new JMenuItem("Save");save.setBackground(new Color(55, 52, 48));
            saveAs = new JMenuItem("Save as");saveAs.setBackground(new Color(55, 52, 48));
            font = new JMenuItem("Font");font.setBackground(new Color(55, 52, 48));
            about = new JMenuItem("About");about.setBackground(new Color(55, 52, 48));

            open.setForeground(Color.white);
            save.setForeground(Color.white);
            saveAs.setForeground(Color.white);
            font.setForeground(Color.white);
            about.setForeground(Color.white);

        }


        menu1.add(open);
        menu1.add(save);
        menu1.add(saveAs);
        menu2.add(font);
        menu3.add(about);

        this.add(menu1);
        this.add(menu2);
        this.add(menu3);

    }



    public JMenu getMenu1() {
        return menu1;
    }

    public void setMenu1(JMenu menu1) {
        this.menu1 = menu1;
    }

    public JMenu getMenu2() {
        return menu2;
    }

    public void setMenu2(JMenu menu2) {
        this.menu2 = menu2;
    }

    public JMenu getMenu3() {
        return menu3;
    }

    public void setMenu3(JMenu menu3) {
        this.menu3 = menu3;
    }

    public JMenuItem getOpen() {
        return open;
    }

    public void setOpen(JMenuItem open) {
        this.open = open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public void setSave(JMenuItem save) {
        this.save = save;
    }

    public JMenuItem getSaveAs() {
        return saveAs;
    }

    public void setSaveAs(JMenuItem saveAs) {
        this.saveAs = saveAs;
    }

    public JMenuItem getMenuFont() {
        return font;
    }

    public void setFont(JMenuItem font) {
        this.font = font;
    }

    public JMenuItem getAbout() {
        return about;
    }

    public void setAbout(JMenuItem about) {
        this.about = about;
    }
}
