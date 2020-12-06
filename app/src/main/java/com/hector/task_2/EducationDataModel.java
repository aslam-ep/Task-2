package com.hector.task_2;

import android.os.Parcel;
import android.os.Parcelable;

public class EducationDataModel implements Parcelable {
    String education, school;
    boolean present;

    public EducationDataModel(){}

    public EducationDataModel(String education, String school, boolean present) {
        this.education = education;
        this.school = school;
        this.present = present;
    }

    protected EducationDataModel(Parcel in) {
        education = in.readString();
        school = in.readString();
        present = in.readByte() != 0;
    }

    public static final Creator<EducationDataModel> CREATOR = new Creator<EducationDataModel>() {
        @Override
        public EducationDataModel createFromParcel(Parcel in) {
            return new EducationDataModel(in);
        }

        @Override
        public EducationDataModel[] newArray(int size) {
            return new EducationDataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(education);
        dest.writeString(school);
        dest.writeByte((byte) (present ? 1 : 0));
    }
}
