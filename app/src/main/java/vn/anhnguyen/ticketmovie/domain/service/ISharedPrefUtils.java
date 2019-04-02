package vn.anhnguyen.ticketmovie.domain.service;

public interface ISharedPrefUtils {
    void setLoginStatus(Boolean loginStatus);

    Boolean getLoginStatus();

    void setLoginStatusToken(String token);

    String getLoginStatusToken();

    void setUserName(String userName);

    String getUserName();

    String getUserId();

    void setUserId(String userId);

    void setAvatar(String avatar_url);

    String getAvatar();

    void setDisplayName(String displayName);

    String getDisplayName();

    void setPoint(long point);

    long getPoint();
}
