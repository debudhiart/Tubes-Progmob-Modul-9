package id.aryad.sipasar;

import java.util.ArrayList;

public class KategoriPengeluaranData {

    ArrayList<KategoriPengeluaranModel> kategoriPengeluaranModel = new ArrayList<>();

    public KategoriPengeluaranData() {
        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(1, "Listrik"));
        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(2, "Sewa Gedung"));
        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(3, "Air"));
        kategoriPengeluaranModel.add(new KategoriPengeluaranModel(4, "Gaji Pegawai"));
    }

    public ArrayList<KategoriPengeluaranModel> getKategoriPengeluaranModel() {
        return kategoriPengeluaranModel;
    }

    public void setKategoriPengeluaranModel(ArrayList<KategoriPengeluaranModel> kategoriPengeluaranModel) {
        this.kategoriPengeluaranModel = kategoriPengeluaranModel;
    }
}
