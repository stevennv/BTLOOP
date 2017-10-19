package steven.btloop.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.CoverTransformer;
import me.crosswall.lib.coverflow.core.PagerContainer;
import steven.btloop.R;
import steven.btloop.adapter.CustomPagerAdapter;
import steven.btloop.adapter.ListProductAdapter;
import steven.btloop.adapter.ListSuggestAdapter;
import steven.btloop.customview.MyCustomLayoutManager;
import steven.btloop.customview.TouchImageView;
import steven.btloop.dialog.DialogLogin;
import steven.btloop.model.Product;
import steven.btloop.model.ProductList;
import steven.btloop.model.UserInfo;
import steven.btloop.utils.Common;
import steven.btloop.utils.SharePreferenceUtils;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ProductDetailActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
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
    private Button btnBuy;
    private Product productDetail;
    private ProductList productList;
    private Product[] suggestList;
    private ListSuggestAdapter adapter;
    private LinearLayoutManager layoutManager;
    private CustomPagerAdapter pagerAdapter;
    private PagerContainer pagerContainer;
    private CallbackManager callbackManager;
    private static int currentPage = 0;
    private SharePreferenceUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        iniUI();
        getData();
    }

    protected void iniUI() {
        utils = new SharePreferenceUtils(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        tivImage = (ImageView) findViewById(R.id.img_detail_product);
        tvProductname = (TextView) findViewById(R.id.tv_name_product);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvOs = (TextView) findViewById(R.id.tv_os);
        tvFrontCamera = (TextView) findViewById(R.id.tv_front_camera);
        tvBackCamera = (TextView) findViewById(R.id.tv_back_camera);
        tvMemory = (TextView) findViewById(R.id.tv_memory);
        tvScreen = (TextView) findViewById(R.id.tv_screen);
        rvSuggest = (RecyclerView) findViewById(R.id.rv_suggest_product);
        vpSlide = (ViewPager) findViewById(R.id.vp_slide_show);
        callbackManager = CallbackManager.Factory.create();
        pagerContainer = (PagerContainer) findViewById(R.id.pager_container);
        layoutManager = new MyCustomLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSuggest.setLayoutManager(layoutManager);
        btnBuy.setOnClickListener(this);
    }

    private void getData() {
        if (getIntent() != null) {
            productDetail = (Product) getIntent().getSerializableExtra(Common.PRODUCT_DETAIL);
            Glide.with(ProductDetailActivity.this).load(productDetail.getArtwork()).into(tivImage);
            tvPrice.setText(productDetail.getPrice());
            tvProductname.setText(productDetail.getName());
            tvTitleToolbar.setText(productDetail.getName());
            tvOs.setText(productDetail.getOs());
            tvFrontCamera.setText(productDetail.getFontCamera());
            tvBackCamera.setText(productDetail.getBackCamera());
            tvMemory.setText(productDetail.getMemory());
            tvScreen.setText(productDetail.getScreen());
            suggestList = utils.getNewestList();
            adapter = new ListSuggestAdapter(ProductDetailActivity.this, suggestList);
            adapter.notifyDataSetChanged();
            rvSuggest.setAdapter(adapter);
            pagerContainer.setVisibility(View.GONE);
            vpSlide.setVisibility(View.GONE);
            final int speedScroll = 1500;
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() {
                int count = 0;

                @Override
                public void run() {
                    if (count < suggestList.length) {
                        rvSuggest.scrollToPosition(++count);
                        handler.postDelayed(this, speedScroll);
                    } else {
                        rvSuggest.scrollToPosition(0);
                        handler.postDelayed(this, speedScroll);
                    }


                }
            };
            handler.postDelayed(runnable, speedScroll);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buy:
//                if (utils.getUserInfo() != null) {
                    Intent intent = new Intent(this, InfoOderActivity.class);
                    intent.putExtra(Common.PRODUCT_DETAIL, productDetail);
                    startActivity(intent);
//                } else {
//                    AlertDialog dialog = new AlertDialog.Builder(this)
//                            .setMessage(getString(R.string.pls_login))
//                            .setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    DialogLogin.dialogLogin(ProductDetailActivity.this, new DialogLogin.IClickedFb() {
//                                        @Override
//                                        public void clickConfirm() {
//                                            loginFB();
//                                        }
//                                    });
//                                }
//                            })
//                            .setNeutralButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            }).show();
//                }

                break;
        }
    }

    private void loginFB() {
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("public_profile", "user_friends", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                String id = profile.getId();
                String name = profile.getFirstName() + profile.getLastName();
                String avatar = "http://graph.facebook.com/"
                        + loginResult.getAccessToken().getUserId() + "/picture?type=large";
                Log.d(TAG, "onSuccess: " + name + "\n" + avatar);
                UserInfo userInfo = new UserInfo(name, avatar, id);
                utils.saveUserInfo(userInfo);
                Intent intent = new Intent(ProductDetailActivity.this, InfoOderActivity.class);
                intent.putExtra(Common.PRODUCT_DETAIL, productDetail);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
