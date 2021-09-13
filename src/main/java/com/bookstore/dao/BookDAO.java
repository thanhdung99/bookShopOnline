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
        return super.find(Book.class, bookId);
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

    public List<Book> search(String keyword){
        return super.findWithNamedQuery("Book.search","keyword",keyword);
    }

    public List<Book> listNewBooks(){
        return super.findWithNamedQuery("Book.listNew",0, 4);
    }

    public List<Book> listBooksByCategory(int categoryId) {
        return super.findWithNamedQuery("Book.findByCategory", "categoryId", categoryId);
    }


}
