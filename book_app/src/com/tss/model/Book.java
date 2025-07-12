package com.tss.model;

import java.io.Serializable;

public class Book implements Serializable {
	private int book_id;
	private String name;
	private String author;
	private boolean isIssued;

	public Book(int book_id, String name, String author) {
		this.book_id = book_id;
		this.name = name;
		this.author = author;
		this.isIssued = false;
	}

	public int getBook_id() {
		return book_id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}
}
