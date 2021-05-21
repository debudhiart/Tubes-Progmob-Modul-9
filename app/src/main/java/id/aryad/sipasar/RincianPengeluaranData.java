package id.aryad.sipasar;

import android.app.Application;

import java.util.ArrayList;

public class RincianPengeluaranData extends Application {

    ArrayList<RincianPengeluaranModel> rincianpengeluaranlist = new ArrayList<>();

    public RincianPengeluaranData() {
        rincianpengeluaranlist.add(new RincianPengeluaranModel(1, 4, 1, 3, 300000,1, "Pembayaran listrik bulan mei", "Bulan", "2-5-2021", "11-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(2, 2, 2, 4, 600000,1, "Pembayaran Air bulan mei", "Bulan", "1-5-2021", "3-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(3, 1, 3, 1, 1000000,1, "Pembayaran service ac bulan mei", "Bualn", "5-5-2021", "10-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(4, 3, 3, 3, 450000,1, "Pembayaran iuran kebersihan bulan mei", "Bulan", "7-5-2021", "15-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(5, 2, 4, 2, 300000,1, "Bembelian ATK bulan minggu 1 mei", "Minggu", "1-5-2021", "4-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(6, 1, 1, 3, 300000,1, "Pembayaran listrik bulan mei", "Bulan", "2-5-2021", "11-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(7, 2, 2, 4, 600000,1, "Pembayaran Air bulan mei", "Bulan", "1-5-2021", "3-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(8, 4, 3, 1, 1000000,1, "Pembayaran service ac bulan mei", "Bualn", "5-5-2021", "10-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(9, 3, 3, 3, 450000,1, "Pembayaran iuran kebersihan bulan mei", "Bulan", "7-5-2021", "15-5-2021"));
        rincianpengeluaranlist.add(new RincianPengeluaranModel(10, 2, 4, 2, 300000,1, "Bembelian ATK bulan minggu 1 mei", "Minggu", "1-5-2021", "4-5-2021"));
    }

    public ArrayList<RincianPengeluaranModel> getRincianpengeluaranlist() {
        return rincianpengeluaranlist;
    }

    public void addRincianPengeluaranData(int id_rincian_pengeluaran, int id_kategori_pengeluaran,
                                          int id_periode_laporan,
                                          int qty,
                                          int total,
                                          int id_admin,
                                          String uraian,
                                          String satuan,
                                          String tanggal_pencatatan,
                                          String tanggal_pelunasan) {

        rincianpengeluaranlist.add(new RincianPengeluaranModel( id_rincian_pengeluaran,
                id_kategori_pengeluaran,
                id_periode_laporan,
                qty,
                total,
                id_admin,
                uraian,
                satuan,
                tanggal_pencatatan,
                tanggal_pelunasan));
    }

    public void setRincianpengeluaranlist(int rincianPengeluaran, int id_kategori_pengeluaran,
                                          int angkaId_Periode,
                                          int angkaQty,
                                          int angkaTotal,
                                          int id_admin,
                                          String textSatuan,
                                          String textUraian,
                                          String textTanggalPencatatan,
                                          String textTanggalPelunasan ) {

        for(int a = 0; a < rincianpengeluaranlist.size() ; a++){

            if(rincianpengeluaranlist.get(a).getId_rincian_pengeluaran() == rincianPengeluaran){
                rincianpengeluaranlist.get(a).setId_rincian_pengeluaran(angkaId_Periode);
                rincianpengeluaranlist.get(a).setId_kategori_pengeluaran(id_kategori_pengeluaran);
                rincianpengeluaranlist.get(a).setId_periode_laporan(angkaId_Periode);
                rincianpengeluaranlist.get(a).setQty(angkaQty);
                rincianpengeluaranlist.get(a).setTotal(angkaTotal);
                rincianpengeluaranlist.get(a).setId_admin(id_admin);
                rincianpengeluaranlist.get(a).setSatuan(textSatuan);
                rincianpengeluaranlist.get(a).setUraian(textUraian);
                rincianpengeluaranlist.get(a).setTanggal_pencatatan(textTanggalPencatatan);
                rincianpengeluaranlist.get(a).setTanggal_pelunasan(textTanggalPelunasan);
            }
        }
    }
}
