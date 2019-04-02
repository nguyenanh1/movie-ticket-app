package vn.anhnguyen.ticketmovie.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vn.anhnguyen.ticketmovie.config.AppConfig;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class SharePrefUtils implements ISharedPrefUtils {

    private final static String PREF_LOGIN_STATUS = "login status";
    private final static String PREF_TOKEN = "token";
    private final static String PREF_USER_ID = "user id";
    private final static String PREF_USER_NAME = "user-name";
    private final static String PREF_AVATAR = "avatar";
    private final static String PREF_DISPLAY_NAME = "display-name";
    private final static String PREF_POINT = "point";

    @SuppressLint("StaticFieldLeak")
    private static SharePrefUtils sInstance;
    private Context mContext;

    public static SharePrefUtils instance() {
        if (sInstance == null)
            sInstance = new SharePrefUtils();

        return sInstance;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public void setNotifyCount(int notifyCount) {
        if (mContext == null)
            return;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConfig.NOTIFY_COUNT, notifyCount);
        editor.apply();
    }

    public int getNotifyCount() {
        if (mContext == null)
            return 0;
        return PreferenceManager.getDefaultSharedPreferences(mContext).getInt(AppConfig.NOTIFY_COUNT, 0);
    }

    //PREF_LOGIN_STATUS
    @Override
    public void setLoginStatus(Boolean loginStatus) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_LOGIN_STATUS, loginStatus);
        editor.apply();
    }

    @Override
    public Boolean getLoginStatus() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getBoolean(PREF_LOGIN_STATUS, false);
    }

    // PREF_TOKEN
    @Override
    public void setLoginStatusToken(String token) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_TOKEN, token);
        editor.apply();
    }

    @Override
    public String getLoginStatusToken() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_TOKEN, null);
    }

    // UserId
    @Override
    public void setUserId(String userId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_USER_ID, userId);
        editor.apply();
    }

    @Override
    public String getUserId() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_USER_ID, null);
    }

    // Username
    @Override
    public void setUserName(String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    @Override
    public String getUserName() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_USER_NAME, "");
    }

    // Avatar
    @Override
    public void setAvatar(String avatar_url) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_AVATAR, avatar_url);
        editor.apply();
    }

    @Override
    public String getAvatar() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_AVATAR, "");
    }

    // Display name
    @Override
    public void setDisplayName(String displayName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_DISPLAY_NAME, displayName);
        editor.apply();
    }

    @Override
    public String getDisplayName() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getString(PREF_DISPLAY_NAME, "");
    }

    // Point
    @Override
    public void setPoint(long point) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(PREF_POINT, point);
        editor.apply();
    }

    @Override
    public long getPoint() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getLong(PREF_POINT, 0);
    }
}
