package com.conduit.plastic.adapter.expandgrid;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BrandEntity;

import java.util.ArrayList;

public class SectionedExpandableGridAdapter extends RecyclerView.Adapter<SectionedExpandableGridAdapter.ViewHolder> {

    //data array
    private ArrayList<Object> mDataArrayList;

    //context
    private final Context mContext;

    //listeners
    private final ItemClickListener mItemClickListener;
    private final SectionStateChangeListener mSectionStateChangeListener;

    //view type
    private static final int VIEW_TYPE_SECTION = R.layout.layout_section;
    private static final int VIEW_TYPE_ITEM = R.layout.adapter_brand; //TODO : change this

    public SectionedExpandableGridAdapter(Context context, ArrayList<Object> dataArrayList,
                                          final GridLayoutManager gridLayoutManager, ItemClickListener itemClickListener,
                                          SectionStateChangeListener sectionStateChangeListener) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mSectionStateChangeListener = sectionStateChangeListener;
        mDataArrayList = dataArrayList;

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isSection(position) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }

    private boolean isSection(int position) {
        return mDataArrayList.get(position) instanceof Section;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(viewType, parent, false), viewType);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (holder.viewType) {
            case VIEW_TYPE_ITEM:
                final BrandEntity item = (BrandEntity) mDataArrayList.get(position);
                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

                int width = wm.getDefaultDisplay().getWidth();
                ViewGroup.LayoutParams params = holder.image.getLayoutParams();
                params.height = (width - 10) / 5;
                params.width = (width - 10) / 5;
                holder.image.setLayoutParams(params);

                Glide.with(mContext).load(item.getBrandLogo()).apply(
                        RequestOptions.overrideOf((width - 20) / 5, (width - 20) / 5)).into(holder.image);
                if (TextUtils.isEmpty(item.getBrandNameCn())) {
                    holder.nameTv.setText(item.getBrandNameEn());
                } else {
                    holder.nameTv.setText(item.getBrandNameCn());
                }
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.itemClicked(item);
                    }
                });
                break;
            case VIEW_TYPE_SECTION:
                final Section section = (Section) mDataArrayList.get(position);
                holder.sectionTextView.setText(section.getName());
                holder.sectionTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != 0)
                            mSectionStateChangeListener.onSectionStateChanged(section, !section.isExpanded());
//                        mItemClickListener.itemClicked(section);
                    }
                });
//                holder.sectionToggleButton.setChecked(section.isExpanded);
//                holder.sectionToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        mSectionStateChangeListener.onSectionStateChanged(section, isChecked);
//                    }
//                });
                if (position == 0) {
                    holder.sectionToggleTextView.setVisibility(View.GONE);
                }
                holder.sectionToggleTextView.setText(section.isExpanded() ? "[展开]" : "[收起]");
                holder.sectionToggleTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != 0)
                            mSectionStateChangeListener.onSectionStateChanged(section, !section.isExpanded());
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isSection(position))
            return VIEW_TYPE_SECTION;
        else return VIEW_TYPE_ITEM;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        //common
        View view;
        int viewType;

        //for section
        TextView sectionTextView;
        TextView sectionToggleTextView;
        ToggleButton sectionToggleButton;

        //for item
        TextView nameTv;
        ImageView image;

        public ViewHolder(View view, int viewType) {
            super(view);
            this.viewType = viewType;
            this.view = view;
            if (viewType == VIEW_TYPE_ITEM) {
                image = (ImageView) view.findViewById(R.id.adapter_brand_img);
                nameTv = (TextView) view.findViewById(R.id.adapter_brand_text);
            } else {
                sectionTextView = (TextView) view.findViewById(R.id.text_section);
                sectionToggleTextView = (TextView) view.findViewById(R.id.text_sectiontoggle);
                sectionToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button_section);
            }
        }
    }
}
