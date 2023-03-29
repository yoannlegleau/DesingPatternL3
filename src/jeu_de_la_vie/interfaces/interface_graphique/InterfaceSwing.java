package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.interfaces.composent_graphique.GameInfoLabel;
import jeu_de_la_vie.interfaces.composent_graphique.GrilleCeluleCanvas;
import jeu_de_la_vie.interfaces.composent_graphique.StartStopButton;
import jeu_de_la_vie.jeu.observateur.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 * @author LE GLEAU Yoann
 * @version 1, 28/03/2023
 */
public class InterfaceSwing extends InterfaceGrafique implements Observateur {

    // composent graphique swing
    private JPanel root;
    private JPanel game;
    private JButton nextGenButton;
    private JButton button1;
    private JButton button3;
    private JButton clearButton;
    private JPanel controlPanel;
    private JPanel jeuPanel;
    private JComboBox comboBox1;
    private JLabel genLabel;
    private JPanel playButtonPanel;
    private JPanel GameInfoPanel;
    private JSlider speedSlider;
    private JLabel speedValueLabel;

    // composent graphique personalisé

    private StartStopButton startStopButton;


    public InterfaceSwing(JeuxDeLaVieFacade jeu) {
        super(jeu);

        // initialisation de la fenetre
        JFrame frame = new JFrame();
        frame.setContentPane(root);
        frame.setSize(800, 600);

        //ouvrire sur le deuxieme ecran //TODO: enlever pour la version finale
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (gs.length > 1) {
            GraphicsConfiguration config = gs[1].getDefaultConfiguration();
            frame.setBounds(config.getBounds().x, config.getBounds().y, 800, 600);
        }

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Jeu de la vie");


        // initialisation des composent
        jeuPanel.add(new GrilleCeluleCanvas(jeu, jeuPanel));
        startStopButton = new StartStopButton(jeu);
        playButtonPanel.add(startStopButton);
        GameInfoPanel.add(new GameInfoLabel(jeu));

        nextGenButton.addActionListener(e -> {
            if (!jeu.isRunning())
                jeu.calculerGenerationSuivante();
        });

        speedValueLabel.setText("1 gen/s");
        speedSlider.addChangeListener(e -> {
            double value = (speedSlider.getValue()/100.0)*(speedSlider.getValue()/100.0) ;
            jeu.setGenPerSec(value);
            DecimalFormat df = new DecimalFormat("#.##");
            speedValueLabel.setText( df.format(value) + " gen/s");
        });



        // initialisation des racourcis clavier
        root.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_0) {
                      startStopButton.action();
                }
            }
        });

        frame.setVisible(true);
        root.requestFocusInWindow();
    }

    @Override
    public void actualiser() {
        genLabel.setText("Generation: " + jeu.getGeneration());
    }
}
