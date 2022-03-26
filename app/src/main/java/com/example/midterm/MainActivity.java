package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id, name, dsc, price, qty;
    Button create, retrieve, update, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //edittext
        id = findViewById(R.id.fruit_id);
        name = findViewById(R.id.fruit_name);
        dsc = findViewById(R.id.fruit_desc);
        price = findViewById(R.id.fruit_price);
        qty = findViewById(R.id.fruit_qty);
        //button
        create = findViewById(R.id.createButton);
        retrieve = findViewById(R.id.retrieveButton);
        update = findViewById(R.id.updateButton);
        delete = findViewById(R.id.deleteButton);
        //database
        DB = new DBHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String dscTXT = dsc.getText().toString();
                String priceTXT = price.getText().toString();
                String qtyTXT = qty.getText().toString();

                //check empty edit texts
                if (idTXT.isEmpty()) {
                    id.setError("Fruit ID is required!");
                    id.requestFocus();
                    return;
                }
                if (nameTXT.isEmpty()) {
                    name.setError("Fruit Name is required!");
                    name.requestFocus();
                    return;
                }
                if (dscTXT.isEmpty()) {
                    dsc.setError("Fruit Description is required!");
                    dsc.requestFocus();
                    return;
                }
                if (priceTXT.isEmpty()) {
                    price.setError("Fruit Price is required!");
                    price.requestFocus();
                    return;
                }
                if (qtyTXT.isEmpty()) {
                    qty.setError("Fruit Quantity is required!");
                    qty.requestFocus();
                    return;
                }

                if (idTXT.isEmpty() || nameTXT.isEmpty() || dscTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean createdata = DB.insertuserdata(idTXT, nameTXT, dscTXT, priceTXT, qtyTXT);
                    if (createdata == true) {
                        Toast.makeText(MainActivity.this, "Fruit Created", Toast.LENGTH_SHORT).show();
                        id.getText().clear();
                        name.getText().clear();
                        dsc.getText().clear();
                        price.getText().clear();
                        qty.getText().clear();
                    } else
                        Toast.makeText(MainActivity.this, "Fruit Record Not Created. Fruit ID Exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String dscTXT = dsc.getText().toString();
                String priceTXT = price.getText().toString();
                String qtyTXT = qty.getText().toString();

                //check empty edit texts
                if (idTXT.isEmpty()) {
                    id.setError("Fruit ID is required!");
                    id.requestFocus();
                    return;
                }
                if (nameTXT.isEmpty()) {
                    name.setError("Fruit name is required!");
                    name.requestFocus();
                    return;
                }
                if (dscTXT.isEmpty()) {
                    dsc.setError("Fruit description is required!");
                    dsc.requestFocus();
                    return;
                }
                if (priceTXT.isEmpty()) {
                    price.setError("Fruit price is required!");
                    price.requestFocus();
                    return;
                }
                if (qtyTXT.isEmpty()) {
                    qty.setError("Fruit quantity is required!");
                    qty.requestFocus();
                    return;
                }

                if (idTXT.isEmpty() || nameTXT.isEmpty() || dscTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean updatedata = DB.updateuserdata(idTXT, nameTXT, dscTXT, priceTXT, qtyTXT);
                    if (updatedata == true) {
                        Toast.makeText(MainActivity.this, "Fruit Record Updated", Toast.LENGTH_SHORT).show();
                        id.getText().clear();
                        name.getText().clear();
                        dsc.getText().clear();
                        price.getText().clear();
                        qty.getText().clear();
                    } else
                        Toast.makeText(MainActivity.this, "Fruit Record Not Updated. Fruit ID does not Exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                Boolean deletedata = DB.deletedata(idTXT);

                //check if id is empty
                if (idTXT.isEmpty()) {
                    id.setError("Fruit ID is required!");
                    id.requestFocus();
                    return;
                }

                if (deletedata == true) {
                    Toast.makeText(MainActivity.this, "Fruit Record Deleted", Toast.LENGTH_SHORT).show();
                    id.getText().clear();
                    name.getText().clear();
                    dsc.getText().clear();
                    price.getText().clear();
                    qty.getText().clear();
                } else
                    Toast.makeText(MainActivity.this, "Fruit Record Not Deleted. Fruit ID does not Exists!", Toast.LENGTH_SHORT).show();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Fruit Record/s Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Fruit ID: " + res.getString(0) + "\n");
                    buffer.append("Fruit Name: " + res.getString(1) + "\n");
                    buffer.append("Fruit Description: " + res.getString(2) + "\n");
                    buffer.append("Fruit Price: " + res.getString(3) + "\n");
                    buffer.append("Fruit Quantity: " + res.getString(4) + "\n\n");
                }

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Fruit Records");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}