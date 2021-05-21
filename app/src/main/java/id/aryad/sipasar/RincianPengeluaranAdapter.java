package id.aryad.sipasar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RincianPengeluaranAdapter extends RecyclerView.Adapter<RincianPengeluaranAdapter.RincianPengeluaranViewHoler> {
    private ArrayList<RincianPengeluaranModel> mRincianPengeluaranList;
    public static final String EXTRA_MESSAGE = "id.aryad.sipasar.MESSAGE";
//    public OnItemClickListener mListener;

    Context context;

    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
        void getData(int position);
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public RincianPengeluaranAdapter(Context ct, ArrayList<RincianPengeluaranModel> listrincianpengeluaran){
        context = ct;
        mRincianPengeluaranList = listrincianpengeluaran;
    }

    @NonNull
    @Override
    public RincianPengeluaranViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new RincianPengeluaranViewHoler(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RincianPengeluaranViewHoler holder, int position) {
        RincianPengeluaranModel currentItem = mRincianPengeluaranList.get(position);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        //Nampil return int
        holder.view_total.setText( formatRupiah.format((double)mRincianPengeluaranList.get(position).getTotal()));
//        holder.view_total.setText( String.valueOf(mRincianPengeluaranList.get(position).getTotal()) );

        holder.view_uraian.setText( mRincianPengeluaranList.get(position).getUraian() );
        try {
            holder.view_tanggal_pencatatan.setText( dateFormat(mRincianPengeluaranList.get(position).getTanggal_pencatatan()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.view_tanggal_pelunasan.setText(dateFormat(mRincianPengeluaranList.get(position).getTanggal_pelunasan()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Log.d("logcat",getTotal());
//        Log.d("key",mRincianPengeluaranList.get(position).getTotal())
//        holder.view_uraian.setText(currentItem.getTotal());
//        holder.view_total.setText( "Budhi" );


    }

    @Override
    public int getItemCount() {
        return mRincianPengeluaranList.size();
    }

    public class RincianPengeluaranViewHoler extends RecyclerView.ViewHolder{
        public TextView view_total;
        public TextView view_uraian;
        public TextView view_tanggal_pencatatan;
        public TextView view_tanggal_pelunasan;
        public ImageView tombol_delete, btn_view, btn_edit;


        public RincianPengeluaranViewHoler(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            view_total = itemView.findViewById(R.id.tv_total);
            view_uraian = itemView.findViewById(R.id.tv_uraian);
            view_tanggal_pencatatan = itemView.findViewById(R.id.tv_tanggal_pencatatan);
            view_tanggal_pelunasan = itemView.findViewById(R.id.tv_tanggal_pelunasan);

            tombol_delete = itemView.findViewById(R.id.btn_delete);
            btn_view = itemView.findViewById(R.id.btn_view);
            btn_edit = itemView.findViewById(R.id.btn_edit);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });

            tombol_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Hapus Data")
                            .setMessage("Anda yakin ingin menghaspu data ini?")
                            .setPositiveButton("Ya", null)
                            .setNegativeButton("Tidak", null)
                            .show();

                    Button tombolYa = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    tombolYa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null){
                                int position = getAdapterPosition();
                                if(position != RecyclerView.NO_POSITION){
//                            Toast.makeText(contextm )
//                            mRincianPengeluaranList.remove(position);
//                            notifyDataSetChanged();
                                    listener.onDeleteClick(position);
                                }
                            }
                            dialog.dismiss();
                        }
                    });

                }
            });

            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
//                            Toast.makeText(contextm )
//                            mRincianPengeluaranList.remove(position);
//                            notifyDataSetChanged();
                            listener.getData(position);
                        }
                    }
//                    Intent editrincianpengeluaran = new Intent(context, EditRincianPengeluaranActivity.class);
//                    context.startActivity(editrincianpengeluaran);

//                    Toast.makeText(context(), "Data tersubmit", Toast.LENGTH_SHORT).show();
                }
            });

            btn_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
//                    Intent viewrincianpengeluaran = new Intent(context, ViewRincianPengeluaranActivity.class);
//                    context.startActivity(viewrincianpengeluaran);
//                    Toast.makeText(context, "Lihat Detail", Toast.LENGTH_SHORT).show();
                }
            });
        }

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
