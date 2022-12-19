package com.mycompany.aplikasimanajemenbarangview;

import com.mycompany.aplikasimanajemenbarangcontroller.ControllerBarang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;

/**
 *
 * @author Prameswara <prameswaara@gmail.com>
 */
@Getter
public class ViewBarang extends JFrame implements ActionListener, MouseListener {

    private ControllerBarang controller;
    private ButtonGroup bGroup;
    private JTable table;
    private DefaultTableModel model;
    private JButton bAdd, bDelete, bUpdate;
    private JRadioButton kBaik, kBuruk;
    private JLabel laName, laQuality, laPrice, laBrand, laHeader;
    private JTextField fieName, fiePrice, fieBrand;
    private JPanel panLaFie, panButton, panTable, panHeader;

    public ViewBarang() throws HeadlessException {
        controller = new ControllerBarang(this);
        compGUI();
        setVisible(true);
    }

    private void compGUI() {
        setTitle("Aplikasi Manajemen Barang");
        setSize(740, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        panel header
        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(this.getWidth(), 50));
        laHeader = new JLabel("Aplikasi Manajemen Barang");
        laHeader.setFont(new Font("Dialog", Font.BOLD, 22));
        laHeader.setHorizontalAlignment(SwingConstants.CENTER);
        panHeader.add(laHeader);

//        label&field panel
        panLaFie = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panLaFie.setBackground(Color.YELLOW);
        panLaFie.setPreferredSize(new Dimension(265, 29));

//      name label & field
        laName = new JLabel("  Name barang");
        laName.setPreferredSize(new Dimension(100, 40));
        fieName = new JTextField();
        fieName.setPreferredSize(new Dimension(120, 30));
        panLaFie.add(laName);
        panLaFie.add(fieName);

//      quality label & field
        laQuality = new JLabel("  Kualitas Barang");
        laQuality.setPreferredSize(new Dimension(100, 40));
        kBaik = new JRadioButton("BAIK");
        kBaik.setPreferredSize(new Dimension(60, 30));
        kBaik.setActionCommand("BAIK");

        kBuruk = new JRadioButton("BURUK");
        kBuruk.setPreferredSize(new Dimension(70, 30));
        kBuruk.setActionCommand("BURUK");
        panLaFie.add(laQuality);
        panLaFie.add(kBaik);
        panLaFie.add(kBuruk);

        bGroup = new ButtonGroup();
        bGroup.add(kBaik);
        bGroup.add(kBuruk);

//      brand label  
        laBrand = new JLabel("  Brand");
        laBrand.setPreferredSize(new Dimension(100, 40));
        fieBrand = new JTextField();
        fieBrand.setPreferredSize(new Dimension(120, 30));
        panLaFie.add(laBrand);
        panLaFie.add(fieBrand);

        laPrice = new JLabel("  Harga Barang");
        laPrice.setPreferredSize(new Dimension(100, 40));
        fiePrice = new JTextField();
        fiePrice.setPreferredSize(new Dimension(120, 30));
        panLaFie.add(laPrice);
        panLaFie.add(fiePrice);

//      Button save(Add)
        bAdd = new JButton("SAVE");
        bAdd.addActionListener(this);

//      Button delete(Delete)
        bDelete = new JButton("DELETE");
        bDelete.addActionListener(this);

//      Button update(Update)
        bUpdate = new JButton("UPDATE");
        bUpdate.addActionListener(this);

        panButton = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panButton.add(bAdd);
        panButton.add(bDelete);
        panButton.add(bUpdate);
        panLaFie.add(panButton);

        panTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "ID",
            "Nama Barang",
            "Kualitas",
            "Brand",
            "Harga"
        }
        );
        table = new JTable(model);
        table.addMouseListener(this);
        JScrollPane scroll = new JScrollPane(table);
        panTable.add(scroll);

        Container contain = getContentPane();
        contain.setLayout(new BorderLayout());
        contain.add(panLaFie, BorderLayout.LINE_START);
        contain.add(panTable, BorderLayout.AFTER_LINE_ENDS);
        contain.add(panHeader, BorderLayout.NORTH);
    }

//    public static void main(String[] args) {
//        new ViewBarang();
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAdd) {
            controller.addData();
            fieName.setText("");
            bGroup.clearSelection();
            fieBrand.setText("");
            fiePrice.setText("");
        } else if (e.getSource() == bDelete) {
            int selectedRow = table.getSelectedRow();
            controller.removeData(selectedRow);
            fieName.setText("");
            bGroup.clearSelection();
            fieBrand.setText("");
            fiePrice.setText("");
        } else if (e.getSource() == bUpdate) {
            int selectedRow = table.getSelectedRow();
            controller.updateData(selectedRow);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        fieName, kualitas, brand, harga,
        int rowSelected = table.getSelectedRow();
        fieName.setText(table.getValueAt(rowSelected, 1).toString());
        if (table.getValueAt(rowSelected, 2).toString().equalsIgnoreCase("BAIK")) {
            kBaik.setSelected(true);
        } else {
            kBuruk.setSelected(true);
        }
        fieBrand.setText(table.getValueAt(rowSelected, 3).toString());
        fiePrice.setText(table.getValueAt(rowSelected, 4).toString());

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
