package modelos;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import utils.FileTreeModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Filesystem_tree extends JFrame implements TreeSelectionListener, ActionListener {

    private JTree file_tree;
    private TreeModel file_tree_model;
    private JPanel upperPanel,lowerPanel;
    private JButton ok,cancel;
    private JScrollPane scrollPane;
    private String selectedFilePath;
    private boolean buttonPressed; //true == ok, false == cancel

    public Filesystem_tree(Editor ed) {

        //frame
        setSize(610,600);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(ed);
        setResizable(false);
        //setMinimumSize(new Dimension(500,500));

        file_tree_model = new FileTreeModel(File.listRoots()[0]);//gets the file tree, every single directory in /
        file_tree = new JTree(file_tree_model);//assigns the model to the tree

        file_tree.addTreeSelectionListener(this);

        ok = new JButton("Ok");ok.addActionListener(this);
        cancel = new JButton("Cancel");cancel.addActionListener(this);

        scrollPane = new JScrollPane(file_tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setViewportView(file_tree);

        scrollPane.setPreferredSize(new Dimension(600,520));


        upperPanel = new JPanel();
        lowerPanel = new JPanel();

        upperPanel.setPreferredSize(new Dimension(600,525));
        lowerPanel.setPreferredSize(new Dimension(200,35));

        lowerPanel.add(ok);
        lowerPanel.add(cancel);
        upperPanel.add(scrollPane);

        this.add(upperPanel);
        this.add(lowerPanel);

        this.setLayout(new FlowLayout());

        buttonPressed = false;

        this.pack();

    }


    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {

        Object selectedNode = file_tree.getLastSelectedPathComponent();

        selectedFilePath = selectedNode.toString();

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String event = actionEvent.getActionCommand();

        switch (event) {

            case "Ok":
                buttonPressed = true;
                this.setVisible(false);
                break;
            case "Cancel":
                buttonPressed = false;
                selectedFilePath = "";
                this.setVisible(false);
                break;

        }

    }

    public JTree getFile_tree() {
        return file_tree;
    }

    public void setFile_tree(JTree file_tree) {
        this.file_tree = file_tree;
    }

    public TreeModel getFile_tree_model() {
        return file_tree_model;
    }

    public void setFile_tree_model(TreeModel file_tree_model) {
        this.file_tree_model = file_tree_model;
    }

    public JPanel getUpperPanel() {
        return upperPanel;
    }

    public void setUpperPanel(JPanel upperPanel) {
        this.upperPanel = upperPanel;
    }

    public JPanel getLowerPanel() {
        return lowerPanel;
    }

    public void setLowerPanel(JPanel lowerPanel) {
        this.lowerPanel = lowerPanel;
    }

    public JButton getOk() {
        return ok;
    }

    public void setOk(JButton ok) {
        this.ok = ok;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public String getSelectedFilePath() {
        return selectedFilePath;
    }

    public void setSelectedFilePath(String selectedFilePath) {
        this.selectedFilePath = selectedFilePath;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }


}
