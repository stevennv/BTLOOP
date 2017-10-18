package steven.btloop.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import steven.btloop.R;
import steven.btloop.adapter.CustomPagerAdapter;
import steven.btloop.adapter.ListProductAdapter;
import steven.btloop.adapter.ListSuggestAdapter;
import steven.btloop.customview.TouchImageView;
import steven.btloop.model.Product;
import steven.btloop.model.ProductList;
import steven.btloop.utils.Common;
import steven.btloop.utils.SharePreferenceUtils;

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
    private ViewPager vpSlide;
    private Product productDetail;
    private ProductList productList;
    private Product[] suggestList;
    private ListSuggestAdapter adapter;
    private SharePreferenceUtils utils;
    private LinearLayoutManager layoutManager;
    private CustomPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        iniUI();
        getData();
    }

    protected void iniUI() {
        utils = new SharePreferenceUtils(this);
        tivImage = (ImageView) findViewById(R.id.img_detail_product);
        tvProductname = (TextView) findViewById(R.id.tv_name_product);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvOs = (TextView) findViewById(R.id.tv_os);
        tvFrontCamera = (TextView) findViewById(R.id.tv_front_camera);
        tvBackCamera = (TextView) findViewById(R.id.tv_back_camera);
        tvMemory = (TextView) findViewById(R.id.tv_memory);
        tvScreen = (TextView) findViewById(R.id.tv_screen);
        rvSuggest = (RecyclerView) findViewById(R.id.rv_suggest_product);
        vpSlide = (ViewPager) findViewById(R.id.vp_slide_show);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvSuggest.setLayoutManager(layoutManager);
    }

    private void getData() {
        if (getIntent() != null) {
            productDetail = (Product) getIntent().getSerializableExtra(Common.PRODUCT_DETAIL);
            Glide.with(ProductDetailActivity.this).load(productDetail.getArtwork()).into(tivImage);
            tvPrice.setText(productDetail.getPrice());
            tvProductname.setText(productDetail.getName());
            tvOs.setText(productDetail.getOs());
            tvFrontCamera.setText(productDetail.getFontCamera());
            tvBackCamera.setText(productDetail.getBackCamera());
            tvMemory.setText(productDetail.getMemory());
            tvScreen.setText(productDetail.getScreen());
            suggestList = utils.getNewestList();
            adapter = new ListSuggestAdapter(ProductDetailActivity.this, suggestList);
            adapter.notifyDataSetChanged();
            pagerAdapter = new CustomPagerAdapter(this, suggestList);
            vpSlide.setAdapter(pagerAdapter);
            rvSuggest.setAdapter(adapter);
            rvSuggest.setVisibility(View.GONE);
            final int speedScroll = 1500;
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() {
                int count = 0;

                @Override
                public void run() {
                    if (count < suggestList.length) {
                        rvSuggest.scrollToPosition(++count);
                        handler.postDelayed(this, speedScroll);
                    }


                }
            };
            handler.postDelayed(runnable, speedScroll);
        }
    }
}
