package manop.mytutor.com.mytutor.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import manop.mytutor.com.mytutor.R;


public class BannerAdepter extends RecyclerView.Adapter<BannerAdepter.BannerViewHolder>{

    private Context context;
    private ArrayList<String> stringArrayList;
    private LayoutInflater layoutInflater;
    private OnClickItem onClickItem;

    public BannerAdepter(Context context, ArrayList<String> stringArrayList, OnClickItem onClickItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.stringArrayList = stringArrayList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_banner, parent, false);
        BannerViewHolder bannerViewHolder = new BannerViewHolder(view);

        return bannerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BannerViewHolder holder, int position) {

        String pathString = stringArrayList.get(position);

        Picasso.get().load(pathString).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickItem(v, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }


    public class BannerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public BannerViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imvBanner);

        }
    }   //Secon Class



}   // Main Class




