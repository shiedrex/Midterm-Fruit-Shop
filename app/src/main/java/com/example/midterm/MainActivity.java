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

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        dsc = findViewById(R.id.desc);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);

        create = findViewById(R.id.createbtn);
        retrieve = findViewById(R.id.retrievebtn);
        update = findViewById(R.id.updatebtn);
        delete = findViewById(R.id.deletebtn);

        DB = new DBHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String dscTXT = dsc.getText().toString();
                String priceTXT = price.getText().toString();
                String qtyTXT = qty.getText().toString();

                if (idTXT.isEmpty()) {
                    id.setError("Product ID is required!");
                    id.requestFocus();
                    return;
                }
                if (nameTXT.isEmpty()) {
                    name.setError("Product name is required!");
                    name.requestFocus();
                    return;
                }
                if (dscTXT.isEmpty()) {
                    dsc.setError("Product description is required!");
                    dsc.requestFocus();
                    return;
                }
                if (priceTXT.isEmpty()) {
                    price.setError("Product price is required!");
                    price.requestFocus();
                    return;
                }
                if (qtyTXT.isEmpty()) {
                    qty.setError("Product quantity is required!");
                    qty.requestFocus();
                    return;
                }

                if (idTXT.isEmpty() || nameTXT.isEmpty() || dscTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill up required fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkinsertdata = DB.insertuserdata(idTXT, nameTXT, dscTXT, priceTXT, qtyTXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(MainActivity.this, "Product Inserted", Toast.LENGTH_SHORT).show();
                        id.getText().clear();
                        name.getText().clear();
                        dsc.getText().clear();
                        price.getText().clear();
                        qty.getText().clear();
                    } else
                        Toast.makeText(MainActivity.this, "Product Not Inserted.Product ID Exists!", Toast.LENGTH_SHORT).show();
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

                if (idTXT.isEmpty()) {
                    id.setError("Product ID is required!");
                    id.requestFocus();
                    return;
                }
                if (nameTXT.isEmpty()) {
                    name.setError("Product name is required!");
                    name.requestFocus();
                    return;
                }
                if (dscTXT.isEmpty()) {
                    dsc.setError("Product description is required!");
                    dsc.requestFocus();
                    return;
                }
                if (priceTXT.isEmpty()) {
                    price.setError("Product price is required!");
                    price.requestFocus();
                    return;
                }
                if (qtyTXT.isEmpty()) {
                    qty.setError("Product quantity is required!");
                    qty.requestFocus();
                    return;
                }

                if (idTXT.isEmpty() || nameTXT.isEmpty() || dscTXT.isEmpty() || priceTXT.isEmpty() || qtyTXT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill up required fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkupdatedata = DB.updateuserdata(idTXT, nameTXT, dscTXT, priceTXT, qtyTXT);
                    if (checkupdatedata == true) {
                        Toast.makeText(MainActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                        id.getText().clear();
                        name.getText().clear();
                        dsc.getText().clear();
                        price.getText().clear();
                        qty.getText().clear();
                    } else
                        Toast.makeText(MainActivity.this, "Product Not Updated.Product ID does not Exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                Boolean checkudeletedata = DB.deletedata(idTXT);

                if (idTXT.isEmpty()) {
                    id.setError("Product ID is required!");
                    id.requestFocus();
                    return;
                }

                if (checkudeletedata == true) {
                    Toast.makeText(MainActivity.this, "Product Deleted", Toast.LENGTH_SHORT).show();
                    id.getText().clear();
                } else
                    Toast.makeText(MainActivity.this, "Product Not Deleted.Product ID does not Exists!", Toast.LENGTH_SHORT).show();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Product/s Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Description: " + res.getString(2) + "\n");
                    buffer.append("Price: " + res.getString(3) + "\n");
                    buffer.append("Quantity: " + res.getString(4) + "\n\n");
                }

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Product Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}