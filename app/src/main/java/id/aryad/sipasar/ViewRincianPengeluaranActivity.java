package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewRincianPengeluaranActivity extends AppCompatActivity {

    //Membuat variabel untuk menyimpan data datetime
//    Button btn_edit;
    KategoriPengeluaranData kategoriPengeluaranData = new KategoriPengeluaranData();
    PeriodePengeluaranData periodePengeluaranData = new PeriodePengeluaranData();
    RincianPengeluaranData rincianPengeluaranData = new RincianPengeluaranData();
    AdminData adminData = new AdminData();

    Button btn_close;
    ArrayList<KategoriPengeluaranModel> kategoriPengeluaranModel = new ArrayList<>();
    ArrayList<PeriodeLaporanModel> periodepengeluaranlist = new ArrayList<>();
    ArrayList<AdminModel> adminList = new ArrayList<AdminModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rincian_pengeluaran);

        //Mengambil id dari tampilan untuk digunakan
        btn_close = findViewById(R.id.btn_close);
//        btn_edit = findViewById(R.id.btn_edit);

//        Intent intent = getIntent();
//        RincianPengeluaranModel rincianPengeluaranModel = intent.getParcelableExtra("Tampil Detail");

        Intent intent = getIntent();
        RincianPengeluaranModel rincianPengeluaranModel = intent.getParcelableExtra("Tampil Data");

        int rincianPengeluaran = rincianPengeluaranModel.getId_rincian_pengeluaran();

//        Toast.makeText(getApplicationContext(), ""+ rincianPengeluaran, Toast.LENGTH_SHORT).show();

        int kategoriPengeluaran = rincianPengeluaranModel.getId_kategori_pengeluaran();
        int periodeLaporan = rincianPengeluaranModel.getId_periode_laporan();
        int qty = rincianPengeluaranModel.getQty();
        int total = rincianPengeluaranModel.getTotal();
        int admin = rincianPengeluaranModel.getId_admin();
        String satuan = rincianPengeluaranModel.getSatuan();
        String uraian = rincianPengeluaranModel.getUraian();
        String tanggalPencatatan = rincianPengeluaranModel.getTanggal_pencatatan();
        String tanggalPelunasan = rincianPengeluaranModel.getTanggal_pelunasan();

//        Log.d("key", String.valueOf(uraian));

        TextView tvRincianPengeluaran = findViewById(R.id.tv_inputid_rincian_pengeluaran);
        tvRincianPengeluaran.setText(String.valueOf(rincianPengeluaran));

        TextView tvKategoriPengeluaran = findViewById(R.id.tv_inputid_kategori_pengeluaran);
        tvKategoriPengeluaran.setText(getNamaKategori(kategoriPengeluaran));

        TextView tvPeriodeLaporan = findViewById(R.id.tv_inputid_periode_laporan);
        tvPeriodeLaporan.setText(getNamaPeriode(periodeLaporan));

        TextView tvQty = findViewById(R.id.tv_inputqty);
        tvQty.setText(String.valueOf(qty+" pcs"));

        TextView tvTotal = findViewById(R.id.tv_inputtotal);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvTotal.setText(formatRupiah.format((double)total));

        TextView tvAdmin = findViewById(R.id.tv_inputid_admin);
        tvAdmin.setText(getNamaAdmin(admin));

        TextView tvSatuan = findViewById(R.id.tv_inputsatuan);
        tvSatuan.setText(String.valueOf(satuan));

        TextView tvUraian = findViewById(R.id.tv_inputuraian);
        tvUraian.setText(String.valueOf(uraian));

        TextView tvTanggalPencatatan = findViewById(R.id.tv_inputtanggal_pencatatan);
        try {
            tvTanggalPencatatan.setText(dateFormat(tanggalPencatatan));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView tvTanggalPelunasan = findViewById(R.id.tv_inputtanggal_pelunasan);
        try {
            tvTanggalPelunasan.setText(dateFormat(tanggalPelunasan));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Membuat fungsi untuk tombol close
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Kembali ke dashboard", Toast.LENGTH_SHORT).show();
                Intent kembali_dashboard = new Intent(ViewRincianPengeluaranActivity.this, DashboardActivity.class);
                startActivity(kembali_dashboard);
            }
        });

        //Membuat fungsi untuk tombol submit
//        btn_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent edit_data = new Intent(ViewRincianPengeluaranActivity.this, EditRincianPengeluaranActivity.class);
//                startActivity(edit_data);
////                Toast.makeText(getApplicationContext(), "Data tersubmit", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public String getNamaKategori(int kategoriPengeluaran){
        for (int i = 0; i < kategoriPengeluaranData.getKategoriPengeluaranModel().size(); i++){
            if(kategoriPengeluaranData.getKategoriPengeluaranModel().get(i).id_kategori_pengeluaran == kategoriPengeluaran){
                return kategoriPengeluaranData.getKategoriPengeluaranModel().get(i).nama_kategori;
            }
        }
        return "";
    }

    public String getNamaPeriode(int periodeLaporan){
        for (int i = 0; i < periodePengeluaranData.getPeriodepengeluaranlist().size(); i++){
            if(periodePengeluaranData.getPeriodepengeluaranlist().get(i).id_periode_laporan == periodeLaporan){
                return periodePengeluaranData.getPeriodepengeluaranlist().get(i).nama_periode;
            }
        }
        return "";
    }

    public String getNamaAdmin(int admin){
        for (int i = 0; i < adminData.getAdminList().size(); i++){
            if(adminData.getAdminList().get(i).id_admin == admin){
                return adminData.getAdminList().get(i).username;
            }
        }
        return "";
    }

    protected Date stringToDate(String dob) throws ParseException {
        //Inisialisasi class SimpleDateFormat
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(dob);
        return date;
    }

    protected String dateFormat(String tanggal) throws ParseException {
        Date date = stringToDate(tanggal);
        String format_tanggal = new SimpleDateFormat("dd MMM yyyy").format(date);
        return format_tanggal;
    }
}