package org.car.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.car.controller.UsertableForCarController;
import org.car.controller.impl.UsertableForCarControllerImpl;
import org.car.dao.UsertableForCarDao;
import org.car.dao.impl.UsertableForCarDeoImpl;
import org.car.entity.CarTable;
import org.car.util.BackImage;
import org.car.util.JDBC;
import org.car.util.JLableTime;
import org.car.util.LocationForSwing;
import org.car.util.MyDefaultTableModel;
import org.car.util.MyTable;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User extends JFrame {


    private JComboBox comboBox;
    private JComboBox comboBox_1 ;
    private JComboBox comboBox_2;
    private int sumRent=0;
    private JTextField textField;
    private JTable table_2;
    private DefaultTableModel defaultTableModel;
    private JTextField textField_1;
    private JTextField textField_2;
    private String username;
    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     * @throws SQLException
     *
     */
    private void clear() {
        table_2.clearSelection();
        sumRent=0;
        comboBox.setSelectedItem("所有");
        comboBox_1.setSelectedItem("所有");
        comboBox_2.setSelectedItem("所有");
        textField.setText("");
        textField_2.setText("");
        textField_1.setText("");
        search();
    }
    private void borrowCar() {
        this.dispose();
        new  BorrowCar(username);
    }
    private void insertBorrowTable() {

        int[] rowArrays=table_2.getSelectedRows();
        if(rowArrays.length<=0) {
            JOptionPane.showMessageDialog(null, "请选择车辆");
        }else {
            int count=0;
            for(int i=0;i<rowArrays.length;i++) {
                String carnumber=(String) defaultTableModel.getValueAt(rowArrays[i],1);
                int day=(int) defaultTableModel.getValueAt(rowArrays[i], 7);
                UsertableForCarDao ud=new UsertableForCarDeoImpl();
                CarTable ct=ud.selectCarTableByBorrowCar(carnumber);
                UsertableForCarController uc=new UsertableForCarControllerImpl();
                boolean flag=uc.insertBorrowTableByBorrowCar(ct, username, day);
                boolean flag2=uc.deleteCarTableByUser(carnumber);
                if(flag&&flag2) {
                    count++;
                }
            }
            if(count==rowArrays.length) {
                JOptionPane.showMessageDialog(null,"租车成功");
                return;
            }else {
                JOptionPane.showMessageDialog(null,"租车失败");
                return;
            }
        }
    }
    private void update() {
        defaultTableModel.setRowCount(0);
        UsertableForCarController uc=new UsertableForCarControllerImpl();
        List<CarTable> list=uc.selectCarTableByUserForall();
        for(int i=1;i<=list.size();i++) {
            Object[] obj= {i,list.get(i-1).getCarNumber(),list.get(i-1).getCarBrand(),list.get(i-1).getCarType(),list.get(i-1).getCarColor(),
                    list.get(i-1).getCarAutoOrHand(),list.get(i-1).getCarRent(),0};
            defaultTableModel.addRow(obj);
        }
    }
    private void search()  {
        int rowCount=defaultTableModel.getRowCount();
        if(rowCount>0) {
            for(int i=rowCount-1;i>=0;i--) {
                defaultTableModel.removeRow(i);
            }
        }
        String carNumber=textField.getText();
        String carType=(String) comboBox.getSelectedItem();
        String carBrand=(String) comboBox_1.getSelectedItem();
        String carAutoOrHand=(String) comboBox_2.getSelectedItem();
        CarTable ct=new CarTable(carNumber, carBrand, carType,carAutoOrHand);
        //UsertableForCarDao ud=new UsertableForCarDeoImpl();
        //ud.selectCarTableByUser(defaultTableModel, ct);
        UsertableForCarController uc=new UsertableForCarControllerImpl();
        List<CarTable> list=uc.selectCarTableByUser1(ct);
        if(list.size()>0&&list!=null) {
            for(int i=1;i<=list.size();i++) {
                Object[] obj= {i,list.get(i-1).getCarNumber(),list.get(i-1).getCarBrand(),list.get(i-1).getCarType(),list.get(i-1).getCarColor(),
                        list.get(i-1).getCarAutoOrHand(),list.get(i-1).getCarRent(),0};
                defaultTableModel.addRow(obj);
            }
        }else{
            JOptionPane.showMessageDialog(this, "没有匹配的汽车");
            return;
        }

    }
    private void sum() {
        textField_1.setText(new Integer(table_2.getSelectedRowCount()).toString());

        int[] rowArrays=table_2.getSelectedRows();

        for(int i=0;i<rowArrays.length;i++) {
            int day=(int) defaultTableModel.getValueAt(rowArrays[i], 7);

            int rent=(int) defaultTableModel.getValueAt(rowArrays[i], 6);

            sumRent=sumRent+day*rent;

        }
        textField_2.setText(new Integer(sumRent).toString());
    }
    private void alterPassword() {

        new AlterPwd(username);
    }
    private void toLogin() {
        this.dispose();
        new Login();
    }
    public User(String username) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(User.class.getResource("/org/car/view/picture/timg.jpg")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog (null, "要退出该程序吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (exit == JOptionPane.YES_OPTION)
                {
                    System.exit (0);
                }

            }
        });

        this.username=username;
        setVisible(true);
        setTitle("租车管理");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1115, 600);
        Container contentPane=getContentPane();
        contentPane.setLayout(null);



        URL url=Login.class.getResource("picture/user_background.jpg");
        BackImage.setBackGround(this, url);

        JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        panel_1.setBounds(147, 63, 949, 490);
        contentPane.add(panel_1);
        panel_1.setLayout(null);



        JLabel lblNewLabel = new JLabel("\u641C\u7D22:\u8F66\u724C:");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel.setBounds(0, 56, 88, 36);
        panel_1.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textField.setBounds(89, 56, 148, 36);
        panel_1.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u8F66\u578B:");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(242, 56, 44, 37);
        panel_1.add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 93, 949, 303);
        panel_1.add(scrollPane);

        String[] columnNames=new String[] {
                "\u5E8F\u53F7", "\u8F66\u724C", "\u54C1\u724C", "\u8F66\u578B", "\u989C\u8272", "换挡类型","租金(元/天)", "要租的天数"
        };

        defaultTableModel= new MyDefaultTableModel(null, columnNames);
        table_2 = new MyTable(defaultTableModel);
        table_2.setAutoCreateRowSorter(true);
        table_2.setRowHeight(30);
        table_2.setAutoResizeMode(4);
        scrollPane.setViewportView(table_2);



        comboBox = new JComboBox();
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u5C0F\u8F7F", "\u5546\u52A1", "\u8D8A\u91CE", "\u8D85\u8DD1"}));

        comboBox.setBounds(287, 56, 61, 36);
        panel_1.add(comboBox);

        JLabel lblNewLabel_2 = new JLabel("\u54C1\u724C:");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(350, 56, 44, 36);
        panel_1.add(lblNewLabel_2);

        comboBox_1 = new JComboBox();
        comboBox_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u5954\u9A70", "\u5B9D\u9A6C", "\u6CD5\u62C9\u5229"}));
        comboBox_1.setBounds(392, 56, 78, 36);
        panel_1.add(comboBox_1);

        JButton btnNewButton_3 = new JButton("\u67E5\u627E");
        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnNewButton_3.setFocusPainted(false);//除去焦点的框
        btnNewButton_3.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                search();

            }
        });
        btnNewButton_3.setBounds(709, 56, 113, 36);
        panel_1.add(btnNewButton_3);

        JLabel lblNewLabel_3 = new JLabel("\u6362\u6321\u7C7B\u578B\uFF1A");
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(494, 56, 88, 36);
        panel_1.add(lblNewLabel_3);

        comboBox_2 = new JComboBox();
        comboBox_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u81EA\u52A8", "\u624B\u52A8", "\u624B\u81EA\u4E00\u4F53"}));
        comboBox_2.setBounds(585, 56, 88, 36);
        panel_1.add(comboBox_2);

        JButton btnNewButton_4 = new JButton("\u91CD\u65B0\u9009\u62E9");
        btnNewButton_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnNewButton_4.setFocusPainted(false);//除去焦点的框
        btnNewButton_4.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        btnNewButton_4.setBounds(836, 56, 113, 36);
        panel_1.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("\u603B\u8BA1:");
        btnNewButton_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_5.setForeground(new Color(0, 255, 255));
        btnNewButton_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnNewButton_5.setFocusPainted(false);//除去焦点的框
        btnNewButton_5.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sum();
            }
        });
        btnNewButton_5.setBounds(0, 430, 93, 36);
        panel_1.add(btnNewButton_5);

        JLabel lblNewLabel_4 = new JLabel("\u4F60\u603B\u5171\u79DF\u4E86:");
        lblNewLabel_4.setForeground(new Color(0, 255, 255));
        lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(132, 430, 103, 36);
        panel_1.add(lblNewLabel_4);

        textField_1 = new JTextField();
        textField_1.setBounds(242, 430, 32, 36);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("\u8F86\u8F66");
        lblNewLabel_5.setForeground(new Color(0, 255, 255));
        lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(287, 430, 44, 36);
        panel_1.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("\u9700\u8981\u652F\u4ED8:");
        lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_6.setForeground(new Color(0, 255, 255));
        lblNewLabel_6.setBounds(375, 430, 78, 36);
        panel_1.add(lblNewLabel_6);

        textField_2 = new JTextField();
        textField_2.setBounds(474, 430, 61, 36);
        panel_1.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("\u5143");
        lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_7.setForeground(new Color(0, 255, 255));
        lblNewLabel_7.setBounds(556, 430, 32, 36);
        panel_1.add(lblNewLabel_7);

        JButton btnNewButton_6 = new JButton("\u79DF\u8F66");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int select=JOptionPane.showConfirmDialog(null, "是否租车吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(select==JOptionPane.YES_OPTION) {
                    insertBorrowTable();
                    update();
                }else {
                    return;
                }
            }
        });
        btnNewButton_6.setForeground(new Color(0, 255, 255));
        btnNewButton_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnNewButton_6.setBounds(803, 430, 113, 36);
        btnNewButton_6.setFocusPainted(false);//除去焦点的框
        btnNewButton_6.setContentAreaFilled(false);//除去默认的背景填充
        panel_1.add(btnNewButton_6);
        JPanel panel_2 = new JPanel();
        panel_2.setOpaque(false);
        panel_2.setBounds(0, 0, 1096, 63);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_8 = new JLabel();
        lblNewLabel_8.setForeground(new Color(255, 0, 0));
        lblNewLabel_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblNewLabel_8.setText("\u6B22\u8FCE\u4F60,");
        lblNewLabel_8.setBounds(0, 0, 83, 30);
        panel_2.add(lblNewLabel_8);

        JLableTime lblNewLabel_9 = new JLableTime();
        lblNewLabel_9.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_9.setForeground(new Color(255, 0, 0));
        lblNewLabel_9.setBounds(900, 0, 196, 30);
        panel_2.add(lblNewLabel_9);

        JLabel lblNewLabel_10 = new JLabel();
        lblNewLabel_10.setText(username);
        lblNewLabel_10.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblNewLabel_10.setForeground(new Color(255, 0, 0));
        lblNewLabel_10.setBounds(0, 33, 83, 30);
        panel_2.add(lblNewLabel_10);


        new Thread(lblNewLabel_9).start();;

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBounds(0, 183, 147, 370);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("\u4FEE\u6539\u5BC6\u7801");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterPassword();
            }
        });

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton.setForeground(new Color(0, 0, 255));
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnNewButton.setIcon(new ImageIcon(User.class.getResource("/org/car/view/picture/user_1.png")));
        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        btnNewButton.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        btnNewButton.setFocusPainted(false);//除去焦点的框
        btnNewButton.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton.setBounds(0, 0, 147, 50);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u5DF2\u79DF\u67E5\u8BE2");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                borrowCar();
            }
        });
        btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnNewButton_1.setForeground(new Color(0, 0, 255));
        btnNewButton_1.setIcon(new ImageIcon(User.class.getResource("/org/car/view/picture/user_1.png")));
        btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_1.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        btnNewButton_1.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        btnNewButton_1.setFocusPainted(false);//除去焦点的框
        btnNewButton_1.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_1.setBounds(0, 70, 147, 50);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toLogin();
            }
        });
        btnNewButton_2.setIcon(new ImageIcon(User.class.getResource("/org/car/view/picture/user_2.jpg")));
        btnNewButton_2.setBounds(0, 318, 50, 50);
        btnNewButton_2.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        btnNewButton_2.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        btnNewButton_2.setFocusPainted(false);//除去焦点的框
        btnNewButton_2.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        panel.add(btnNewButton_2);

        setResizable(false);
        LocationForSwing.setLocation(this);

    }
}
