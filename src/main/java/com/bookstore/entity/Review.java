package com.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Review {
    private int reviewId;
    private int bookId;
    private int customerId;
    private int rating;
    private String headline;
    private String comment;
    private Timestamp reviewTime;
    private Book bookByBookId;
    private Customer customerByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Basic
    @Column(name = "book_id", nullable = false, insertable = false, updatable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "headline", nullable = false, length = 128)
    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = 500)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "review_time", nullable = false)
    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (reviewId != review.reviewId) return false;
        if (bookId != review.bookId) return false;
        if (customerId != review.customerId) return false;
        if (rating != review.rating) return false;
        if (headline != null ? !headline.equals(review.headline) : review.headline != null) return false;
        if (comment != null ? !comment.equals(review.comment) : review.comment != null) return false;
        if (reviewTime != null ? !reviewTime.equals(review.reviewTime) : review.reviewTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + bookId;
        result = 31 * result + customerId;
        result = 31 * result + rating;
        result = 31 * result + (headline != null ? headline.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (reviewTime != null ? reviewTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }
}
