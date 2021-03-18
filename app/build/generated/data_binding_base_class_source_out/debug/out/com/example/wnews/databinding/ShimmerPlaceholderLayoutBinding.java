// Generated by view binder compiler. Do not edit!
package com.example.wnews.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.wnews.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ShimmerPlaceholderLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView detailNewsItem;

  @NonNull
  public final ImageButton imageButtonLikeItem;

  @NonNull
  public final SimpleDraweeView imageNewsItem;

  @NonNull
  public final TextView titleNewsItem;

  private ShimmerPlaceholderLayoutBinding(@NonNull LinearLayout rootView,
      @NonNull TextView detailNewsItem, @NonNull ImageButton imageButtonLikeItem,
      @NonNull SimpleDraweeView imageNewsItem, @NonNull TextView titleNewsItem) {
    this.rootView = rootView;
    this.detailNewsItem = detailNewsItem;
    this.imageButtonLikeItem = imageButtonLikeItem;
    this.imageNewsItem = imageNewsItem;
    this.titleNewsItem = titleNewsItem;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ShimmerPlaceholderLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ShimmerPlaceholderLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.shimmer_placeholder_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ShimmerPlaceholderLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.detail_news_item;
      TextView detailNewsItem = rootView.findViewById(id);
      if (detailNewsItem == null) {
        break missingId;
      }

      id = R.id.image_button_like_item;
      ImageButton imageButtonLikeItem = rootView.findViewById(id);
      if (imageButtonLikeItem == null) {
        break missingId;
      }

      id = R.id.image_news_item;
      SimpleDraweeView imageNewsItem = rootView.findViewById(id);
      if (imageNewsItem == null) {
        break missingId;
      }

      id = R.id.title_news_item;
      TextView titleNewsItem = rootView.findViewById(id);
      if (titleNewsItem == null) {
        break missingId;
      }

      return new ShimmerPlaceholderLayoutBinding((LinearLayout) rootView, detailNewsItem,
          imageButtonLikeItem, imageNewsItem, titleNewsItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}