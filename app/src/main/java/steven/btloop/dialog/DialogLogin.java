package steven.btloop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import steven.btloop.R;

/**
 * Created by TruongNV on 9/21/2017.
 */

public class DialogLogin {
    public static void dialogLogin(Context context, final IClickedFb iClickedFb) {
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        dialog.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.dialog_login);
        dialog.setContentView(R.layout.dialog_login);
        FacebookSdk.sdkInitialize(context);
        FacebookSdk.setApplicationId(context.getResources().getString(R.string.facebook_app_id));
        ImageView imgFb = (ImageView) dialog.findViewById(R.id.img_fb);
        ImageView imgGg = (ImageView) dialog.findViewById(R.id.img_gg);
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.img_close);
        imgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickedFb.clickConfirm();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public interface IClickedFb {
        void clickConfirm();
    }

}
