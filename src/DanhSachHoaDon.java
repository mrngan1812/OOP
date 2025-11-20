import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DanhSachHoaDon {
    private HoaDon[] mang;
    private int n;

    public DanhSachHoaDon() { this(20); }

    public DanhSachHoaDon(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new HoaDon[sucChuaBanDau];
        this.n = 0;
    }

    public HoaDon[] getMang() { return mang; }
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    void damBaoSucChua() {
        if (n >= mang.length) {
            HoaDon[] moi = new HoaDon[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach hoa don rong");
            return;
        }
        System.out.println("\t====== Danh sach hoa don ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them hoa don =====
    public void them() {
        HoaDon hd = new HoaDon();
        hd.nhap();
        damBaoSucChua();
        mang[n++] = hd;
        System.out.println("\tDanh sach hoa don sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach hoa don sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma hoa don: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaHoaDon().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin hoa don:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don!");
        }
    }

    // ===== Tim kiem theo ma khach hang =====
    public void timKiemTheoMaKhachHang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma khach hang: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaKhachHang().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin hoa don:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay hoa don!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        double tongDoanhThu = 0;
        for (int i = 0; i < n; i++) {
            tongDoanhThu += mang[i].getTongTien();
        }
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so hoa don: " + n);
        System.out.printf("Tong doanh thu: %.2f%n", tongDoanhThu);
    }

    // ===== Thong ke tong tien tu ngay A den ngay B =====
    public void thongKeTongTienTuNgayAToNgayB() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        System.out.println("=== THONG KE TONG TIEN THEO NGAY ===");
        try {
            System.out.print("Nhap ngay bat dau (A) theo dinh dang yyyy-MM-dd: ");
            String strNgayA = sc.nextLine();
            Date ngayA = dateFormat.parse(strNgayA);
            System.out.print("Nhap ngay ket thuc (B) theo dinh dang yyyy-MM-dd: ");
            String strNgayB = sc.nextLine();
            Date ngayB = dateFormat.parse(strNgayB);
            if (ngayA.after(ngayB)) {
                System.out.println("LOI: Ngay bat dau phai truoc hoac bang ngay ket thuc.");
                return;
            }
            double tongTienThongKe = 0.0;
            int soHoaDon = 0;
            System.out.println("\n--- Cac hoa don tim thay trong khoang tu " + strNgayA + " den " + strNgayB + " ---");
            System.out.printf("%-10s %-15s %-10s %-15s\n",
                    "Ma hoa don", "Ma khach hang", "Ngay lap hoa don", "Tong tien");
            System.out.println("-------------------------------------------------------");
            for (int i = 0; i < n; i++) {
                String ngayLapHD_str = mang[i].getNgayLap();
                Date ngayLapHD;
                try {
                    ngayLapHD = dateFormat.parse(ngayLapHD_str);
                } catch (ParseException e) {
                    System.out.println("Canh bao: Bo qua hoa don " + mang[i].getMaHoaDon() + " do sai dinh dang ngay: " + ngayLapHD_str);
                    continue;
                }
                if (!ngayLapHD.before(ngayA) && !ngayLapHD.after(ngayB)) {
                    mang[i].xuat();
                    tongTienThongKe += mang[i].getTongTien();
                    soHoaDon++;
                }
            }
            System.out.println("-------------------------------------------------------");
            System.out.println("Tim thay " + soHoaDon + " hoa don hop le.");
            System.out.printf("TONG TIEN: %.2f\n", tongTienThongKe);
        } catch (ParseException e) {
            System.out.println("LOI: Dinh dang ngay nhap vao khong hop le.");
            System.out.println("Vui long nhap dung dinh dang yyyy-MM-dd (vi du: 2024-01-15).");
        }
    }

    // ===== Kiem tra ton tai trong mang =====
    private boolean kiemTraTonTai(String[] arr, int soLuongHienTai, String value) {
        for (int i = 0; i < soLuongHienTai; i++) {
            if (arr[i] != null && arr[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    // ===== Tim index trong mang =====
    private int timIndex(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // ===== Thong ke theo khach hang va nam =====
    public void thongKeTheoKhachHangVaNam() {
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String[] khachHangTam = new String[n];
        String[] namTam = new String[n];
        int soLuongKH = 0;
        int soLuongNam = 0;
        for (int i = 0; i < n; i++) {
            HoaDon hd = mang[i];
            String maKH = hd.getMaKhachHang();
            String nam;
            if (!kiemTraTonTai(khachHangTam, soLuongKH, maKH)) {
                khachHangTam[soLuongKH] = maKH;
                soLuongKH++;
            }
            try {
                Date ngayLap = fullFormat.parse(hd.getNgayLap());
                nam = yearFormat.format(ngayLap);
                if (!kiemTraTonTai(namTam, soLuongNam, nam)) {
                    namTam[soLuongNam] = nam;
                    soLuongNam++;
                }
            } catch (ParseException e) {
                System.out.println("Canh bao: Sai dinh dang ngay tai hoa don " + hd.getMaHoaDon());
            }
        }
        if (soLuongKH == 0 || soLuongNam == 0) {
            System.out.println("Khong co du lieu de thong ke.");
            return;
        }
        String[] dsKH = Arrays.copyOf(khachHangTam, soLuongKH);
        String[] dsNam = Arrays.copyOf(namTam, soLuongNam);
        Arrays.sort(dsKH);
        Arrays.sort(dsNam);
        double[][] bangThongKe = new double[soLuongKH][soLuongNam];
        for (int i = 0; i < n; i++) {
            HoaDon hd = mang[i];
            String maKH = hd.getMaKhachHang();
            String nam;
            try {
                nam = yearFormat.format(fullFormat.parse(hd.getNgayLap()));
            } catch (ParseException e) {
                continue;
            }
            int indexKH = timIndex(dsKH, maKH);
            int indexNam = timIndex(dsNam, nam);
            if (indexKH != -1 && indexNam != -1) {
                bangThongKe[indexKH][indexNam] += hd.getTongTien();
            }
        }
        System.out.println("\n===== BANG THONG KE DOANH THU THEO KHACH HANG VA NAM =====");
        System.out.printf("%-15s", "KH/NAM");
        for (String nam : dsNam) {
            System.out.printf(" | %15s", nam);
        }
        System.out.printf(" | %15s\n", "TONG (THEO KH)");
        for (int i = 0; i < dsNam.length + 2; i++) System.out.print("-----------------");
        System.out.println();
        double[] tongCongTheoNam = new double[soLuongNam];
        double tongCongChung = 0.0;
        for (int i = 0; i < soLuongKH; i++) {
            System.out.printf("%-15s", dsKH[i]);
            double tongCongTheoKH = 0.0;
            for (int j = 0; j < soLuongNam; j++) {
                double giaTri = bangThongKe[i][j];
                System.out.printf(" | %15.2f", giaTri);
                tongCongTheoKH += giaTri;
                tongCongTheoNam[j] += giaTri;
            }
            System.out.printf(" | %15.2f\n", tongCongTheoKH);
            tongCongChung += tongCongTheoKH;
        }
        for (int i = 0; i < dsNam.length + 2; i++) System.out.print("-----------------");
        System.out.println();
        System.out.printf("%-15s", "TONG (THEO NAM)");
        for (int j = 0; j < soLuongNam; j++) {
            System.out.printf(" | %15.2f", tongCongTheoNam[j]);
        }
        System.out.printf(" | %15.2f\n", tongCongChung);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/hoadon.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                HoaDon hd = mang[i];
                sb.append(String.format("%s|%s|%s|%.2f%n",
                        hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getNgayLap(), hd.getTongTien()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " hoa don vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/hoadon.txt");
            if (!Files.exists(file)) {
                System.out.println("File hoadon.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    try {
                        HoaDon hd = new HoaDon();
                        hd.setMaHoaDon(parts[0].trim());
                        hd.setMaKhachHang(parts[1].trim());
                        hd.setNgayLap(parts[2].trim());
                        hd.setTongTien(Double.parseDouble(parts[3].trim()));
                        damBaoSucChua();
                        mang[n++] = hd;
                    } catch (Exception ignored) {}
                }
            }
            System.out.println("Da doc " + n + " hoa don tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


