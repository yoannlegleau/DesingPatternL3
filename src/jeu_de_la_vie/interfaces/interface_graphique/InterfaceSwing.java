package jeu_de_la_vie.interfaces.interface_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.interfaces.composent_graphique.GameInfoLabel;
import jeu_de_la_vie.interfaces.composent_graphique.GrilleCeluleCanvas;
import jeu_de_la_vie.interfaces.composent_graphique.StartStopButton;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyDencity;
import jeu_de_la_vie.jeu.init_strategy.InitStrategyEmpty;
import jeu_de_la_vie.jeu.observateur.Observateur;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Locale;

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

        speedValueLabel.setText(3 + " gen/s");
        speedSlider.addChangeListener(e -> {
            double value = (speedSlider.getValue() / 100.0) * (speedSlider.getValue() / 100.0);
            jeu.setGenPerSec(value);
            DecimalFormat df = new DecimalFormat("#.##");
            speedValueLabel.setText(df.format(value) + " gen/s");
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
        densityValueLabel.setText(densitySlider.getValue() + " %");
        densitySlider.addChangeListener(e -> {
            setDencityStrategie();
        });
        dimentionLabel.setText(denciterRadius + "");
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

        dencity = densitySlider.getValue() / 100.0;
        densityValueLabel.setText((int) (dencity * 100) + " %");

        jeu.setInitStrategie(new InitStrategyDencity(denciterRadius, denciterRadius, dencity));
    }

    @Override
    public void actualiser() {
        genLabel.setText("Generation: " + jeu.getGeneration());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        game = new JPanel();
        game.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(32, 0, 32, 16), -1, -1));
        root.add(game, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        GameInfoPanel = new JPanel();
        GameInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        game.add(GameInfoPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        game.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15025631)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        jeuPanel = new JPanel();
        jeuPanel.setLayout(new BorderLayout(0, 0));
        panel1.add(jeuPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        game.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        drawDeadCellCheckBox = new JCheckBox();
        drawDeadCellCheckBox.setContentAreaFilled(false);
        drawDeadCellCheckBox.setLabel("DrawDeadCell");
        drawDeadCellCheckBox.setText("DrawDeadCell");
        panel2.add(drawDeadCellCheckBox);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.add(panel3);
        zoomOut = new JButton();
        zoomOut.setText("-");
        panel3.add(zoomOut);
        zoomIn = new JButton();
        zoomIn.setText("+");
        panel3.add(zoomIn);
        rencenterButton = new JButton();
        rencenterButton.setLabel("Rencentrer");
        rencenterButton.setText("Rencentrer");
        panel3.add(rencenterButton);
        controlPanel = new JPanel();
        controlPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(32, 16, 32, 16), -1, -1));
        controlPanel.setBackground(new Color(-5908825));
        controlPanel.setEnabled(false);
        root.add(controlPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(476, 10), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        controlPanel.add(separator1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 15), null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel4.setBackground(new Color(-16777216));
        panel4.setOpaque(false);
        controlPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(204, 0), null, 0, false));
        nextGenButton = new JButton();
        nextGenButton.setText(">");
        panel4.add(nextGenButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playButtonPanel = new JPanel();
        playButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        playButtonPanel.setOpaque(false);
        panel4.add(playButtonPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(61, 10), null, 0, false));
        speedSlider = new JSlider();
        speedSlider.setMaximum(500);
        speedSlider.setMinimum(0);
        speedSlider.setOpaque(false);
        speedSlider.setValue(173);
        panel4.add(speedSlider, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Vitesse de generation :");
        panel4.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speedValueLabel = new JLabel();
        speedValueLabel.setText("Label");
        panel4.add(speedValueLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("MV Boli", Font.BOLD, 48, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("JEUX DE LA VIE");
        controlPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        controlPanel.add(separator2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setOpaque(false);
        controlPanel.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Regle de genaration");
        panel5.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rullComboBox = new JComboBox();
        panel5.add(rullComboBox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newGameButton = new JButton();
        newGameButton.setText("Generer");
        panel5.add(newGameButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Methode D'initialisation");
        panel5.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        initStrategyComboBox = new JComboBox();
        panel5.add(initStrategyComboBox, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        initMethodFormField = new JPanel();
        initMethodFormField.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        initMethodFormField.setOpaque(false);
        panel5.add(initMethodFormField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        intiDencityFormFied = new JPanel();
        intiDencityFormFied.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(8, 8, 8, 8), -1, -1));
        intiDencityFormFied.setEnabled(true);
        intiDencityFormFied.setFocusable(true);
        intiDencityFormFied.setOpaque(false);
        intiDencityFormFied.setVisible(true);
        initMethodFormField.add(intiDencityFormFied, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        intiDencityFormFied.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-14855650)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label5 = new JLabel();
        label5.setText("Densité");
        intiDencityFormFied.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        densitySlider = new JSlider();
        densitySlider.setOpaque(false);
        intiDencityFormFied.add(densitySlider, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        densityValueLabel = new JLabel();
        densityValueLabel.setText("Label");
        intiDencityFormFied.add(densityValueLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Dienetion");
        intiDencityFormFied.add(label6, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dimentionTextField1 = new JFormattedTextField();
        intiDencityFormFied.add(dimentionTextField1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        dimentionButton = new JButton();
        dimentionButton.setText("OK");
        intiDencityFormFied.add(dimentionButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dimentionLabel = new JLabel();
        dimentionLabel.setText("Label");
        intiDencityFormFied.add(dimentionLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}
