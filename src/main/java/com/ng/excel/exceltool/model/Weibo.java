package com.ng.excel.exceltool.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Weibo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer weiboId;

    private Integer userId;

    private String weiboText;

    private Date createDate;

    @Transient
    private List<Comments> commentsList;

    @Transient
    private List<Fans> fansList;

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWeiboText() {
        return weiboText;
    }

    public void setWeiboText(String weiboText) {
        this.weiboText = weiboText;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public List<Fans> getFansList() {
        return fansList;
    }

    public void setFansList(List<Fans> fansList) {
        this.fansList = fansList;
    }
}
