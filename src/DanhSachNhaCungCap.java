import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class DanhSachNhaCungCap {
    private NhaCungCap[] mang;
    private int n;

    public DanhSachNhaCungCap() { this(20); }

    public DanhSachNhaCungCap(int sucChuaBanDau) {
        if (sucChuaBanDau <= 0) sucChuaBanDau = 10;
        this.mang = new NhaCungCap[sucChuaBanDau];
        this.n = 0;
    }

    private void damBaoSucChua() {
        if (n >= mang.length) {
            NhaCungCap[] moi = new NhaCungCap[mang.length * 2];
            System.arraycopy(mang, 0, moi, 0, n);
            mang = moi;
        }
    }

    public void xemDanhSach() {
        if (n == 0) { System.out.println("Danh sach NCC rong"); return; }
        for (int i = 0; i < n; i++) {
            NhaCungCap ncc = mang[i];
            System.out.printf("%s | %s | %s | %s%n",
                    ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getDiaChi());
        }
    }

    public void them() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        NhaCungCap ncc = new NhaCungCap();
        System.out.print("Ma NCC: "); ncc.setMaNCC(sc.nextLine().trim());
        System.out.print("Ten NCC: "); ncc.setTenNCC(sc.nextLine().trim());
        System.out.print("SDT: "); ncc.setSoDienThoai(sc.nextLine().trim());
        System.out.print("Dia chi: "); ncc.setDiaChi(sc.nextLine().trim());
        damBaoSucChua();
        mang[n++] = ncc;
        System.out.println("Da them NCC.");
    }

    public void xoa() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma NCC can xoa: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            if (mang[i].getMaNCC().equals(ma)) {
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
        System.out.print("Nhap ma NCC can sua: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            NhaCungCap nc = mang[i];
            if (nc.getMaNCC().equals(ma)) {
                System.out.print("Ten moi: "); String s = sc.nextLine(); if (!s.isBlank()) nc.setTenNCC(s.trim());
                System.out.print("SDT moi: "); s = sc.nextLine(); if (!s.isBlank()) nc.setSoDienThoai(s.trim());
                System.out.print("Dia chi moi: "); s = sc.nextLine(); if (!s.isBlank()) nc.setDiaChi(s.trim());
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
            NhaCungCap ncc = mang[i];
            if (ncc.getMaNCC().toLowerCase().contains(kw)
                    || ncc.getTenNCC().toLowerCase().contains(kw)
                    || ncc.getSoDienThoai().toLowerCase().contains(kw)) {
                System.out.printf("%s | %s | %s | %s%n",
                        ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getDiaChi());
            }
        }
    }

    public void thongKe() {
        System.out.printf("Tong so NCC: %d%n", n);
    }

    public void ghiFile() {
        try {
            Path file = Path.of("data/nhacungcap.txt");
            Files.createDirectories(file.getParent());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                NhaCungCap ncc = mang[i];
                sb.append(String.format("%s|%s|%s|%s%n",
                        ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSoDienThoai(), ncc.getDiaChi()));
            }
            Files.writeString(file, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Da ghi " + n + " nha cung cap vao file.");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile() {
        try {
            Path file = Path.of("data/nhacungcap.txt");
            if (!Files.exists(file)) {
                System.out.println("File nhacungcap.txt chua ton tai.");
                return;
            }
            n = 0;
            for (String line : Files.readAllLines(file)) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    NhaCungCap ncc = new NhaCungCap(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                    damBaoSucChua();
                    mang[n++] = ncc;
                }
            }
            System.out.println("Da doc " + n + " nha cung cap tu file.");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }
}


