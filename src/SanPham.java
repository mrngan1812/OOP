import java.util.Scanner;

public abstract class SanPham {
    private String masanpham;
    private String tensanpham;
    private int soluong;
    private double dongia;

    public SanPham() {}

    public SanPham(String masanpham, String tensanpham, int soluong, double dongia) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public SanPham(SanPham sp){
        this.masanpham = sp.masanpham;
        this.tensanpham = sp.tensanpham;
        this.soluong = sp.soluong;
        this.dongia = sp.dongia;
    }

    // Get/Set
    public String getMaSanPham() { return masanpham; }
    public void setMaSanPham(String maSanPham) { this.masanpham = maSanPham; }

    public String getTenSanPham() { return tensanpham; }
    public void setTenSanPham(String tenSanPham) { this.tensanpham = tenSanPham; }

    public int getSoLuong() { return soluong; }
    public void setSoLuong(int soLuong) { this.soluong = soLuong; }

    public double getDonGia() { return dongia; }
    public void setDonGia(double donGia) { this.dongia = donGia; }

    // Nhap/Xuat
    public void nhap() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma san pham: ");
        this.masanpham = sc.nextLine().trim();

        System.out.print("Ten san pham: ");
        this.tensanpham = sc.nextLine().trim();

        System.out.print("So luong: ");
        this.soluong = readIntSafe();

        System.out.print("Don gia: ");
        this.dongia = readDoubleSafe();
    }

    public void xuat() {
        System.out.printf("[Ma:%s] %s | SL:%d | Don gia:%.2f",
                masanpham, tensanpham, soluong, dongia);
    }

    protected int readIntSafe() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine().trim();
            try { return Integer.parseInt(line); } catch (NumberFormatException e) {
                System.out.print("Nhap so nguyen hop le: ");
            }
        }
    }

    protected double readDoubleSafe() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine().trim();
            try { return Double.parseDouble(line); } catch (NumberFormatException e) {
                System.out.print("Nhap so thuc hop le: ");
            }
        }
    }
}


