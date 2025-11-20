import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachNhanVien {
    private NhanVien[] mang;
    private int n;

    public DanhSachNhanVien() { this(20); }

    public DanhSachNhanVien(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new NhanVien[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            NhanVien[] moi = new NhanVien[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach nhan vien rong");
            return;
        }
        System.out.println("\t====== Danh sach nhan vien ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them nhan vien =====
    public void them() {
        NhanVien nv = new NhanVien();
        nv.nhap();
        damBaoSucChua();
        mang[n++] = nv;
        System.out.println("\tDanh sach nhan vien sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach nhan vien sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nhan vien can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nhan vien can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma nhan vien: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNhanVien().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin nhan vien:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nhan vien!");
        }
    }

    // ===== Tim kiem theo ten =====
    public void timKiemTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ten nhan vien: ");
        String ten = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getHoTen().equalsIgnoreCase(ten)) {
                System.out.println("=> Thong tin nhan vien:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nhan vien!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so nhan vien: " + n);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/nhanvien.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                NhanVien nv = mang[i];
                sb.append(String.format("%s|%s|%s|%.2f|%s%n",
                        nv.getMaNhanVien(), nv.getHoTen(), nv.getNgaySinh(), nv.getLuongThang(), nv.getSoDienThoai()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " nhan vien vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/nhanvien.txt");
            if (!Files.exists(file)) {
                System.out.println("File nhanvien.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        NhanVien nv = new NhanVien(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                                Double.parseDouble(parts[3].trim()), parts[4].trim());
                        damBaoSucChua();
                        mang[n++] = nv;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " nhan vien tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


