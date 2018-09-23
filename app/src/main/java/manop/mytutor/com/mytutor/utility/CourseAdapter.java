package manop.mytutor.com.mytutor.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import manop.mytutor.com.mytutor.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private Context context;
    private ArrayList<String> nameCourseStringArrayList, periodStringArrayList,
            contentStringArrayList;
    private LayoutInflater layoutInflater;
    private OnClickItem onClickItem;

    public CourseAdapter(Context context, ArrayList<String> nameCourseStringArrayList,
                         ArrayList<String> periodStringArrayList,
                         ArrayList<String> contentStringArrayList,
                         OnClickItem onClickItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.nameCourseStringArrayList = nameCourseStringArrayList;
        this.periodStringArrayList = periodStringArrayList;
        this.contentStringArrayList = contentStringArrayList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_view_course, parent, false);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {

        String nameCourse = nameCourseStringArrayList.get(position);
        String periodString = periodStringArrayList.get(position);
        String contentString = contentStringArrayList.get(position);

        holder.nameCourseTextView.setText(nameCourse);
        holder.periodTextView.setText(periodString);
        holder.contentTextView.setText(contentString);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickItem(v, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameCourseStringArrayList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        private TextView nameCourseTextView, periodTextView, contentTextView;

        public CourseViewHolder(View itemView) {
            super(itemView);

            nameCourseTextView = itemView.findViewById(R.id.txtNameCourse);
            periodTextView = itemView.findViewById(R.id.txtPeriod);
            contentTextView = itemView.findViewById(R.id.txtContent);
        }
    }




}
