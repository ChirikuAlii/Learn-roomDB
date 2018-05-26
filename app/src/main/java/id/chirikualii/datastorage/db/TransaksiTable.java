package id.chirikualii.datastorage.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "transaksi")
public class TransaksiTable {

    //membuat kolom id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaksiId")
    Integer id;

    //kolom produk
    @ColumnInfo(name = "produk")
    String produk;

    //kolom harga
    @ColumnInfo(name = "harga_produk")
    Integer harga;

    //constructor
    public TransaksiTable(String produk, Integer harga) {
        this.id = id;
        this.produk = produk;
        this.harga = harga;
    }

    //setter and getter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }


}
