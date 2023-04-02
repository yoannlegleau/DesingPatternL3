package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.interfaces.composent_graphique.GameInfoLabel;
import jeu_de_la_vie.interfaces.composent_graphique.GrilleCeluleCanvas;
import jeu_de_la_vie.interfaces.composent_graphique.StartStopButton;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyDencity;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyEmpty;
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
    private JButton zoomIn;
    private JButton zoomOut;
    private JPanel controlPanel;
    private JPanel jeuPanel;
    private JComboBox<String> rullComboBox;
    private JComboBox<String> initStrategyComboBox;
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
    private JFormattedTextField dimentionTextField1;
    private JButton dimentionButton;
    private JLabel dimentionLabel;
    private JCheckBox drawDeadCellCheckBox;
    private JButton rencenterButton;


    // composent graphique personalisé

    private GrilleCeluleCanvas grilleCeluleCanvas;

    private StartStopButton startStopButton;

    // Utilitaire de generation de jeu de la vie
    // Generation par densité
    int denciterRadius = InitStrategyDencity.DEFAULT_SIZE;
    double dencity = 0.5;

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
        grilleCeluleCanvas = new GrilleCeluleCanvas(jeu, jeuPanel);
        jeuPanel.add(grilleCeluleCanvas);
        startStopButton = new StartStopButton(jeu);
        playButtonPanel.add(startStopButton);
        GameInfoPanel.add(new GameInfoLabel(jeu));
        drawDeadCellCheckBox.setSelected(grilleCeluleCanvas.isDrawDeadCells());
        drawDeadCellCheckBox.addActionListener(e -> grilleCeluleCanvas.setDrawDeadCells(drawDeadCellCheckBox.isSelected()));
        zoomIn.addActionListener(e -> grilleCeluleCanvas.upCellSize());
        zoomOut.addActionListener(e -> grilleCeluleCanvas.downCellSize());
        rencenterButton.addActionListener(e -> grilleCeluleCanvas.reCenter());

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

        initStrategyComboBox.addActionListener(e -> {
            changeInitStrategy((String) initStrategyComboBox.getSelectedItem());
        });

        initStrategyComboBox.addItem("Vide");


        initStrategyComboBox.addItem("Densitè");
        densityValueLabel.setText(densitySlider.getValue()+" %");
        densitySlider.addChangeListener(e -> {
            setDencityStrategie();
        });
        dimentionLabel.setText(denciterRadius+"");
        dimentionTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setDencityStrategie();
                }
            }
        });
        dimentionButton.addActionListener(e -> setDencityStrategie());






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

    private void changeInitStrategy(String selectedItem) {

        //faire disparaitre tot les formulaires
        intiDencityFormFied.setVisible(false);

        switch (selectedItem) {
            case "Densitè":
                intiDencityFormFied.setVisible(true);
                jeu.setInitStrategie(new InitStrategyDencity(jeu.getxMax(), jeu.getyMax(), densitySlider.getValue() / 100.0));
                break;
            case "Vide":
                jeu.setInitStrategie(new InitStrategyEmpty());
                break;
        }
    }

    private void addRullToSelect(JComboBox rullComboBox) {
        rullComboBox.addItem("Clasique (Conway)");
        rullComboBox.addItem("HighLife");
    }
    private void setDencityStrategie() {
        String nbSring = dimentionTextField1.getText().replaceAll("[^0-9]", "");
        if (nbSring.length() != 0) {
            denciterRadius = Integer.parseInt(nbSring);
            dimentionLabel.setText(nbSring);
            dimentionTextField1.setText(nbSring);
        }

        dencity = densitySlider.getValue()/100.0;
        densityValueLabel.setText((int)(dencity*100) + " %");

        jeu.setInitStrategie(new InitStrategyDencity(denciterRadius,denciterRadius,dencity));
    }

    @Override
    public void actualiser() {
        genLabel.setText("Generation: " + jeu.getGeneration());
    }
}
