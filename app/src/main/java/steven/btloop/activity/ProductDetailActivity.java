package steven.btloop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import steven.btloop.R;
import steven.btloop.adapter.ListProductAdapter;
import steven.btloop.adapter.ListSuggestAdapter;
import steven.btloop.customview.TouchImageView;
import steven.btloop.model.Product;
import steven.btloop.utils.Common;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView tivImage;
    private TextView tvProductname;
    private TextView tvPrice;
    private TextView tvOs;
    private TextView tvFrontCamera;
    private TextView tvBackCamera;
    private TextView tvMemory;
    private TextView tvScreen;
    private RecyclerView rvSuggest;
    private Product productDetail;
    private List<Product> productList = new ArrayList<>();
    private ListSuggestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        iniUI();
        getData();
    }

    protected void iniUI() {
        tivImage = (ImageView) findViewById(R.id.img_detail_product);
        tvProductname = (TextView) findViewById(R.id.tv_name_product);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvOs = (TextView) findViewById(R.id.tv_os);
        tvFrontCamera = (TextView) findViewById(R.id.tv_front_camera);
        tvBackCamera = (TextView) findViewById(R.id.tv_back_camera);
        tvMemory = (TextView) findViewById(R.id.tv_memory);
        tvScreen = (TextView) findViewById(R.id.tv_screen);
        rvSuggest = (RecyclerView) findViewById(R.id.rv_suggest_product);
    }

    private void getData() {
        if (getIntent() != null) {
            productDetail = (Product) getIntent().getSerializableExtra(Common.PRODUCT_DETAIL);
//            productList = (List<Product>) getIntent().getSerializableExtra(Common.LIST_PRODUCT_SUGGEST);
            Glide.with(ProductDetailActivity.this).load(productDetail.getArtwork()).into(tivImage);
            tvPrice.setText(productDetail.getPrice());
            tvProductname.setText(productDetail.getName());
            tvOs.setText(productDetail.getOs());
            tvFrontCamera.setText(productDetail.getFontCamera());
            tvBackCamera.setText(productDetail.getBackCamera());
            tvMemory.setText(productDetail.getMemory());
            tvScreen.setText(productDetail.getScreen());
//            adapter = new ListSuggestAdapter(ProductDetailActivity.this, productList);
//            adapter.notifyDataSetChanged();
//            rvSuggest.setAdapter(adapter);
        }
    }
}
