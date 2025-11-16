import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachKhachHang implements IDanhSach {
    private KhachHang[] mang;
    private int n;

    public DanhSachKhachHang() {
        this(20);
    }

    public DanhSachKhachHang(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new KhachHang[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            KhachHang[] moi = new KhachHang[mang.length + 1];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void add(KhachHang kh) {
        damBaoSucChua();
        mang[n++] = kh;
    }

    // ===== Xem danh sach =====
    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("=> Danh sach khach hang rong");
            return;
        }
        System.out.println("\t====== Danh sach khach hang ======");
        for (int i = 0; i < n; i++) {
            mang[i].xuat();
        }
    }

    // ===== Them khach hang =====
    public void them() {
        KhachHang kh = new KhachHang();
        kh.nhap();
        add(kh);
        System.out.println("\tDanh sach khach hang sau khi them:");
        xemDanhSach();
    }

    // ===== Xoa theo ma =====
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can xoa: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaKhachHang().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    mang[j] = mang[j + 1];
                }
                n--;
                System.out.println("\tDanh sach khach hang sau khi xoa:");
                xemDanhSach();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay khach hang can xoa!");
        }
    }

    // ===== Sua thong tin =====
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can sua: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaKhachHang().equalsIgnoreCase(ma)) {
                System.out.println("Nhap thong tin moi:");
                mang[i].nhap();
                found = true;
                System.out.println("\tThong tin sau khi sua:");
                mang[i].xuat();
                break;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay khach hang can sua!");
        }
    }

    // ===== Tim kiem =====
    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu khoa (ma/ten/sdt): ");
        String kw = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            KhachHang kh = mang[i];
            if (kh.getMaKhachHang().equalsIgnoreCase(kw)
                    || kh.getHoTen().equalsIgnoreCase(kw)
                    || kh.getSoDienThoai().equalsIgnoreCase(kw)) {
                System.out.println("=> Thong tin khach hang:");
                mang[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("=> Khong tim thay khach hang!");
        }
    }

    // ===== Thong ke =====
    public void thongKe() {
        System.out.println("\t====== Thong ke ======");
        System.out.println("Tong so khach hang: " + n);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/khachhang.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                KhachHang kh = mang[i];
                sb.append(String.format("%s|%s|%s|%s%n",
                        kh.getMaKhachHang(), kh.getHoTen(), kh.getSoDienThoai(), kh.getDiaChi()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " khach hang vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/khachhang.txt");
            if (!Files.exists(file)) {
                System.out.println("File khachhang.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    KhachHang kh = new KhachHang(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                    add(kh);
                }
            }
            System.out.println("Da doc " + n + " khach hang tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


