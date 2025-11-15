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
}


