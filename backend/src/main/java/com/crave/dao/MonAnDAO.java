package com.crave.dao;

import com.crave.model.MonAn;
import com.crave.util.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {
    private static final String BASE_SELECT = """
            SELECT MaMon, TenMon, MaDM, GiaBan, HinhAnh, MoTa, TrangThai
            FROM MonAn
            WHERE TrangThai = 'DangBan'
            """;

    public List<MonAn> getAllMonAn() {
        List<MonAn> list = new ArrayList<>();
        String sql = BASE_SELECT + " ORDER BY MaMon";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapMonAn(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<MonAn> getMonAnByDanhMuc(String maDM) {
        List<MonAn> list = new ArrayList<>();
        String sql = BASE_SELECT + " AND MaDM = ? ORDER BY MaMon";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDM);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapMonAn(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<MonAn> getPopularMonAn(int limit) {
        List<MonAn> list = new ArrayList<>();
        String sql = BASE_SELECT + " ORDER BY MaMon LIMIT ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapMonAn(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private MonAn mapMonAn(ResultSet rs) throws Exception {
        return new MonAn(
                rs.getString("MaMon"),
                rs.getString("TenMon"),
                rs.getString("MaDM"),
                rs.getDouble("GiaBan"),
                rs.getString("HinhAnh"),
                rs.getString("MoTa"),
                rs.getString("TrangThai")
        );
    }
}
