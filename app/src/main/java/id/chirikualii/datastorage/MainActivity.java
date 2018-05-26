package id.chirikualii.datastorage;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.chirikualii.datastorage.db.MyDatabase;
import id.chirikualii.datastorage.db.TransaksiTable;

public class MainActivity extends AppCompatActivity {

    EditText edtProduk;
    EditText edtHarga;
    Button btnTambah;
    TextView txtResult;
    MyDatabase database;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtProduk = findViewById(R.id.edt_produk);
        edtHarga = findViewById(R.id.edt_harga);
        btnTambah = findViewById(R.id.btn_simpan);
        txtResult = findViewById(R.id.textView);

        //init database
        database = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class,
                "myDatabase.db")
                .build();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanTransaksi();
            }
        });

    }

    private void simpanTransaksi() {
        String produk = edtProduk.getText().toString();
        String harga=  edtHarga.getText().toString();



        if (produk.isEmpty() || harga.isEmpty()){

            Toast.makeText(this, "produk atau harga harus diisi!", Toast.LENGTH_SHORT).show();

        }else{
            //masukkan data ke dalam tabel transaksi
            TransaksiTable transaksi = new TransaksiTable(produk, Integer.valueOf(harga));

            //membuat asynctask
            new AsyncTask<TransaksiTable,Void ,String>(){

                //memlakukan pekerjaan di background
                //data yang di proses Transaksi
                //secara default data yang di proses selalu berbentuk array
                //jika data tidak dlm berbentuk array maka cukup gunakan index [0]
                @Override
                protected String doInBackground(TransaksiTable... transaksis) {
                    //melakukan Query INSERT
                    database.getTransaksiDao().simpanTransaksi(transaksis[0]);

                    // liat data query dari (SELECT * FROM transaksi)
                    //data di simpan dalam bentuk list
                    List<TransaksiTable> transaksiTableList = database.getTransaksiDao().getTransaksi();

                    //menampilkan semua list data menggunakan perulangan dan di simpan satu string
                    for (int i=0 ; i <transaksiTableList.size(); i++){
                        result+= transaksiTableList.get(i).getProduk() + " Harga:" + transaksiTableList.get(i).getHarga() +"\n";
                    }
                    return result;
                }

                //jika selesai akan mengerjakan fungsi onPostExecute
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);

                    //set text
                    //nilai String s berasal dari return result pada method doInBackground
                    txtResult.setText(s);
                }
                //men execute data transaksi yang ada pada baris 63 untuk diproses
                // pada method doInBackground dan onPostExecute
            }.execute(transaksi);
        }

    }
}
