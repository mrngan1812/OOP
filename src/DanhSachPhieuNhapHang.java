import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachPhieuNhapHang {
    private PhieuNhapHang[] mang;
    private int n;

    public DanhSachPhieuNhapHang() { this(20); }

    public DanhSachPhieuNhapHang(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new PhieuNhapHang[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            PhieuNhapHang[] moi = new PhieuNhapHang[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach phieu nhap rong"); return; }
        for (int i = 0; i < n; i++) {
            PhieuNhapHang pn = mang[i];
            System.out.printf("%s | NV:%s | Ngay:%s | NCC:%s | Tong:%.2f | CT:%d%n",
                    pn.getMaPhieuNhap(), pn.getMaNhanVien(), pn.getNgayNhap(), pn.getMaNCC(), pn.getTongTien(), pn.getChiTiet().size());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        PhieuNhapHang pn = new PhieuNhapHang();
        System.out.print("Ma PN: "); pn.setMaPhieuNhap(sc.nextLine().trim());
        System.out.print("Ma NV: "); pn.setMaNhanVien(sc.nextLine().trim());
        System.out.print("Ngay nhap: "); pn.setNgayNhap(sc.nextLine().trim());
        System.out.print("Ma NCC: "); pn.setMaNCC(sc.nextLine().trim());
        System.out.print("Tong tien: ");
        try { pn.setTongTien(Double.parseDouble(sc.nextLine().trim())); } catch (Exception ignored) {}
        damBaoSucChua();
        mang[n++] = pn;
        System.out.println("Da them phieu nhap.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma PN can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaPhieuNhap().equals(ma)) {
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
        System.out.print("Nhap ma PN can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            PhieuNhapHang pn = mang[i];
            if (pn.getMaPhieuNhap().equals(ma)) {
                System.out.print("Ma NV moi: "); String s = sc.nextLine(); if (!s.isBlank()) pn.setMaNhanVien(s.trim());
                System.out.print("Ngay nhap moi: "); s = sc.nextLine(); if (!s.isBlank()) pn.setNgayNhap(s.trim());
                System.out.print("Ma NCC moi: "); s = sc.nextLine(); if (!s.isBlank()) pn.setMaNCC(s.trim());
                System.out.print("Tong tien moi: "); s = sc.nextLine(); if (!s.isBlank()) try { pn.setTongTien(Double.parseDouble(s.trim())); } catch (Exception ignored) {}
                System.out.println("Da cap nhat.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void timKiem() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (maPN/maNV/maNCC/ngay): ");
        String kw = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < n; i++) {
            PhieuNhapHang pn = mang[i];
            if (pn.getMaPhieuNhap().toLowerCase().contains(kw)
                    || pn.getMaNhanVien().toLowerCase().contains(kw)
                    || pn.getMaNCC().toLowerCase().contains(kw)
                    || pn.getNgayNhap().toLowerCase().contains(kw)) {
                System.out.printf("%s | NV:%s | Ngay:%s | NCC:%s | Tong:%.2f | CT:%d%n",
                        pn.getMaPhieuNhap(), pn.getMaNhanVien(), pn.getNgayNhap(), pn.getMaNCC(), pn.getTongTien(), pn.getChiTiet().size());
            }
        }
    }

    public void thongKe() {
        int soPN = n;
        double tongChiPhi = 0;
        for (int i = 0; i < n; i++) tongChiPhi += mang[i].getTongTien();
        System.out.printf("So phieu nhap: %d | Tong chi phi: %.2f%n", soPN, tongChiPhi);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/phieunhaphang.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                PhieuNhapHang pn = mang[i];
                sb.append(String.format("%s|%s|%s|%s|%.2f%n",
                        pn.getMaPhieuNhap(), pn.getMaNhanVien(), pn.getNgayNhap(), pn.getMaNCC(), pn.getTongTien()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " phieu nhap hang vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/phieunhaphang.txt");
            if (!Files.exists(file)) {
                System.out.println("File phieunhaphang.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        PhieuNhapHang pn = new PhieuNhapHang();
                        pn.setMaPhieuNhap(parts[0].trim());
                        pn.setMaNhanVien(parts[1].trim());
                        pn.setNgayNhap(parts[2].trim());
                        pn.setMaNCC(parts[3].trim());
                        pn.setTongTien(Double.parseDouble(parts[4].trim()));
                        damBaoSucChua();
                        mang[n++] = pn;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " phieu nhap hang tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


