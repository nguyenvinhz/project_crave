package com.crave.servlet;

import com.crave.dao.DanhMucDAO;
import com.crave.dao.MonAnDAO;
import com.crave.model.DanhMuc;
import com.crave.model.MonAn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/api/home", "/api/categories", "/api/foods"})
public class HomeServlet extends HttpServlet {
    private final DanhMucDAO danhMucDAO = new DanhMucDAO();
    private final MonAnDAO monAnDAO = new MonAnDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json;charset=UTF-8");

        String path = request.getServletPath();
        String category = request.getParameter("category");

        try (PrintWriter out = response.getWriter()) {
            if ("/api/categories".equals(path)) {
                out.print(toCategoryJson(danhMucDAO.getAllDanhMuc()));
                return;
            }

            if ("/api/foods".equals(path)) {
                List<MonAn> foods = category == null || category.isBlank()
                        ? monAnDAO.getAllMonAn()
                        : monAnDAO.getMonAnByDanhMuc(category);
                out.print(toFoodJson(foods));
                return;
            }

            out.print("{\"categories\":" + toCategoryJson(danhMucDAO.getAllDanhMuc())
                    + ",\"foods\":" + toFoodJson(monAnDAO.getPopularMonAn(8)) + "}");
        }
    }

    private String toCategoryJson(List<DanhMuc> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            DanhMuc item = list.get(i);
            sb.append(String.format(
                    "{\"maDM\":\"%s\",\"tenDM\":\"%s\",\"moTa\":\"%s\",\"hinhAnh\":\"%s\"}",
                    escape(item.getMaDM()),
                    escape(item.getTenDM()),
                    escape(item.getMoTa()),
                    escape(item.getHinhAnh())
            ));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private String toFoodJson(List<MonAn> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            MonAn item = list.get(i);
            sb.append(String.format(
                    "{\"maMon\":\"%s\",\"tenMon\":\"%s\",\"maDM\":\"%s\",\"giaBan\":%s,\"hinhAnh\":\"%s\",\"moTa\":\"%s\",\"trangThai\":\"%s\"}",
                    escape(item.getMaMon()),
                    escape(item.getTenMon()),
                    escape(item.getMaDM()),
                    (long) item.getGiaBan(),
                    escape(item.getHinhAnh()),
                    escape(item.getMoTa()),
                    escape(item.getTrangThai())
            ));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private String escape(String value) {
        if (value == null) return "";
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
