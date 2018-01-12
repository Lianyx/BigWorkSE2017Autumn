package util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MemberSearchCondition implements Serializable{

    int degree;
    private UserCategory userCategory;

    public MemberSearchCondition() {
    }

    public MemberSearchCondition(int degree, UserCategory userCategory) {
        this.degree = degree;
        this.userCategory = userCategory;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }
}
