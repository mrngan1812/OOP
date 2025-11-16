import java.util.Scanner;

public class KhachHang {
    private String makhachhang;
    private String hoten;
    private String sodienthoai;
    private String diachi;

    public KhachHang() {}

    public KhachHang(String ma, String ten, String sdt, String diaChi) {
        this.makhachhang = ma;
        this.hoten = ten;
        this.sodienthoai = sdt;
        this.diachi = diaChi;
    }
    public KhachHang(KhachHang k) {
        this.makhachhang = k.makhachhang;
        this.hoten = k.hoten;
        this.sodienthoai = k.sodienthoai;
        this.diachi = k.diachi;
    }

    public String getMaKhachHang() { return makhachhang; }
    public void setMaKhachHang(String maKhachHang) { this.makhachhang = maKhachHang; }
    public String getHoTen() { return hoten; }
    public void setHoTen(String hoTen) { this.hoten = hoTen; }
    public String getSoDienThoai() { return sodienthoai; }
    public void setSoDienThoai(String soDienThoai) { this.sodienthoai = soDienThoai; }
    public String getDiaChi() { return diachi; }
    public void setDiaChi(String diaChi) { this.diachi = diaChi; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma khach hang: ");
        this.makhachhang = sc.nextLine();
        System.out.print("Ho ten: ");
        this.hoten = sc.nextLine();
        System.out.print("So dien thoai: ");
        this.sodienthoai = sc.nextLine();
        System.out.print("Dia chi: ");
        this.diachi = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%s | %s | %s | %s%n",
                makhachhang, hoten, sodienthoai, diachi);
    }
}

