import java.util.Scanner;

public class HangSanPham {
    private String mahang;
    private String tenhang;
    private String quocgia;

    public HangSanPham() {}

    public HangSanPham(String mahang, String tenhang, String quocgia) {
        this.mahang = mahang;
        this.tenhang = tenhang;
        this.quocgia = quocgia;
    }

    public HangSanPham(HangSanPham hsx) {
        this.mahang = hsx.mahang;
        this.tenhang = hsx.tenhang;
        this.quocgia = hsx.quocgia;
    }

    public String getMaHang() { return mahang; }
    public void setMaHang(String maHang) { this.mahang = maHang; }

    public String getTenHang() { return tenhang; }
    public void setTenHang(String tenHang) { this.tenhang = tenHang; }

    public String getQuocGia() { return quocgia; }
    public void setQuocGia(String quocGia) { this.quocgia = quocGia; }

    public void nhap() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma hang: ");
        this.mahang = sc.nextLine().trim();

        System.out.print("Ten hang: ");
        this.tenhang = sc.nextLine().trim();

        System.out.print("Quoc gia: ");
        this.quocgia = sc.nextLine().trim();
    }

    public void xuat() {
        System.out.printf("[Hang:%s] %s | %s\n", mahang, tenhang, quocgia);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", mahang, tenhang, quocgia);
    }
}


