package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ParkingLotGUI extends JFrame {
    private final int NUM_ROWS = 5;
    private final int NUM_COLS = 5;
    private final int SPACE_WIDTH = 100;
    private final int SPACE_HEIGHT = 100;
    private final int PARKING_LOT_WIDTH = NUM_COLS * SPACE_WIDTH;
    private final int PARKING_LOT_HEIGHT = NUM_ROWS * SPACE_HEIGHT;
    private JPanel parkingLotPanel;
    private JLabel[][] spaceLabels;
    private JButton findPathButton;
    private JTextField spaceNumberField;
    private ArrayList<Point> path;

    public ParkingLotGUI() {
        super("Parking Lot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
        setSize(PARKING_LOT_WIDTH, PARKING_LOT_HEIGHT + 80);
        setVisible(true);
    }

    private void createComponents() {
        // Create parking lot panel
        parkingLotPanel = new JPanel();
        parkingLotPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        parkingLotPanel.setPreferredSize(new Dimension(PARKING_LOT_WIDTH, PARKING_LOT_HEIGHT));
        spaceLabels = new JLabel[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                JLabel spaceLabel = new JLabel();
                spaceLabel.setOpaque(true);
                spaceLabel.setPreferredSize(new Dimension(SPACE_WIDTH, SPACE_HEIGHT));
                spaceLabel.setBackground(Color.GREEN);
                spaceLabels[row][col] = spaceLabel;
                parkingLotPanel.add(spaceLabel);
            }
        }
        add(parkingLotPanel, BorderLayout.NORTH);

        // Create find path button and space number field
        findPathButton = new JButton("Find Path");
        spaceNumberField = new JTextField(10);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Enter Space Number:"));
        buttonPanel.add(spaceNumberField);
        buttonPanel.add(findPathButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listener to find path button
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String spaceNumber = spaceNumberField.getText();
                Point spaceLocation = findSpaceLocation(spaceNumber);
                if (spaceLocation != null) {
                    path = findPath(spaceLocation);
                    showPath();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid space number.");
                }
            }
        });
    }

    private Point findSpaceLocation(String spaceNumber) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (spaceLabels[row][col].getText().equals(spaceNumber)) {
                    return new Point(col, row);
                }
            }
        }
        return null;
    }

    private ArrayList<Point> findPath(Point spaceLocation) {
        ArrayList<Point> path = new ArrayList<>();
        // TODO: Implement pathfinding algorithm
        return path;
    }

    private void showPath() {
        Graphics2D g2 = (Graphics2D) parkingLotPanel.getGraphics();
        g2.setColor(Color.RED);
        Point prev = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            Point curr = path.get(i);
            int startX = prev.x * SPACE_WIDTH + SPACE_WIDTH / 2;
            int startY = prev.y * SPACE_HEIGHT + SPACE_HEIGHT / 2;
            int endX = curr.x * SPACE_WIDTH + SPACE_WIDTH / 2;
            int endY = curr.y * SPACE_HEIGHT + SPACE_HEIGHT / 2;
            g2.drawLine(startX, startY, endX, endY);
            prev = curr;
        }
    }

    public static void main(String[] args) {
        new ParkingLotGUI();
    }
}
