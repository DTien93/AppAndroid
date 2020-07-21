package com.example.quanlysinhvien;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    ArrayList<SinhVien> arr;
    Context ct;

    public SinhVienAdapter(Context context, int resource, ArrayList<SinhVien> o) {
        super(context, resource, o);
        this.ct = context;
        this.arr = data.getData().arrSV;
        System.out.println("du" + data.getData().arrSV.size());
    }

    @Override
    public void notifyDataSetChanged() {
        this.arr = data.getData().arrSV;
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        viewHolder v;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_sinh_vien, null);
            v = new viewHolder(row);
            row.setTag(v);
        } else {
            v = (viewHolder) row.getTag();
        } if (arr.size()>0) {
           System.out.println("du");
            v.setView(arr.get(position));
        }
        return row;
    }
    class viewHolder {
        TextView txvTen, txvSDT, txvEmail, txvLop;
        ImageView imgGT;

        public viewHolder(View v) {
            txvTen = (TextView)v.findViewById(R.id.txtTen);
            txvEmail = (TextView)v.findViewById(R.id.txtEmail);
            txvLop = (TextView)v.findViewById(R.id.txvlop);
            txvSDT = (TextView)v.findViewById(R.id.txtSDT);
            imgGT = (ImageView)v.findViewById(R.id.imgGT);
        }
        public void setView(SinhVien v) {
            txvTen.setText(v.getTen());
            txvSDT.setText(v.getSodienthoai());
            txvLop.setText(v.getLophoc());
            txvEmail.setText(v.getEmail());
            System.out.println("du" + v.getGioitinh());
            if (v.getGioitinh().equals("nu")) {
                imgGT.setImageResource(R.drawable.nu);
            } else {
                imgGT.setImageResource(R.drawable.nam);
            }
        }
    }
}
