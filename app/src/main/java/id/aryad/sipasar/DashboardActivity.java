package id.aryad.sipasar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Button btn_plus;
//    ImageButton btn_view;
//    ImageButton btn_edit;
//    ImageButton btn_delete;
    public static final String EXTRA_MESSAGE = "Id_periode";
    public static final String EXTRA_MESSAGE1 = "Jumlah_Data_Rincian";
    private RecyclerView mRecyclerView;
    private Spinner spinner;
    String pilihan_periode;
    int id_periode;
    private Integer adminId;

    PeriodePengeluaranData periodePengeluaranData = new PeriodePengeluaranData();
    RincianPengeluaranData rincianPengeluaranData;

    //    PeriodeLaporanAdapter periodeLaporanAdapter;
    private RincianPengeluaranAdapter rincianPengeluaranAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<RincianPengeluaranModel> datarincianPengeluaranAdapter = new ArrayList<>();
    ArrayList<RincianPengeluaranModel> daftarRincianPengeluaran = new ArrayList<>();

//    ArrayList<RincianPengeluaranModel> rincianpengeluaranlist = new ArrayList<>();
//    ArrayList<PeriodeLaporanModel> periodepengeluaranlist = new ArrayList<>();

    private DrawerLayout nav_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rincianPengeluaranData =  (RincianPengeluaranData) getApplicationContext();

        nav_drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.item_nav);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, nav_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        nav_drawer.addDrawerListener(toggle);
        toggle.syncState();

//        Intent login = new Intent();
//        login = getIntent();
////        adminId = login.getIntExtra("adminId", 0);
//        String adminId = login.getStringExtra(DashboardActivity.EXTRA_MESSAGE);
//        Toast.makeText( this, " "+ getSharedPreferences().getInt()., Toast.LENGTH_SHORT ).show();

//        if (savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.tampil_fragmen, new DashboardFragment()).commit();
//        }

        btn_plus = (Button)findViewById(R.id.btn_tambahPengeluaran);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formrincian = new Intent(getApplicationContext(), TambahRincianPengeluaranActivity.class);

                int jumlahData = rincianPengeluaranData.getRincianpengeluaranlist().size();
                jumlahData = Integer.valueOf(jumlahData);
                jumlahData = jumlahData+1;
//                Toast.makeText(getApplicationContext(), jumlahData, Toast.LENGTH_SHORT).show();

                pilihan_periode = spinner.getSelectedItem().toString();
                for (int i = 0 ; i <periodePengeluaranData.getPeriodepengeluaranlist().size() ; i++){
                    if(pilihan_periode.equals(periodePengeluaranData.getPeriodepengeluaranlist().get(i).toString())){
                        id_periode = periodePengeluaranData.getPeriodepengeluaranlist().get(i).id_periode_laporan;
                        break;
                    }
                }
                formrincian.putExtra(EXTRA_MESSAGE,id_periode);
                formrincian.putExtra(EXTRA_MESSAGE1,jumlahData);
                startActivityForResult(formrincian, 1);
            }
        });

//        rincianpengeluaranlist.add(new RincianPengeluaranModel(1, 1, 1, 3, 300000,1, "Pembayaran listrik bulan mei", "Bulan", "2-5-2021", "11-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(2, 2, 2, 4, 600000,1, "Pembayaran Air bulan mei", "Bulan", "1-5-2021", "3-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(3, 1, 3, 1, 1000000,1, "Pembayaran service ac bulan mei", "Bualn", "5-5-2021", "10-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(4, 3, 3, 3, 450000,1, "Pembayaran iuran kebersihan bulan mei", "Bulan", "7-5-2021", "15-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(5, 2, 4, 2, 300000,1, "Bembelian ATK bulan minggu 1 mei", "Minggu", "1-5-2021", "4-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(6, 1, 1, 3, 300000,1, "Pembayaran listrik bulan mei", "Bulan", "2-5-2021", "11-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(7, 2, 2, 4, 600000,1, "Pembayaran Air bulan mei", "Bulan", "1-5-2021", "3-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(8, 1, 3, 1, 1000000,1, "Pembayaran service ac bulan mei", "Bualn", "5-5-2021", "10-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(9, 3, 3, 3, 450000,1, "Pembayaran iuran kebersihan bulan mei", "Bulan", "7-5-2021", "15-5-2021"));
//        rincianpengeluaranlist.add(new RincianPengeluaranModel(10, 2, 4, 2, 300000,1, "Bembelian ATK bulan minggu 1 mei", "Minggu", "1-5-2021", "4-5-2021"));

