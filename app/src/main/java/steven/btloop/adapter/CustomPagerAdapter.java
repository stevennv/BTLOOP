package steven.btloop.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import steven.btloop.R;
import steven.btloop.model.Product;

/**
 * Created by Admin on 10/18/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {
    private Context context;
    private Product[] list;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context, Product[] list) {
        this.context = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Product product = list[position];
        View itemView = mLayoutInflater.inflate(R.layout.item_product, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_detail_product);
        Glide.with(context).load(product.getArtwork()).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
