import java.util.Scanner;

public class Dep extends SanPham {
    private String loaiquai;
    private String chatlieudep;

    public Dep() {}

    public Dep(String ma, String ten, int soluong, double dongia,
               String loaiquai, String chatlieudep) {
        super(ma, ten, soluong, dongia);
        this.loaiquai = loaiquai;
        this.chatlieudep = chatlieudep;
    }

    public Dep(Dep d) {
        super(d.getMaSanPham(), d.getTenSanPham(), d.getSoLuong(), d.getDonGia());
        this.loaiquai = d.loaiquai;
        this.chatlieudep = d.chatlieudep;
    }

    public String getLoaiQuai() { return loaiquai; }
    public void setLoaiQuai(String loaiQuai) { this.loaiquai = loaiQuai; }

    public String getChatLieuDep() { return chatlieudep; }
    public void setChatLieuDep(String chatLieuDep) { this.chatlieudep = chatLieuDep; }

    @Override
    public void nhap() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        super.nhap();
        System.out.print("Loai quai: ");
        this.loaiquai = sc.nextLine().trim();
        System.out.print("Chat lieu dep: ");
        this.chatlieudep = sc.nextLine().trim();
    }

    @Override
    public void xuat() {
        System.out.print("Dep  | ");
        super.xuat();
        System.out.printf(" | Loai quai: %s | Chat lieu: %s%n", loaiquai, chatlieudep);
    }
}


