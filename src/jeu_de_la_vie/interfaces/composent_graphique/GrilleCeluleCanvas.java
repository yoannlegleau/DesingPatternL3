package jeu_de_la_vie.interfaces.composent_graphique;

import jeu_de_la_vie.interfaces.JeuxDeLaVieFacade;
import jeu_de_la_vie.jeu.JeuDeLaVie;
import jeu_de_la_vie.jeu.cellule.Cellule;
import jeu_de_la_vie.jeu.observateur.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 29/03/2023
 */
public class GrilleCeluleCanvas extends Canvas implements Observateur {

    private JeuxDeLaVieFacade jeu;
    private JPanel jeuPanel;
    private int cellsize;

    private Boolean drawDeadCells = false;

    Double cellCenterX = 0.0 , cellCenterY = 0.0;
    Double mouseCellX = 0.0 , mouseCellY = 0.0;

    private boolean drag = false;

    private double startX = 0 , startY = 0;
    private double startCenterX = 0 , startCenterY = 0;

    private int cellsizeMax = 200 , cellsizeMin = 5;


    public GrilleCeluleCanvas(JeuxDeLaVieFacade jeu , JPanel parent) {
        super();
        this.jeu = jeu;
        jeu.atacheObservateur(this);
        this.jeuPanel = parent;

        //
        cellsize = 40;

        // gestion du du zoom et des deplacement

        addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0)
                upCellSize();
            else
                downCellSize();
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Dimension size = getSize();
                double mouseRelatX = (evt.getX() - (size.width/2) - (cellsize/2)) ;
                if (mouseRelatX < 0)
                    mouseCellX = (mouseRelatX / cellsize);
                else
                    mouseCellX = (mouseRelatX / cellsize) + 1;

                double mouseRelatY = ((evt.getY() - (size.height/2) - (cellsize/2)));
                if (mouseRelatY < 0)
                    mouseCellY = (mouseRelatY / cellsize);
                else
                    mouseCellY = (mouseRelatY / cellsize) + 1;
            }

            public void mouseDragged(MouseEvent e) {
                if (drag) {
                    cellCenterX = startCenterX + (startX - mouseCellX);
                    cellCenterY = startCenterY + (startY - mouseCellY);
                    repaint();
                }
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {

            private double startX = 0 , startY = 0;
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drag = true;
                startX = mouseCellX;
                startY = mouseCellY;
                startCenterX = cellCenterX;
                startCenterY = cellCenterY;
            }

            public void mouseReleased(MouseEvent e) {
                drag = false;
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Enregistre l'état des touches directionnelles
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        cellCenterY--;
                        break;
                    case KeyEvent.VK_DOWN:
                        cellCenterY++;
                        break;
                    case KeyEvent.VK_LEFT:
                        cellCenterX--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        cellCenterX++;
                        break;
                }
                repaint();
            }
        });

        // Ajouter le JPanel en bas du canvas


        this.setMinimumSize(new Dimension(0, 0));
        jeuPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeCanvas();
            }
        });
    }

    public Boolean isDrawDeadCells() {
        return drawDeadCells;
    }

    public void setDrawDeadCells(Boolean drawDeadCells) {
        this.drawDeadCells = drawDeadCells;
        repaint();
    }

    public void reCenter() {
        cellCenterX = 0.0;
        cellCenterY = 0.0;
        repaint();
    }

    public void upCellSize(){
        if (this.cellsize < cellsizeMax) {
            this.cellsize += 1;
            repaint();
        }

    }

    public void downCellSize(){
        if (this.cellsize > cellsizeMin) {
            this.cellsize -= 1;
            repaint();
        }
    }

    private void resizeCanvas() {
        this.setPreferredSize(new Dimension(jeuPanel.getWidth(), jeuPanel.getHeight()));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (jeu == null || g == null) return;

        jeu.detacheObservateur(this); // on ne veut pas que le jeu actualise la grille pendant qu'on la dessine

        Dimension size = getSize();
        int xCellCapacity = (size.width / cellsize) + 2;
        int yCellCapacity = (size.height / cellsize) + 2;

        int pxCenterX = (size.width / 2);
        int pxCenterY = (size.height / 2);


        for (Cellule c : jeu.getGrid().getSubGridToList(
                (int) (cellCenterX-(xCellCapacity/2)), (int) (cellCenterY-(yCellCapacity/2)),
                xCellCapacity, yCellCapacity)) {
            if(c.estVivante())
                g.setColor(new Color(67, 160, 71));
            else {
                if (drawDeadCells)
                    g.setColor(new Color(241, 223, 223));
                else
                    continue;
            }
            g.fillOval((int) (pxCenterX+(c.getX()-cellCenterX)*cellsize - (cellsize/2)),
                    (int) (pxCenterY+ (c.getY()-cellCenterY)*cellsize - (cellsize/2)),
                    cellsize,cellsize);

        }
        jeu.atacheObservateur(this); // on remet l'observateur
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actualiser() {
        repaint();
    }

}
