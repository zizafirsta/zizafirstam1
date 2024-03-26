import java.util.Scanner;

public class Balok extends BangunRuang{
    private double panjang, lebar, tinggi;
    Scanner scanner = new Scanner(System.in);

    Balok(String name) {
        super(name);
    }

    @Override
    public void inputNilai(){
        super.inputNilai();
        System.out.print("Masukkan nilai panjang:");
        panjang = scanner.nextDouble();
        System.out.print("Masukkan nilai lebar:");
        lebar = scanner.nextDouble();
        System.out.print("Masukkan nilai tinggi:");
        tinggi = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan() {
        double hasil = 2 * ((panjang*lebar)+ (panjang*tinggi) + (lebar*tinggi));
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume() {
        double hasil = panjang * lebar * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}
