package mousedrawing;

import com.sun.glass.events.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.*;

/**
 *
 * @author compsci
 */
public class MouseDrawing extends JFrame {

    /**
     *
     * Adds left color and right color colorlabel adds 8 colors in an array
     *
     * @var xPrevious keeps track of mouse x corrordinate
     */
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("file");
    JMenuItem newMenuItem = new JMenuItem("new");
    JMenuItem exitMenuItem = new JMenuItem("exit");
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[12]; //num of colors
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;

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
        fileMenu.add(newMenuItem);
        mainMenuBar.setBackground(Color.MAGENTA);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        drawPanel.setPreferredSize(new Dimension(500, 400));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridConstraints);

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

        colorPanel.setPreferredSize(new Dimension(80, 160+70));
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

        }
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
        colorLabel[10].setBackground(Color.getHSBColor(0.1F,  0.2F,  0.3F));
        colorLabel[11].setBackground(Color.getHSBColor(0.5F,  0.6F,  0.7F));
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

}
