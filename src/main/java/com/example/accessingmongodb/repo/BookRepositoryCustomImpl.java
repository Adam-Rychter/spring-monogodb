package com.example.accessingmongodb.repo;

import com.example.accessingmongodb.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRepositoryCustomImpl implements  BookRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> query(DynamicQuery dynamicQuery) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if(dynamicQuery.getAuthorNameLike() != null) {
            criteria.add(Criteria.where("authorNames").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
                    dynamicQuery.getAuthorNameLike(), MongoRegexCreator.MatchMode.CONTAINING
            ), "i"));
        }
        if(dynamicQuery.getPublishDateBefore() != null) {
            criteria.add(Criteria.where("publishDate").lte(dynamicQuery.getPublishDateBefore()));
        }
        if(dynamicQuery.getPublishDateAfter() != null) {
            criteria.add(Criteria.where("publishDate").gte(dynamicQuery.getPublishDateAfter()));
        }
        if(dynamicQuery.getSubject() != null) {
            criteria.add(Criteria.where("subjects").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
                    dynamicQuery.getSubject(), MongoRegexCreator.MatchMode.LIKE
            ), "i"));
        }
        if(!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Book.class);
    }

    public class DynamicQuery {

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

        String getAuthorNameLike() {
            return authorNameLike;
        }

        public void setAuthorNameLike(String authorNameLike) {
            this.authorNameLike = authorNameLike;
        }

        Date getPublishDateBefore() {
            return publishDateBefore;
        }

        public void setPublishDateBefore(Date publishDateBefore) {
            this.publishDateBefore = publishDateBefore;
        }

        Date getPublishDateAfter() {
            return publishDateAfter;
        }

        public void setPublishDateAfter(Date publishDateAfter) {
            this.publishDateAfter = publishDateAfter;
        }

        String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
}
