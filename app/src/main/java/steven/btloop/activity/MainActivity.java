package steven.btloop.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Share;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import steven.btloop.R;
import steven.btloop.adapter.ListProductAdapter;
import steven.btloop.dialog.DialogLogin;
import steven.btloop.model.Product;
import steven.btloop.model.UserInfo;
import steven.btloop.utils.Common;
import steven.btloop.utils.SharePreferenceUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private CallbackManager callbackManager;
    private CircularImageView civAvatar;
    private TextView tvName;
    private SharePreferenceUtils utils;
    private ListProductAdapter adapter;
    private RecyclerView rvListProduct;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iniUI();
    }

    private void iniUI() {
        utils = new SharePreferenceUtils(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvName = (TextView) findViewById(R.id.tv_name);
//        tvName.setText("Mời đăng nhập");
//            tvName.setOnClickListener(this);

        rvListProduct = (RecyclerView) findViewById(R.id.rv_list_product);
        layoutManager = new GridLayoutManager(this, 2);
        rvListProduct.setLayoutManager(layoutManager);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        civAvatar = (CircularImageView) view.findViewById(R.id.imageView);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(getString(R.string.pls_login));
        callbackManager = CallbackManager.Factory.create();
        checkLogin();
        navigationView.setNavigationItemSelectedListener(this);
        view.setOnClickListener(this);
        getListProduct();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
            DialogLogin.dialogLogin(this, new DialogLogin.IClickedFb() {
                @Override
                public void clickConfirm() {
                    loginFB();
                }
            });
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            LoginManager.getInstance().logOut();
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            civAvatar.setVisibility(View.GONE);
            tvName.setText(getString(R.string.pls_login));
            utils.saveUserInfo(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_name:
                DialogLogin.dialogLogin(this, new DialogLogin.IClickedFb() {
                    @Override
                    public void clickConfirm() {
                        Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                        loginFB();
                    }
                });
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
                Glide.with(MainActivity.this).load(avatar).into(civAvatar);
                tvName.setText(name);
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

    private void checkLogin() {
        if (utils.getUserInfo() == null) {
            civAvatar.setVisibility(View.GONE);
            tvName.setText(getString(R.string.pls_login));
        } else {
            Glide.with(MainActivity.this).load(utils.getUserInfo().getUrlAvatar()).into(civAvatar);
            tvName.setText(utils.getUserInfo().getName());
        }
    }

    private void getListProduct() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải...");
        progressDialog.show();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(Common.URL_GET_LIST)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, "onResponse: " + json);
                Gson gson = new Gson();
                Product[] product = gson.fromJson(json, Product[].class);
                adapter = new ListProductAdapter(MainActivity.this, product);
                adapter.notifyDataSetChanged();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        rvListProduct.setAdapter(adapter);
                    }
                });
//                List<Product> suggestList = new ArrayList<Product>();
                for (int i = 0; i < 10; i++) {

                    suggestList.add(product[i]);
                }
                Log.d(TAG, "onResponse: CHECK_DATA  " + suggestList.get(0).getBackCamera());
                utils.saveNewestList(suggestList);
            }
        });
    }
}


