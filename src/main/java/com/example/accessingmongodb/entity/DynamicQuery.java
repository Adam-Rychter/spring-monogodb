package com.example.accessingmongodb.entity;

import java.util.Date;

class DynamicQuery {

    private String authorNameLike;
    private Date publishDateBefore;
    private Date publishDateAfter;
    private String subject;

    public DynamicQuery() {
    }

    public DynamicQuery(String authorNameLike, Date publishDateBefore, Date publishDateAfter, String subject) {
        this.authorNameLike = authorNameLike;
        this.publishDateBefore = publishDateBefore;
        this.publishDateAfter = publishDateAfter;
        this.subject = subject;
    }

    public String getAuthorNameLike() {
        return authorNameLike;
    }

    public void setAuthorNameLike(String authorNameLike) {
        this.authorNameLike = authorNameLike;
    }

    public Date getPublishDateBefore() {
        return publishDateBefore;
    }

    public void setPublishDateBefore(Date publishDateBefore) {
        this.publishDateBefore = publishDateBefore;
    }

    public Date getPublishDateAfter() {
        return publishDateAfter;
    }

    public void setPublishDateAfter(Date publishDateAfter) {
        this.publishDateAfter = publishDateAfter;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
