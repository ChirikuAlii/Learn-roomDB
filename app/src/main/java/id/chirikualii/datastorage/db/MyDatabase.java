package id.chirikualii.datastorage.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TransaksiTable.class}, version = 1)
public abstract class MyDatabase  extends RoomDatabase{

    public abstract TransaksiDao getTransaksiDao();
}
