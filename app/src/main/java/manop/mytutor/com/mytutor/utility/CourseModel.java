package manop.mytutor.com.mytutor.utility;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseModel implements Parcelable{

    private String Subject, Period, Content, Source;

    public CourseModel() {
    }

    public CourseModel(String subject, String period, String content, String source) {
        Subject = subject;
        Period = period;
        Content = content;
        Source = source;
    }

    protected CourseModel(Parcel in) {
        Subject = in.readString();
        Period = in.readString();
        Content = in.readString();
        Source = in.readString();
    }

    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>() {
        @Override
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Subject);
        dest.writeString(Period);
        dest.writeString(Content);
        dest.writeString(Source);
    }
}
