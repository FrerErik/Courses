public class book {
  private String title;
  private String author;
  private Patron patron;
  
  public Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.patron = null;
  }
  public Patron showPatron() {
    return patron;
  }

  public void addPatron(Patron p) {
    this.patron = p;
  }

  public String getTitle() {
    return title;
  }

}