package com.librarymanagement.views;

import com.librarymanagement.entities.Book;
import com.librarymanagement.entities.Category;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class TestView extends JPanel {
    private JTable table;
    private BookTableModel bookTableModel;


    public TestView() {
        bookTableModel = new BookTableModel();
        table = new JTable(bookTableModel);
        List<Book> books = new ArrayList<>();
        Book book = null;
        books.add(book = new Book());
        bookTableModel.setBooks(books);
        book.setTitle("fuck you");
        book.setCategory(new Category("", "18+"));
        add(table);
    }
}

class BookTableModel implements TableModel{
    private final Class[] classes = new Class[] {
            String.class, String.class, String.class, String.class, String.class
    };

    private final String[] columnNames = new String[] {
            "BookID", "Title", "Author", "Category", "Keyword"
    };
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private final static int BOOKID = 0;
    private final static int TITLE= 1;
    private final static int AUTHOR= 2;
    private final static int CATEGORY= 3;
    private final static int KEYWORD = 4;

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
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
        switch(c) {
            case BOOKID:
                return book.getId();
            case TITLE:
                return book.getTitle();
            case AUTHOR:
                return book.getAuthor();
            case CATEGORY:
                return book.getCategory().getName();
            case KEYWORD:
                return book.getKeyword();
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }
}
