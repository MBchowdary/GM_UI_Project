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
import com.example.lastfm.topTagsModel.TagItem;

import java.util.List;

public class ItemTopTagsAdapter extends RecyclerView.Adapter<ItemTopTagsAdapter.ItemViewHolder> {

    private static final String TAG = "ItemTopTagsAdapter";
    private List<TagItem> mTagItems;
    private OnItemClickListener mOnItemClickListener;
    private static SparseBooleanArray sSelectedItems = new SparseBooleanArray();;

    public interface OnItemClickListener {
        public void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        Log.i(TAG,"setOnItemClickListener() ");
        mOnItemClickListener = onItemClickListener;
    }

    public ItemTopTagsAdapter(List<TagItem> tagItems) {
        mTagItems = tagItems;
    }

    public void setTagItems(List<TagItem> tagItems) {
        mTagItems = tagItems;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTopTag;
        public RelativeLayout mBackground;

        public ItemViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTopTag = itemView.findViewById(R.id.top_tag_view);
            mBackground = itemView.findViewById(R.id.list_item_background);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sSelectedItems.get(getAdapterPosition(), false)) {
                        sSelectedItems.delete(getAdapterPosition());
                        mBackground.setSelected(false);
                        Log.i(TAG," mCardView.setSelected(false) ");
                    } else {
                        sSelectedItems.put(getAdapterPosition(), true);
                        mBackground.setSelected(true);
                        Log.i(TAG," mCardView.setSelected(true) ");
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_list_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, mOnItemClickListener);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        TagItem tagItem = mTagItems.get(position);
        itemViewHolder.mTopTag.setText(tagItem.getName());
        itemViewHolder.mBackground.setSelected(sSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return mTagItems.size();
    }
}