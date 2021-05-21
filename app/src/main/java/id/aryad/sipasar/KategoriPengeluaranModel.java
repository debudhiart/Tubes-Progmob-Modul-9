package id.aryad.sipasar;

public class KategoriPengeluaranModel {
    int id_kategori_pengeluaran;
    String nama_kategori;

    public KategoriPengeluaranModel(int id_kategori_pengeluaran, String nama_kategori) {
        this.id_kategori_pengeluaran = id_kategori_pengeluaran;
        this.nama_kategori = nama_kategori;
    }

    public int getId_kategori_pengeluaran() {
        return id_kategori_pengeluaran;
    }

    @Override
    public String toString() {
        return nama_kategori;
    }
}
