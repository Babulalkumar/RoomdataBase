package com.example.mvvmpattern.adpter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmpattern.R;
import com.example.mvvmpattern.databinding.ItemBinding;
import com.example.mvvmpattern.model.Item;

import java.util.List;

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.CatViewHolder> {

    private Context context;
    private List<Item> postCodeModels;
    private ItemBinding binding;
    BooksListAdapter.click click;

    public BooksListAdapter(Context context, List<Item> cats, BooksListAdapter.click click) {
        this.context = context;
        this.postCodeModels = cats;
        this.click = click;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate (LayoutInflater.from (parent.getContext ()), R.layout.item, parent, false);
        return new CatViewHolder (binding.getRoot ());
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Item cat = postCodeModels.get (position);
        binding.setPostcode (cat);
        try {
            Glide.with (context).load (cat.getVolumeInfo ().getImageLinks ().getSmallThumbnail ()).centerCrop ().into (binding.imageView);
        } catch (Exception e) {
            Glide.with (context).load ("http://books.google.com/books/content?id=cFBptKRXrk4C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api").centerCrop ().into (binding.imageView);

            e.printStackTrace ();
        }
        //binding.setItemClickListeners(this);
        // binding.executePendingBindings();
        binding.rowIndex.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                click.itemClick (position);
               /* Intent intent = new Intent (context, LoginActivity.class);
                context.startActivity (intent);*/
                /*Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("zipcode", cat.getId());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return postCodeModels.size ();
    }

    public void getAllDatas(List<Item> cats) {
        this.postCodeModels = cats;
    }

/*
    @Override
    public void cardClicked(PostCodeModel f) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("zipcode", f.getId());
        context.startActivity(intent);
    }
*/

    public static class CatViewHolder extends RecyclerView.ViewHolder {

        public CatViewHolder(@NonNull View itemView) {
            super (itemView);
        }
    }

    public interface click {
        void itemClick(int position);
    }
}