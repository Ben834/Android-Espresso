package com.example.ben.espresso_contrib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link android.support.v7.widget.RecyclerView.Adapter} for displaying an artist.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private List<Comment> mComments;
    private OnItemClickListener mOnItemClickListener;

    public CommentAdapter(OnItemClickListener listener, List<Comment> comments) {
        mComments = comments;
        mOnItemClickListener = listener;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_comment,
                parent, false));
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, final int position) {
        Comment comment = mComments.get(position);
        holder.name.setText(comment.getName());
        holder.content.setText(comment.getDescription());
        if(mOnItemClickListener != null){
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
