package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.logging.Logger;

public class ParkingLotGUI extends JFrame {
    private static final Logger logger = Logger.getLogger(ParkingLotGUI.class.getName());

    private static final int SPACE_WIDTH = 24; // 停车位宽度
    private static final int SPACE_HEIGHT = 53; // 停车位高度
    private static final int PARKING_ROWS = 5; // 停车场行数
    private static final int PARKING_COLS = 8; // 停车场列数
    // 定义道路的宽度和高度
    final int ROAD_WIDTH = 20;
    final int ROAD_HEIGHT = 20;

    private JPanel parkingLotPanel; // 停车场图像面板
    private JLabel[][] spaceLabels; // 停车位标签
    private JPanel buttonPanel; // 按钮面板
    private JLabel spaceNumberLabel; // 车位号标签
    private JTextField spaceNumberField; // 车位号输入框
    private JButton findPathButton; // 查找路径按钮

    public ParkingLotGUI() {
        super("Parking Lot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
        setSize(800, 600);
        setVisible(true);
    }

    private void createComponents() {

        // 设置停车场数组
        char[][] parkingLotArray = {
                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },

                { 'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R' },
                { 'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R' },

                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },

                { 'R', 'E', 'E', 'E', 'E', 'R', 'E', 'E', 'E', 'R' },
                { 'R', 'E', 'E', 'E', 'C', 'R', 'E', 'E', 'E', 'R' },

                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },

                { 'R', 'E', 'E', 'E', 'E', 'R', 'E', 'E', 'E', 'R' },
                { 'R', 'E', 'E', 'E', 'E', 'R', 'E', 'E', 'E', 'R' },
                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' }
        };

        // 创建停车场图像面板
        parkingLotPanel = new JPanel();
        parkingLotPanel.setLayout(new GridLayout(parkingLotArray.length, parkingLotArray[0].length));
        spaceLabels = new JLabel[parkingLotArray.length][parkingLotArray[0].length];


        // 遍历 parkingLotArray 数组，创建标签并添加到面板上
        for (char[] chars : parkingLotArray) {
            for (char type : chars) {
                JLabel label = new JLabel();
                label.setPreferredSize(new Dimension(SPACE_WIDTH, SPACE_HEIGHT));
                label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                switch (type) {
                    case 'R':
                        label.setBackground(Color.GRAY); // 道路用灰色
                        break;
                    case 'C':
                        label.setBackground(Color.RED); // 汽车用红色
                        break;
                    case 'E':
                        label.setBackground(Color.GREEN); // 空车位用绿色
                        break;
                }
                label.setOpaque(true);

                parkingLotPanel.add(label);
            }
        }


        // 创建按钮面板
        buttonPanel = new JPanel();
        spaceNumberLabel = new JLabel("车位:");
        spaceNumberField = new JTextField(10);
        findPathButton = new JButton("查找");


        // 添加查找路径按钮的动作监听器
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取用户输入的车位号
                String spaceNumber = spaceNumberField.getText().trim();
                // TODO: 查找路径并显示
                // 例如：
                int row = 2;
                int col = 3;
                JLabel spaceLabel = spaceLabels[row][col];
                spaceLabel.setBackground(Color.RED);
            }
        });

        // 将组件添加到主面板
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(parkingLotPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(spaceNumberLabel);
        buttonPanel.add(spaceNumberField);
        buttonPanel.add(findPathButton);
        setContentPane(mainPanel);


    }


    public static void main(String[] args) {
        new ParkingLotGUI();
    }

}
