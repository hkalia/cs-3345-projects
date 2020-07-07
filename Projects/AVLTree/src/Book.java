
public class Book implements Comparable<Book> {
	private String ISBN;
	private String lexVal;
	private String bookTitle;
	private String authorLastName;
	
	Book(String isbn, String title, String lastName) {
		ISBN = isbn;
		bookTitle = title;
		authorLastName = lastName;
		lexVal = ISBN.replaceAll("[^0-9]", "");			
	}
	
	// Compares ISBN lexicographically 
	//		if this.ISBN > book.ISBN   -> return +integer
	//		if this.ISBN < book.ISBN   -> return -integer
	//		if this.ISBN = book.ISBN   -> return  0
	@Override
	public int compareTo(Book book) {
		return this.getLexVal().compareTo(book.getLexVal());
	}
	
	// Getter methods 
	public String getISBN() {
		return ISBN;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	
	public String getAuthorName() {
		return authorLastName;
	}
	
	public String getLexVal() {
		return lexVal;
	}
}

