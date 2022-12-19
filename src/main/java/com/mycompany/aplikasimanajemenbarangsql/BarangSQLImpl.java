package com.mycompany.aplikasimanajemenbarangsql;

import com.mycompany.aplikasimanajemenbarangentity.Barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prameswara <prameswaara@gmail.com>
 */
public class BarangSQLImpl implements BarangSQL {

    String database = "barang_gudang";

//        long id = 0;
//        String query = "INSERT INTO public.tabel_barang(\n"
//                + "	nama_barang, kualitas_barang, brand_barang, harga_barang)\n"
//                + "	VALUES ( ?, ?, ?, ?) returning id_barang as id;";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            Connection conn = new ConnectionManager().getConnection("barang_gudang");
//            ps = conn.prepareStatement(query);
//            int i = 1;
//
//            ps.setString(i++, barang.getNamaBarang());
//            ps.setString(i++, barang.getKualitas());
//            ps.setString(i++, barang.getBrand());
//            ps.setString(i++, barang.getHarga());
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                id = rs.getLong("id");
//            }
//    finaly
//            ps.close();
//            rs.close();
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
    @Override
    public long insertData(Barang barang) {
        /*
        PreparedStatement 
        we can passing parameter (?) for the values. 
        Its value will be set by calling the setter methods of PreparedStatement.
        PreparedStatement is used for improves the perfomence of the application
        if we use PrepareStatement the Perfomance of Application will be faster, because query is compiled only once
         */
        long idBarang = 0;

        String query = "INSERT INTO public.tabel_barang(\n"
                + "	nama_barang, kualitas_barang, brand_barang, harga_barang)\n"
                + "	VALUES (?, ?, ?, ?) RETURNING id_barang,nama_barang, brand_barang, harga_barang ;";

        PreparedStatement preSt = null;
        ResultSet rSet = null;
        Connection conn = new ConnectionManager().getConnection(database);
        try {
            preSt = conn.prepareStatement(query);

            int column = 1;
            preSt.setString(column++, barang.getNamaBarang());
            preSt.setString(column++, barang.getKualitas());
            preSt.setString(column++, barang.getHarga());

            rSet = preSt.executeQuery();
            if (rSet.next()) {
                idBarang = rSet.getLong("id_barang");
            }

        } catch (Exception e) {
            Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                preSt.close();
            } catch (SQLException ex) {
                Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return idBarang;
    }

    @Override
    public void deleteData(long idBarang) {
//        Connection conn = new ConnectionManager().getConnection(database);
//        String query = "DELETE FROM public.tabel_barang\n"
//                + "	WHERE id_barang = ?;";
//        PreparedStatement preSt = null;
//        try {
//            preSt = conn.prepareStatement(query);
//            preSt.setLong(1, idBarang);
//            preSt.executeUpdate();
//            conn.close();
//            preSt.close();
//            System.out.println("affected row ");
//        } catch (Exception e) {
//            Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, e);
//        }
        String query = "DELETE FROM public.tabel_barang "
                + "WHERE id_barang = ?;";
        PreparedStatement preSt = null;
        Connection conn = new ConnectionManager().getConnection(database);
        try {
            preSt = conn.prepareStatement(query);
            preSt.setLong(1, idBarang);
            preSt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    preSt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void updateData(Barang barang) {
        System.out.println(barang.getNamaBarang());
        System.out.println(barang.getKualitas());
        System.out.println(barang.getBrand());
        System.out.println(barang.getHarga());
        System.out.println(barang.getId());
        String query = "UPDATE public.tabel_barang\n"
                + "	SET nama_barang=?, kualitas_barang=?, brand_barang=?, harga_barang=?\n"
                + "	WHERE id_barang=?;";
        PreparedStatement preSt = null;
        try {
            Connection conn = new ConnectionManager().getConnection(database);
            preSt = conn.prepareStatement(query);
            int column = 1;
            preSt.setString(column++, barang.getNamaBarang());
            preSt.setString(column++, barang.getKualitas());
            preSt.setString(column++, barang.getBrand());
            preSt.setString(column++, barang.getHarga());
            preSt.setLong(column++, barang.getId());
            preSt.executeUpdate();
            conn.close();
            preSt.close();
        } catch (Exception e) {
            Logger.getLogger(BarangSQLImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
