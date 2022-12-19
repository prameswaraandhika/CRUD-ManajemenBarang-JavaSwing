package com.mycompany.aplikasimanajemenbarangcontroller;

import com.mycompany.aplikasimanajemenbarangentity.Barang;
import com.mycompany.aplikasimanajemenbarangsql.BarangSQL;
import com.mycompany.aplikasimanajemenbarangsql.BarangSQLImpl;
import com.mycompany.aplikasimanajemenbarangview.ViewBarang;

/**
 *
 * @author Prameswara <prameswaara@gmail.com>
 */
public class ControllerBarang {

    BarangSQL sql = new BarangSQLImpl();
    Barang barang;
    ViewBarang view;

    public ControllerBarang(ViewBarang view) {
        this.view = view;
    }

    public void addData() {
        String result = "";
        if (view.getKBaik().isSelected()) {
            result = view.getKBaik().getActionCommand();
        } else {
            result = view.getKBuruk().getActionCommand();
        }
        barang = Barang
                .builder()
                .namaBarang(view.getFieName().getText())
                .kualitas(result)
                .brand(view.getFieBrand().getText())
                .harga(view.getFiePrice().getText())
                .build();

        long id = sql.insertData(barang);

        barang.setId(id);

        view.getModel().addRow(new Object[]{
            barang.getId(),
            barang.getNamaBarang(),
            barang.getKualitas(),
            barang.getBrand(),
            barang.getHarga()
        });
    }

    public void removeData(int rows) {
//        Barang barangbR = (Barang) view.getModel().getValueAt(rows, 0);
        long id = (long) view.getModel().getValueAt(rows, 0);
        view.getModel().removeRow(rows);
        sql.deleteData(id);
    }

    public void updateData(int rows) {
        String resultRB = "";
        if (view.getKBaik().isSelected()) {
            resultRB = view.getKBaik().getActionCommand();
        } else {
            resultRB = view.getKBuruk().getActionCommand();
        }
        long idBarang = (long) view.getModel().getValueAt(rows, 0);
        barang = Barang
                .builder()
                .namaBarang(view.getFieName().getText())
                .kualitas(resultRB)
                .brand(view.getFieBrand().getText())
                .harga(view.getFiePrice().getText())
                .build();
        barang.setId(idBarang);
        view.getModel().setValueAt(barang.getId(), rows, 0);
        view.getModel().setValueAt(barang.getNamaBarang(), rows, 1);
        view.getModel().setValueAt(barang.getKualitas(), rows, 2);
        view.getModel().setValueAt(barang.getBrand(), rows, 3);
        view.getModel().setValueAt(barang.getHarga(), rows, 4);
        sql.updateData(barang);
    }
}
