import java.util.Scanner;

public class NhaCungCap {
    private String mancc;
    private String tenncc;
    private String sodienthoai;
    private String diachi;

    public NhaCungCap() {}

    public NhaCungCap(String ma, String ten, String sdt, String diaChi) {
        this.mancc = ma; this.tenncc = ten; this.sodienthoai = sdt; this.diachi = diaChi;
    }

    public String getMaNCC() { return mancc; }
    public void setMaNCC(String maNCC) { this.mancc = maNCC; }
    public String getTenNCC() { return tenncc; }
    public void setTenNCC(String tenNCC) { this.tenncc = tenNCC; }
    public String getSoDienThoai() { return sodienthoai; }
    public void setSoDienThoai(String soDienThoai) { this.sodienthoai = soDienThoai; }
    public String getDiaChi() { return diachi; }
    public void setDiaChi(String diaChi) { this.diachi = diaChi; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma nha cung cap: ");
        this.mancc = sc.nextLine();
        System.out.print("Ten nha cung cap: ");
        this.tenncc = sc.nextLine();
        System.out.print("So dien thoai: ");
        this.sodienthoai = sc.nextLine();
        System.out.print("Dia chi: ");
        this.diachi = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%s | %s | %s | %s%n",
                mancc, tenncc, sodienthoai, diachi);
    }
}


