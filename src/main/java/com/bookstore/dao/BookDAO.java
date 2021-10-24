package com.bookstore.dao;

import com.bookstore.entity.Book;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book>{
    public BookDAO() {

    }
    @Override
    public Book create(Book book) {
        Date date = new Date();
        book.setLastUpdateTime(new Timestamp(date.getTime()));
        return super.create(book);
    }

    @Override
    public Book update(Book book) {
        return super.update(book);
    }

    @Override
    public Book get(Object bookId) {
        Book book = super.find(Book.class, bookId);
        book.setAverageRating();
        book.setRatingStars();
        book.setRatingPercentArr();
        return book;
    }

    @Override
    public void delete(Object bookId) {
        super.delete(Book.class, bookId);
    }

    @Override
    public List<Book> listAll() {
        return super.findWithNamedQuery("Book.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Book.countAll");
    }

    public long countByKeyword(String keyword){
        return super.countWithNamedQuery("Book.countByKeyword","keyword", keyword);
    }

    public long countByCategory(int categoryId){
        return super.countWithNamedQuery("Book.countByCategory","categoryId",categoryId);
    }

    public Book findByTitle(String title){
        List<Book> result = super.findWithNamedQuery("Book.findByTitle","title",title);
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }

    public List<Book> search(String keyword, int page, int limit){
        return super.findWithNamedQuery("Book.search","keyword",keyword, page, limit);
    }

    public List<Book> listNewBooks(){
        return super.findWithNamedQuery("Book.listNew",0, 4);
    }

    public List<Book> listBooksByCategory(int categoryId, int page, int limit) {
        return super.findWithNamedQuery("Book.findByCategory", "categoryId", categoryId, page, limit);
    }

}
