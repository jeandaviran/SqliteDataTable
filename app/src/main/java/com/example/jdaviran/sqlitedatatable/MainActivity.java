package com.example.jdaviran.sqlitedatatable;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.jdaviran.sqlitedatatable.database.DBHelper;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    TableLayout tableLayout,tableLayout2;
    EditText txtname,txtdni,txtboleta,txtmonto;
    List<Producto> object = new ArrayList<Producto>();
    List<Producto> objectCanje = new ArrayList<Producto>();
    Producto producto;
    DBHelper db;
    SharedPreferences prefs;
    List<Values> productoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        tableLayout=(TableLayout)findViewById(R.id.tableLayout);
        tableLayout2=(TableLayout)findViewById(R.id.tableLayout2);
        txtname = findViewById(R.id.txtname);
        txtdni = findViewById(R.id.txtdni);
        txtboleta = findViewById(R.id.txtboleta);
        txtmonto = findViewById(R.id.txtmonto);
        ((Button) findViewById(R.id.btSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save("05/12/2018",txtname.getText().toString(),txtdni.getText().toString(),Integer.parseInt(txtboleta.getText().toString()),Double.parseDouble(txtmonto.getText().toString()));
                Save();
            }
        });
        //list
        productoArrayList=new ArrayList<>();
        db = new DBHelper(MainActivity.this);
        object.add(new Producto(1, "Cepillo colgate"));
        object.add(new Producto(2, "Cepillo kolynos"));
        object.add(new Producto(3, "Jabón protex"));
        object.add(new Producto(4, "Jabón rosado"));
        object.add(new Producto(5, "Jabón nivea"));
        object.add(new Producto(6, "Pasta dental colgate"));
        object.add(new Producto(7, "Pasta dental kolynos"));

        objectCanje.add(new Producto(8, "Cepillo colgate canje"));
        objectCanje.add(new Producto(9, "Cepillo kolynos canje"));
        objectCanje.add(new Producto(10, "Jabón protex canje"));
        objectCanje.add(new Producto(11, "Jabón rosado canje"));

        //get
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //
        for (int i=0;i<object.size();i++){
            final int position = i;
            View tableRow = LayoutInflater.from(this).inflate(R.layout.molde,null,false);
            TextView Name  = (TextView) tableRow.findViewById(R.id.textView);
            CheckBox check  = (CheckBox) tableRow.findViewById(R.id.checkBox);
            final EditText cantidad  = (EditText) tableRow.findViewById(R.id.editText);

            Name.setText(object.get(i).getProducto());
            //valores
            String response= prefs.getString("value-producto-id-"+object.get(i).getIdproducto(), "");
            try {
                JSONObject jsonObject = new JSONObject(response);
            if (jsonObject != null){
                    check.setChecked(jsonObject.getBoolean("val_check"));
                    cantidad.setText(jsonObject.getString("val_content"));
                    cantidad.setEnabled(true);
                productoArrayList.add(new Values(jsonObject.getInt("val_id"),jsonObject.getBoolean("val_check"),jsonObject.getString("val_content")));
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            check.setOnClickListener(onClick(object.get(i).getIdproducto(),check,cantidad));
            tableLayout.addView(tableRow);
        }

        for (int k = 0; k<objectCanje.size(); k++){
            final int position = k;
            View tableRow = LayoutInflater.from(this).inflate(R.layout.molde,null,false);
            TextView Name_canje  = (TextView) tableRow.findViewById(R.id.textView);
            final CheckBox check_canje  = (CheckBox) tableRow.findViewById(R.id.checkBox);
            final EditText cantidad_canje  = (EditText) tableRow.findViewById(R.id.editText);

            Name_canje.setText(objectCanje.get(k).getProducto());

            String response= prefs.getString("value-canje-id-"+object.get(k).getIdproducto(), "");
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject != null){
                    //seleccionando los cajones
                    check_canje.setChecked(jsonObject.getBoolean("val_check"));
                    cantidad_canje.setText(jsonObject.getString("val_content"));
                    cantidad_canje.setEnabled(true);
                    //guardandolos en un array
                    productoArrayList.add(new Values(jsonObject.getInt("val_id"),jsonObject.getBoolean("val_check"),jsonObject.getString("val_content")));
                }
            }catch (Exception e){e.printStackTrace();}
            check_canje.setOnClickListener(onClick2(objectCanje.get(k).getIdproducto(),check_canje,cantidad_canje));
            tableLayout2.addView(tableRow);
        }
    }

    View.OnClickListener onClick(final int id, final CheckBox row,final EditText text) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                if (row.isChecked()){
                    text.setEnabled(true);
                    text.requestFocus();
                    //editor.putInt("value-id-"+id,id);
                    //editor.putBoolean("value-"+id, row.isChecked() ? true : false);
                    //editor.apply();

                    text.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                public void onFocusChange(View v, boolean hasFocus) {
                                    if (!hasFocus) {
                                        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                                        //editor.putString("value-text-id-"+id,text.getText().toString());
                                        // editor.apply();
                                        productoArrayList.add(new Values(id, row.isChecked() ? true : false, text.getText().toString()));
                                        Gson gson = new Gson();
                                        Values values = new Values(id, row.isChecked() ? true : false, text.getText().toString());
                                        String json = gson.toJson(values);
                                        editor.putString("value-producto-id-" + id, json);
                                        editor.apply();
                                    }
                                }
                            });
                        }
                    });

                }else{
                    text.setEnabled(false);
                    editor.remove("value-producto-id"+id).commit();
                    //productoArrayList.remove(id-1);
                    text.setText("");
                }
            }
        };
    }

    View.OnClickListener onClick2(final int id, final CheckBox row,final EditText text) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                if (row.isChecked()){
                    text.setEnabled(true);
                    text.requestFocus();
                    text.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                public void onFocusChange(View v, boolean hasFocus) {
                                    if (!hasFocus) {
                                        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                                        //editor.putString("value-text-id-"+id,text.getText().toString());
                                        // editor.apply();
                                        productoArrayList.add(new Values(id, row.isChecked() ? true : false, text.getText().toString()));
                                        Gson gson = new Gson();
                                        Values values = new Values(id, row.isChecked() ? true : false, text.getText().toString());
                                        String json = gson.toJson(values);
                                        editor.putString("value-canje-id-" + id, json);
                                        editor.apply();
                                    }
                                }
                            });
                        }
                            });

                }else{
                    text.setEnabled(false);
                    editor.remove("value-canje-id-"+id).commit();
                    text.setText("");
                }
            }
        };
    }

    private void Save(){
        try {
            LinearLayout focuslayout = findViewById(R.id.line1);
            focuslayout.requestFocus();
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            Producto producto = new Producto("21/12/2018", txtname.getText().toString(), txtdni.getText().toString(), Integer.parseInt(txtboleta.getText().toString()), Double.parseDouble(txtmonto.getText().toString()));
            if (dbHelper.insCanje(producto)) {
                if (productoArrayList.size() > 0) {
                    for (int i = 0; i < productoArrayList.size(); i++) {
                        Log.i("LOG", productoArrayList.get(i).getVal_content());
                        int idcanje = dbHelper.getLastTransaction();
                        ProductoDetalle productoDetalle = new ProductoDetalle(idcanje, productoArrayList.get(i).val_id, productoArrayList.get(i).val_check, Integer.parseInt(productoArrayList.get(i).val_content));
                        if (dbHelper.insCanjeDetalle(productoDetalle)) {
                        } else {
                        }
                    }
                }
            } else {

            }
        }catch (Exception e){e.printStackTrace();}
    }

}
