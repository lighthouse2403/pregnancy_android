package com.dangthuy.trolybabau.ui.clothes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dangthuy.trolybabau.data.model.Clothes;
import com.dangthuy.trolybabau.data.model.ClothesSection;
import com.dangthuy.trolybabau.databinding.ItemClothesBinding;
import com.dangthuy.trolybabau.databinding.ItemHeaderClothesBinding;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 5/1/2021.
 */
public class HospitalAdapter extends SectioningAdapter {
    private List<ClothesSection> mData = new ArrayList<>();

    public void setData(List<ClothesSection> data) {
        this.mData = data;
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        return new ItemViewHolder((ItemClothesBinding.inflate(LayoutInflater.from(parent.getContext()))).getRoot());
    }


    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        Clothes clothes = mData.get(sectionIndex).getData().get(itemIndex);
        ((ItemViewHolder) viewHolder).bind(clothes);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        return new HeaderViewHolder(ItemHeaderClothesBinding.inflate(LayoutInflater.from(parent.getContext())).getRoot());
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        String header = mData.get(sectionIndex).getSection();
        ((HeaderViewHolder) viewHolder).bind(header);
    }

    @Override
    public int getNumberOfSections() {
        return mData.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mData.get(sectionIndex).getData().size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        private ItemClothesBinding binding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            binding = ItemClothesBinding.bind(itemView);
        }

        public void bind(Clothes clothes) {
            binding.tvName.setText(clothes.getName());
            binding.tvNumber.setText(clothes.getAmount());
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        private ItemHeaderClothesBinding binding;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            binding = ItemHeaderClothesBinding.bind(itemView);
        }

        public void bind(String name) {
            binding.tvTitle.setText(name);
        }
    }
}
