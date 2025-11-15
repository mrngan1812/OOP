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
            ChiTietNhapHang[] moi = new ChiTietNhapHang[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach CTNH rong"); return; }
        for (int i = 0; i < n; i++) {
            ChiTietNhapHang ct = mang[i];
            System.out.printf("CT:%s | SP:%s | SL:%d | Don gia:%.2f | Tien:%.2f%n",
                    ct.getMaChiTietNhapHang(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        ChiTietNhapHang ct = new ChiTietNhapHang();
        System.out.print("Ma CTNH: "); ct.setMaChiTietNhapHang(sc.nextLine().trim());
        System.out.print("Ma SP: "); ct.setMaSanPham(sc.nextLine().trim());
        System.out.print("So luong: "); try { ct.setSoLuong(Integer.parseInt(sc.nextLine().trim())); } catch (Exception ignored) {}
        System.out.print("Don gia: "); try { ct.setDonGia(Double.parseDouble(sc.nextLine().trim())); } catch (Exception ignored) {}
        ct.setThanhTien(ct.getSoLuong() * ct.getDonGia());
        damBaoSucChua();
        mang[n++] = ct;
        System.out.println("Da them CTNH.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma CTNH can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (ma.equals(mang[i].getMaChiTietNhapHang())) {
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
        System.out.print("Nhap ma CTNH can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            ChiTietNhapHang ct = mang[i];
            if (ma.equals(ct.getMaChiTietNhapHang())) {
                System.out.print("So luong moi: "); String s = sc.nextLine(); if (!s.isBlank()) try { ct.setSoLuong(Integer.parseInt(s.trim())); } catch (Exception ignored) {}
                System.out.print("Don gia moi: "); s = sc.nextLine(); if (!s.isBlank()) try { ct.setDonGia(Double.parseDouble(s.trim())); } catch (Exception ignored) {}
                ct.setThanhTien(ct.getSoLuong() * ct.getDonGia());
                System.out.println("Da cap nhat.");
                return;
            }
        }
        System.out.println("Khong tim thay.");
    }

    public void timKiem() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (maCTNH/maSP): ");
        String kw = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < n; i++) {
            ChiTietNhapHang ct = mang[i];
            if ((ct.getMaChiTietNhapHang() != null && ct.getMaChiTietNhapHang().toLowerCase().contains(kw))
                    || ct.getMaSanPham().toLowerCase().contains(kw)) {
                System.out.printf("CT:%s | SP:%s | SL:%d | Don gia:%.2f | Tien:%.2f%n",
                        ct.getMaChiTietNhapHang(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien());
            }
        }
    }

    public void thongKe() {
        int soDong = n;
        double tongTien = 0;
        for (int i = 0; i < n; i++) tongTien += mang[i].getThanhTien();
        System.out.printf("So dong CTNH: %d | Tong tien: %.2f%n", soDong, tongTien);
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


