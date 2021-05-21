package id.aryad.sipasar;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PeriodeLaporanModel {

    int id_periode_laporan, id_laporan_sebelumnya, uang_kas;
    String nama_periode, tanggal_awal_periode, tanggal_akhir_periode;

    public PeriodeLaporanModel(int id_periode_laporan,
                               int id_laporan_sebelumnya,
                               int uang_kas,
                               String nama_periode,
                               String tanggal_awal_periode,
                               String tanggal_akhir_periode) {

        this.id_periode_laporan = id_periode_laporan;
        this.id_laporan_sebelumnya = id_laporan_sebelumnya;
        this.uang_kas = uang_kas;
        this.nama_periode = nama_periode;
        this.tanggal_awal_periode = tanggal_awal_periode;
        this.tanggal_akhir_periode = tanggal_akhir_periode;
    }

    public int getId_periode_laporan() {
        return id_periode_laporan;
    }

    public int getId_laporan_sebelumnya() {
        return id_laporan_sebelumnya;
    }

    public int getUang_kas() {
        return uang_kas;
    }

    @Override
    public String toString() {
        return nama_periode;
    }

    public String getTanggal_awal_periode() {
        return tanggal_awal_periode;
    }

    public String getTanggal_akhir_periode() {
        return tanggal_akhir_periode;
    }
}
