package com.dangthuy.trolybabau.ui.vaccine.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dangthuy.trolybabau.data.model.VaccineAddress;
import com.dangthuy.trolybabau.data.model.VaccineAddressSection;
import com.dangthuy.trolybabau.data.response.VaccineAddressResponse;
import com.dangthuy.trolybabau.databinding.ItemHeaderVaccineAddressBinding;
import com.dangthuy.trolybabau.databinding.ItemVaccineAddressBinding;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhongthai on 4/25/2021.
 */
public class VaccineAddressAdapter extends SectioningAdapter {
    private ItemVaccineAddressBinding itemBinding;
    private ItemHeaderVaccineAddressBinding headerBinding;

    private List<VaccineAddressSection> mData = new ArrayList<>();

    public void setData(List<VaccineAddressSection> mData) {
        this.mData = mData;
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        itemBinding = ItemVaccineAddressBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ItemViewHolder(itemBinding.getRoot());
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        VaccineAddress address = mData.get(sectionIndex).getAddresses().get(itemIndex);
        itemBinding.tvName.setText(address.getName());
        itemBinding.tvPlace.setText(address.getAddress());
        itemBinding.tvPhone.setText(address.getPhone());
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        headerBinding = ItemHeaderVaccineAddressBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new HeaderViewHolder(headerBinding.getRoot());
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        String header = mData.get(sectionIndex).getSection();
        headerBinding.tvName.setText(header);
    }

    @Override
    public int getNumberOfSections() {
        return mData.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mData.get(sectionIndex).getAddresses().size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }
}
