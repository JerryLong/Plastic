package com.conduit.plastic.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.conduit.plastic.adapter.base.base.ItemViewDelegate;
import com.conduit.plastic.adapter.base.base.ItemViewDelegateManager;
import com.conduit.plastic.adapter.base.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<T> mDatas = new ArrayList<>();
    protected int headCount = 1;

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener<T> mOnItemClickListener;
    protected OnItemLongClickListener<T> mOnItemLongClickListener;

    public MultiItemTypeAdapter(Context mContext) {
        this.mContext = mContext;
        mDatas = new ArrayList<T>();
        mItemViewDelegateManager = new ItemViewDelegateManager();

    }

    public MultiItemTypeAdapter(Context context, List<T> datas, int headCount) {
        this.mContext = context;
        this.mDatas = datas;
        this.headCount = headCount + 1;
    }

    public MultiItemTypeAdapter(Context context, int headCount) {
        this.mContext = context;
        this.headCount = headCount + 1;
    }

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public void setNewData(List<T> datas) {
        mDatas = datas;
        this.notifyDataSetChanged();
    }

    public void setMoreData(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount + 1;
    }

    public int getCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    public void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition() - headCount);
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition() - headCount;
                    mOnItemClickListener.onItemClick(v, viewHolder, mDatas.get(position), position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = viewHolder.getAdapterPosition() - headCount;

                    return mOnItemLongClickListener.onItemLongClick(v, viewHolder, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (mDatas != null ) {
            itemCount = mDatas.size();
        }
        return itemCount;
    }

    public void remove(int position) {
        mDatas.remove(position + headCount);
        notifyItemRemoved(position + headCount);
        if ((position - headCount) != mDatas.size()) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position + headCount, mDatas.size() - (position - headCount));
        }
        notifyDataSetChanged();

    }

    public List<T> getDatas() {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, RecyclerView.ViewHolder holder, T obj, int position);

    }

    public interface OnItemLongClickListener<T> {

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T obj, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }
}
