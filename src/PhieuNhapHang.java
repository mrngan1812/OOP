import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhieuNhapHang {
    private String maphieunhap;
    private String manhanvien;
    private String ngaynhap;
    private double tongtien;
    private String mancc;
    private final List<ChiTietNhapHang> chitiet = new ArrayList<>();

    public String getMaPhieuNhap() { return maphieunhap; }
    public void setMaPhieuNhap(String maPhieuNhap) { this.maphieunhap = maPhieuNhap; }
    public String getMaNhanVien() { return manhanvien; }
    public void setMaNhanVien(String maNhanVien) { this.manhanvien = maNhanVien; }
    public String getNgayNhap() { return ngaynhap; }
    public void setNgayNhap(String ngayNhap) { this.ngaynhap = ngayNhap; }
    public double getTongTien() { return tongtien; }
    public void setTongTien(double tongTien) { this.tongtien = tongTien; }
    public String getMaNCC() { return mancc; }
    public void setMaNCC(String maNCC) { this.mancc = maNCC; }
    public List<ChiTietNhapHang> getChiTiet() { return chitiet; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma phieu nhap: ");
        this.maphieunhap = sc.nextLine();
        System.out.print("Ma nhan vien: ");
        this.manhanvien = sc.nextLine();
        System.out.print("Ngay nhap: ");
        this.ngaynhap = sc.nextLine();
        System.out.print("Ma nha cung cap: ");
        this.mancc = sc.nextLine();
        System.out.print("Tong tien: ");
        try {
            this.tongtien = Double.parseDouble(sc.nextLine());
        } catch (Exception ignored) {}
    }

    public void xuat() {
        System.out.printf("%s | NV:%s | Ngay:%s | NCC:%s | Tong:%.2f | CT:%d%n",
                maphieunhap, manhanvien, ngaynhap, mancc, tongtien, chitiet.size());
    }
}


