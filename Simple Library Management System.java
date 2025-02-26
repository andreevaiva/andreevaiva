import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = {new Book("Wuthering Heights", "Emily Bronte", 1847),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("Emma", "Jane Austen", 1816),
                new Book("Matilda", "Roald Dahl", 1988)};

        System.out.print("Welcome to Iva's Library!\n\nMenu:\n1. Display all books\n2. Update information about a book\n3. Borrow a book\n4. Return a book\n5. Exit\n");

        int input = 0;
        int index=-1;

        do{
            System.out.print("\nEnter code: ");

            input = scanner.nextInt();

            switch(input){
                case 1:
                    displayLibrary(books);
                    break;
                case 2:
                    System.out.print("Enter code of the book you want to update: ");
                    index= scanner.nextInt();
                    System.out.print("Enter new title: ");
                    String newTitle = "";
                    while(newTitle.equals("")) {
                        newTitle = scanner.nextLine();
                    }

                    System.out.print("Enter new author: ");
                    String newAuthor = "";
                    while(newAuthor.equals("")) {
                        newAuthor = scanner.nextLine();
                    }

                    System.out.print("Enter new year: ");
                    int newYear = scanner.nextInt();

                    books[index].updateBookInfo(newTitle, newAuthor, newYear);
                    System.out.println("Book updated successfully!");
                    break;
                case 3:
                    System.out.print("Enter name of the book you want to borrow: ");
                    String nameofBorrow="";
                    while(nameofBorrow.equals("")) {
                        nameofBorrow = scanner.nextLine();
                    }
                    index=findBookByTitle(books, nameofBorrow);
                    if(index==-1) {
                        System.out.println("Book not found!");
                    }else {
                        if(books[index].getBorrowerName().equals(null)) {
                            System.out.print("Enter your name: ");
                            String name = "";
                            while (name.equals("")) {
                                name = scanner.nextLine();
                            }
                            books[index].borrowBook(name);
                            System.out.println("Book borrowed successfully!");
                        }else{
                            System.out.print("This book is already borrowed!\n");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter name of the book you want to return: ");
                    String nameOfReturn="";
                    while(nameOfReturn.equals("")) {
                        nameOfReturn = scanner.nextLine();
                    }

                    index=findBookByTitle(books, nameOfReturn);
                    if(index==-1){
                        System.out.print("Book not found!\n");
                    }else {
                        books[index].returnBook();
                        System.out.println("Book returned successfully!");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");

            }

        }while(input != 5);

    }
    static void displayLibrary(Book[] books){
        for(int i = 0; i < books.length; i++){
            System.out.println((i+1)+". "+books[i].getDetails());
        }
    }

    static int findBookByTitle(Book[] books, String title){
        for(int i = 0; i < books.length; i++){
            if(books[i].getTitle().equals(title)){
                return i;
            }
        }
        return -1;
    }
}

class Book{
    String title;
    String author;
    int yearPublished;
    String borrowerName;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowerName = null;
    }

    public String getDetails(){
        if(borrowerName == null) {
            return title + " by " + author + ", published in " + yearPublished + ".\n";
        }else{
            return title + " by " + author + ", published in " + yearPublished + ". This book is currently borrowed by " + borrowerName + "\n";
        }
    }

    public void updateBookInfo(String newTitle, String newAuthor, int newYearPublished){
        this.title = newTitle;
        this.author = newAuthor;
        this.yearPublished = newYearPublished;
    }

    public void borrowBook(String borrowerName){
        this.borrowerName = borrowerName;
    }

    public void returnBook(){
        this.borrowerName=null;
    }

    public String getTitle(){
        return title;
    }
    
    public String getBorrowerName(){
        return borrowerName;
    }
}
