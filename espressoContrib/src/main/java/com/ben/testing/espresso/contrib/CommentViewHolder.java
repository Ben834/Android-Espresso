package com.ben.testing.espresso.contrib;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ben.espresso_contrib.R;

/**
 * The {@link RecyclerView} displaying the comments for a record.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    public TextView name, content;
    public View container;

    public CommentViewHolder(View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.row_comment_layout);
        name = (TextView) itemView.findViewById(R.id.row_comment_user_name);
        content = (TextView) itemView.findViewById(R.id.row_comment_content);
    }
}
