import java.util.ArrayList;
import java.util.List;

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
}