//        periodepengeluaranlist.add(new PeriodeLaporanModel(1,0,20000000, "Kuartal 1 2021", "1-1-2021", "31-3-2021"));
//        periodepengeluaranlist.add(new PeriodeLaporanModel(2,1,10000000, "Kuartal 2 2021", "1-4-2021", "31-6-2021"));
//        periodepengeluaranlist.add(new PeriodeLaporanModel(3,2,25000000, "Kuartal 3 2021", "1-7-2021", "31-9-2021"));
//        periodepengeluaranlist.add(new PeriodeLaporanModel(4,3,15000000, "Kuartal 4 2021", "1-10-2021", "31-12-2021"));

        spinner = (Spinner)findViewById(R.id.sp_periodeLaporan);
        ArrayAdapter<PeriodeLaporanModel> periodeLaporanAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_spinner_dropdown_item,
                periodePengeluaranData.getPeriodepengeluaranlist());

        spinner.setAdapter(periodeLaporanAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pilihan_periode = spinner.getSelectedItem().toString();
                for (int i = 0 ; i <periodePengeluaranData.getPeriodepengeluaranlist().size() ; i++){
                    if(pilihan_periode.equals(periodePengeluaranData.getPeriodepengeluaranlist().get(i).toString())){
                        id_periode = periodePengeluaranData.getPeriodepengeluaranlist().get(i).id_periode_laporan;
                        break;
                    }
                }
                urutPeriode();
//                datarincianPengeluaranAdapter.clear();
//                for (int i = 0 ; i <rincianPengeluaranData.getRincianpengeluaranlist().size() ; i++){
//                    if(rincianPengeluaranData.getRincianpengeluaranlist().get(i).id_periode_laporan == id_periode){
//                        datarincianPengeluaranAdapter.add(rincianPengeluaranData.getRincianpengeluaranlist().get(i));
//
//                    }
//                }
                mulaiAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        PeriodeLaporanAdapter sp_periodeLaporanAdapter = new PeriodeLaporanAdapter(this, R.layout.sp_periodelaporan_item);
//        spinner.setAdapter();
//        periodeLaporanAdapter = new PeriodeLaporanAdapter(getContext(), periodepengeluaranlist);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Toast.makeText(getApplicationContext(),"Tampil Halaman Profile", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_dashboard:
//                Toast.makeText(getApplicationContext(),"Tampil Halaman Dashboard", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.tampil_fragmen, new DashboardFragment()).commit();
                Intent dashboard_Intent = new Intent(DashboardActivity.this, DashboardActivity.class);

                break;
            case R.id.nav_logout:
//                Toast.makeText(getApplicationContext(),"Anda Logout", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("keyUsername", "defaultValue");
                editor.putString("keyPass", "defaultValue");

                editor.apply();

                Intent login_intent =new Intent (DashboardActivity.this, LoginManagerActivity.class);
                startActivity(login_intent);

                break;
        }
        nav_drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void removeItem(int position){
        Log.d("pos", String.valueOf(position));
        int selectedPeriode = datarincianPengeluaranAdapter.get(position).id_rincian_pengeluaran;
        datarincianPengeluaranAdapter.remove(position);
        rincianPengeluaranAdapter.notifyItemRemoved(position);

        Log.d("key", String.valueOf(selectedPeriode));
        for (int i=0; i<rincianPengeluaranData.getRincianpengeluaranlist().size();i++){
            Log.d("key", String.valueOf(rincianPengeluaranData.getRincianpengeluaranlist().get(i).id_rincian_pengeluaran));
            if(rincianPengeluaranData.getRincianpengeluaranlist().get(i).id_rincian_pengeluaran == selectedPeriode){
                rincianPengeluaranData.getRincianpengeluaranlist().remove(i);
                break;
            }
        }
//        rincianpengeluaranlist.forEach((item)->{
//            if(item.id_periode_laporan == selectedPeriode){
//                rincianpengeluaranlist.remove()
//            }
//        });
//        datarincianPengeluaranAdapter.clear();
//        rincianPengeluaranAdapter.notifyItemRemoved(position);
//
//        datarincianPengeluaranAdapter.clear();

//        datarincianPengeluaranAdapter.notifyItemRemoved(position);

    }

