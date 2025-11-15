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
            NhanVien[] moi = new NhanVien[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach nhan vien rong"); return; }
        for (int i = 0; i < n; i++) {
            NhanVien nv = mang[i];
            System.out.printf("%s | %s | %s | %.2f | %s%n",
                    nv.getMaNhanVien(), nv.getHoTen(), nv.getNgaySinh(), nv.getLuongThang(), nv.getSoDienThoai());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        NhanVien nv = new NhanVien();
        System.out.print("Ma NV: "); nv.setMaNhanVien(sc.nextLine().trim());
        System.out.print("Ho ten: "); nv.setHoTen(sc.nextLine().trim());
        System.out.print("Ngay sinh: "); nv.setNgaySinh(sc.nextLine().trim());
        System.out.print("Luong thang: ");
        double luong = 0; try { luong = Double.parseDouble(sc.nextLine().trim()); } catch (Exception ignored) {}
        nv.setLuongThang(luong);
        System.out.print("So dien thoai: "); nv.setSoDienThoai(sc.nextLine().trim());
        damBaoSucChua();
        mang[n++] = nv;
        System.out.println("Da them NV.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma NV can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNhanVien().equals(ma)) {
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
        System.out.print("Nhap ma NV can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            NhanVien nv = mang[i];
            if (nv.getMaNhanVien().equals(ma)) {
                System.out.print("Ho ten moi: "); String s = sc.nextLine(); if (!s.isBlank()) nv.setHoTen(s.trim());
                System.out.print("Ngay sinh moi: "); s = sc.nextLine(); if (!s.isBlank()) nv.setNgaySinh(s.trim());
                System.out.print("Luong thang moi: "); s = sc.nextLine(); if (!s.isBlank()) try { nv.setLuongThang(Double.parseDouble(s.trim())); } catch (Exception ignored) {}
                System.out.print("SDT moi: "); s = sc.nextLine(); if (!s.isBlank()) nv.setSoDienThoai(s.trim());
                System.out.println("Da cap nhat.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void timKiem() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (ma/ten/sdt): ");
        String kw = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < n; i++) {
            NhanVien nv = mang[i];
            if (nv.getMaNhanVien().toLowerCase().contains(kw)
                    || nv.getHoTen().toLowerCase().contains(kw)
                    || nv.getSoDienThoai().toLowerCase().contains(kw)) {
                System.out.printf("%s | %s | %s | %.2f | %s%n",
                        nv.getMaNhanVien(), nv.getHoTen(), nv.getNgaySinh(), nv.getLuongThang(), nv.getSoDienThoai());
            }
        }
    }

    public void thongKe() {
        System.out.printf("Tong so nhan vien: %d%n", n);
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


