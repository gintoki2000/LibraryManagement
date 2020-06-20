/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.models;

import com.librarymanagement.entities.Book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author ngotrung
 */
public class BookTableModel implements TableModel{
    
    private final Class[] classes = new Class[]{String.class, String.class, String.class, String.class, String.class};
    private final String[] columnNames = new String[]{"BookID", "Title", "Author", "Category", "Keyword"};
    private List<Book> books;
    private final List<TableModelListener> listeners;
    
    public BookTableModel() {
        books = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyChanged();
    }
    
    public void setBookAt(int row, Book book) {
        books.set(row, book);
        notifyChanged(row);
    }
    public static final int BOOKID = 0;
    public static final int TITLE = 1;
    public static final int AUTHOR = 2;
    public static final int CATEGORY = 3;
    public static final int KEYWORD = 4;
    public static final int COLUMN_COUNT  = 5;

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return classes[i];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Book book = books.get(r);
        switch (c) {
            case BOOKID:
                return book.getId();
            case TITLE:
                return book.getTitle();
            case AUTHOR:
                return book.getAuthor();
            case CATEGORY:
                return book.getCategory();
            case KEYWORD:
                return book.getKeyword();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (!(classes[col].isInstance(value))) {
            throw new IllegalArgumentException();
        }
        Book book = books.get(row);
        switch(col) {
            case BOOKID:
                book.setId((String) value);
                notifyChanged(row);
                break;
            case TITLE:
                book.setTitle((String) value);
                notifyChanged(row);
                break;
            case AUTHOR:
                book.setAuthor((String) value);
                notifyChanged(row);
                break;
            case CATEGORY:
                book.setCategory((String) value);
                notifyChanged(row);
                break;
            case KEYWORD:
                book.setKeyword((String) value);
                notifyChanged(row);
                break;
        }
        
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    private void notifyChanged(int row) {
        TableModelEvent event = new TableModelEvent(this, row);
        listeners.forEach((listener) -> {
            listener.tableChanged(event);
        });
    }

    private void notifyChanged() {
       TableModelEvent event = new TableModelEvent(this);
       listeners.forEach(listener -> {
           listener.tableChanged(event);
       });
    }

   
}
