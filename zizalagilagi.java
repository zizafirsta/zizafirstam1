

import java.util.Scanner;

public class ziza {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Data Pengguna
        String adminUsername = "admin";
        String adminPassword = "admin";
        String[] mahasiswaNIMs = {""};

        while (true) {
            System.out.println("library System");
            System.out.println("1.Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. exit");
            System.out.print("Choose option (1-3): ");

            int option = scanner.nextInt();
            scanner.nextLine(); //Membuang karakter newline

            switch (option) {
                case 1:
                    System.out.print("Enter your NIM\t: ");
                    String nim = scanner.next();

                    int digitNim = nim.length();

                    if (digitNim != 15){
                        System.out.println("");
                    } else {
                        System.out.println("Successful Login as Student");
                    }
                    break;

                case 2:
                    System.out.print("Enter your username (admin): ");
                    String adminUserInput = scanner.nextLine();
                    System.out.print("Enter your password (admin): ");
                    String adminPasswordInput = scanner.nextLine();

                    //validasi admin
                    if (adminUserInput.equals(adminUsername) && adminPasswordInput.equals(adminPassword)) {
                        System.out.println("Successfull Login as Admin");
                    } else {
                        System.out.println("Admin User Not Found");

                    }
                    break;

                case 3:
                    System.out.println("Exiting the library system. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 3.");
                    break;
            }
        }
    }

    // Validate NIM length
    private static boolean isValidNIM(String nim) {
        return nim.length() == 15;
    }

    // Check if NIM is in the list of mahasiswaNIMs
    private static boolean isMahasiswa(String nim, String[] mahasiswaNIMs) {
        for (String mahasiswaNIM : mahasiswaNIMs) {
            if (mahasiswaNIM.equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
