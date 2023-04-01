package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.interfaces.composent_graphique.GameInfoLabel;
import jeu_de_la_vie.interfaces.composent_graphique.GrilleCeluleCanvas;
import jeu_de_la_vie.interfaces.composent_graphique.StartStopButton;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyDencity;
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
    private JComboBox<String> rullComboBox;
    private JComboBox<String> intStrategyComboBox;
    private JLabel genLabel;
    private JPanel playButtonPanel;
    private JPanel GameInfoPanel;
    private JSlider speedSlider;
    private JLabel speedValueLabel;
    private JButton newGameButton;
    private JPanel initMethodFormField;
    private JPanel intiDencityFormFied;
    private JSlider densitySlider;
    private JLabel densityValueLabel;


    // composent graphique personalisé

    private StartStopButton startStopButton;


    // Utilitaire de generation de jeu de la vie
    // TODO: refactor dans une classe a part






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
        frame.setMinimumSize(new Dimension(800, 600));
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

        speedValueLabel.setText(3+" gen/s");
        speedSlider.addChangeListener(e -> {
            double value = (speedSlider.getValue()/100.0)*(speedSlider.getValue()/100.0) ;
            jeu.setGenPerSec(value);
            DecimalFormat df = new DecimalFormat("#.##");
            speedValueLabel.setText( df.format(value) + " gen/s");
        });


        // Generation d'une nouvelle grille

        newGameButton.addActionListener(e -> {
            jeu.newJeuDeLaVie();
        });

        // Choix des regles

        addRullToSelect(rullComboBox);

        rullComboBox.addActionListener(e -> {
            jeu.setRull((String) rullComboBox.getSelectedItem());
        });


        // Choix de la strategie d'initialisation

        intStrategyComboBox.addItem("densitè");
        densityValueLabel.setText(densitySlider.getValue()+" %");
        densitySlider.addChangeListener(e -> {
            int value = (densitySlider.getValue());
            densityValueLabel.setText( value + " %");
            jeu.setInitStrategie(new InitStrategyDencity(jeu.getxMax(), jeu.getyMax(), value/100.0));
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

    private void addRullToSelect(JComboBox rullComboBox) {
        rullComboBox.addItem("Clasique (Conway)");
        rullComboBox.addItem("HighLife");
    }

    @Override
    public void actualiser() {
        genLabel.setText("Generation: " + jeu.getGeneration());
    }
}
