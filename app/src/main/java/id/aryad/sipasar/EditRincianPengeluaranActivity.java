package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditRincianPengeluaranActivity extends AppCompatActivity {

    //Membuat variabel untuk menyimpan data datetime
    EditText etDatePencatatan;
    EditText etDatePelunasan;
    EditText etQty;
    EditText etUraian;
    EditText etSatuan;

    Button btn_edit;
    Button btn_close;
    int editRincian;
    int id_kategori, id_periode, id_admin;
    int INDEX_KATEGORI_PENGELUARAN, INDEX_PERIODE_PENGELUARAN, INDEX_ADMIN;
    private int resultcode=RESULT_OK;

    String pilihan_kategori, pilihan_periode, pilihan_admin;
    private Spinner spinner_kategori, spinner_periode, spinner_admin;

//    ArrayList<KategoriPengeluaranModel> kategoriPengeluaranModel = new ArrayList<>();
    private ArrayList<RincianPengeluaranModel> editRincianPengeluaranModels;

    KategoriPengeluaranData kategoriPengeluaranData = new KategoriPengeluaranData();
    PeriodePengeluaranData periodePengeluaranData = new PeriodePengeluaranData();
    AdminData adminData = new AdminData();
    RincianPengeluaranData rincianPengeluaranData = new RincianPengeluaranData();

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rincian_pengeluaran);

//        RincianPengeluaranModel data = getData(id_);
//        setData(data)

        Intent intent = getIntent();
        editRincian = intent.getIntExtra(DashboardActivity.EXTRA_MESSAGE, 0);

//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(1, "Listrik"));
//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(2, "Sewa Gedung"));
//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(3, "Air"));
//        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(4, "Gaji Pegawai"));

//        EditText tamnmpilPeriodePengeluaran = (EditText) findViewById(R.id.et_inputid_periode_laporan);

        Toast.makeText(getApplicationContext(), "Id rincian:"+editRincian, Toast.LENGTH_SHORT).show();


//        Toast.makeText( context, "Anda tidak mengambil foto", Toast.LENGTH_SHORT ).show();
//        tamnmpilPeriodePengeluaran.setText("" + editRincian);

        //Mengambil id dari tampilan untuk digunakan
        btn_close = findViewById(R.id.btn_cancel);
        btn_edit = findViewById(R.id.btn_edit);

        etDatePencatatan = findViewById(R.id.et_edittanggal_pencatatan);
        etDatePelunasan = findViewById(R.id.et_edittanggal_pelunasan);

        Intent ambilData = getIntent();
        RincianPengeluaranModel rincianPengeluaranModel = ambilData.getParcelableExtra("Tampil Data");

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

        TextView etRincianPengeluaran = findViewById(R.id.et_editid_rincian_pengeluaran);
        etRincianPengeluaran.setText(String.valueOf(rincianPengeluaran));

//        TextView tvKategoriPengeluaran = findViewById(R.id.tv_inputid_kategori_pengeluaran);
//        tvKategoriPengeluaran.setText(String.valueOf(kategoriPengeluaran));

//        EditText etPeriodeLaporan = findViewById(R.id.et_editid_periode_laporan);
//        etPeriodeLaporan.setText(String.valueOf(periodeLaporan));

        EditText etQty = findViewById(R.id.et_editqty);
        etQty.setText(String.valueOf(qty));

        EditText etTotal = findViewById(R.id.et_edittotal);
//        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        etTotal.setText(String.valueOf(total));

//        EditText etAdmin = findViewById(R.id.et_editid_admin);
//        etAdmin.setText(String.valueOf(admin));

        EditText etSatuan = findViewById(R.id.et_editsatuan);
        etSatuan.setText(String.valueOf(satuan));

        EditText etUraian = findViewById(R.id.et_edituraian);
        etUraian.setText(String.valueOf(uraian));

        EditText etTanggalPencatatan = findViewById(R.id.et_edittanggal_pencatatan);
        try {
            etTanggalPencatatan.setText(dateFormat(tanggalPencatatan));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EditText etTanggalPelunasan = findViewById(R.id.et_edittanggal_pelunasan);
        try {
            etTanggalPelunasan.setText(dateFormat(tanggalPelunasan));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Membuat variabel untuk menyimpan data datetime
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        spinner_kategori = (Spinner)findViewById(R.id.sp_editid_kategori_pengeluaran);
        ArrayAdapter<KategoriPengeluaranModel> kategoriPengeluaranAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_spinner_dropdown_item,
                kategoriPengeluaranData.getKategoriPengeluaranModel());

        spinner_kategori.setAdapter((kategoriPengeluaranAdapter));

        INDEX_KATEGORI_PENGELUARAN = kategoriPengeluaran;
        spinner_kategori.setSelection(INDEX_KATEGORI_PENGELUARAN-1);

        spinner_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pilihan_kategori = spinner_kategori.getSelectedItem().toString();
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

        spinner_periode = (Spinner)findViewById(R.id.sp_editid_periode_pengeluaran);
        ArrayAdapter<PeriodeLaporanModel> periodeLaporanAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_spinner_dropdown_item,
                periodePengeluaranData.getPeriodepengeluaranlist());

        spinner_periode.setAdapter(periodeLaporanAdapter);

        INDEX_PERIODE_PENGELUARAN = periodeLaporan;
        spinner_periode.setSelection(INDEX_PERIODE_PENGELUARAN-1);

        spinner_periode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pilihan_periode = spinner_periode.getSelectedItem().toString();
                for (int i = 0 ; i <periodePengeluaranData.getPeriodepengeluaranlist().size() ; i++){
                    if(pilihan_periode.equals(periodePengeluaranData.getPeriodepengeluaranlist().get(i).toString())){
                        id_periode = periodePengeluaranData.getPeriodepengeluaranlist().get(i).id_periode_laporan;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spinner_admin = (Spinner)findViewById(R.id.sp_editid_admin);
//        ArrayAdapter<AdminModel> adminAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_dropdown_item,adminData.getAdminList());
//        spinner_admin.setAdapter(adminAdapter);
//
//        INDEX_ADMIN = admin;
//        spinner_admin.setSelection(INDEX_ADMIN-1);
//
//        spinner_admin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                pilihan_admin = spinner_admin.getSelectedItem().toString();
//                for (int i = 0 ; i <adminData.getAdminList().size() ; i++){
//                    if(pilihan_admin.equals(adminData.getAdminList().get(i).toString())){
//                        id_admin = adminData.getAdminList().get(i).id_admin;
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        //Membuat fungsi untuk tombol close
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Kembali ke dashboard", Toast.LENGTH_SHORT).show();
                Intent kembali_dashboard = new Intent(EditRincianPengeluaranActivity.this, DashboardActivity.class);
                startActivity(kembali_dashboard);


            }
        });

        //Membuat fungsi untuk tombol edit
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(EditRincianPengeluaranActivity.this, DashboardActivity.class);
                Intent intent = new Intent();

