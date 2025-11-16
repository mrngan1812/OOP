import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachChiTietNhapHang {
    private ChiTietNhapHang[] mang;
    private int n;

    public DanhSachChiTietNhapHang() { this(20); }

    public DanhSachChiTietNhapHang(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new ChiTietNhapHang[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            ChiTietNhapHang[] moi = new ChiTietNhapHang[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach chi tiet nhap hang rong");
            return;
        }
        System.out.println("\t====== Danh sach chi tiet nhap hang ======");
        for (int i = 0; i < n; i++) {
            ChiTietNhapHang ct = mang[i];
            System.out.printf("CT:%s | SP:%s | SL:%d | Don gia:%.2f | Tien:%.2f%n",
                    ct.getMaChiTietNhapHang(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien());
        }
    }

    // ===== Them chi tiet nhap hang =====
    public void them() {
        Scanner sc = new Scanner(System.in);
        ChiTietNhapHang ct = new ChiTietNhapHang();
        System.out.print("Nhap ma chi tiet nhap hang: ");
        ct.setMaChiTietNhapHang(sc.nextLine());
        System.out.print("Nhap ma san pham: ");
        ct.setMaSanPham(sc.nextLine());
        System.out.print("Nhap so luong: ");
        try {
            ct.setSoLuong(Integer.parseInt(sc.nextLine()));
        } catch (Exception ignored) {}
        System.out.print("Nhap don gia: ");
        try {
            ct.setDonGia(Double.parseDouble(sc.nextLine()));
        } catch (Exception ignored) {}
        ct.setThanhTien(ct.getSoLuong() * ct.getDonGia());
        damBaoSucChua();
        mang[n++] = ct;
        System.out.println("\tDanh sach chi tiet nhap hang sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chi tiet nhap hang can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaChiTietNhapHang() != null && mang[i].getMaChiTietNhapHang().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach chi tiet nhap hang sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet nhap hang can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma chi tiet nhap hang can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaChiTietNhapHang() != null && mang[i].getMaChiTietNhapHang().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet nhap hang can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (maCTNH/maSP): ");
        String kw = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            ChiTietNhapHang ct = mang[i];
            if ((ct.getMaChiTietNhapHang() != null && ct.getMaChiTietNhapHang().equalsIgnoreCase(kw))
                    || ct.getMaSanPham().equalsIgnoreCase(kw)) {
                System.out.println("=> Thong tin chi tiet nhap hang:");
                System.out.printf("CT:%s | SP:%s | SL:%d | Don gia:%.2f | Tien:%.2f%n",
                        ct.getMaChiTietNhapHang(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien());
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay chi tiet nhap hang!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        double tongTien = 0;
        for (int i = 0; i < n; i++) {
            tongTien += mang[i].getThanhTien();
        }
        System.out.println("\t====== Thong ke ======");
        System.out.println("So dong chi tiet nhap hang: " + n);
        System.out.printf("Tong tien: %.2f%n", tongTien);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/chitietnhaphang.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ChiTietNhapHang ct = mang[i];
                sb.append(String.format("%s|%s|%d|%.2f|%.2f%n",
                        ct.getMaChiTietNhapHang(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " chi tiet nhap hang vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/chitietnhaphang.txt");
            if (!Files.exists(file)) {
                System.out.println("File chitietnhaphang.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        ChiTietNhapHang ct = new ChiTietNhapHang();
                        ct.setMaChiTietNhapHang(parts[0].trim());
                        ct.setMaSanPham(parts[1].trim());
                        ct.setSoLuong(Integer.parseInt(parts[2].trim()));
                        ct.setDonGia(Double.parseDouble(parts[3].trim()));
                        ct.setThanhTien(Double.parseDouble(parts[4].trim()));
                        damBaoSucChua();
                        mang[n++] = ct;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " chi tiet nhap hang tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}



