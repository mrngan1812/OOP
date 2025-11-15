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
            HoaDon[] moi = new HoaDon[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach hoa don rong"); return; }
        for (int i = 0; i < n; i++) {
            HoaDon hd = mang[i];
            System.out.printf("%s | KH:%s | Ngay:%s | Tong:%.2f | CT:%d%n",
                    hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getNgayLap(), hd.getTongTien(), hd.getChiTiet().size());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        HoaDon hd = new HoaDon();
        System.out.print("Ma HD: "); hd.setMaHoaDon(sc.nextLine().trim());
        System.out.print("Ma KH: "); hd.setMaKhachHang(sc.nextLine().trim());
        System.out.print("Ngay lap: "); hd.setNgayLap(sc.nextLine().trim());
        System.out.print("Tong tien: ");
        try { hd.setTongTien(Double.parseDouble(sc.nextLine().trim())); } catch (Exception ignored) {}
        damBaoSucChua();
        mang[n++] = hd;
        System.out.println("Da them hoa don.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma HD can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equals(ma)) {
                for (int j = i; j < n - 1; j++) mang[j] = mang[j + 1];
                n--;
                System.out.println("Da xoa.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void sua() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma HD can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            HoaDon hd = mang[i];
            if (hd.getMaHoaDon().equals(ma)) {
                System.out.print("Ma KH moi: "); String s = sc.nextLine(); if (!s.isBlank()) hd.setMaKhachHang(s.trim());
                System.out.print("Ngay lap moi: "); s = sc.nextLine(); if (!s.isBlank()) hd.setNgayLap(s.trim());
                System.out.print("Tong tien moi: "); s = sc.nextLine(); if (!s.isBlank()) try { hd.setTongTien(Double.parseDouble(s.trim())); } catch (Exception ignored) {}
                System.out.println("Da cap nhat.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void timKiem() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (maHD/maKH/ngay): ");
        String kw = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < n; i++) {
            HoaDon hd = mang[i];
            if (hd.getMaHoaDon().toLowerCase().contains(kw)
                    || hd.getMaKhachHang().toLowerCase().contains(kw)
                    || hd.getNgayLap().toLowerCase().contains(kw)) {
                System.out.printf("%s | KH:%s | Ngay:%s | Tong:%.2f | CT:%d%n",
                        hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getNgayLap(), hd.getTongTien(), hd.getChiTiet().size());
            }
        }
    }

    public void thongKe() {
        int soHD = n;
        double tongDoanhThu = 0;
        for (int i = 0; i < n; i++) tongDoanhThu += mang[i].getTongTien();
        System.out.printf("So hoa don: %d | Tong doanh thu: %.2f%n", soHD, tongDoanhThu);
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


