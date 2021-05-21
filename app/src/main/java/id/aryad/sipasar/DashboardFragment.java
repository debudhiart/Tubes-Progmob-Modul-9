package id.aryad.sipasar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    Button btn_plus;
//    ImageButton btn_view;
//    ImageButton btn_edit;
//    ImageButton btn_delete;

    private RecyclerView mRecyclerView;
    private Spinner spinner;
    String pilihan_periode;
    int id_periode;


//    PeriodeLaporanAdapter periodeLaporanAdapter;
    RincianPengeluaranAdapter rincianPengeluaranAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<RincianPengeluaranModel> datarincianPengeluaranAdapter = new ArrayList<>();
    ArrayList<RincianPengeluaranModel> rincianpengeluaranlist = new ArrayList<>();
    ArrayList<PeriodeLaporanModel> periodepengeluaranlist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btn_plus = (Button) getView().findViewById(R.id.btn_tambahPengeluaran);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formrincian = new Intent(getContext(), TambahRincianPengeluaranActivity.class);
                startActivity(formrincian);
            }
        });

        rincianpengeluaranlist.add(new RincianPengeluaranModel(1, 1, 1, 3, 300000,1, "Pembayaran listrik bulan mei", "Bulan", "2-5-2021", "11-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(2, 2, 2, 4, 600000,1, "Pembayaran Air bulan mei", "Bulan", "1-5-2021", "3-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(3, 1, 3, 1, 1000000,1, "Pembayaran service ac bulan mei", "Bualn", "5-5-2021", "10-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(4, 3, 3, 3, 450000,1, "Pembayaran iuran kebersihan bulan mei", "Bulan", "7-5-2021", "15-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(5, 2, 4, 2, 300000,1, "Bembelian ATK bulan minggu 1 mei", "Minggu", "1-5-2021", "4-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(1, 1, 1, 3, 300000,1, "Pembayaran listrik bulan mei", "Bulan", "2-5-2021", "11-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(2, 2, 2, 4, 600000,1, "Pembayaran Air bulan mei", "Bulan", "1-5-2021", "3-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(3, 1, 3, 1, 1000000,1, "Pembayaran service ac bulan mei", "Bualn", "5-5-2021", "10-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(4, 3, 3, 3, 450000,1, "Pembayaran iuran kebersihan bulan mei", "Bulan", "7-5-2021", "15-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(5, 2, 4, 2, 300000,1, "Bembelian ATK bulan minggu 1 mei", "Minggu", "1-5-2021", "4-5-2021"));


        periodepengeluaranlist.add(new PeriodeLaporanModel(1,0,20000000, "Kuartal 1 2021", "1-1-2021", "31-3-2021"));
        periodepengeluaranlist.add(new PeriodeLaporanModel(2,1,10000000, "Kuartal 2 2021", "1-4-2021", "31-6-2021"));
        periodepengeluaranlist.add(new PeriodeLaporanModel(3,2,25000000, "Kuartal 3 2021", "1-7-2021", "31-9-2021"));
        periodepengeluaranlist.add(new PeriodeLaporanModel(4,3,15000000, "Kuartal 4 2021", "1-10-2021", "31-12-2021"));
        spinner = (Spinner) getView().findViewById(R.id.sp_periodeLaporan);
        ArrayAdapter<PeriodeLaporanModel> periodeLaporanAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,periodepengeluaranlist);
        spinner.setAdapter(periodeLaporanAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pilihan_periode = spinner.getSelectedItem().toString();
                for (int i = 0 ; i <periodepengeluaranlist.size() ; i++){
                    if(pilihan_periode.equals(periodepengeluaranlist.get(i).toString())){
                        id_periode = periodepengeluaranlist.get(i).id_periode_laporan;
                        break;
                    }
                }
                datarincianPengeluaranAdapter.clear();
                for (int i = 0 ; i <rincianpengeluaranlist.size() ; i++){
                    if(rincianpengeluaranlist.get(i).id_periode_laporan == id_periode){
                        datarincianPengeluaranAdapter.add(new RincianPengeluaranModel(rincianpengeluaranlist.get(i).id_rincian_pengeluaran,
                                rincianpengeluaranlist.get(i).id_kategori_pengeluaran, rincianpengeluaranlist.get(i).id_periode_laporan,
                                rincianpengeluaranlist.get(i).qty, rincianpengeluaranlist.get(i).total, rincianpengeluaranlist.get(i).id_admin,
                                rincianpengeluaranlist.get(i).uraian, rincianpengeluaranlist.get(i).satuan, rincianpengeluaranlist.get(i).tanggal_pencatatan,
                                rincianpengeluaranlist.get(i).tanggal_pelunasan));
                    }
                }
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

    public void removeItem(int position){
        rincianpengeluaranlist.remove(position);
        rincianPengeluaranAdapter.notifyDataSetChanged();
        datarincianPengeluaranAdapter.clear();
//        rincianPengeluaranAdapter.notifyItemChanged(position);

    }

    public void mulaiAdapter(){
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        Log.d("Budhi", String.valueOf(datarincianPengeluaranAdapter.size()));
        rincianPengeluaranAdapter = new RincianPengeluaranAdapter(getContext(), datarincianPengeluaranAdapter);

        mRecyclerView.setAdapter(rincianPengeluaranAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        rincianPengeluaranAdapter.setOnItemClickListener(new RincianPengeluaranAdapter.OnItemClickListener() {
//
//            @Override
//            public void onDeleteClick(int position) {
//                removeItem(position);
//            }
//        });
    }
}
