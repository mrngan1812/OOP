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
            LoaiSP[] moi = new LoaiSP[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach loai SP rong"); return; }
        for (int i = 0; i < n; i++) {
            LoaiSP l = mang[i];
            System.out.printf("%s | %s%n", l.getMaLoai(), l.getTenLoai());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        LoaiSP l = new LoaiSP();
        System.out.print("Ma loai: "); l.setMaLoai(sc.nextLine().trim());
        System.out.print("Ten loai: "); l.setTenLoai(sc.nextLine().trim());
        damBaoSucChua();
        mang[n++] = l;
        System.out.println("Da them loai SP.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma loai can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaLoai().equals(ma)) {
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
        System.out.print("Nhap ma loai can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            LoaiSP l = mang[i];
            if (l.getMaLoai().equals(ma)) {
                System.out.print("Ten moi: "); String s = sc.nextLine(); if (!s.isBlank()) l.setTenLoai(s.trim());
                System.out.println("Da cap nhat.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void timKiem() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (ma/ten): ");
        String kw = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < n; i++) {
            LoaiSP l = mang[i];
            if (l.getMaLoai().toLowerCase().contains(kw) || l.getTenLoai().toLowerCase().contains(kw)) {
                System.out.printf("%s | %s%n", l.getMaLoai(), l.getTenLoai());
            }
        }
    }

    public void thongKe() {
        System.out.printf("Tong so loai SP: %d%n", n);
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


