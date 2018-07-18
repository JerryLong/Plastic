package com.conduit.plastic.adapter.expandgrid;


import com.conduit.plastic.entity.BrandEntity;

public interface ItemClickListener {
    void itemClicked(BrandEntity item);
    void itemClicked(Section section);
}
