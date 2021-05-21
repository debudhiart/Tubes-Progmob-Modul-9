//package id.aryad.sipasar;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.util.List;
//
//public class PeriodeLaporanAdapter extends ArrayAdapter<PeriodeLaporanModel> {
//    LayoutInflater layoutInflater;
//
//    public PeriodeLaporanAdapter(@NonNull DashboardFragment context, int resource, @NonNull List<PeriodeLaporanModel> periodeLaporanModels) {
//        super(context, resource, periodeLaporanModels);
//        layoutInflater = LayoutInflater.from(context);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View rowView = layoutInflater.inflate(R.layout.sp_periodelaporan_item, null, true);
//        PeriodeLaporanModel periodeLaporanModel = getItem(position);
//        TextView nama_periode = (TextView)rowView.findViewById(R.id.tv_periodeLaporan);
//        return rowView;
//    }
//
//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if (convertView == null)
//            convertView = layoutInflater.inflate(R.layout.sp_periodelaporan_item, parent, false);
//        PeriodeLaporanModel periodeLaporanModel = getItem(position);
//        TextView nama_periode = (TextView)convertView.findViewById(R.id.tv_periodeLaporan);
//        return convertView;
//    }
//}
