import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachChiTietHoaDon {
    private ChiTietHoaDon[] mang;
    private int n;

    public DanhSachChiTietHoaDon() { this(20); }

    public DanhSachChiTietHoaDon(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new ChiTietHoaDon[sucChuaBanDau];
        this.n = 0;
    }

    public ChiTietHoaDon[] getMang() { return mang; }
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    void damBaoSucChua() {
        if (n >= mang.length) {
            ChiTietHoaDon[] moi = new ChiTietHoaDon[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach chi tiet hoa don rong");
            return;
        }
        System.out.println("\t====== Danh sach chi tiet hoa don ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them chi tiet hoa don =====
    public void them() {
        ChiTietHoaDon ct = new ChiTietHoaDon();
        ct.nhap();
        damBaoSucChua();
        mang[n++] = ct;
        System.out.println("\tDanh sach chi tiet hoa don sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma HD va ma SP =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can xoa: ");
        String maHD = sc.nextLine();
        System.out.print("Nhap ma san pham can xoa: ");
        String maSP = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(maHD) && mang[i].getMaSanPham().equalsIgnoreCase(maSP)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach chi tiet hoa don sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet hoa don can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can sua: ");
        String maHD = sc.nextLine();
        System.out.print("Nhap ma san pham can sua: ");
        String maSP = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(maHD) && mang[i].getMaSanPham().equalsIgnoreCase(maSP)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet hoa don can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiemTheoMaHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma hoa don: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin chi tiet hoa don:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet hoa don!");
        }
    }

    // ===== Tim kiem theo ma san pham =====
    public void timKiemTheoMaSanPham() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma san pham: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaSanPham().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin chi tiet hoa don:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet hoa don!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        double tongTien = 0;
        for (int i = 0; i < n; i++) {
            tongTien += mang[i].getThanhTien();
        }
        System.out.println("\t====== Thong ke ======");
        System.out.println("So dong chi tiet hoa don: " + n);
        System.out.printf("Tong tien: %.2f%n", tongTien);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/chitiethoadon.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ChiTietHoaDon ct = mang[i];
                sb.append(String.format("%s|%s|%d|%.2f|%.2f%n",
                        ct.getMaHoaDon(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " chi tiet hoa don vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/chitiethoadon.txt");
            if (!Files.exists(file)) {
                System.out.println("File chitiethoadon.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        ChiTietHoaDon ct = new ChiTietHoaDon();
                        ct.setMaHoaDon(parts[0].trim());
                        ct.setMaSanPham(parts[1].trim());
                        ct.setSoLuong(Integer.parseInt(parts[2].trim()));
                        ct.setDonGia(Double.parseDouble(parts[3].trim()));
                        ct.setThanhTien(Double.parseDouble(parts[4].trim()));
                        damBaoSucChua();
                        mang[n++] = ct;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " chi tiet hoa don tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}



