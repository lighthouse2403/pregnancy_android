package com.dangthuy.trolybabau.common.customview;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BetweenSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "BetweenSpacesItem";
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int GRID = 2;

    private final int mVerticalSpace;
    private final int mHorizontalSpace;

    public BetweenSpacesItemDecoration(final int verticalSpace, final int horizontalSpace) {
        mVerticalSpace = dpToPixels(verticalSpace);
        mHorizontalSpace = dpToPixels(horizontalSpace);
    }

    @Override
    public void getItemOffsets(@NonNull final Rect outRect, @NonNull final View view, @NonNull final RecyclerView parent, @NonNull final RecyclerView.State state) {
        final int position = parent.getChildViewHolder(view).getAdapterPosition();
        final int itemCount = state.getItemCount();
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager != null) {
            setSpacingForDirection(outRect, layoutManager, position, itemCount);
        } else {
            Log.d(TAG, "getItemOffsets: layoutManager null");
        }
    }

    private void setSpacingForDirection(@NonNull final Rect outRect,
                                        @NonNull final RecyclerView.LayoutManager layoutManager,
                                        final int position,
                                        final int itemCount) {
        final int displayMode = resolveDisplayMode(layoutManager);

        switch (displayMode) {
            case HORIZONTAL:
                outRect.left = 0;
                outRect.right = position == itemCount - 1 ? 0 : mHorizontalSpace;
                outRect.top = 0;
                outRect.bottom = 0;
                break;
            case VERTICAL:
                outRect.left = 0;
                outRect.right = 0;
                outRect.top = 0;
                outRect.bottom = position == itemCount - 1 ? 0 : mVerticalSpace;
                break;
            case GRID:
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                final int cols = gridLayoutManager.getSpanCount();
                final int rows = itemCount / cols;

                outRect.left = position % cols == 0 ? 0 : mHorizontalSpace / 2;
                outRect.right = (position + 1) % cols == 0 ? 0 : mHorizontalSpace / 2;
                outRect.top = position < cols ? 0 : mVerticalSpace / 2;
                outRect.bottom = position / cols == rows ? 0 : mVerticalSpace / 2;
                break;
        }
    }

    private int resolveDisplayMode(final RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            return GRID;
        }
        if (layoutManager.canScrollHorizontally()) {
            return HORIZONTAL;
        }
        return VERTICAL;
    }

    public static int dpToPixels(final float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}