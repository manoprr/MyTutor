package manop.mytutor.com.mytutor.utility;

import android.os.Parcel;
import android.os.Parcelable;

public class BannnerModel implements Parcelable {
    private String Image;
    private String uidString;

    public BannnerModel() {
    }

    public BannnerModel(String image, String uidString) {
        Image = image;
        this.uidString = uidString;
    }

    protected BannnerModel(Parcel in) {
        Image = in.readString();
        uidString = in.readString();
    }

    public static final Creator<BannnerModel> CREATOR = new Creator<BannnerModel>() {
        @Override
        public BannnerModel createFromParcel(Parcel in) {
            return new BannnerModel(in);
        }

        @Override
        public BannnerModel[] newArray(int size) {
            return new BannnerModel[size];
        }
    };

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUidString() {
        return uidString;
    }

    public void setUidString(String uidString) {
        this.uidString = uidString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Image);
        dest.writeString(uidString);
    }
}