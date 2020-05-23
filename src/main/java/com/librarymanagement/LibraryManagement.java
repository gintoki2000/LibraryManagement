package com.librarymanagement;

import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.utils.ComponenContainer;

/**
 * Hello world!
 *
 */


public class LibraryManagement {



	public LibraryManagement() {
		ComponenContainer container = new ComponenContainer(Context.class);
		BookRepository bookRepo = (BookRepository) container.getComponent(BookRepository.class);
		System.out.println(container.getComponents());
		System.out.println(bookRepo.getAllBooks());
		System.out.println(bookRepo.searchByTitle("Lập trình Java"));
		System.out.println(bookRepo.getBook("B0002"));
	}
	public static void main(String[] args) {
		LibraryManagement libraryManagement = new LibraryManagement();
	}
}
