package com.dangthuy.trolybabau.common.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.dangthuy.trolybabau.R;
import com.dangthuy.trolybabau.common.utils.ToolBarType;
import com.dangthuy.trolybabau.databinding.ToolbarLayoutBinding;

/**
 * Created by nhongthai on 21/03/2021.
 */
public class ToolBar extends ConstraintLayout {

    private ToolbarLayoutBinding binding;

    public enum Item {
        BACK, SAVE, ADD
    }

    private OnItemToolBarClickListener listener;

    public void setListener(OnItemToolBarClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemToolBarClickListener {
        void onItemClick(Item item);
    }

    public ToolBar(Context context) {
        this(context, null);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private OnClickListener onClickListerer = view -> {
        Item item;
        switch (view.getId()) {
            case R.id.btnAdd:
                item = Item.ADD;
                break;
            case R.id.btnSave:
                item = Item.SAVE;
                break;
            case R.id.btnBack:
                item = Item.BACK;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        listener.onItemClick(item);
    };

    private void init(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.toolbar_layout, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setOnClickListener();
    }

    private void setOnClickListener() {
        binding.btnSave.setOnClickListener(onClickListerer);
        binding.btnAdd.setOnClickListener(onClickListerer);
        binding.btnBack.setOnClickListener(onClickListerer);
    }

    public void setLayoutView(ToolBarType type) {
        if (type.equals(ToolBarType.SETUP)) {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnBack.setVisibility(View.GONE);
            binding.btnSave.setVisibility(View.VISIBLE);
            binding.tvTitle.setText(type.getName());
        } else if (type.equals(ToolBarType.BABY_INFO)) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.btnBack.setVisibility(View.VISIBLE);
            binding.btnSave.setVisibility(View.GONE);
            binding.tvTitle.setText(type.getName());
        } else if (type.equals(ToolBarType.BABY_INFO_TAB)) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.btnBack.setVisibility(View.GONE);
            binding.btnSave.setVisibility(View.GONE);
            binding.tvTitle.setText(type.getName());
        } else if(type.equals(ToolBarType.EXPECT)) {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnBack.setVisibility(View.VISIBLE);
            binding.btnSave.setVisibility(View.VISIBLE);
            binding.tvTitle.setText(type.getName());
        } else if (type.equals(ToolBarType.DEFAULT)) {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnBack.setVisibility(View.VISIBLE);
            binding.btnSave.setVisibility(View.GONE);
        }
        else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnBack.setVisibility(View.VISIBLE);
            binding.btnSave.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitle(String title) {
        binding.tvTitle.setText(title);
    }
}
