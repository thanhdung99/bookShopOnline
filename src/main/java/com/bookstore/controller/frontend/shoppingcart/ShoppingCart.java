package com.bookstore.controller.frontend.shoppingcart;

import com.bookstore.entity.Book;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCart {

    private Map<Book, Integer> items = new HashMap<>();
    private int totalItems;
    private int totalQuantity;
    private double totalAmount;

    public int getTotalItems() {
        return items.size();
    }

    public int getTotalQuantity() {
        int total = 0;
        Iterator<Book> iterator = items.keySet().iterator();
        while (iterator.hasNext()){
            Book next = iterator.next();
            Integer quantity = items.get(next);
            total += quantity;
        }
        return total;
    }

    public double getTotalAmount() {
        double total = 0.0f;
        Iterator<Book> iterator = items.keySet().iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            Integer quantity = items.get(book);
            double subTotal = quantity * book.getPrice();
            total += subTotal;
        }
        return total;
    }

    public Map<Book, Integer> getItems() {
        return items;
    }

    public void addItem(Book book){
        if (items.containsKey(book)){
            Integer quantity = items.get(book) + 1;
            items.put(book, quantity);
        }else {
            items.put(book, 1);
        }
    }

    public void removeItem(Book book){
        items.remove(book);
    }
    public void updateCart(int[] bookIds, int[] quantities){
        for (int i = 0; i < bookIds.length; i++){
            Book key = new Book(bookIds[i]);
            Integer value = quantities[i];
            items.put(key, value);
        }
    }

    public void clear(){
        items.clear();
    }
}
