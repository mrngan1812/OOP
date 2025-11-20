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

    public PhieuNhapHang[] getMang() { return mang; }
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    void damBaoSucChua() {
        if (n >= mang.length) {
            PhieuNhapHang[] moi = new PhieuNhapHang[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach phieu nhap hang rong");
            return;
        }
        System.out.println("\t====== Danh sach phieu nhap hang ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them phieu nhap hang =====
    public void them() {
        PhieuNhapHang pn = new PhieuNhapHang();
        pn.nhap();
        damBaoSucChua();
        mang[n++] = pn;
        System.out.println("\tDanh sach phieu nhap hang sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaPhieuNhap().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach phieu nhap hang sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay phieu nhap hang can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma phieu nhap can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaPhieuNhap().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay phieu nhap hang can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma phieu nhap: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            PhieuNhapHang pn = mang[i];
            if (pn.getMaPhieuNhap().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin phieu nhap hang:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay phieu nhap hang!");
        }
    }
    // ===== Tim kiem theo ma nhan vien =====
    public void timKiemTheoMaNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa ma nhan vien: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            PhieuNhapHang pn = mang[i];
            if (pn.getMaNhanVien().equalsIgnoreCase(ma)) {
                System.out.println("=> Thong tin phieu nhap hang:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay phieu nhap hang!");
        }
    }
    // ===== Thong ke =====
    public void thongKe() {
        double tongChiPhi = 0;
        for (int i = 0; i < n; i++) {
            tongChiPhi += mang[i].getTongTien();
        }
        System.out.println("\t====== Thong ke ======");
        System.out.println("So phieu nhap hang: " + n);
        System.out.printf("Tong chi phi: %.2f%n", tongChiPhi);
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























/* 
    //tim them kh
    public timKiemTheoMaKh(){
    Scanner sc = Scanner(System.in)
    boolean found;
    String ma sc.nextLine();
    for(int i = 0; i < n; i++){
        if(mang[i].getmakhachhang().equalsIgnoreCase(ma))
        System.out.println("thong tin cua phieu nhap hang")
        mang[i].xuat();
        }
        System.out.println("khong tim thay thong tin phieu nhap")
    }

 
    //tinh tong  theo thang, nam 
    public void tongTienTheoThang(){
        double tong = 0;
        System.out.println("chon thang: ");
        for(int i = 0; i < n; i++){
        
        }
    }



*/
}

