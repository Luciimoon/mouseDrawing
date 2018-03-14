package mousedrawing;

import com.sun.glass.events.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

import javax.swing.*;

/**
 *
 * @author compsci
 */
public class MouseDrawing extends JFrame {

    /**
     *
     * Adds left color and right color color label adds 8 colors in an array
     *
     * @var xPrevious keeps track of mouse x coordinate
     */
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("file");
    JMenu canvasMenu = new JMenu("Canvas");
    JMenuItem newMenuItem = new JMenuItem("Clear");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenuItem resizeMenuItem = new JMenuItem("Resize");

    JMenuItem blueMenuItem = new JMenuItem("Blue");
    JMenuItem redMenuItem = new JMenuItem("Red");
    JMenuItem magentaMenuItem = new JMenuItem("Magenta");
    JMenuItem greenMenuItem = new JMenuItem("Green");

    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[12]; //num of colors
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;
    int Panelx = 500;
    int Panely = 400;

    /**
     *
     * @param args main
     */
    public static void main(String[] args) {

        new MouseDrawing().setVisible(true);

    }

    /**
     *
     */
    public MouseDrawing() {

        //frame constructor
        setTitle("art by Mitch!");
        setResizable(false);
        getContentPane().setBackground(Color.CYAN);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());

        //build menu
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(canvasMenu);
        canvasMenu.add(blueMenuItem);
        canvasMenu.add(redMenuItem);
        canvasMenu.add(magentaMenuItem);
        canvasMenu.add(greenMenuItem);
        fileMenu.add(newMenuItem);
        fileMenu.add(resizeMenuItem);
        mainMenuBar.setBackground(Color.MAGENTA);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newMenuItemActionPerformed(e);
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenuItemActionPerformed(e);
            }
        });
        //color action
        blueMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blueMenuItemActionPerformed(e);
            }
        });

        redMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redMenuItemActionPerformed(e);
            }
        });

        magentaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                magentaMenuItemActionPerformed(e);
            }
        });

        greenMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greenMenuItemActionPerformed(e);
            }
        });
        //end of color action

        /*  resizeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resizeMenuItemMenuItemActionPerformed(e);
            }

        }); */
        drawPanel.setPreferredSize(new Dimension(Panelx * 2, Panely * 2));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridConstraints);

        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                drawPanelMousePressed(e);
            }

        });
        drawPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                drawPanelmouseDragged(e);
            }
        });
        drawPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                drawPanelmouseReleased(e);
            }
        });

        leftColorLabel.setPreferredSize(new Dimension(40, 40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(5, 2, 5, 5);
        getContentPane().add(leftColorLabel, gridConstraints);

        rightColorLabel.setPreferredSize(new Dimension(40, 40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(5, 2, 5, 5);
        getContentPane().add(rightColorLabel, gridConstraints);

        colorPanel.setPreferredSize(new Dimension(80, 160 + 70));
        colorPanel.setBorder(BorderFactory.createTitledBorder("colors"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(colorPanel, gridConstraints);

        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for (int i = 0; i < 12; i++) {

            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30, 30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 6;
            colorPanel.add(colorLabel[i], gridConstraints);
            if (i == 5) {
                j++;
            }
            colorLabel[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    colorMousePressed(e);
                }
            });

        } // end of for loop

        //set color labels
        colorLabel[0].setBackground(Color.GRAY);
        colorLabel[1].setBackground(Color.BLUE);
        colorLabel[2].setBackground(Color.GREEN);
        colorLabel[3].setBackground(Color.CYAN);
        colorLabel[4].setBackground(Color.RED);
        colorLabel[5].setBackground(Color.MAGENTA);
        colorLabel[6].setBackground(Color.YELLOW);
        colorLabel[7].setBackground(Color.WHITE);
        colorLabel[8].setBackground(Color.ORANGE);
        colorLabel[9].setBackground(Color.PINK);
        colorLabel[10].setBackground(Color.getHSBColor(0.1F, 0.2F, 0.3F));
        colorLabel[11].setBackground(Color.getHSBColor(0.5F, 0.6F, 0.7F));
        leftColor = colorLabel[1].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[4].getBackground();
        rightColorLabel.setBackground(rightColor);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screenSize.width - getWidth())), (int) (.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
        // create graphics object
        g2D = (Graphics2D) drawPanel.getGraphics();
    }

    private void exitForm(WindowEvent e) {
        g2D.dispose();
        System.exit(0);
    }

    private void colorMousePressed(MouseEvent e) {
        // decide which color is selected
        Component clickedColor = e.getComponent();
        //make a sound
        Toolkit.getDefaultToolkit().beep();
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }

    }

    private void exitMenuItemActionPerformed(ActionEvent e) {

        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Program",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return;
        } else {
            exitForm(null);
        }
    }

    private void drawPanelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {

            xPrevious = e.getX();
            yPrevious = e.getY();
            if (e.getButton() == MouseEvent.BUTTON1) {
                drawColor = leftColor;
            } else { //includes both middle and right
                drawColor = rightColor;
            }
        }
    }

    private void drawPanelmouseDragged(MouseEvent e) {
        Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
        g2D.setPaint(drawColor);
        g2D.draw(myLine);
        xPrevious = e.getX();
        yPrevious = e.getY();
    }

    private void drawPanelmouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
            Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
            g2D.setPaint(drawColor);
            g2D.draw(myLine);
        }
    }

    private void newMenuItemActionPerformed(ActionEvent e) {

        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new drawing?", "New Drawing",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
        }
    }

    /*
    private void resizeMenuItemMenuItemActionPerformed(ActionEvent e) {
        String responseX;
        responseX = JOptionPane.showInputDialog(null, "What is your X dimension?");
        Panelx = responseX;
        String responseY;
        responseY = JOptionPane.showConfirmDialog(null, "What is your Y dimension?");
        Panely = responseY;
    }
     */

    private void blueMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.BLUE);
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    private void redMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.RED);
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    private void magentaMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.MAGENTA);
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    private void greenMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.GREEN);
        
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }
}
