package id.chirikualii.datastorage.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TransaksiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void simpanTransaksi(TransaksiTable  transaksi);

    @Query("SELECT * FROM transaksi")
    List<TransaksiTable> getTransaksi();
}
