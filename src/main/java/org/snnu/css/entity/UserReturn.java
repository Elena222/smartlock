package org.snnu.css.entity;

/**
 * Created by Dante on 2017/1/14.
 */
public class UserReturn {
   private int userId;
   private String Type;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "UserReturn{" +
                "userId=" + userId +
                ", Type='" + Type + '\'' +
                '}';
    }
}
