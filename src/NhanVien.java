import java.util.Scanner;

public class NhanVien {
    private String manhanvien;
    private String hoten;
    private String ngaysinh;
    private double luongthang;
    private String sodienthoai;

    public NhanVien() {}

    public NhanVien(String ma, String ten, String ngaySinh, double luong, String sdt) {
        this.manhanvien = ma;
        this.hoten = ten;
        this.ngaysinh = ngaySinh;
        this.luongthang = luong;
        this.sodienthoai = sdt;
    }

    public String getMaNhanVien() { return manhanvien; }
    public void setMaNhanVien(String maNhanVien) { this.manhanvien = maNhanVien; }
    public String getHoTen() { return hoten; }
    public void setHoTen(String hoTen) { this.hoten = hoTen; }
    public String getNgaySinh() { return ngaysinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaysinh = ngaySinh; }
    public double getLuongThang() { return luongthang; }
    public void setLuongThang(double luongThang) { this.luongthang = luongThang; }
    public String getSoDienThoai() { return sodienthoai; }
    public void setSoDienThoai(String soDienThoai) { this.sodienthoai = soDienThoai; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma nhan vien: ");
        this.manhanvien = sc.nextLine();
        System.out.print("Ho ten: ");
        this.hoten = sc.nextLine();
        System.out.print("Ngay sinh: ");
        this.ngaysinh = sc.nextLine();
        System.out.print("Luong thang: ");
        try {
            this.luongthang = Double.parseDouble(sc.nextLine());
        } catch (Exception ignored) {}
        System.out.print("So dien thoai: ");
        this.sodienthoai = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%s | %s | %s | %.2f | %s%n",
                manhanvien, hoten, ngaysinh, luongthang, sodienthoai);
    }
}


