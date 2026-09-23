package com.crave.dao;

import com.crave.model.DanhMuc;
import com.crave.util.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = """
                SELECT d.MaDM, d.TenDM, d.MoTa,
                       (
                           SELECT m.HinhAnh
                           FROM MonAn m
                           WHERE m.MaDM = d.MaDM
                             AND m.TrangThai = 'DangBan'
                             AND m.HinhAnh IS NOT NULL
                             AND m.HinhAnh <> ''
                           LIMIT 1
                       ) AS HinhAnh
                FROM DanhMuc d
                ORDER BY d.MaDM
                """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DanhMuc(
                        rs.getString("MaDM"),
                        rs.getString("TenDM"),
                        rs.getString("MoTa"),
                        rs.getString("HinhAnh")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
