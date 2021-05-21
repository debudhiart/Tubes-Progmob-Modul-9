package id.aryad.sipasar;

import java.util.ArrayList;

public class PeriodePengeluaranData {
    ArrayList<PeriodeLaporanModel> periodepengeluaranlist = new ArrayList<>();

    public PeriodePengeluaranData() {
        periodepengeluaranlist.add(new PeriodeLaporanModel(1,0,20000000, "Kuartal 1 2021", "1-1-2021", "31-3-2021"));
        periodepengeluaranlist.add(new PeriodeLaporanModel(2,1,10000000, "Kuartal 2 2021", "1-4-2021", "31-6-2021"));
        periodepengeluaranlist.add(new PeriodeLaporanModel(3,2,25000000, "Kuartal 3 2021", "1-7-2021", "31-9-2021"));
        periodepengeluaranlist.add(new PeriodeLaporanModel(4,3,15000000, "Kuartal 4 2021", "1-10-2021", "31-12-2021"));
    }

    public ArrayList<PeriodeLaporanModel> getPeriodepengeluaranlist() {
        return periodepengeluaranlist;
    }

    public void setPeriodepengeluaranlist(ArrayList<PeriodeLaporanModel> periodepengeluaranlist) {
        this.periodepengeluaranlist = periodepengeluaranlist;
    }
}
