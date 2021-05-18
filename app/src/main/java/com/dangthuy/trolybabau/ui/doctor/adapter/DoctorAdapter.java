package com.dangthuy.trolybabau.ui.doctor.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.data.model.Doctor;
import com.dangthuy.trolybabau.databinding.ItemDoctorBinding;

import java.util.List;

/**
 * Created by nhongthai on 5/3/2021.
 */
public class DoctorAdapter extends BaseQuickAdapter<Doctor, BaseViewHolder> {

    public final static int ACTION_VIEW = 0;
    public final static int ACTION_CALL = 1;

    public DoctorAdapter(@Nullable List<Doctor> data) {
        super(R.layout.item_doctor, data);
    }

    public interface IClickItemListener {
        void onClick(Doctor doctor, int action);
    }

    private IClickItemListener listener;

    public void setListener(IClickItemListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Doctor item) {
        ItemDoctorBinding binding = ItemDoctorBinding.bind(helper.itemView);
        Bitmap placeholder = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.doctor_list);
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), placeholder);
        circularBitmapDrawable.setCircular(true);
        final int resourceId = mContext.getResources().getIdentifier(item.getImage(), "drawable", mContext.getPackageName());
        Glide.with(mContext).load(resourceId)
                .error(circularBitmapDrawable)
                .placeholder(circularBitmapDrawable)
                .transform(new CircleCrop())
                .into(binding.ivImage);
        binding.tvName.setText(item.getName());
        binding.tvPhone.setText(item.getPhone());
        binding.tvAddress.setText(item.getAddress());
        binding.tvName.setOnClickListener(view -> listener.onClick(item, ACTION_VIEW));
        binding.tvPhone.setOnClickListener(view -> listener.onClick(item, ACTION_CALL));
        binding.ivPhone.setOnClickListener(view -> listener.onClick(item, ACTION_CALL));
        if (item.getStarRank() > 0) {
            binding.rate.setVisibility(View.VISIBLE);
            binding.rate.setRating(item.getStarRank());
        } else {
            binding.rate.setVisibility(View.GONE);
        }
    }
}
