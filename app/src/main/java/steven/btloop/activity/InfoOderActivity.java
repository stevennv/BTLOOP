package steven.btloop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import steven.btloop.R;

public class InfoOderActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtEmail;
    private EditText edtPhoneNumber;
    private TextView tvProductName;
    private TextView tvPrice;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_oder);
        iniUI();
    }

    protected void iniUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        tvProductName = (TextView) findViewById(R.id.tv_name_product);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvTitleToolbar.setText(getString(R.string.cancel));
    }
}
