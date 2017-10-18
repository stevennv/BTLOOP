package steven.btloop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import steven.btloop.model.Product;
import steven.btloop.model.ProductList;
import steven.btloop.model.UserInfo;

/**
 * Created by TruongNV on 9/21/2017.
 */

public class SharePreferenceUtils {
    private static SharePreferenceUtils mIntent = null;
    private static final String SHARE_NAME = "BTLOOP";
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public SharePreferenceUtils(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
        mIntent = this;
    }

    public static SharePreferenceUtils getIntent(Context context) {
        if (mIntent == null)
            mIntent = new SharePreferenceUtils(context);
        return mIntent;
    }

    public void saveUserInfo(UserInfo userInfo) {
        String json = gson.toJson(userInfo);
        editor.putString(Constant.USER_INFO, json);
        editor.commit();
    }

    public UserInfo getUserInfo() {
        String json = preferences.getString(Constant.USER_INFO, "");
        UserInfo userInfo = gson.fromJson(json, UserInfo.class);
        return userInfo;
    }

    public void saveNewestList(List<Product> list) {
        String json = gson.toJson(list);
        Log.d("SHARE", "saveNewestList: " + json);
        editor.putString(Common.LIST_PRODUCT_SUGGEST, json);
        editor.commit();
    }

    public Product[] getNewestList() {
        String json = preferences.getString(Common.LIST_PRODUCT_SUGGEST, "");
        Product[] list = gson.fromJson(json, Product[].class);
        return list;
    }
}