//                String textId_rincian = etQty.getText().toString();
//                int angkaId_Id_rincian = Integer.valueOf(textId_rincian);

//                String textId_Periode = etQty.getText().toString();
//                int angkaId_Periode = Integer.valueOf(textId_Periode);

//                int textId_kategori = id_kategori;
//                int angkaId_Id_kategori = Integer.valueOf(textId_kategori);

//                String textId_admin = etQty.getText().toString();
//                int angkaId_admin = Integer.valueOf(textId_admin);

                String textQty = etQty.getText().toString();
                int angkaQty = Integer.valueOf(textQty);

                String textTotal = etTotal.getText().toString();
                int angkaTotal = Integer.valueOf(textTotal);

                String textSatuan = etSatuan.getText().toString();

                String textUraian = etUraian.getText().toString();

                String textTanggalPencatatan = etTanggalPencatatan.getText().toString();

                String textTanggalPelunasan = etTanggalPelunasan.getText().toString();

                Toast.makeText(getApplicationContext(), ""+ textTanggalPelunasan, Toast.LENGTH_SHORT).show();

                rincianPengeluaranData.setRincianpengeluaranlist(rincianPengeluaran,
                        id_periode,
                        id_kategori,
                        angkaQty,
                        angkaTotal,
                        admin,
                        textSatuan,
                        textUraian,
                        textTanggalPencatatan,
                        textTanggalPelunasan );

//                int rincianPengeluaran,int angkaId_Periode, int angkaQty, int angkaTotal, String textSatuan, String textUraian, String textTanggalPencatatan, String textTanggalPelunasan

//                editRincianPengeluaranModels.add(new RincianPengeluaranModel(angkaId_Id_rincian, id_kategori, angkaId_Periode, angkaQty, angkaTotal, angkaId_admin, textUraian, textSatuan, textTanggalPencatatan, textTanggalPelunasan));
//                for(int a = 0; a < rincianPengeluaranData.getRincianpengeluaranlist().size() ; a++){
//                    if(rincianPengeluaranData.getRincianpengeluaranlist().get(a).getId_rincian_pengeluaran() == rincianPengeluaran){
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setId_rincian_pengeluaran(rincianPengeluaran);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setId_periode_laporan(angkaId_Periode);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setId_rincian_pengeluaran(angkaId_Periode);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setQty(angkaQty);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setTotal(angkaTotal);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setSatuan(textSatuan);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setUraian(textUraian);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setTanggal_pencatatan(textTanggalPencatatan);
//                        rincianPengeluaranData.getRincianpengeluaranlist().get(a).setTanggal_pelunasan(textTanggalPelunasan);
//                    }
//                }

//                intent.putExtra("Tampil Data", editRincianPengeluaranModels.get());

//                intent.putExtra("editRincianPengeluaran", tambahRincianPengeluaranModels);
                setResult(0, intent);
                finish();

//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Data diedit", Toast.LENGTH_SHORT).show();
            }
        });

        //Membuat fungsi ambil tanggal untuk pencatatan
        etDatePencatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EditRincianPengeluaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String datePencatatan = day+"-"+month+"-"+year;
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
                        EditRincianPengeluaranActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String datePelunasan = day+"-"+month+"-"+year;
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
    }


    private void setData(RincianPengeluaranModel data) {
        etDatePelunasan.setText(data.getTanggal_pelunasan());
    }

    //Ambil seluruh data dari id editRincian
    private RincianPengeluaranModel getData() {
        DashboardActivity dashboardActivity = new DashboardActivity();
        RincianPengeluaranModel data = null;
        for (int i = 0; i < dashboardActivity.rincianPengeluaranData.getRincianpengeluaranlist().size() ;i++){
            RincianPengeluaranModel rincianPengeluaranModel = dashboardActivity.rincianPengeluaranData.getRincianpengeluaranlist().get(i);
            if (rincianPengeluaranModel.id_rincian_pengeluaran == editRincian){
                data = rincianPengeluaranModel;
            }
        }
        return data;
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