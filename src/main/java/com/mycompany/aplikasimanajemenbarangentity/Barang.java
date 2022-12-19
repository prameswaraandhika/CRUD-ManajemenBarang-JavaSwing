package com.mycompany.aplikasimanajemenbarangentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Prameswara <prameswaara@gmail.com>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Barang {

    private long id;
    private String namaBarang;
    private String kualitas;
    private String brand;
    private String harga;

//    public String toString() {
//        return String.valueOf(id);
//    }
}
