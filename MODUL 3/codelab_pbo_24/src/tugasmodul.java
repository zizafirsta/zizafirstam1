import java.util.ArrayList;
import java.util.Scanner;

public class tugasmodul{
    private static ArrayList<Book>  bookList = new ArrayList<>();
    private static ArrayList<Student> studentsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Mengisi stok buku
        bookList.add(0, new Book("123", "malioboro at midnight", 41, "hartigan"));
        bookList.add(1, new Book("889", "dago love story", 16, "dago"));
        bookList.add(2, new Book("765", "hello cello", 28, "marcello"));

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Library System Login");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih antara (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan NIM : ");
                    String nimStudent = scanner.next();
                    if (nimStudent.length() != 15) {
                        System.out.println("NIM tidak valid! Harus 15 karakter.");
                        break;
                    }
                    Student student = new Student(nimStudent);
                    student.login();
                    break;
                case 2:
                    Admin admin = new Admin();
                    admin.login();
                    break;
                case 3:
                    System.out.println("Terima kasih semoga puas dengan pelayanan kami");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static abstract class User {
        private String nim;

        public User(String nim) {
            this.nim = nim;
        }

        public String getNim() {
            return nim;
        }

        public abstract void displayBooks();
    }

    public static class Student extends User {
        private String name;
        private String faculty;
        private String studyProgram;
        private ArrayList<Book> borrowedBooks = new ArrayList<>();

        public Student(String nim) {
            super(nim);
        }

        public Student(String nim, String name, String faculty, String studyProgram) {
            super(nim);
            this.name = name;
            this.faculty = faculty;
            this.studyProgram = studyProgram;
        }

        public void login() {
            if (checkNim(getNim())) {
                System.out.println("Login berhasil sebagai Mahasiswa");
                menuStudent();
            } else {
                System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan");
            }
        }

        private boolean checkNim(String nim) {
            return nim.length() == 15;
        }

        private void menuStudent() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Dashboard Mahasiswa");
                System.out.println("1. Tampilkan Buku");
                System.out.println("2. Pinjam Buku");
                System.out.println("3. Kembalikan Buku");
                System.out.println("4. Logout");
                System.out.print("Pilih antara (1-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        logout();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }

        @Override
        public void displayBooks() {
            System.out.println("Daftar Buku Tersedia:");
            for (Book book : bookList) {
                if (book != null) {
                    System.out.println("- " + book.getJudul() + " oleh " + book.getAuthor() + " (Stok: " + book.getStok() + ")");
                }
            }
        }

        private void borrowBook() {
            System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
            String bookId = scanner.next();
            Book selectedBook = findBookById(bookId);
            if (selectedBook != null && selectedBook.getStok() > 0) {
                selectedBook.setStok(selectedBook.getStok() - 1);
                borrowedBooks.add(selectedBook);
                System.out.println("Berhasil meminjam buku: " + selectedBook.getJudul());
            } else {
                System.out.println("Buku tidak tersedia atau ID buku tidak ditemukan");
            }
        }

        private void returnBook() {
            if (borrowedBooks.isEmpty()) {
                System.out.println("Anda belum meminjam buku.");
                return;
            }
            System.out.println("Buku yang Anda pinjam:");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println((i + 1) + ". " + borrowedBooks.get(i).getJudul());
            }
            System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= borrowedBooks.size()) {
                Book returnedBook = borrowedBooks.remove(choice - 1);
                returnedBook.setStok(returnedBook.getStok() + 1);
                System.out.println("Buku " + returnedBook.getJudul() + " berhasil dikembalikan.");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        private Book findBookById(String id) {
            for (Book book : bookList) {
                if (book != null && book.getIdBuku().equals(id)) {
                    return book;
                }
            }
            return null;
        }

        private void logout() {
            System.out.println("Logout berhasil.");
        }

        public String getName() {
            return name;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getStudyProgram() {
            return studyProgram;
        }

        public ArrayList<Book> getBorrowedBooks() {
            return borrowedBooks;
        }
    }

    public static class Admin extends User {
        private ArrayList<Student> studentList = new ArrayList<>();

        public Admin() {
            super("admin");
        }

        public void login() {
            System.out.print("Masukkan Username (admin): ");
            String username = scanner.next();
            System.out.print("Masukkan Password (admin): ");
            String password = scanner.next();
            if (isAdmin(username, password)) {
                System.out.println("Login berhasil sebagai Admin Slot");
                menuAdmin();
            } else {
                System.out.println("User Admin tidak ditemukan");
            }
        }

        private boolean isAdmin(String username, String password) {
            // Implementasi verifikasi admin
            return username.equals("admin") && password.equals("admin");
        }

        private void menuAdmin() {
            while (true) {
                System.out.println("Dashboard Admin");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Tampilkan Mahasiswa");
                System.out.println("3. Input Buku");
                System.out.println("4. Tampilkan Daftar Buku");
                System.out.println("5. Logout");
                System.out.print("Pilih antara (1-5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        inputBook();
                        break;
                    case 4:
                        displayBooks();
                        break;
                    case 5:
                        System.out.println("Logout berhasil.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }

        private void addStudent() {
            // Implementasi penambahan mahasiswa
            System.out.println("Menambahkan mahasiswa...");
            System.out.print("Masukkan Nama: ");
            String name = scanner.next();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.next();
            if (nim.length() != 15) {
                System.out.println("NIM tidak valid! Harus 15 karakter.");
                return;
            }
            System.out.print("Masukkan Fakultas: ");
            String faculty = scanner.next();
            System.out.print("Masukkan Program Studi: ");
            String studyProgram = scanner.next();
            studentList.add(new Student(nim, name, faculty, studyProgram));
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan.");
        }

        private void inputBook() {
            // Implementasi input buku
            System.out.println("Memasukkan buku...");
            System.out.println("Pilih jenis buku:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Pilih jenis buku (1-3): ");
            int bookType = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            String idBuku, judul, author;
            int stok;
            System.out.print("Masukkan judul buku: ");
            judul = scanner.nextLine();
            System.out.print("Masukkan author buku: ");
            author = scanner.nextLine();
            System.out.print("Masukkan stok buku: ");
            stok = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            switch (bookType) {
                case 1:
                    idBuku = generateId("HB");
                    bookList.add(new HistoryBook(idBuku, judul, stok, author));
                    break;
                case 2:
                    idBuku = generateId("SB");
                    bookList.add(new StoryBook(idBuku, judul, stok, author));
                    break;
                case 3:
                    idBuku = generateId("TB");
                    bookList.add(new TextBook(idBuku, judul, stok, author));
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    return;
            }
            System.out.println("Buku berhasil ditambahkan.");
        }

        @Override
        public void displayBooks() {
            // Implementasi menampilkan daftar buku
            System.out.println("Daftar Buku Tersedia:");
            for (Book book : bookList) {
                if (book != null) {
                    System.out.println("- " + book.getJudul() + " oleh " + book.getAuthor() + " (Stok: " + book.getStok() + ")");
                }
            }
        }

        private void displayStudents() {
            // Implementasi menampilkan daftar mahasiswa
            System.out.println("Daftar Mahasiswa yang terdaftar:");
            for (Student student : studentList) {
                System.out.println("Nama: " + student.getName());
                System.out.println("NIM: " + student.getNim());
                System.out.println("Fakultas: " + student.getFaculty());
                System.out.println("Program Studi: " + student.getStudyProgram());
                if (!student.getBorrowedBooks().isEmpty()) {
                    System.out.println("  Meminjam Buku:");
                    for (Book book : student.getBorrowedBooks()) {
                        System.out.println("    - " + book.getJudul());
                    }
                }
            }
        }

        private String generateId(String prefix) {
            // Implementasi pembuatan ID unik
            // Contoh: HB001, SB002, TB003, dst.
            int nextId = bookList.size() + 1;
            return prefix + String.format("%03d", nextId);
        }
    }

    public static class Book {
        private String idBuku;
        private String judul;
        private int stok;
        private String author;

        public Book(String idBuku, String judul, int stok, String author) {
            this.idBuku = idBuku;
            this.judul = judul;
            this.stok = stok;
            this.author = author;
        }

        public String getIdBuku() {
            return idBuku;
        }

        public String getJudul() {
            return judul;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getAuthor() {
            return author;
        }
    }

    public static class HistoryBook extends Book {
        public HistoryBook(String idBuku, String judul, int stok, String author) {
            super(idBuku, judul, stok, author);
        }
    }

    public static class StoryBook extends Book {
        public StoryBook(String idBuku, String judul, int stok, String author) {
            super(idBuku, judul, stok, author);
        }
    }

    public static class TextBook extends Book {
        public TextBook(String idBuku, String judul, int stok, String author) {
            super(idBuku, judul, stok, author);
        }
    }
}




abstract class User {
    private String nim;

    public User(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public abstract void displayBooks(tugasmodul[] bookList);

    public abstract void displayBooks(ArrayList<tugasmodul.Book>bookList);
}