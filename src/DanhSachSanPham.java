import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachSanPham {
    private SanPham[] mangsanpham;
    private int n; // so phan tu hien co

    public DanhSachSanPham(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mangsanpham = new SanPham[sucChuaBanDau];
        this.n = 0;
    }

    public int getN() { return n; }
    public SanPham[] getMangSanPham() { return Arrays.copyOf(mangsanpham, n); }

    public void setN(int n) { this.n = Math.max(0, Math.min(n, mangsanpham.length)); }
    public void setMangSanPham(SanPham[] arr) {
        this.mangsanpham = Arrays.copyOf(arr, arr.length);
        this.n = Math.min(arr.length, this.mangsanpham.length);
    }

    private void damBaoSucChua() {
        if (n >= mangsanpham.length) {
            mangsanpham = Arrays.copyOf(mangsanpham, mangsanpham.length + 1);
        }
    }

    public void nhapDanhSach() {
        System.out.print("Nhap so luong n: ");
        int so = readIntSafe();
        for (int i = 0; i < so; i++) {
            System.out.println("Chon loai (1-Giay, 2-Dep): ");
            int chon = readIntSafe();
            themSanPhamTheoLoai(chon);
        }
    }

    // ===== Xem danh sach =====
    public void xuatDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach rong");
            return;
        }
        for (int i = 0; i < n; i++) {
            mangsanpham[i].xuat();
        }
    }

    // ===== Them san pham =====
    public void themSanPham() {
        System.out.println("Chon loai (1-Giay, 2-Dep): ");
        int chon = readIntSafe();
        themSanPhamTheoLoai(chon);
    }

    private void themSanPhamTheoLoai(int chon) {
        SanPham sp;
        if (chon == 1) sp = new Giay(); 
        else sp = new Dep();
        sp.nhap();
        damBaoSucChua();
        mangsanpham[n++] = sp; 
    }

    // Xóa sản phẩm theo mã (có tham số)
    public boolean xoaSanPhamTheoMa(String masanpham) {
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getMaSanPham().equals(masanpham)) {
                // Dịch chuyển các phần tử về phía trước
                for (int j = i; j < n - 1; j++) {
                    mangsanpham[j] = mangsanpham[j + 1];
                }
                n--;
                System.out.println("Da xoa san pham co ma: " + masanpham);
                return true;
            }
        }
        System.out.println("Khong tim thay san pham co ma: " + masanpham);
        return false;
    }

    // ===== Xoa san pham theo ma =====
    public void xoaSanPhamTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma san pham can xoa: ");
        String masanpham = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getMaSanPham().equalsIgnoreCase(masanpham)) {
                for (int j = i; j < n - 1; j++) {
                    mangsanpham[j] = mangsanpham[j + 1];
                }
                n--;
                System.out.println("=> Da xoa san pham co ma: " + masanpham);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay san pham co ma: " + masanpham);
        }
    }

    // ===== Sua thong tin san pham =====
    public void suaSanPhamTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma san pham can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getMaSanPham().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mangsanpham[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mangsanpham[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay san pham can sua!");
        }
    }

    // ===== Tim kiem theo ma hoac ten =====
    public void timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma san pham: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getMaSanPham().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin san pham:");
                mangsanpham[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay san pham!");
        }
    }
    // ===== Tim kiem theo ten =====
    public void timKiemTheoTen() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ten san pham: ");
        String ten = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getTenSanPham().equalsIgnoreCase(ten)) {
                System.out.println("=> Thong tin san pham:");
                mangsanpham[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay san pham!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        int tongSL = 0;
        double tongGiaTri = 0;
        for (int i = 0; i < n; i++) {
            tongSL += mangsanpham[i].getSoLuong();
            tongGiaTri += mangsanpham[i].getSoLuong() * mangsanpham[i].getDonGia();
        }
        System.out.println("=> So luong mat hang: " + n);
        System.out.println("=> Tong so luong: " + tongSL);
        System.out.printf("=> Tong gia tri: %.2f%n", tongGiaTri);
    }

    // ===== Tim san pham theo ma =====
    public SanPham timSanPhamTheoMa(String ma) {
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getMaSanPham().equalsIgnoreCase(ma)) {
                return mangsanpham[i];
            }
        }
        return null;
    }


    // ===== Cap nhat so luong san pham =====
    public void capNhatSoLuongSP(String ma, int soLuong) {
        for (int i = 0; i < n; i++) {
            if (mangsanpham[i].getMaSanPham().equalsIgnoreCase(ma)) {
                int slMoi = mangsanpham[i].getSoLuong() + soLuong;
                if (slMoi < 0) slMoi = 0;
                mangsanpham[i].setSoLuong(slMoi);
                return;
            }
        }
    }

    private int readIntSafe() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Nhap so nguyen hop le: ");
            }
        }
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/sanpham.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                SanPham sp = mangsanpham[i];
                if (sp instanceof Giay) {
                    Giay g = (Giay) sp;
                    sb.append(String.format("1|%s|%s|%d|%.2f|%.2f|%s%n",
                            g.getMaSanPham(), g.getTenSanPham(), g.getSoLuong(), g.getDonGia(),
                            g.getDoDayDe(), g.getLoaiDeGiay()));
                } else if (sp instanceof Dep) {
                    Dep d = (Dep) sp;
                    sb.append(String.format("2|%s|%s|%d|%.2f|%s|%s%n",
                            d.getMaSanPham(), d.getTenSanPham(), d.getSoLuong(), d.getDonGia(),
                            d.getLoaiQuai(), d.getChatLieuDep()));
                }
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " san pham vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/sanpham.txt");
            if (!Files.exists(file)) {
                System.out.println("File sanpham.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    try {
                        int loai = Integer.parseInt(parts[0].trim());
                        String ma = parts[1].trim();
                        String ten = parts[2].trim();
                        int sl = Integer.parseInt(parts[3].trim());
                        double dg = Double.parseDouble(parts[4].trim());
                        SanPham sp;
                        if (loai == 1 && parts.length >= 7) {
                            // Giay
                            double dodayde = Double.parseDouble(parts[5].trim());
                            String loaide = parts[6].trim();
                            sp = new Giay(ma, ten, sl, dg, dodayde, loaide);
                        } else if (loai == 2 && parts.length >= 7) {
                            // Dep
                            String loaiquai = parts[5].trim();
                            String chatlieu = parts[6].trim();
                            sp = new Dep(ma, ten, sl, dg, loaiquai, chatlieu);
                        } else {
                            continue;
                        }
                        damBaoSucChua();
                        mangsanpham[n++] = sp;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " san pham tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


