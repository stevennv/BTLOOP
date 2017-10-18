package steven.btloop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import steven.btloop.R;
import steven.btloop.model.Product;

/**
 * Created by Admin on 10/17/2017.
 */

public class ListSuggestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Product[] listSuggest;

    public ListSuggestAdapter(Context context, Product[] listSuggest) {
        this.context = context;
        this.listSuggest = listSuggest;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Product product = listSuggest[position];
        myViewHolder.tvPrice.setText(product.getPrice());
        myViewHolder.tvName.setText(product.getName());
        String url;
        url = product.getArtwork();
        Glide.with(context).load(url).into(myViewHolder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return listSuggest.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView imgProduct;
        public TextView tvPrice;
        public LinearLayout rlItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            imgProduct = (ImageView) itemView.findViewById(R.id.img_detail_product);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            rlItem = (LinearLayout) itemView.findViewById(R.id.rl_item);
        }
    }
}
