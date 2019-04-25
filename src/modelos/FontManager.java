package modelos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
Explicacion de todo esto por arriba:

esta es la ventanita con la lista para elegir el tipo de letra para el editor de texto
muy bien, la lista tiene que tener un listener de eventos, de tipo listSelectionListener
 */

public class FontManager extends JFrame implements ListSelectionListener, ActionListener {

    private JList fontList;
    private JList fontSizeList;
    private JPanel basePanel,listPanel,textPanel,buttonPanel;
    private JLabel info;
    private JButton apply,cancel;
    private JScrollPane scrollPane,scrollPane2;
    private String selectedFont;//actual font
    private int selectedFontSize;
    private boolean buttonPressed; //to check what button was pressed

    public FontManager(Editor ed) {

        //frame
        setSize(360,415);
        setTitle("Fonts");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(ed);
        setResizable(false);
        //setMinimumSize(new Dimension(200,200));

        fontList = new JList();
        fontSizeList = new JList();
        fontList.setModel(modelList());
        fontSizeList.setModel(modelList(50));
        scrollPane = new JScrollPane();
        scrollPane2 = new JScrollPane();

        selectedFont = ed.getTextBox().getFontName();
        selectedFontSize = ed.getTextBox().getFontSize();

        basePanel = new JPanel();
        textPanel = new JPanel();
        listPanel = new JPanel();
        buttonPanel = new JPanel();
        info = new JLabel("<html>System fonts<br>" +
                " ------------------------------------------ <br>" +
                " ------------------------------------------ <br>" +
                "Font: "+selectedFont +"<br>" +
                "Font size: "+selectedFontSize+"</html>");

        apply = new JButton("Apply");
        cancel = new JButton("Cancel");

        scrollPane.setViewportView(fontList);
        scrollPane2.setViewportView(fontSizeList);

        listPanel.add(scrollPane);
        textPanel.add(info);

        basePanel.add(textPanel);
        basePanel.add(listPanel);
        basePanel.add(scrollPane2);

        buttonPanel.add(apply);
        buttonPanel.add(cancel);

        basePanel.add(buttonPanel);
        this.add(basePanel);
        this.pack();

        fontList.getSelectionModel().addListSelectionListener(this);
        fontSizeList.getSelectionModel().addListSelectionListener(this);
        apply.addActionListener(this);
        cancel.addActionListener(this);


    }

    private DefaultListModel modelList(){//sets all the options in the list

        DefaultListModel model = new DefaultListModel<>();

        for(int i=0;i<getSysFonts().size();i++) {

            model.addElement(getSysFonts().get(i));

        }

        return model;
    }

    private DefaultListModel modelList(int n){//sets all the options in the list

        DefaultListModel model = new DefaultListModel<>();

        for(int i=2;i<n;i+=2) {

            model.addElement(i);

        }

        return model;
    }

    private ArrayList getSysFonts() {

        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ArrayList<String> fontListArray = new ArrayList<>();

        Font[] fonts = e.getAllFonts(); // Get the fonts

        for(Font f : fonts) {

            fontListArray.add(f.getFontName());

        }

        return fontListArray;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        String auxSelectedFont="";

        if (!lsm.isSelectionEmpty()) {

            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {

                    if(lsm.equals(fontList.getSelectionModel())) {

                        selectedFont = getSysFonts().get(i).toString();//changes the actual selected font
                    }
                    else {
                        selectedFontSize = (i * 2) +2;//lame but useful
                    }

                    if(selectedFont.length() > 20) {//si la letra es muy grande para la ventana

                        for(int j=0;j<18;j++) {

                            auxSelectedFont = auxSelectedFont + selectedFont.charAt(j);

                        }

                        auxSelectedFont = auxSelectedFont +"...";

                        info.setText("<html>System fonts<br>" +
                                " ------------------------------------------ <br>" +
                                " ------------------------------------------ <br>" +
                                "Font: "+auxSelectedFont +"<br>" +
                                "Font size: "+selectedFontSize+"</html>");//updating information label

                    }else {

                        info.setText("<html>System fonts<br>" +
                                " ------------------------------------------ <br>" +
                                " ------------------------------------------ <br>" +
                                "Font: " + selectedFont + "<br>" +
                                "Font size: " + selectedFontSize + "</html>");//updating information label
                    }
                }
            }
            //
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String event = actionEvent.getActionCommand();

        switch (event) {

            case "Apply":
                buttonPressed = true;
                this.setVisible(false);
                break;
            case "Cancel":
                buttonPressed = false;
                this.setVisible(false);
                break;

        }

    }

    public JList getFontList() {
        return fontList;
    }

    public void setFontList(JList fontList) {
        this.fontList = fontList;
    }

    public JPanel getBasePanel() {
        return basePanel;
    }

    public void setBasePanel(JPanel basePanel) {
        this.basePanel = basePanel;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    public void setListPanel(JPanel listPanel) {
        this.listPanel = listPanel;
    }

    public JPanel getTextPanel() {
        return textPanel;
    }

    public void setTextPanel(JPanel textPanel) {
        this.textPanel = textPanel;
    }

    public JLabel getInfo() {
        return info;
    }

    public void setInfo(JLabel info) {
        this.info = info;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public String getSelectedFont() {
        return selectedFont;
    }

    public void setSelectedFont(String selectedFont) {
        this.selectedFont = selectedFont;
    }

    public JList getFontSizeList() {
        return fontSizeList;
    }

    public void setFontSizeList(JList fontSizeList) {
        this.fontSizeList = fontSizeList;
    }

    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }

    public void setScrollPane2(JScrollPane scrollPane2) {
        this.scrollPane2 = scrollPane2;
    }

    public int getSelectedFontSize() {
        return selectedFontSize;
    }

    public void setSelectedFontSize(int selectedFontSize) {
        this.selectedFontSize = selectedFontSize;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getApply() {
        return apply;
    }

    public void setApply(JButton apply) {
        this.apply = apply;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }
}
