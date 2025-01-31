import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Random;
import java.io.*;

public class GUI {
    private JFrame frame1;
    private JLabel timeLabel;
    private Color initColor = getGreen();

    //Green selection method
    private Color getGreen() {
        int minGreen = 60;
        int maxGreen = 255;
        Random green = new Random();
        int randomGreen = minGreen + green.nextInt((maxGreen - minGreen) + 1);
        return new Color(0, randomGreen, 0); // hue and saturation?
    }

    public GUI() {
        //Frame construction
        frame1 = new JFrame("GUI Test");
        frame1.setSize(500, 300);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setLayout(new BorderLayout());
        frame1.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        frame1.getContentPane().setBackground(initColor);
        frame1.setLocationRelativeTo(null);

        // Initial time Panel construction
        LocalDateTime now = LocalDateTime.now();
        String cleanTime = now.toString().split("\\.")[0].replace("T", " ");
        timeLabel = new JLabel("Current Date and Time: " + cleanTime);
        timeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JPanel timePanel = new JPanel();
        timePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        timePanel.setBackground(null);
        timePanel.setLayout(new GridBagLayout());
        timePanel.add(timeLabel);
        frame1.add(timePanel, BorderLayout.CENTER);

        // Menu bar construction
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        Font menuFont = new Font("Serif", Font.PLAIN, 18);

        // First selection, build functions in another class?
        JMenuItem timeItem = new JMenuItem("Update Clock");
        timeItem.setFont(menuFont);
        timeItem.addActionListener(_ -> {
            timeLabel.setText("Current Date and Time: " +
                    LocalDateTime.now().toString().split("\\.")[0].replace("T", " "));
            JOptionPane.showMessageDialog(frame1, "Clock updated successfully.");
        });

        // Second selection
        JMenuItem writeItem = new JMenuItem("Log Time");
        writeItem.setFont(menuFont);
        writeItem.addActionListener(_ -> {
            try (FileWriter writer = new FileWriter("src/log.txt", true)) {
                writer.write(timeLabel.getText() + "\n");
                JOptionPane.showMessageDialog(frame1, "Time saved successfully.");
            } catch (Exception i) {
                JOptionPane.showMessageDialog(frame1, "Unable to save file due to: " + i.getMessage());
            }
        });

        // Third selection
        JMenuItem colorItem = new JMenuItem("Change Background Color");
        colorItem.setFont(menuFont);
        colorItem.setBackground(initColor);
        colorItem.setOpaque(true);
        colorItem.addActionListener(_ -> {
            frame1.getContentPane().setBackground(getGreen());
            JOptionPane.showMessageDialog(frame1, "Background color changed successfully.");
        });

        // Fourth selection
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(menuFont);
        exitItem.addActionListener(_ ->
            System.exit(0)
        );

        menu.setFont(menuFont);
        menu.add(timeItem);
        menu.add(writeItem);
        menu.add(colorItem);
        menu.add(exitItem);
        bar.add(menu);
        frame1.setJMenuBar(bar);
        frame1.setVisible(true);
    }
}
