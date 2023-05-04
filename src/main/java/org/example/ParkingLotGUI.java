package org.example;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.logging.Logger;

public class ParkingLotGUI extends JFrame {
    private static final Logger logger = Logger.getLogger(ParkingLotGUI.class.getName());

    private static int SPACE_WIDTH=50; // 停车位宽度
    private static int SPACE_HEIGHT=50; // 停车位高度
    private char[][] parkingLotArray;

    private JPanel parkingLotPanel; // 停车场图像面板
    private JLabel infoLabel; // 停车场信息

    private JLabel[][] spaceLabels; // 停车位标签
    private JPanel buttonPanel; // 按钮面板
    private JLabel spaceNumberLabel; // 车位号标签
    private JTextField spaceNumberField; // 车位号输入框
    private JButton findPathButton; // 查找路径按钮

    public ParkingLotGUI() {
        super("Parking Lot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
        setSize(800, 800);
        setVisible(true);
    }

    private void createComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        parkingLotPanel = new JPanel();
        // 创建JLabel并设置文本
        JLabel centerLabel = new JLabel("停车场路径规划V1.0");
        centerLabel.setFont(new Font("宋体", Font.BOLD, 30));

        centerLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平居中
        // 将JLabel添加到JPanel中心
        parkingLotPanel.setLayout(new BorderLayout());
        parkingLotPanel.add(centerLabel, BorderLayout.CENTER);


        JComboBox<String> mapComboBox = new JComboBox<>();
        mapComboBox.addItem("请选择");

        // 获取所有地图文件名
        File mapsDir = new File("parkinglotMap");
        File[] mapFiles = mapsDir.listFiles();
        if (mapFiles != null) {
            for (File file : mapFiles) {
                    mapComboBox.addItem(file.getName());
            }
        }

         // 监听下拉框选择变化
        mapComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    mainPanel.remove(parkingLotPanel); // 移除原有的停车场面板

                    // 获取选择的地图文件名
                    String selectedMap = (String) e.getItem();
                    // 拼接文件路径
                    String mapFilePath = "parkinglotMap/" + selectedMap;
                    parkingLotArray = readParkingLotArrayFromFile(mapFilePath);
                    parkingLotPanel = updateParkingLotPanel(parkingLotArray);

                    mainPanel.add(parkingLotPanel, BorderLayout.CENTER); // 添加更新后的停车场面板
                    mainPanel.revalidate(); // 重新布局并绘制面板
                    mainPanel.repaint();
                }
            }
        });






        // 添加下拉框选择组件到主面板
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectPanel.add(new JLabel("停车场地图: "));
        JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.add(mapComboBox, BorderLayout.CENTER);
        selectPanel.add(mapPanel);

        infoLabel = new JLabel("点击车位查看信息", JLabel.CENTER);
        // 设置JFrame的布局为BorderLayout
        setLayout(new BorderLayout());


        Box box = Box.createHorizontalBox();
        box.add(selectPanel);
        box.add(Box.createHorizontalStrut(10)); // 加一个水平间距
        box.add(infoLabel);
        mainPanel.add(box, BorderLayout.NORTH);


        // 创建按钮面板
        buttonPanel = new JPanel();
        spaceNumberLabel = new JLabel("车位:");
        spaceNumberField = new JTextField(10);
        findPathButton = new JButton("查找");

        // 添加查找路径按钮的动作监听器
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String startNode = "";
                for (int i = 0; i < parkingLotArray.length; i++) {
                    for (int j = 0; j < parkingLotArray[0].length; j++) {
                        if (parkingLotArray[i][j] == 'S') {
                            startNode = i + "-" + j;
                            break;
                        }
                    }
                }

                // 获取用户输入的车位号
                String spaceNumber = spaceNumberField.getText().trim();
                GraphPath<String, DefaultEdge> shortestPath =   GraphUtils.DijkstraPath(parkingLotArray,startNode,spaceNumber);
                char[][] newParkingLotArray = parkingLotArray.clone();
                for (int i = 0; i < parkingLotArray.length; i++) {
                    newParkingLotArray[i] = parkingLotArray[i].clone();
                }

                for (String vertex : shortestPath.getVertexList()) {
                    String[] parts = vertex.split("-");
                    int num1 = Integer.parseInt(parts[0]);
                    int num2 = Integer.parseInt(parts[1]);
                    newParkingLotArray[num1][num2] = 'P';

                }
                mainPanel.remove(parkingLotPanel); // 移除原有的停车场面板

                parkingLotPanel = updateParkingLotPanel(newParkingLotArray);

                mainPanel.add(parkingLotPanel, BorderLayout.CENTER); // 添加更新后的停车场面板
                mainPanel.revalidate(); // 重新布局并绘制面板
                mainPanel.repaint();

            }
        });






        // 将组件添加到主面板
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(parkingLotPanel, BorderLayout.CENTER);

        buttonPanel.add(spaceNumberLabel);
        buttonPanel.add(spaceNumberField);
        buttonPanel.add(findPathButton);
        setContentPane(mainPanel);



    }

    private JPanel updateParkingLotPanel( char[][] parkingLotArray) {
        parkingLotPanel = new JPanel();
        // 创建停车场图像面板
        parkingLotPanel.setLayout(new GridLayout(parkingLotArray.length, parkingLotArray[0].length));
        spaceLabels = new JLabel[parkingLotArray.length][parkingLotArray[0].length];
        //动态调整图片大小
        int space_wigth = Math.max(1, SPACE_WIDTH / (parkingLotArray.length / 10));
        int space_heigth = Math.max(1, SPACE_HEIGHT/(parkingLotArray.length/10));

        // 遍历 parkingLotArray 数组，创建标签并添加到面板上
        for (int i = 0; i < parkingLotArray.length; i++) {
            for (int j = 0; j < parkingLotArray[i].length; j++) {
                char type = parkingLotArray[i][j];
                switch (type) {
                    case 'R':
                        JLabel label = createLabel("/road.png", Color.WHITE,i,j,space_wigth,space_heigth);
                        parkingLotPanel.add(label);
                        break;
                    case 'C':
                        label = createLabel("/car_red.png", Color.WHITE,i,j,space_wigth,space_heigth);
                        parkingLotPanel.add(label);
                        break;
                    case 'E':
                        label = createLabel("/car_green.png", Color.WHITE,i,j,space_wigth,space_heigth);
                        parkingLotPanel.add(label);
                        break;
                    case 'P':
                        label = new JLabel();
                        label.setBackground(Color.GREEN);
                        label.setOpaque(true);
                        parkingLotPanel.add(label, BorderLayout.CENTER); // 添加到停车场面板的中央
                        break;
                    case 'S':
                        label = new JLabel("入口");
                        label.setFont(label.getFont().deriveFont(20f)); // 改变字体大小
                        label.setForeground(Color.BLACK); // 改变字体颜色
                        label.setBackground(Color.WHITE);
                        label.setOpaque(true);

                        parkingLotPanel.add(label, BorderLayout.CENTER); // 添加到停车场面板的中央

                        break;
                }
            }
        }
        return parkingLotPanel;
    }


    private JLabel createLabel(String imagePath, Color backgroundColor,int i, int j,int space_wigth,int space_heigth) {

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        Image img = icon.getImage().getScaledInstance(space_wigth, space_heigth, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);
        JLabel label = new JLabel(newIcon);

        label.setPreferredSize(new Dimension(space_wigth, space_heigth));
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        label.setBackground(backgroundColor);
        label.setOpaque(true);
        // 设置其他JLabel的鼠标监听器
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 当鼠标点击JLabel时，将其文本设置为信息显示区域JLabel的文本
                infoLabel.setText("车位号："+String.valueOf(i)+"-"+ j+"    ");
            }
        };

        label.addMouseListener(mouseAdapter);

        return label;
    }
    public static char[][] readParkingLotArrayFromFile(String filePath) {
        List<char[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.toCharArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[][] parkingLotArray = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            parkingLotArray[i] = lines.get(i);
        }

        return parkingLotArray;
    }



}
