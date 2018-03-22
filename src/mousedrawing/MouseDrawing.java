package mousedrawing;

import com.sun.glass.events.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     *  xPrevious keeps track of mouse x coordinate
     */
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("file");
    JMenu canvasMenu = new JMenu("Canvas");
    JMenu penMenu = new JMenu("Pen");
    JMenuItem newMenuItem = new JMenuItem("Clear");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenuItem resizeMenuItem = new JMenuItem("Resize");

    JMenuItem blueMenuItem = new JMenuItem("Blue");
    JMenuItem redMenuItem = new JMenuItem("Red");
    JMenuItem magentaMenuItem = new JMenuItem("Magenta");
    JMenuItem greenMenuItem = new JMenuItem("Green");
    JMenuItem pinkMenuItem = new JMenuItem("Pink");

    JMenuItem smallMenuItem = new JMenuItem("Small");
    JMenuItem mediumMenuItem = new JMenuItem("Medium");
    JMenuItem largeMenuItem = new JMenuItem("Large");

    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[20]; //num of colors
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;
    int Panelx = 500;
    int Panely = 400;
    int penSize = 1;
    
    /**
     *  rgb values are 234, 86, 60
     */
    public final static Color lightRed  = new Color(234, 86, 60);
    /**
     * rgb values are 135, 238, 242
     */
    public final static Color darkRed  = new Color(135, 238, 242);
    /**
     * rgb values are 255, 124, 124
     */
    public final static Color paleRed  = new Color(255, 124, 124);
    /**
     * rgb values are 229, 229, 229
     */
    public final static Color darkBlue  = new Color(229, 229, 229);
    /**
     * rgb values are 77, 157, 188
     */
    public final static Color lightBlue  = new Color(77, 157, 188);
    /**
     * rgb values are 0, 196, 160
     */
    public final static Color teal  = new Color(0, 196, 160);
    /**
     * rgb values are 43, 107, 8
     */
    public final static Color darkGreen  = new Color(43, 107, 8);
    /**
     * rgb values are 130, 177, 255
     */
    public final static Color paleBlue  = new Color(130, 177, 255);
    /**
     * rgb values are 195, 0, 255
     */
    public final static Color darkPurple  = new Color(195, 0, 255);
    /**
     * rgb values are 15, 153, 93
     */
    public final static Color seaGreen  = new Color(15, 153, 93);
    /**
     * rgb values are 158, 22, 0
     */
    public final static Color goodRed  = new Color(158, 22, 0);
    /**
     * rgb values are 66, 137, 130
     */
    public final static Color deepseaGreen  = new Color(66, 137, 130);
    /**
     *
     * @param args main
     */
    public static void main(String[] args) {

        new MouseDrawing().setVisible(true);

    }

    /**
     * Creates a drawing program
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
        mainMenuBar.add(penMenu);
        canvasMenu.add(blueMenuItem);
        canvasMenu.add(redMenuItem);
        canvasMenu.add(pinkMenuItem);
        canvasMenu.add(magentaMenuItem);
        canvasMenu.add(greenMenuItem);
        penMenu.add(smallMenuItem);
        penMenu.add(mediumMenuItem);
        penMenu.add(largeMenuItem);
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
        
         pinkMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pinkMenuItemActionPerformed(e);
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

        //pen size
        smallMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smallMenuItemActionPerformed(e);
            }
        });

        mediumMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediumMenuItemActionPerformed(e);
            }
        });

        largeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                largeMenuItemActionPerformed(e);
            }
        });

        /*resizeMenuItem.addActionListener(new ActionListener() {
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

        colorPanel.setPreferredSize(new Dimension(80, 160 + 170));
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
        for (int i = 0; i < 20; i++) {

            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30, 30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 10;
            colorPanel.add(colorLabel[i], gridConstraints);
            if (i == 9) {
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
        colorLabel[1].setBackground(deepseaGreen);
        colorLabel[2].setBackground(Color.BLUE);
        colorLabel[3].setBackground(lightBlue);
        colorLabel[4].setBackground(lightRed);
        colorLabel[5].setBackground(Color.RED);
        colorLabel[6].setBackground(Color.MAGENTA);
        colorLabel[7].setBackground(darkGreen);
        colorLabel[8].setBackground(Color.ORANGE);
        colorLabel[9].setBackground(darkBlue);
        colorLabel[10].setBackground(Color.getHSBColor(0.1F, 0.2F, 0.3F));
        colorLabel[11].setBackground(teal);
        colorLabel[12].setBackground(paleBlue);
        colorLabel[13].setBackground(darkRed);
        colorLabel[14].setBackground(paleRed);
        colorLabel[15].setBackground(goodRed);
        colorLabel[16].setBackground(darkPurple);
        colorLabel[17].setBackground(Color.GREEN);
        colorLabel[18].setBackground(Color.YELLOW);
        colorLabel[19].setBackground(Color.WHITE);
        leftColor = colorLabel[1].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[4].getBackground();
        rightColorLabel.setBackground(rightColor);
        
        /*
        public final static Color lightRed  = new Color(242, 79, 79);
    public final static Color darkRed  = new Color(102, 12, 12);
    public final static Color paleRed  = new Color(255, 124, 124);
    public final static Color darkBlue  = new Color(25, 98, 140);
    public final static Color lightBlue  = new Color(50, 146, 201);
    public final static Color teal  = new Color(0, 196, 160);
        */
        

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

        if (penSize == 1) {

            Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());

            g2D.setPaint(drawColor);
            g2D.draw(myLine);
            xPrevious = e.getX();
            yPrevious = e.getY();

            
            
        }
        
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

    
    /*private void resizeMenuItemMenuItemActionPerformed(ActionEvent e) {
    
    
    String responseX;
    responseX = JOptionPane.showInputDialog(null, "What is your X dimension?");
    Panelx = Integer.parseInt(responseX);
    String responseY;
    responseY = JOptionPane.showInputDialog(null, "What is your Y dimension?");
    Panely = Integer.parseInt(responseY);
    
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((int) (.5 * (screenSize.width - getWidth())), (int) (.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
    // create graphics object
    g2D = (Graphics2D) drawPanel.getGraphics();
    }*/
     
    private void blueMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.BLUE);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseDrawing.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    private void redMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.RED);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseDrawing.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }
    
    private void pinkMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.PINK);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseDrawing.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    private void magentaMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.MAGENTA);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseDrawing.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    private void greenMenuItemActionPerformed(ActionEvent e) {

        g2D.setPaint(Color.GREEN);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseDrawing.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));

    }

    // pen size
    private void smallMenuItemActionPerformed(ActionEvent e) {
        //penSize = 1;
        g2D.setStroke(new BasicStroke(1));
    }

    private void mediumMenuItemActionPerformed(ActionEvent e) {
        //penSize = 2;
        g2D.setStroke(new BasicStroke(10));
    }

    private void largeMenuItemActionPerformed(ActionEvent e) {
        //penSize = 3;
        g2D.setStroke(new BasicStroke(100));
    }
}
