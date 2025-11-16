import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachHangSanPham {
    private HangSanPham[] mang;
    private int n;

    public DanhSachHangSanPham() { this(20); }

    public DanhSachHangSanPham(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new HangSanPham[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            HangSanPham[] moi = new HangSanPham[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach hang san pham rong");
            return;
        }
        System.out.println("\t====== Danh sach hang san pham ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them hang san pham =====
    public void them() {
        HangSanPham h = new HangSanPham();
        h.nhap();
        damBaoSucChua();
        mang[n++] = h;
        System.out.println("\tDanh sach hang san pham sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hang can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHang().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach hang san pham sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hang san pham can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hang can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHang().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hang san pham can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (ma/ten/quoc gia): ");
        String kw = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            HangSanPham h = mang[i];
            if (h.getMaHang().equalsIgnoreCase(kw)
                    || h.getTenHang().equalsIgnoreCase(kw)
                    || h.getQuocGia().equalsIgnoreCase(kw)) {
                System.out.println("=> Thong tin hang san pham:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hang san pham!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so hang san pham: " + n);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/hangsanpham.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                HangSanPham h = mang[i];
                sb.append(String.format("%s|%s|%s%n",
                        h.getMaHang(), h.getTenHang(), h.getQuocGia()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " hang san pham vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/hangsanpham.txt");
            if (!Files.exists(file)) {
                System.out.println("File hangsanpham.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    HangSanPham h = new HangSanPham(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    damBaoSucChua();
                    mang[n++] = h;
                }
            }
            System.out.println("Da doc " + n + " hang san pham tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}



