package com.librarymanagement.entities;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String id;
    private String title;
    private String author;
    private boolean status;
    private String keyword;
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return isStatus() == book.isStatus() &&
                Objects.equals(getId(), book.getId()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getKeyword(), book.getKeyword()) &&
                Objects.equals(getCategory(), book.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), isStatus(), getKeyword(), getCategory());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", keyword='" + keyword + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
