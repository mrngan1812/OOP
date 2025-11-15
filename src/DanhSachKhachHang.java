import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachKhachHang {
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
            KhachHang[] moi = new KhachHang[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void add(KhachHang kh) {
        damBaoSucChua();
        mang[n++] = kh;
    }

    public void xemDanhSach() {
        if (n == 0) {
            System.out.println("Danh sach khach hang rong");
            return;
        }
        for (int i = 0; i < n; i++) {
            KhachHang kh = mang[i];
            System.out.printf("%s | %s | %s | %s%n",
                    kh.getMaKhachHang(), kh.getHoTen(), kh.getSoDienThoai(), kh.getDiaChi());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        KhachHang kh = new KhachHang();
        System.out.print("Ma KH: ");
        kh.setMaKhachHang(sc.nextLine().trim());
        System.out.print("Ho ten: ");
        kh.setHoTen(sc.nextLine().trim());
        System.out.print("So dien thoai: ");
        kh.setSoDienThoai(sc.nextLine().trim());
        System.out.print("Dia chi: ");
        kh.setDiaChi(sc.nextLine().trim());
        add(kh);
        System.out.println("Da them KH.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma KH can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaKhachHang().equals(ma)) {
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
        System.out.print("Nhap ma KH can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaKhachHang().equals(ma)) {
                System.out.print("Ho ten moi (bo trong de giu nguyen): ");
                String s = sc.nextLine(); if (!s.isBlank()) mang[i].setHoTen(s.trim());
                System.out.print("SDT moi (bo trong de giu nguyen): ");
                s = sc.nextLine(); if (!s.isBlank()) mang[i].setSoDienThoai(s.trim());
                System.out.print("Dia chi moi (bo trong de giu nguyen): ");
                s = sc.nextLine(); if (!s.isBlank()) mang[i].setDiaChi(s.trim());
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
            KhachHang kh = mang[i];
            if (kh.getMaKhachHang().toLowerCase().contains(kw)
                    || kh.getHoTen().toLowerCase().contains(kw)
                    || kh.getSoDienThoai().toLowerCase().contains(kw)) {
                System.out.printf("%s | %s | %s | %s%n",
                        kh.getMaKhachHang(), kh.getHoTen(), kh.getSoDienThoai(), kh.getDiaChi());
            }
        }
    }

    public void thongKe() {
        System.out.printf("Tong so khach hang: %d%n", n);
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


