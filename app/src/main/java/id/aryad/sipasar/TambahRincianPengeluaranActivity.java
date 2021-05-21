package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TambahRincianPengeluaranActivity extends AppCompatActivity {

    //Deklarasi variabel
//    TextView tvDate;
    EditText etDatePencatatan;
    EditText etDatePelunasan;
    EditText etQty;
    EditText etUraian;
    EditText etSatuan;
    EditText etTotal;

    Button btn_submit;
    Button btn_close;

    int id_admin;
    int id_kategori;
    private int resultcode=RESULT_OK;

    AdminData adminData = new AdminData();
    KategoriPengeluaranData kategoriPengeluaranData = new KategoriPengeluaranData();
    PeriodePengeluaranData periodePengeluaranData = new PeriodePengeluaranData();
//    RincianPengeluaranData rincianPengeluaranData = new RincianPengeluaranData();


    RincianPengeluaranData rincianPengeluaranData;
    private Spinner spinner;

    String pilihan_kategori, datePelunasan, datePencatatan;
    private ArrayList<RincianPengeluaranModel> tambahRincianPengeluaranModels;

//    ArrayList<KategoriPengeluaranModel> kategoriPengeluaranModel = new ArrayList<>();

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_rincian_pengeluaran);

        rincianPengeluaranData = (RincianPengeluaranData) getApplicationContext();

        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        id_admin = sharedPreferences.getInt("keyID", 0);
        Intent intent = getIntent();

        tambahRincianPengeluaranModels = new ArrayList<>();

        int periode_laporan = intent.getIntExtra(DashboardActivity.EXTRA_MESSAGE, 0);
        int jumlahData = intent.getIntExtra(DashboardActivity.EXTRA_MESSAGE1, 0);
//        Toast.makeText(getApplicationContext(), "Kembali ke dashboard"+jumlahData, Toast.LENGTH_SHORT).show();

//        String judul = judulaplikasi.getText().toString();
//        String judul = judulaplikasi.getText().toString();
//        String judul = judulaplikasi.getText().toString();
//        String judul = judulaplikasi.getText().toString();
//        String judul = judulaplikasi.getText().toString();
//        String judul = judulaplikasi.getText().toString();

        EditText tampilIdAdmin = (EditText) findViewById(R.id.et_inputid_admin);
        tampilIdAdmin.setText(getNamaAdmin(id_admin));

        EditText tamnmpilPeriodePengeluaran = (EditText) findViewById(R.id.et_inputid_periode_laporan);
        tamnmpilPeriodePengeluaran.setText(getNamaPeriode(periode_laporan));

//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(1, "Listrik"));
//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(2, "Sewa Gedung"));
//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(3, "Air"));
//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(4, "Gaji Pegawai"));

        //Mengambil id dari tampilan untuk digunakan
//        tvDate = findViewById(R.id.tv_tanggal_pencatatan);

        btn_close = findViewById(R.id.btn_cancel);
        btn_submit = findViewById(R.id.btn_submit);

        etDatePencatatan = findViewById(R.id.et_inputtanggal_pencatatan);
        etDatePelunasan = findViewById(R.id.et_inputtanggal_pelunasan);

        //Membuat variabel untuk menyimpan data datetime
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

