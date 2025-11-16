import java.util.Scanner;

public class ChiTietNhapHang {
    private String machitietnhaphang;
    private String masanpham;
    private int soluong;
    private double dongia;
    private double thanhtien;

    public String getMaChiTietNhapHang() { return machitietnhaphang; }
    public void setMaChiTietNhapHang(String maChiTietNhapHang) { this.machitietnhaphang = maChiTietNhapHang; }
    public String getMaSanPham() { return masanpham; }
    public void setMaSanPham(String maSanPham) { this.masanpham = maSanPham; }
    public int getSoLuong() { return soluong; }
    public void setSoLuong(int soLuong) { this.soluong = soLuong; }
    public double getDonGia() { return dongia; }
    public void setDonGia(double donGia) { this.dongia = donGia; }
    public double getThanhTien() { return thanhtien; }
    public void setThanhTien(double thanhTien) { this.thanhtien = thanhTien; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma chi tiet nhap hang: ");
        this.machitietnhaphang = sc.nextLine();
        System.out.print("Ma san pham: ");
        this.masanpham = sc.nextLine();
        System.out.print("So luong: ");
        try {
            this.soluong = Integer.parseInt(sc.nextLine());
        } catch (Exception ignored) {}
        System.out.print("Don gia: ");
        try {
            this.dongia = Double.parseDouble(sc.nextLine());
        } catch (Exception ignored) {}
        this.thanhtien = this.soluong * this.dongia;
    }

    public void xuat() {
        System.out.printf("CT:%s | SP:%s | SL:%d | Don gia:%.2f | Tien:%.2f%n",
                machitietnhaphang, masanpham, soluong, dongia, thanhtien);
    }
}


