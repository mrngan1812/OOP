import java.util.Scanner;

public class Giay extends SanPham {
    private double dodayde;
    private String loaidegiay;

    public Giay() {}

    public Giay(String ma, String ten, int soluong, double dongia,
                double dodayde, String loaidegiay) {
        super(ma, ten, soluong, dongia);
        this.dodayde = dodayde;
        this.loaidegiay = loaidegiay;
    }

    public Giay(Giay g){
        super(g.getMaSanPham(), g.getTenSanPham(), g.getSoLuong(), g.getDonGia());
        this.dodayde = g.dodayde;
        this.loaidegiay = g.loaidegiay;
    }

    public double getDoDayDe() { return dodayde; }
    public void setDoDayDe(double doDayDe) { this.dodayde = doDayDe; }

    public String getLoaiDeGiay() { return loaidegiay; }
    public void setLoaiDeGiay(String loaiDeGiay) { this.loaidegiay = loaiDeGiay; }

    @Override
    public void nhap() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        super.nhap();
        System.out.print("Do day de (mm): ");
        this.dodayde = readDoubleSafe();
        System.out.print("Loai de giay: ");
        this.loaidegiay = sc.nextLine().trim();
    }

    @Override
    public void xuat() {
        System.out.print("Giay | ");
        super.xuat();
        System.out.printf(" | Do day de: %.1fmm | Loai de: %s%n", dodayde, loaidegiay);
    }
}


