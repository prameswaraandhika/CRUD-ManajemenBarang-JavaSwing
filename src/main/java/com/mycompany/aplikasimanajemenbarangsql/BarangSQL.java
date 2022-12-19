package com.mycompany.aplikasimanajemenbarangsql;

import com.mycompany.aplikasimanajemenbarangentity.Barang;

/**
 *
 * @author Prameswara <prameswaara@gmail.com>
 */
public interface BarangSQL {

    public long insertData(Barang barang);

    public void deleteData(long idBarang);

    public void updateData(Barang barang);

}
