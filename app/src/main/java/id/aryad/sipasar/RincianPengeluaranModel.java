package id.aryad.sipasar;

import android.os.Parcel;
import android.os.Parcelable;

public class RincianPengeluaranModel implements Parcelable {
    int id_rincian_pengeluaran, id_kategori_pengeluaran, id_periode_laporan, qty, total, id_admin;
    String uraian, satuan, tanggal_pencatatan, tanggal_pelunasan;

    public RincianPengeluaranModel(int id_rincian_pengeluaran, int id_kategori_pengeluaran, int id_periode_laporan, int qty, int total, int id_admin, String uraian, String satuan, String tanggal_pencatatan, String tanggal_pelunasan) {
        this.id_rincian_pengeluaran = id_rincian_pengeluaran;
        this.id_kategori_pengeluaran = id_kategori_pengeluaran;
        this.id_periode_laporan = id_periode_laporan;
        this.qty = qty;
        this.total = total;
        this.id_admin = id_admin;
        this.uraian = uraian;
        this.satuan = satuan;
        this.tanggal_pencatatan = tanggal_pencatatan;
        this.tanggal_pelunasan = tanggal_pelunasan;
    }

    protected RincianPengeluaranModel(Parcel in) {
        id_rincian_pengeluaran = in.readInt();
        id_kategori_pengeluaran = in.readInt();
        id_periode_laporan = in.readInt();
        qty = in.readInt();
        total = in.readInt();
        id_admin = in.readInt();
        uraian = in.readString();
        satuan = in.readString();
        tanggal_pencatatan = in.readString();
        tanggal_pelunasan = in.readString();
    }

    public static final Creator<RincianPengeluaranModel> CREATOR = new Creator<RincianPengeluaranModel>() {
        @Override
        public RincianPengeluaranModel createFromParcel(Parcel in) {
            return new RincianPengeluaranModel(in);
        }

        @Override
        public RincianPengeluaranModel[] newArray(int size) {
            return new RincianPengeluaranModel[size];
        }
    };

    public int getId_rincian_pengeluaran() {
        return id_rincian_pengeluaran;
    }

    public int getId_kategori_pengeluaran() {
        return id_kategori_pengeluaran;
    }

    public int getId_periode_laporan() {
        return id_periode_laporan;
    }

    public int getQty() {
        return qty;
    }

    public int getTotal() {
        return total;
    }

    public int getId_admin() {
        return id_admin;
    }

    public String getUraian() {
        return uraian;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getTanggal_pencatatan() {
        return tanggal_pencatatan;
    }

    public String getTanggal_pelunasan() {
        return tanggal_pelunasan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_rincian_pengeluaran);
        dest.writeInt(id_kategori_pengeluaran);
        dest.writeInt(id_periode_laporan);
        dest.writeInt(qty);
        dest.writeInt(total);
        dest.writeInt(id_admin);
        dest.writeString(uraian);
        dest.writeString(satuan);
        dest.writeString(tanggal_pencatatan);
        dest.writeString(tanggal_pelunasan);
    }

    public void setId_rincian_pengeluaran(int id_rincian_pengeluaran) {
        this.id_rincian_pengeluaran = id_rincian_pengeluaran;
    }

    public void setId_kategori_pengeluaran(int id_kategori_pengeluaran) {
        this.id_kategori_pengeluaran = id_kategori_pengeluaran;
    }

    public void setId_periode_laporan(int id_periode_laporan) {
        this.id_periode_laporan = id_periode_laporan;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public void setTanggal_pencatatan(String tanggal_pencatatan) {
        this.tanggal_pencatatan = tanggal_pencatatan;
    }

    public void setTanggal_pelunasan(String tanggal_pelunasan) {
        this.tanggal_pelunasan = tanggal_pelunasan;
    }
}
