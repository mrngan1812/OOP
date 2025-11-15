import java.util.ArrayList;
import java.util.List;

public class HoaDon {
    private String mahoadon;
    private String makhachhang;
    private String ngaylap;
    private double tongtien;
    private final List<ChiTietHoaDon> chitiet = new ArrayList<>();

    public String getMaHoaDon() { return mahoadon; }
    public void setMaHoaDon(String maHoaDon) { this.mahoadon = maHoaDon; }
    public String getMaKhachHang() { return makhachhang; }
    public void setMaKhachHang(String maKhachHang) { this.makhachhang = maKhachHang; }
    public String getNgayLap() { return ngaylap; }
    public void setNgayLap(String ngayLap) { this.ngaylap = ngayLap; }
    public double getTongTien() { return tongtien; }
    public void setTongTien(double tongTien) { this.tongtien = tongTien; }
    public List<ChiTietHoaDon> getChiTiet() { return chitiet; }
}


