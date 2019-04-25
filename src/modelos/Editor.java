package modelos;

import utils.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class Editor extends JFrame implements ActionListener {

    private static final String OS_VERSION = System.getProperty("os.name");
    private static final int OPEN_OPERATION = 0;//constants to indicate whats the current operation of the filesystem_tree
    private static final int SAVE_OPERATION = 1;
    private JPanel basePanel, upperPanel;
    private Textbox textBox;
    private Menu menuBar_;
    private FontManager fontManager;
    private FileManager fileManager;
    private Filesystem_tree filesystem_tree;
    private About_info about_info;
    private ImageIcon frameIcon;
    private JScrollPane textScrollPane;
    private int filesystem_tree_operation;

    public Editor() {

        //setting the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        initComponents();

    }

    private void initComponents() {

        //FRAME
        setSize(960,700);
        setTitle("Coffee Text Editor - Untitled.txt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //panels
        basePanel = new JPanel();
        upperPanel = new JPanel();

        //some OS specific stuff
        if(OS_VERSION.contains("Windows")) {

            upperPanel.setBackground(new Color(237, 234, 229));
            basePanel.setBackground(new Color(237, 234, 229));

        }else {

            upperPanel.setBackground(new Color(75, 72, 67));
            basePanel.setBackground(new Color(75, 72, 67));
        }

        frameIcon = new ImageIcon(this.getClass().getResource("/resources/coffee-cup.png"));
        this.setIconImage(frameIcon.getImage());

        upperPanel.setPreferredSize(new Dimension(950,25));


        //Text

        textBox = new Textbox("Serif",16,new Color(237, 234, 229),new Color(26, 28, 32));
        textBox.setPreferredSize(new Dimension(2500,25000));
        textBox.setEditable(true);
        textScrollPane = new JScrollPane(textBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //File manager
        fileManager = new FileManager();

        //fontManager
        fontManager = new FontManager(this);

        //adding a window listener to the font manager, to change the textbox font only if the manager is closed
        fontManager.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

                if(fontManager.isButtonPressed()) {

                    textBox.setFontName(fontManager.getSelectedFont());
                    textBox.setFontSize(fontManager.getSelectedFontSize());

                }

            }
        });

        filesystem_tree = new Filesystem_tree(this);

        filesystem_tree.addWindowListener(new WindowListener() {//same with the filesystem_tree frame
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

                if(filesystem_tree.isButtonPressed() && filesystem_tree_operation == OPEN_OPERATION) {//if we are opening a file

                    File aux = new File(filesystem_tree.getSelectedFilePath());

                    if(aux.exists() && aux.canRead()) {//check if the file exists an can be read

                        //if it does then we proceed with getting the text out of that file and pasting it into the texbox and changing the title
                        fileManager.setFilePath(aux.getPath());

                        textBox.setText(fileManager.fileRead());

                        changeTitle("Coffe Text Editor - "+fileManager.getFilePath());

                    }else {//if it doesnt, well it doesnt exist or cant be read

                        JOptionPane.showMessageDialog(null,"Could not open file");

                    }

                }else if(filesystem_tree.isButtonPressed() && filesystem_tree_operation == SAVE_OPERATION) {//if we are saving a file

                    fileManager.setLine(textBox.getText());
                    File aux;
                    String fp;

                    if((fp = filesystem_tree.getSelectedFilePath()+"/"+JOptionPane.showInputDialog(null,"File name",".txt")) !=null) {

                        aux = new File(fp);

                        if(aux.exists()) {

                            int returnValue;

                            returnValue = JOptionPane.showConfirmDialog(null, "File already exists, overwrite?", "Save As", JOptionPane.YES_NO_OPTION);

                            switch(returnValue) {

                                case JOptionPane.YES_OPTION://overwrite
                                    fileManager.setFilePath(aux.getPath());
                                    //aux.delete();
                                    fileManager.fileFlush();
                                    fileManager.fileWrite();
                                    changeTitle("Coffe Text editor - "+fileManager.getFilePath());
                                    break;

                                case JOptionPane.NO_OPTION://cancel
                                    JOptionPane.showMessageDialog(null,"Save cancelled");

                            }

                        }else {
                            //if file doesnt exist then its all ok
                            fileManager.setFilePath(aux.getPath());
                            changeTitle("Coffe Text editor - "+fileManager.getFilePath());
                            fileManager.fileWrite();

                        }

                    } else {

                        JOptionPane.showMessageDialog(null,"Save cancelled");
                    }

                }

            }
        });

        //info window
        about_info = new About_info(this);

        //Menu
        menuBar_ = new Menu(this);

        menuBar_.setBackground(new Color(55, 52, 48));

        menuBar_.setPreferredSize(new Dimension(955,25));

        menuBar_.getMenu1().addActionListener(this);
        menuBar_.getMenu2().addActionListener(this);
        menuBar_.getMenu3().addActionListener(this);
        menuBar_.getSaveAs().addActionListener(this);
        menuBar_.getSave().addActionListener(this);
        menuBar_.getOpen().addActionListener(this);
        menuBar_.getMenuFont().addActionListener(this);
        menuBar_.getAbout().addActionListener(this);

        //"Layout"
        basePanel.setLayout(new BoxLayout(basePanel,BoxLayout.Y_AXIS));
        upperPanel.add(menuBar_);
        basePanel.add(upperPanel);
        basePanel.add(textScrollPane);
        this.add(basePanel);
        //this.pack();


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String event = actionEvent.getActionCommand();

        switch (event) {

            case "Save as":
                saveAs();
                break;
            case "Save" :
                save();
                break;
            case "Open" :
                filesystem_tree_operation = OPEN_OPERATION;
                filesystem_tree.setTitle("Open");
               filesystem_tree.setVisible(true);
                break;
            case "Font":
                fontManager.setVisible(true);
                break;
            case "About":
                about_info.setVisible(true);
                break;

        }


    }

    public void saveAs() {

        filesystem_tree_operation = SAVE_OPERATION;
        filesystem_tree.setTitle("Save");
        filesystem_tree.setVisible(true);

    }

    public void save() {

        File aux;

        aux = new File(fileManager.getFilePath());

        if(aux.exists()) {

            //if the file exists then we write the changes
            fileManager.fileFlush();
            fileManager.setLine(textBox.getText());
            fileManager.fileWrite();

        }else {

            //if it doesnt, we have to create one
            saveAs();

        }

    }

    public void changeTitle(String title) {

        this.setTitle(title);

    }

    public JPanel getBasePanel() {
        return basePanel;
    }

    public void setBasePanel(JPanel basePanel) {
        this.basePanel = basePanel;
    }

    public JPanel getUpperPanel() {
        return upperPanel;
    }

    public void setUpperPanel(JPanel upperPanel) {
        this.upperPanel = upperPanel;
    }


    public Textbox getTextBox() {
        return textBox;
    }

    public void setTextBox(Textbox textBox) {
        this.textBox = textBox;
    }

    public Menu getMenu() {
        return menuBar_;
    }

    public void setMenu(Menu menuBar) {
        this.menuBar_ = menuBar;
    }

    public Menu getMenuBar_() {
        return menuBar_;
    }

    public void setMenuBar_(Menu menuBar_) {
        this.menuBar_ = menuBar_;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public void setFontManager(FontManager fontManager) {
        this.fontManager = fontManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public ImageIcon getFrameIcon() {
        return frameIcon;
    }

    public void setFrameIcon(ImageIcon frameIcon) {
        this.frameIcon = frameIcon;
    }

    public JScrollPane getTextScrollPane() {
        return textScrollPane;
    }

    public void setTextScrollPane(JScrollPane textScrollPane) {
        this.textScrollPane = textScrollPane;
    }

    public static String getOsVersion() {
        return OS_VERSION;
    }

    public Filesystem_tree getFilesystem_tree() {
        return filesystem_tree;
    }

    public void setFilesystem_tree(Filesystem_tree filesystem_tree) {
        this.filesystem_tree = filesystem_tree;
    }

    public int getFilesystem_tree_operation() {
        return filesystem_tree_operation;
    }

    public void setFilesystem_tree_operation(int filesystem_tree_operation) {
        this.filesystem_tree_operation = filesystem_tree_operation;
    }
}
