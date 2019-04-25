package modelos;

import javax.swing.*;
import java.awt.*;

//this is where you write shit n stuff

public class Textbox extends JTextArea {

    private String fontName;
    private int fontSize;
    private Color backgroundColor,foregroundColor;

    public Textbox(String fontName,int fontSize,Color backgroundColor,Color foregroundColor) {

        this.fontName = fontName;
        this.fontSize = fontSize;

        this.setFont(new Font(fontName,Font.PLAIN,fontSize));
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);

    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {

        this.fontName = fontName;
        this.setFont(new Font(fontName,Font.PLAIN,fontSize));
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {

        this.fontSize = fontSize;
        this.setFont(new Font(fontName,Font.PLAIN,fontSize));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Textbox textbox = (Textbox) o;

        if (fontSize != textbox.fontSize) return false;
        if (fontName != null ? !fontName.equals(textbox.fontName) : textbox.fontName != null) return false;
        if (backgroundColor != null ? !backgroundColor.equals(textbox.backgroundColor) : textbox.backgroundColor != null)
            return false;
        return foregroundColor != null ? foregroundColor.equals(textbox.foregroundColor) : textbox.foregroundColor == null;

    }

    @Override
    public int hashCode() {
        int result = fontName != null ? fontName.hashCode() : 0;
        result = 31 * result + fontSize;
        result = 31 * result + (backgroundColor != null ? backgroundColor.hashCode() : 0);
        result = 31 * result + (foregroundColor != null ? foregroundColor.hashCode() : 0);
        return result;
    }
}
