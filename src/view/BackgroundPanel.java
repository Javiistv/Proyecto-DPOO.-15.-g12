package view;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Ajusta la imagen al tamaño del panel
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel con imagen de fondo");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPanel panel = new BackgroundPanel("D:/javis/Downloads/Terminal.jpg"); // Reemplaza con la ubicación de tu imagen
        frame.add(panel);
        frame.setVisible(true);
    }
}