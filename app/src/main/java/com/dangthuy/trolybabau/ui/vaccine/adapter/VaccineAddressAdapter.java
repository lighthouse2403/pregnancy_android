package com.dangthuy.trolybabau.ui.vaccine.adapter;

import android.view.LayoutInflater;
import android.view.View;
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
    private List<VaccineAddressSection> mData = new ArrayList<>();

    public void setData(List<VaccineAddressSection> mData) {
        this.mData = mData;
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        return new ItemViewHolder((ItemVaccineAddressBinding.inflate(LayoutInflater.from(parent.getContext()))).getRoot());
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        VaccineAddress address = mData.get(sectionIndex).getAddresses().get(itemIndex);
        ((ItemViewHolder) viewHolder).bind(address);
    }


    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        return new HeaderViewHolder((ItemHeaderVaccineAddressBinding.inflate(LayoutInflater.from(parent.getContext()))).getRoot());
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

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        private ItemVaccineAddressBinding itemBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemBinding = ItemVaccineAddressBinding.bind(itemView);
        }

        public void bind(VaccineAddress item) {
            itemBinding.tvName.setText(item.getName());
            itemBinding.tvPlace.setText(item.getAddress());
            itemBinding.tvPhone.setText(item.getPhone());
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        private ItemHeaderVaccineAddressBinding headerBinding;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerBinding = ItemHeaderVaccineAddressBinding.bind(itemView);
        }

        public void bind(String name) {
            headerBinding.tvName.setText(name);
        }
    }
}
