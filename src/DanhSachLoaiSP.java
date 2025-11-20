import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachLoaiSP {
    private LoaiSP[] mang;
    private int n;

    public DanhSachLoaiSP() { this(20); }

    public DanhSachLoaiSP(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new LoaiSP[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            LoaiSP[] moi = new LoaiSP[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach loai san pham rong");
            return;
        }
        System.out.println("\t====== Danh sach loai san pham ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them loai san pham =====
    public void them() {
        LoaiSP l = new LoaiSP();
        l.nhap();
        damBaoSucChua();
        mang[n++] = l;
        System.out.println("\tDanh sach loai san pham sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma loai can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaLoai().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach loai san pham sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay loai san pham can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma loai can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaLoai().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay loai san pham can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma loai san pham: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaLoai().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin loai san pham:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay loai san pham!");
        }
    }

    // ===== Tim kiem theo ten =====
    public void timKiemTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ten loai san pham: ");
        String ten = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getTenLoai().equalsIgnoreCase(ten)) {
                System.out.println("=> Thong tin loai san pham:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay loai san pham!");
        }
    }
    // ===== Thong ke =====
    public void thongKe() {
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so loai san pham: " + n);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/loaisanpham.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                LoaiSP l = mang[i];
                sb.append(String.format("%s|%s%n",
                        l.getMaLoai(), l.getTenLoai()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " loai san pham vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/loaisanpham.txt");
            if (!Files.exists(file)) {
                System.out.println("File loaisanpham.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    LoaiSP l = new LoaiSP(parts[0].trim(), parts[1].trim());
                    damBaoSucChua();
                    mang[n++] = l;
                }
            }
            System.out.println("Da doc " + n + " loai san pham tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}