//        tvDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(RincianPengeluaranActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//        setListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month+1;
//                String date = day+"-"+month+"-"+year;
//                tvDate.setText(date);
//            }
//        };
        //Membuat fungsi untuk tombol close

        //Membuat fungsi ambil tanggal untuk pencatatan
        etDatePencatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahRincianPengeluaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        datePencatatan = day+"-"+month+"-"+year;
                        try {
                            etDatePencatatan.setText(dateFormat(datePencatatan));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },year, month,day);
                datePickerDialog.show();
            }
        });

        //Membuat fungsi ambil tanggal untuk pelunasan
        etDatePelunasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahRincianPengeluaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        datePelunasan = day+"-"+month+"-"+year;
                        try {
                            etDatePelunasan.setText(dateFormat(datePelunasan));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },year, month,day);
                datePickerDialog.show();
            }
        });

        spinner = (Spinner)findViewById(R.id.sp_inputid_kategori_pengeluaran);
        ArrayAdapter<KategoriPengeluaranModel> kategoriPengeluaranAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_spinner_dropdown_item,
                kategoriPengeluaranData.getKategoriPengeluaranModel());

        spinner.setAdapter((kategoriPengeluaranAdapter));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pilihan_kategori = spinner.getSelectedItem().toString();
                for (int i = 0 ; i <kategoriPengeluaranData.getKategoriPengeluaranModel().size() ; i++){
                    if(pilihan_kategori.equals(kategoriPengeluaranData.getKategoriPengeluaranModel().get(i).toString())){
                        id_kategori = kategoriPengeluaranData.getKategoriPengeluaranModel().get(i).id_kategori_pengeluaran;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali_dashboard = new Intent(TambahRincianPengeluaranActivity.this, DashboardActivity.class);
                startActivity(kembali_dashboard);
            }
        });

        //Membuat fungsi untuk tombol submit
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etQty = findViewById(R.id.et_inputqty);
                String textQty = etQty.getText().toString();
                int angkaQty = Integer.valueOf(textQty);

                etTotal = findViewById(R.id.et_inputtotal);
                String textTotal = etTotal.getText().toString();
                int angkaTotal = Integer.valueOf(textTotal);

                etUraian = findViewById(R.id.et_inputuraian);
                String textUraian = etUraian.getText().toString();

                etSatuan = findViewById(R.id.et_inputsatuan);
                String textSatuan = etSatuan.getText().toString();

                Toast.makeText(getApplicationContext(), " "+textUraian, Toast.LENGTH_SHORT).show();
//                Log.d("kuantitas", String.valueOf(textQty));
//                DashboardActivity databaru = new DashboardActivity();

                //masukin input disini tempat untuk masukin id yang baru
//                rincianPengeluaranData.addRincianPengeluaranData(jumlahData, id_kategori, periode_laporan, angkaQty, angkaTotal, id_admin, textUraian, textSatuan, datePencatatan, datePelunasan);

//                tambahRincianPengeluaranModels.add(new RincianPengeluaranModel(jumlahData, id_kategori, periode_laporan, angkaQty,
//                        angkaTotal, id_admin, textUraian, textSatuan, datePencatatan, datePelunasan));
                rincianPengeluaranData.addRincianPengeluaranData(jumlahData, id_kategori, periode_laporan, angkaQty,
                        angkaTotal, id_admin, textUraian, textSatuan, datePencatatan, datePelunasan);
                Intent intent = new Intent();
                intent.putExtra("tambahRincianPengeluaran", tambahRincianPengeluaranModels);
                setResult(resultcode, intent);
                finish();

            }
        });

    }

    public String getNamaAdmin(int admin){
        for (int i = 0; i < adminData.getAdminList().size(); i++){
            if(adminData.getAdminList().get(i).id_admin == admin){
                return adminData.getAdminList().get(i).username;
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

//    @Override
//    public void onBackPressed() {
////        ArrayList<String> newPeriodeLaporan= new ArrayList<String>();
////        newPeriodeLaporan.add(editNama.getText().toString());
////        newPeriodeLaporan.add(editTglAwal.getText().toString());
////        newPeriodeLaporan.add(editTglAkhir.getText().toString());
////        newPeriodeLaporan.add(editIdSebelumnya.getText().toString());
//////        Intent intent = new Intent(BuatLaporanActivity.this, TargetActivity.class);
////        intent.putStringArrayListExtra("list", newPeriodeLaporan);
////        Intent intent = new Intent(BuatLaporanActivity.this, MainActivity.class);
//        Intent intent = new Intent();
//        intent.putExtra("newPeriodeLaporan", tambahRincianPengeluaranModels);
//        setResult(resultcode, intent);
//        finish();
//    }

}