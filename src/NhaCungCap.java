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
}


