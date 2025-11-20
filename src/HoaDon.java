import java.util.Scanner;

public class HoaDon {
    private String mahoadon;
    private String makhachhang;
    private String ngaylap;
    private double tongtien;

    public String getMaHoaDon() { return mahoadon; }
    public void setMaHoaDon(String maHoaDon) { this.mahoadon = maHoaDon; }
    public String getMaKhachHang() { return makhachhang; }
    public void setMaKhachHang(String maKhachHang) { this.makhachhang = maKhachHang; }
    public String getNgayLap() { return ngaylap; }
    public void setNgayLap(String ngayLap) { this.ngaylap = ngayLap; }
    public double getTongTien() { return tongtien; }
    public void setTongTien(double tongTien) { this.tongtien = tongTien; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma hoa don: ");
        this.mahoadon = sc.nextLine();
        System.out.print("Ma khach hang: ");
        this.makhachhang = sc.nextLine();
        System.out.print("Ngay lap: ");
        this.ngaylap = sc.nextLine();
        System.out.print("Tong tien: ");
        try {
            this.tongtien = Double.parseDouble(sc.nextLine());
        } catch (Exception ignored) {}
    }

    public void xuat() {
        System.out.printf("%s | KH:%s | Ngay:%s | Tong:%.2f%n",
                mahoadon, makhachhang, ngaylap, tongtien);
    }
}


