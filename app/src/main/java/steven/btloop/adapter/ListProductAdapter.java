package steven.btloop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import steven.btloop.R;
import steven.btloop.model.Product;

/**
 * Created by TruongNV on 10/3/2017.
 */

public class ListProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Product[] list;

    public ListProductAdapter(Context context, Product[] list) {
        this.context = context;
        this.list = list;
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
        final Product product = list[position];
        myViewHolder.tvPrice.setText(product.getPrice());
        myViewHolder.tvName.setText(product.getName());
        String url;
        if (product.getArtwork()== null || product.getArtwork().equals("")){
            url = product.getCategory().get(0).getArtwork();
        } else {
            url = product.getArtwork();
        }
        Glide.with(context).load(url).into(myViewHolder.imgProduct);
        myViewHolder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, product.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.length;
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
