package com.example.ahmad.w.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

/**
 * Created by ahmad on 23/07/17.
 */


@Table(name = "User")
public class User extends Model implements Parcelable {

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Password")
    private String password;


    public User() {
        super();
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        gender = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String name, String email, String phone, String gender, String pass) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(gender);
        dest.writeString(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private static List<User> getAllUser() {
        return new Select().from(User.class).execute();
    }

    public static void updateUser(long id, User user) {
        new Update(User.class).set("Name = ?, Email = ?, Phone = ?, Gender = ?, Password = ?",
                user.getName(), user.getEmail(), user.getPhone(), user.getGender(),
                user.getPassword())
                .where("Id = ?", id).execute();
    }

    public static boolean checkUser(String email, String password) {
        User user = new Select()
                .from(User.class)
                .where("Email = '" + email + "' AND Password = '" + password + "'")
                .executeSingle();
        if (user != null) return true;
        else return false;
    }

    public static User getUserLogin(String email, String password) {
        User user = new Select()
                .from(User.class)
                .where("Email = '" + email + "' AND Password = '" + password + "'")
                .executeSingle();
        return user;
    }
}
