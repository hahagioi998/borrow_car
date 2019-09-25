package org.car.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.car.controller.UsertableForCarController;
import org.car.controller.impl.UsertableForCarControllerImpl;
import org.car.dao.UsertableForCarDao;
import org.car.dao.impl.UsertableForCarDeoImpl;
import org.car.entity.BorrowTable;
import org.car.entity.CarTable;
import org.car.util.BackImage;
import org.car.util.JLableTime;
import org.car.util.LocationForSwing;
import org.car.util.MyDefaultTableModel;
import org.car.util.MyTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class BorrowCar extends JFrame {
    private String username;
    private DefaultTableModel defaultTableModel;
    private JTable table;
    private JTextField textField;
    private JComboBox comboBox_1;
    private JComboBox comboBox ;
    private JComboBox comboBox_2;
    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    private void toUser() {
        this.dispose();
        new User(username);
    }
    private void returnCar() {
        int[] rowCounts=table.getSelectedRows();
        if(rowCounts.length<=0) {
            JOptionPane.showMessageDialog(null, "请选择车辆");
        }else {
            int count=0;
            for(int i=0;i<rowCounts.length;i++) {
                String carnumber=(String) defaultTableModel.getValueAt(rowCounts[i], 1);

                UsertableForCarController uc=new UsertableForCarControllerImpl();
                CarTable car=uc.selectBorrowTableByBorrowCarForReturn(carnumber);

                boolean flag=uc.insertCartableByBorrowCar(car);

                boolean flag2=uc.deleteBorrowCarByBorrowCar(carnumber);
                if(flag&&flag2) {
                    count++;
                }

            }
            if(count==rowCounts.length) {
                JOptionPane.showMessageDialog(null,"还车成功");
                return;
            }else {
                JOptionPane.showMessageDialog(null,"还车失败");
                return;
            }
        }
    }
    private void update() {
        defaultTableModel.setRowCount(0);
        insertBorrowTable();


    }
    private void insertBorrowTable() {

        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        List<BorrowTable> list= ud.selectBorrowTableByBorrowCar(username);
        if(list.size()>0&&list!=null) {
            for(int i=1;i<=list.size();i++) {
                Object[] obj= {i,list.get(i-1).getCar().getCarNumber(),list.get(i-1).getCar().getCarBrand(),list.get(i-1).getCar().getCarType(),
                        list.get(i-1).getCar(). getCarColor(),list.get(i-1).getCar().getCarAutoOrHand(),list.get(i-1).getCar().getCarRent(),
                        list.get(i-1).getRentDay(),list.get(i-1).getBorrowTime(),list.get(i-1).getReturnTime()};
                defaultTableModel.addRow(obj);
            }
        }
    }
    private void search() {
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
        UsertableForCarController uc=new UsertableForCarControllerImpl();
        List<BorrowTable> list=uc.selectBorrowTableByBorrowCar(ct);
        if(list.size()>0&&list!=null) {
            for(int i=1;i<=list.size();i++) {
                Object[] obj= {i,list.get(i-1).getCar().getCarNumber(),list.get(i-1).getCar().getCarBrand(),list.get(i-1).getCar().getCarType(),list.get(i-1).getCar().getCarColor(),
                        list.get(i-1).getCar().getCarAutoOrHand(),list.get(i-1).getCar().getCarRent(),list.get(i-1).getRentDay(),list.get(i-1).getBorrowTime(),list.get(i-1).getReturnTime()};
                defaultTableModel.addRow(obj);
            }
        }else{
            JOptionPane.showMessageDialog(this, "没有匹配的汽车");
            return;
        }
    }
    public BorrowCar(String username) {
        this.username=username;
        String[] columnNames=new String[] {
                "\u5E8F\u53F7", "\u8F66\u724C", "\u54C1\u724C", "\u8F66\u578B", "\u989C\u8272", "\u6362\u6321\u7C7B\u578B", "\u79DF\u91D1(\u5143/\u5929)","租的天数", "\u79DF\u8FDB\u65F6\u95F4", "\u5230\u671F\u65F6\u95F4"
        };
        defaultTableModel=new MyDefaultTableModel(null, columnNames);
        setIconImage(Toolkit.getDefaultToolkit().getImage(BorrowCar.class.getResource("/org/car/view/picture/timg.jpg")));
        setTitle("\u8FD8\u8F66\u9875\u9762");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog (null, "要退出该程序吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (exit == JOptionPane.YES_OPTION)
                {
                    System.exit (0);
                }

            }
            @Override
            public void windowOpened(WindowEvent e) {
                insertBorrowTable();
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1237, 600);
        Container contentPane=getContentPane();
        contentPane.setLayout(null);
        setVisible(true);

        URL url=Login.class.getResource("picture/borrowcar_background.jpg");
        BackImage.setBackGround(this, url);





        JPanel panel = new JPanel();
        panel.setBounds(0, 160, 1232, 359);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);


        table = new MyTable(defaultTableModel);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(30);
        table.setModel(defaultTableModel);
        scrollPane.setViewportView(table);



        JLabel lblNewLabel = new JLabel("\u641C\u7D22:\u8F66\u724C:");
        lblNewLabel.setBounds(0, 122, 88, 36);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(96, 122, 148, 36);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u8F66\u578B:");
        lblNewLabel_1.setBounds(250, 122, 44, 36);
        getContentPane().add(lblNewLabel_1);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u5C0F\u8F7F", "\u5546\u52A1", "\u8D85\u8DD1", "\u8D8A\u91CE"}));
        comboBox.setBounds(293, 122, 61, 36);
        getContentPane().add(comboBox);

        JLabel lblNewLabel_2 = new JLabel("\u54C1\u724C:");
        lblNewLabel_2.setBounds(370, 122, 44, 36);
        getContentPane().add(lblNewLabel_2);

        comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u5B9D\u9A6C", "\u5954\u9A70", "\u6CD5\u62C9\u5229"}));
        comboBox_1.setBounds(416, 122, 78, 36);
        getContentPane().add(comboBox_1);

        JLabel lblNewLabel_3 = new JLabel("\u6362\u6321\u7C7B\u578B\uFF1A");
        lblNewLabel_3.setBounds(511, 122, 75, 36);
        getContentPane().add(lblNewLabel_3);

        comboBox_2 = new JComboBox();
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u81EA\u52A8", "\u624B\u52A8", "\u624B\u81EA\u4E00\u4F53"}));
        comboBox_2.setBounds(588, 122, 88, 36);
        getContentPane().add(comboBox_2);

        JButton btnNewButton = new JButton("\u67E5\u627E");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton.setBounds(968, 122, 113, 36);
        btnNewButton.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton.setFocusPainted(false);//除去焦点的框
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u8FD8\u8F66");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnCar();
                update();
            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_1.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_1.setFocusPainted(false);//除去焦点的框
        btnNewButton_1.setBounds(1119, 122, 113, 36);
        getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toUser();
            }
        });
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_2.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        btnNewButton_2.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        btnNewButton_2.setFocusPainted(false);//除去焦点的框
        btnNewButton_2.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_2.setIcon(new ImageIcon(BorrowCar.class.getResource("/org/car/view/picture/borrow_1.png")));
        btnNewButton_2.setBounds(1174, 0, 56, 56);
        getContentPane().add(btnNewButton_2);

        JLabel lblNewLabel_4 = new JLabel("        \u8F66        \u5E93");
        lblNewLabel_4.setForeground(new Color(0, 0, 255));
        lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 40));
        lblNewLabel_4.setBounds(435, 0, 306, 77);
        getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel();
        lblNewLabel_5.setText("欢迎你,"+username);
        lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 18));
        lblNewLabel_5.setForeground(new Color(255, 0, 0));
        lblNewLabel_5.setBounds(0, 0, 197, 30);
        getContentPane().add(lblNewLabel_5);

        JLableTime lblNewLabel_6 = new JLableTime();
        lblNewLabel_6.setForeground(new Color(255, 0, 0));
        lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(1042, 521, 190, 30);
        getContentPane().add(lblNewLabel_6);
        new Thread(lblNewLabel_6).start();

        setResizable(false);
        LocationForSwing.setLocation(this);

    }
}
