package com.example.lastfm.adapter;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfm.R;
import com.example.lastfm.topAlbumsModel.AlbumItem;
import com.example.lastfm.utils.Utils;

import java.util.List;

public class ItemTopAlbumsAdapter extends RecyclerView.Adapter<ItemTopAlbumsAdapter.ItemViewHolder> {

    private static final String TAG = "ItemTopAlbumsAdapter";
    private List<AlbumItem> mAlbumItems;
    private ItemTopAlbumsAdapter.OnItemClickListener mOnItemClickListener;
    private static SparseBooleanArray sSelectedItems = new SparseBooleanArray();

    public interface OnItemClickListener {
        public void onItemClicked(int position);
    }

    public void setOnItemClickListener(ItemTopAlbumsAdapter.OnItemClickListener onItemClickListener) {
        Log.i(TAG, "setOnItemClickListener() ");
        mOnItemClickListener = onItemClickListener;
    }

    public ItemTopAlbumsAdapter(List<AlbumItem> albumItems) {
        mAlbumItems = albumItems;
    }

    public void setAlbumItems(List<AlbumItem> albumItems) {
        mAlbumItems = albumItems;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTopAlbum;
        public RelativeLayout mBackground;

        public ItemViewHolder(@NonNull final View itemView, final ItemTopAlbumsAdapter.OnItemClickListener listener) {
            super(itemView);
            mTopAlbum = itemView.findViewById(R.id.top_album_view);
            mBackground = itemView.findViewById(R.id.list_item_background);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sSelectedItems.get(getAdapterPosition(), false)) {
                        sSelectedItems.delete(getAdapterPosition());
                        mBackground.setSelected(false);
                        Log.i(TAG, " mCardView.setSelected(false) ");
                    } else {
                        sSelectedItems.put(getAdapterPosition(), true);
                        mBackground.setSelected(true);
                        Log.i(TAG, " mCardView.setSelected(true) ");
                    }
                    if (listener != null) {
                        final int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.albums_list_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemTopAlbumsAdapter.ItemViewHolder(view, mOnItemClickListener);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTopAlbumsAdapter.ItemViewHolder itemViewHolder, int position) {
        Log.i(TAG,"mAlbumItems length: "+mAlbumItems.size()+"Position: "+position);
        AlbumItem albumItem = mAlbumItems.get(position);
        Log.i(TAG,"albumItem.getArtist().getName(): "+albumItem.getArtist().getName());
        itemViewHolder.mTopAlbum.setText(albumItem.getArtist().getName());
        if(Utils.clearState){
            sSelectedItems.clear();
            Utils.clearState = false;
        }
        itemViewHolder.mBackground.setSelected(sSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return mAlbumItems.size();
    }

}