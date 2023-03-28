package jeu_de_la_vie.interfaces;

import jeu_de_la_vie.JeuDeLaVie;

import javax.swing.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 28/03/2023
 */
public class InterfaceSwing extends InterfaceGrafique {

    // composent graphique swing
    private JPanel root;
    private JPanel game;
    private JList list1;
    private JButton button5;
    private JButton button6;
    private JButton button1;
    private JButton button3;
    private JButton clearButton;
    private JSlider slider1;
    private JPanel controlPanel;

    public InterfaceSwing(JeuDeLaVie jeu) {
        super(jeu);
    }



}
