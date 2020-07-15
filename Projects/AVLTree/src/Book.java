public class Book {
    public String ISBN;
    public String title;
    public String authorLastName;

    public Book(String ISBN, String title, String authorLastName) {
        this.ISBN = ISBN;
        this.title = title;
        this.authorLastName = authorLastName;
    }

    public Book(String line) {
        int i1 = line.indexOf(' ');
        int i2 = line.lastIndexOf(' ');

        this.ISBN = line.substring(0, i1);
        this.title = line.substring(i1 + 1, i2);
        this.authorLastName = line.substring(i2 + 1);
    }

    public String asString() {
        return ISBN + " " + title + " " + authorLastName;
    }
}
