import java.util.Scanner;

public class LoaiSP {
    private String maloai;
    private String tenloai;

    public LoaiSP() {}
    public LoaiSP(String ma, String ten) { this.maloai = ma; this.tenloai = ten; }

    public String getMaLoai() { return maloai; }
    public void setMaLoai(String maLoai) { this.maloai = maLoai; }
    public String getTenLoai() { return tenloai; }
    public void setTenLoai(String tenLoai) { this.tenloai = tenLoai; }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma loai: ");
        this.maloai = sc.nextLine();
        System.out.print("Ten loai: ");
        this.tenloai = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%s | %s%n", maloai, tenloai);
    }
}


