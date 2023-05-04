package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JLabelMouseListenerDemo extends JFrame {
    private JLabel infoLabel;

    public JLabelMouseListenerDemo() {
        // 创建信息显示区域JLabel
        infoLabel = new JLabel("Click on a JLabel to display its text here.", JLabel.CENTER);

        // 设置JFrame的布局为BorderLayout
        setLayout(new BorderLayout());

        // 将信息显示区域JLabel添加到JFrame的顶部
        add(infoLabel, BorderLayout.NORTH);

        // 创建其他JLabel
        JLabel label1 = new JLabel("Label 1");
        JLabel label2 = new JLabel("Label 2");
        JLabel label3 = new JLabel("Label 3");

        // 设置其他JLabel的鼠标监听器
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 当鼠标点击JLabel时，将其文本设置为信息显示区域JLabel的文本
                JLabel clickedLabel = (JLabel) e.getSource();
                infoLabel.setText(clickedLabel.getText());
            }
        };

        label1.addMouseListener(mouseAdapter);
        label2.addMouseListener(mouseAdapter);
        label3.addMouseListener(mouseAdapter);

        // 将其他JLabel添加到JFrame的中央
        add(label1, BorderLayout.WEST);
        add(label2, BorderLayout.CENTER);
        add(label3, BorderLayout.EAST);

        // 设置JFrame的大小和可见性
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JLabelMouseListenerDemo();
    }
}
