package DataBase_Library_Management;

import java.util.LinkedList;

public class Book {
  private String ISBN;
  private String Title;
  private int quantity;
  private String publisher;
  private LinkedList<String> linkedlist = new LinkedList<String>();
  private String price;
  private String catalog;
  private String year;
  public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getCatalog() {
	return catalog;
}
public void setCatalog(String catalog) {
	this.catalog = catalog;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getISBN() {
	return ISBN;
}
public void setISBN(String iSBN) {
	ISBN = iSBN;
}
public String getTitle() {
	return Title;
}
public void setTitle(String title) {
	Title = title;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public LinkedList<String> getAuthors() {
	return linkedlist;
}
public void addAuthor(String element) {
	this.linkedlist.add(element);
}
}
