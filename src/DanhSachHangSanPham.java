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
            HangSanPham[] moi = new HangSanPham[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach hang san pham rong"); return; }
        for (int i = 0; i < n; i++) {
            HangSanPham h = mang[i];
            System.out.printf("%s | %s | %s%n", h.getMaHang(), h.getTenHang(), h.getQuocGia());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        HangSanPham h = new HangSanPham();
        System.out.print("Ma hang: "); h.setMaHang(sc.nextLine().trim());
        System.out.print("Ten hang: "); h.setTenHang(sc.nextLine().trim());
        System.out.print("Quoc gia: "); h.setQuocGia(sc.nextLine().trim());
        damBaoSucChua();
        mang[n++] = h;
        System.out.println("Da them hang.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hang can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHang().equals(ma)) {
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
        System.out.print("Nhap ma hang can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            HangSanPham h = mang[i];
            if (h.getMaHang().equals(ma)) {
                System.out.print("Ten moi: "); String s = sc.nextLine(); if (!s.isBlank()) h.setTenHang(s.trim());
                System.out.print("Quoc gia moi: "); s = sc.nextLine(); if (!s.isBlank()) h.setQuocGia(s.trim());
                System.out.println("Da cap nhat.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void timKiem() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (ma/ten/quoc gia): ");
        String kw = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < n; i++) {
            HangSanPham h = mang[i];
            if (h.getMaHang().toLowerCase().contains(kw)
                    || h.getTenHang().toLowerCase().contains(kw)
                    || h.getQuocGia().toLowerCase().contains(kw)) {
                System.out.printf("%s | %s | %s%n", h.getMaHang(), h.getTenHang(), h.getQuocGia());
            }
        }
    }

    public void thongKe() {
        System.out.printf("Tong so hang san pham: %d%n", n);
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