//    public void showItem(int position){
//        Intent showRincian = new Intent(getApplicationContext(), ViewRincianPengeluaranActivity.class);
////        int itemEdit = datarincianPengeluaranAdapter.get(position).getId_rincian_pengeluaran();
//
//        showRincian.putExtra("Tampil Detail",datarincianPengeluaranAdapter.get(position));
//        startActivity(showRincian);
//
//    }

    public void editItem(int position){
        Intent editRincian = new Intent(getApplicationContext(), EditRincianPengeluaranActivity.class);
        int itemEdit = datarincianPengeluaranAdapter.get(position).getId_rincian_pengeluaran();

//        rincianpengeluaranlist.get(position).setId_kategori_pengeluaran(1);
//        rincianpengeluaranlist.get(position).setId_periode_laporan(1);
//        rincianpengeluaranlist.get(position).setId_admin(1);
//        rincianpengeluaranlist.get(position).setUraian(1);
//        rincianpengeluaranlist.get(position).setSatuan(1);
//        rincianpengeluaranlist.get(position).setTanggal_pencatatan(1);
//        rincianpengeluaranlist.get(position).setTanggal_pelunasan(1);
//        pilihan_periode = spinner.getSelectedItem().toString();
//        for (int i = 0 ; i <periodepengeluaranlist.size() ; i++){
//            if(pilihan_periode.equals(periodepengeluaranlist.get(i).toString())){
//                id_periode = periodepengeluaranlist.get(i).id_periode_laporan;
//                break;
//            }
//        Toast.makeText(getApplicationContext(),""+itemEdit, Toast.LENGTH_SHORT).show();

        editRincian.putExtra(EXTRA_MESSAGE,itemEdit);
        editRincian.putExtra("Tampil Data", datarincianPengeluaranAdapter.get(position));

        startActivity(editRincian);
    }

    public void mulaiAdapter(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
//        Log.d("Budhi", String.valueOf(datarincianPengeluaranAdapter.size()));
        rincianPengeluaranAdapter = new RincianPengeluaranAdapter(this, datarincianPengeluaranAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(rincianPengeluaranAdapter);


        rincianPengeluaranAdapter.setOnItemClickListener(new RincianPengeluaranAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }

            @Override
            public void getData(int position) {
                editItem(position);
            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(DashboardActivity.this, ViewRincianPengeluaranActivity.class);
                intent.putExtra("Tampil Data", datarincianPengeluaranAdapter.get(position));

                startActivity(intent);
//                showItem(position);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {

                ArrayList<RincianPengeluaranModel> tambahRincianPengeluaran = new ArrayList<RincianPengeluaranModel>();
                tambahRincianPengeluaran = (ArrayList<RincianPengeluaranModel>) data.getSerializableExtra("tambahRincianPengeluaran");
                Log.d("jalan", String.valueOf("jalan"));
//
//                Toast.makeText(getApplicationContext(),tambahRincianPengeluaran.get(0).satuan,Toast.LENGTH_LONG).show();

//                datarincianPengeluaranAdapter.addAll(tambahRincianPengeluaran);
//                datarincianPengeluaranAdapter.addAll(tambahRincianPengeluaran);
//                rincianPengeluaranData.getRincianpengeluaranlist().add(tambahRincianPengeluaran));
                urutPeriode();
                rincianPengeluaranAdapter.notifyDataSetChanged();

//                mulaiAdapter();

            }
        }else if(requestCode == 2){
//            ArrayList<RincianPengeluaranModel> tambahRincianPengeluaran = new ArrayList<RincianPengeluaranModel>();
//            tambahRincianPengeluaran = (ArrayList<RincianPengeluaranModel>) data.getSerializableExtra("editRincianPengeluaran");
//            Log.d("jalan", String.valueOf("jalan"));
//
//            Toast.makeText(getApplicationContext(),tambahRincianPengeluaran.get(0).satuan,Toast.LENGTH_LONG).show();
//
//            datarincianPengeluaranAdapter.addAll(tambahRincianPengeluaran);
//            datarincianPengeluaranAdapter.addAll(tambahRincianPengeluaran);
//            rincianPengeluaranData.getRincianpengeluaranlist().add(tambahRincianPengeluaran));

            urutPeriode();
            rincianPengeluaranAdapter.notifyDataSetChanged();

//            mulaiAdapter();
        }
//        if (requestCode == 2) {
//            if(resultCode == RESULT_CANCELED) {
//
////                ArrayList<RincianPengeluaranModel> tambahRincianPengeluaran = new ArrayList<RincianPengeluaranModel>();
////                tambahRincianPengeluaran = (ArrayList<RincianPengeluaranModel>) data.getSerializableExtra("editRincianPengeluaran");
////                Log.d("jalan", String.valueOf("jalan"));
////
////                Toast.makeText(getApplicationContext(),tambahRincianPengeluaran.get(0).satuan,Toast.LENGTH_LONG).show();
//
////                datarincianPengeluaranAdapter.addAll(tambahRincianPengeluaran);
////                datarincianPengeluaranAdapter.addAll(tambahRincianPengeluaran);
////                rincianPengeluaranData.getRincianpengeluaranlist().add(tambahRincianPengeluaran));
//                urutPeriode();
//                rincianPengeluaranAdapter.notifyDataSetChanged();
//
////                mulaiAdapter();
//
//            }
//        }
    }

    public void urutPeriode(){
        daftarRincianPengeluaran.clear();
//        Collections.copy(daftarRincianPengeluaran,rincianPengeluaranData.getRincianpengeluaranlist());
        daftarRincianPengeluaran.addAll(rincianPengeluaranData.getRincianpengeluaranlist());
        Log.d("internasional", String.valueOf(daftarRincianPengeluaran.size()));
        datarincianPengeluaranAdapter.clear();
        for (int i = 0 ; i <daftarRincianPengeluaran.size() ; i++){
            if(daftarRincianPengeluaran.get(i).id_periode_laporan == id_periode){
                datarincianPengeluaranAdapter.add(new RincianPengeluaranModel(daftarRincianPengeluaran.get(i).id_rincian_pengeluaran,
                        daftarRincianPengeluaran.get(i).id_kategori_pengeluaran, daftarRincianPengeluaran.get(i).id_periode_laporan,
                        daftarRincianPengeluaran.get(i).qty, daftarRincianPengeluaran.get(i).total,
                        daftarRincianPengeluaran.get(i).id_admin, daftarRincianPengeluaran.get(i).uraian,
                        daftarRincianPengeluaran.get(i).satuan, daftarRincianPengeluaran.get(i).tanggal_pencatatan,
                        daftarRincianPengeluaran.get(i).tanggal_pelunasan));

            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        int banyakdata = rincianPengeluaranData.getRincianpengeluaranlist().size();
        Log.d("jumlah1", String.valueOf(banyakdata));
    }
}