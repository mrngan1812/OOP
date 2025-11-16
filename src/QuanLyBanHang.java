import java.util.Scanner;

public class QuanLyBanHang {
    private final DanhSachSanPham dsSanPham = new DanhSachSanPham(20);
    private final DanhSachKhachHang dsKhachHang = new DanhSachKhachHang();
    private final DanhSachNhanVien dsNhanVien = new DanhSachNhanVien();
    private final DanhSachNhaCungCap dsNCC = new DanhSachNhaCungCap();
    private final DanhSachHangSanPham dsHang = new DanhSachHangSanPham();
    private final DanhSachLoaiSP dsLoai = new DanhSachLoaiSP();
    private final DanhSachHoaDon dsHoaDon = new DanhSachHoaDon();
    private final DanhSachChiTietHoaDon dsCTHD = new DanhSachChiTietHoaDon();
    private final DanhSachPhieuNhapHang dsPhieuNhap = new DanhSachPhieuNhapHang();
    private final DanhSachChiTietNhapHang dsCTNhap = new DanhSachChiTietNhapHang();

    public void docTatCaFile() {
        System.out.println("=== Dang doc du lieu tu file... ===");
        dsSanPham.docFile();
        dsKhachHang.docFile();
        dsNhanVien.docFile();
        dsNCC.docFile();
        dsHang.docFile();
        dsLoai.docFile();
        dsHoaDon.docFile();
        dsCTHD.docFile();
        dsPhieuNhap.docFile();
        dsCTNhap.docFile();
        System.out.println("=== Hoan thanh doc du lieu. ===\n");
    }

    public void ghiTatCaFile() {
        System.out.println("\n=== Dang ghi du lieu vao file... ===");
        dsSanPham.ghiFile();
        dsKhachHang.ghiFile();
        dsNhanVien.ghiFile();
        dsNCC.ghiFile();
        dsHang.ghiFile();
        dsLoai.ghiFile();
        dsHoaDon.ghiFile();
        dsCTHD.ghiFile();
        dsPhieuNhap.ghiFile();
        dsCTNhap.ghiFile();
        System.out.println("=== Hoan thanh ghi du lieu. ===");
    }

    public void menuChinh() {
        docTatCaFile();
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU CHINH ===");
            System.out.println("1. Quan ly san pham");
            System.out.println("2. Quan ly hoa don - Chi tiet hoa don");
            System.out.println("3. Quan ly phieu nhap - Chi tiet nhap");
            System.out.println("4. Quan ly khach hang");
            System.out.println("5. Quan ly nhan vien");
            System.out.println("6. Quan ly nha cung cap");
            System.out.println("7. Quan ly hang san pham");
            System.out.println("8. Quan ly loai san pham");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int chon;
            try { chon = Integer.parseInt(line); } catch (NumberFormatException e) { continue; }
            switch (chon) {
                case 1: menuQLSP(); break;
                case 2: menuQLHoaDon(); break;
                case 3: menuQLPhieuNhap(); break;
                case 4: menuQLKhachHang(); break;
                case 5: menuQLNhanVien(); break;
                case 6: menuQLNCC(); break;
                case 7: menuQLHang(); break;
                case 8: menuQLLoaiSP(); break;
                case 0:
                    ghiTatCaFile();
                    System.out.println("Tam biet!");
                    return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLSP() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly san pham ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsSanPham.xuatDanhSach(); break;
                case 2: dsSanPham.themSanPham(); break;
                case 3: dsSanPham.xoaSanPhamTheoMa(); break;
                case 4: dsSanPham.suaSanPhamTheoMa(); break;
                case 5: dsSanPham.timKiem(); break;
                case 6: dsSanPham.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLHoaDon() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly hoa don ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("7. Quan ly chi tiet hoa don");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsHoaDon.xemDanhSach(); break;
                case 2: dsHoaDon.them(); break;
                case 3: dsHoaDon.xoa(); break;
                case 4: dsHoaDon.sua(); break;
                case 5: dsHoaDon.timKiem(); break;
                case 6: dsHoaDon.thongKe(); break;
                case 7: menuQLCTHD(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLCTHD() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly chi tiet hoa don ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsCTHD.xemDanhSach(); break;
                case 2: dsCTHD.them(); break;
                case 3: dsCTHD.xoa(); break;
                case 4: dsCTHD.sua(); break;
                case 5: dsCTHD.timKiem(); break;
                case 6: dsCTHD.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLPhieuNhap() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly phieu nhap ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("7. Quan ly chi tiet nhap hang");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsPhieuNhap.xemDanhSach(); break;
                case 2: dsPhieuNhap.them(); break;
                case 3: dsPhieuNhap.xoa(); break;
                case 4: dsPhieuNhap.sua(); break;
                case 5: dsPhieuNhap.timKiem(); break;
                case 6: dsPhieuNhap.thongKe(); break;
                case 7: menuQLCTNhap(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLCTNhap() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly chi tiet nhap hang ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsCTNhap.xemDanhSach(); break;
                case 2: dsCTNhap.them(); break;
                case 3: dsCTNhap.xoa(); break;
                case 4: dsCTNhap.sua(); break;
                case 5: dsCTNhap.timKiem(); break;
                case 6: dsCTNhap.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLLoaiSP() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly loai san pham ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsLoai.xemDanhSach(); break;
                case 2: dsLoai.them(); break;
                case 3: dsLoai.xoa(); break;
                case 4: dsLoai.sua(); break;
                case 5: dsLoai.timKiem(); break;
                case 6: dsLoai.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLKhachHang() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly khach hang ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsKhachHang.xemDanhSach(); break;
                case 2: dsKhachHang.them(); break;
                case 3: dsKhachHang.xoa(); break;
                case 4: dsKhachHang.sua(); break;
                case 5: dsKhachHang.timKiem(); break;
                case 6: dsKhachHang.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLNhanVien() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly nhan vien ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsNhanVien.xemDanhSach(); break;
                case 2: dsNhanVien.them(); break;
                case 3: dsNhanVien.xoa(); break;
                case 4: dsNhanVien.sua(); break;
                case 5: dsNhanVien.timKiem(); break;
                case 6: dsNhanVien.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLNCC() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly nha cung cap ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsNCC.xemDanhSach(); break;
                case 2: dsNCC.them(); break;
                case 3: dsNCC.xoa(); break;
                case 4: dsNCC.sua(); break;
                case 5: dsNCC.timKiem(); break;
                case 6: dsNCC.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }

    private void menuQLHang() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Quan ly hang san pham ---");
            System.out.println("1. Xem ds");
            System.out.println("2. Them");
            System.out.println("3. Xoa");
            System.out.println("4. Sua");
            System.out.println("5. Tim kiem");
            System.out.println("6. Thong ke");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            String line = sc.nextLine().trim();
            int c; try { c = Integer.parseInt(line); } catch (Exception e) { continue; }
            switch (c) {
                case 1: dsHang.xemDanhSach(); break;
                case 2: dsHang.them(); break;
                case 3: dsHang.xoa(); break;
                case 4: dsHang.sua(); break;
                case 5: dsHang.timKiem(); break;
                case 6: dsHang.thongKe(); break;
                case 0: return;
                default: System.out.println("Khong hop le!");
            }
        }
    }
}


