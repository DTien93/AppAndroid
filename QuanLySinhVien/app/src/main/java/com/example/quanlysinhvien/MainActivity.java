package com.example.quanlysinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView lstSinhVien;
    SinhVienAdapter adapter;
    TuongTacDatabase database;
    Dialog dialog;
    viewDialog viewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstSinhVien = (ListView) findViewById(R.id.lstSinhVien);

        database = new TuongTacDatabase(this);

        getDATA();

        adapter = new SinhVienAdapter(this, 0, data.getData().arrSV);

        lstSinhVien.setAdapter(adapter);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_add_delete_change);
        viewDialog = new viewDialog(dialog);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idTrangChu:
                break;
            case R.id.idQuanLy:
                break;
            case R.id.iddkHoc:
                dialog.show();
                break;
            case R.id.idTimKiem:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getDATA() {
        database.open();
        data.getData().arrSV = new ArrayList<>(database.getAlldata());
        System.out.println("du" + data.getData().arrSV.size());
        database.close();
    }

    class viewDialog {
        EditText edtTen, edtSdt, edtGmail;
        Button btnNgaySinh, btnThem;
        Spinner splLop;
        String lop, gt = "nam";
        RadioGroup GT;

        //
        public viewDialog(Dialog v) {
            edtTen = (EditText)v.findViewById(R.id.edtTen);
            edtGmail = (EditText)v.findViewById(R.id.edtEmail);
            edtSdt = (EditText)v.findViewById(R.id.edtSDT);

            btnNgaySinh = (Button)v.findViewById(R.id.ChonNgay);
            btnThem = (Button)v.findViewById(R.id.btnThem);

            splLop = (Spinner)v.findViewById(R.id.splLop);

            GT = (RadioGroup)v.findViewById(R.id.RGGT);
            setRS();
            setBtnThem();
        }

        private void setBtnThem() {
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GT.getCheckedRadioButtonId() == R.id.nam) {
                        gt = "nam";
                    } else {
                        gt = "nu";
                    }
                    data.getData().arrSV.add(getSV());
                    database.open();
                    database.themSV(getSV());
                    database.close();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
        }

        private void setRS() {
            splLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    lop = getResources().getStringArray(R.array.lop)[i];

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    lop = getResources().getStringArray(R.array.lop)[0];
                }
            });
        }
        private SinhVien getSV() {
            SinhVien sv = new SinhVien();
            sv.setTen(edtTen.getText().toString());
            sv.setEmail(edtGmail.getText().toString());
            sv.setSodienthoai(edtSdt.getText().toString());
            sv.setLophoc(lop);
            sv.setGioitinh(gt);
            return sv;
        }
    }
}
