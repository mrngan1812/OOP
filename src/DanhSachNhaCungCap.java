import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachNhaCungCap {
    private NhaCungCap[] mang;
    private int n;

    public DanhSachNhaCungCap() { this(20); }

    public DanhSachNhaCungCap(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new NhaCungCap[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            NhaCungCap[] moi = new NhaCungCap[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach nha cung cap rong");
            return;
        }
        System.out.println("\t====== Danh sach nha cung cap ======");
        for (int i = 0; i < n; i++) {
            NhaCungCap ncc = mang[i];
            System.out.printf("%s | %s | %s | %s%n",
                    ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getDiaChi());
        }
    }

    // ===== Them nha cung cap =====
    public void them() {
        Scanner sc = new Scanner(System.in);
        NhaCungCap ncc = new NhaCungCap();
        System.out.print("Nhap ma nha cung cap: ");
        ncc.setMaNCC(sc.nextLine());
        System.out.print("Nhap ten nha cung cap: ");
        ncc.setTenNCC(sc.nextLine());
        System.out.print("Nhap so dien thoai: ");
        ncc.setSoDienThoai(sc.nextLine());
        System.out.print("Nhap dia chi: ");
        ncc.setDiaChi(sc.nextLine());
        damBaoSucChua();
        mang[n++] = ncc;
        System.out.println("\tDanh sach nha cung cap sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha cung cap can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNCC().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach nha cung cap sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nha cung cap can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nha cung cap can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNCC().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nha cung cap can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (ma/ten/sdt): ");
        String kw = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            NhaCungCap ncc = mang[i];
            if (ncc.getMaNCC().equalsIgnoreCase(kw)
                    || ncc.getTenNCC().equalsIgnoreCase(kw)
                    || ncc.getSoDienThoai().equalsIgnoreCase(kw)) {
                System.out.println("=> Thong tin nha cung cap:");
                System.out.printf("%s | %s | %s | %s%n",
                        ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getDiaChi());
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay nha cung cap!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so nha cung cap: " + n);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/nhacungcap.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                NhaCungCap ncc = mang[i];
                sb.append(String.format("%s|%s|%s|%s%n",
                        ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getDiaChi()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " nha cung cap vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/nhacungcap.txt");
            if (!Files.exists(file)) {
                System.out.println("File nhacungcap.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    NhaCungCap ncc = new NhaCungCap(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                    damBaoSucChua();
                    mang[n++] = ncc;
                }
            }
            System.out.println("Da doc " + n + " nha cung cap tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


