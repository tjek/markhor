/*******************************************************************************
 * Copyright 2015 ShopGun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.shopgun.android.utils.recyclerview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewObserver extends RecyclerView.OnScrollListener {

    public static final String TAG = RecyclerViewObserver.class.getSimpleName();

    public enum Direction {
        NONE, UP, DOWN
    }

    final OnRecyclerViewScroller mSlider;

    public RecyclerViewObserver(OnRecyclerViewScroller slider) {
        this.mSlider = slider;
    }

    Direction mDirection = Direction.NONE;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_DRAGGING:
                mDirection = Direction.NONE;
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                if (mDirection == Direction.NONE && isEverythingVisible(recyclerView)) {
                    mSlider.onScrollDown();
                } else if (mDirection == Direction.UP) {
                    mSlider.onScrollUp();
                } else if (mDirection == Direction.DOWN) {
                    mSlider.onScrollDown();
                }
                break;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mDirection = dy > 0 ? Direction.UP : (dy == 0 ? Direction.NONE : Direction.DOWN);
    }

    private boolean isEverythingVisible(RecyclerView recyclerView) {
        LinearLayoutManager llm = (LinearLayoutManager)recyclerView.getLayoutManager();
        int scrollY = recyclerView.getScrollY();
        int first = llm.findFirstCompletelyVisibleItemPosition();
        int lastPos = llm.findLastCompletelyVisibleItemPosition();
        int itemCount = recyclerView.getAdapter().getItemCount();
        return scrollY == 0 && first == 0 && lastPos == itemCount-1;
    }

    public interface OnRecyclerViewScroller {
        void onScrollDown();
        void onScrollUp();
    }

}
