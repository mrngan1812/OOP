import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachHoaDon {
    private HoaDon[] mang;
    private int n;

    public DanhSachHoaDon() { this(20); }

    public DanhSachHoaDon(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new HoaDon[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            HoaDon[] moi = new HoaDon[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach hoa don rong");
            return;
        }
        System.out.println("\t====== Danh sach hoa don ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them hoa don =====
    public void them() {
        HoaDon hd = new HoaDon();
        hd.nhap();
        damBaoSucChua();
        mang[n++] = hd;
        System.out.println("\tDanh sach hoa don sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach hoa don sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (maHD/maKH/ngay): ");
        String kw = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            HoaDon hd = mang[i];
            if (hd.getMaHoaDon().equalsIgnoreCase(kw)
                    || hd.getMaKhachHang().equalsIgnoreCase(kw)
                    || hd.getNgayLap().equalsIgnoreCase(kw)) {
                System.out.println("=> Thong tin hoa don:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        double tongDoanhThu = 0;
        for (int i = 0; i < n; i++) {
            tongDoanhThu += mang[i].getTongTien();
        }
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so hoa don: " + n);
        System.out.printf("Tong doanh thu: %.2f%n", tongDoanhThu);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/hoadon.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                HoaDon hd = mang[i];
                sb.append(String.format("%s|%s|%s|%.2f%n",
                        hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getNgayLap(), hd.getTongTien()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " hoa don vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/hoadon.txt");
            if (!Files.exists(file)) {
                System.out.println("File hoadon.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    try {
                        HoaDon hd = new HoaDon();
                        hd.setMaHoaDon(parts[0].trim());
                        hd.setMaKhachHang(parts[1].trim());
                        hd.setNgayLap(parts[2].trim());
                        hd.setTongTien(Double.parseDouble(parts[3].trim()));
                        damBaoSucChua();
                        mang[n++] = hd;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " hoa don tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


