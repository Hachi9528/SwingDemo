package main.java.dao;

import main.java.model.Msg;

import java.sql.*;
import java.util.*;



/**
 * @function recevMsg()
 * @function sendMsg()
 */

public class MsgDao {
    /**
     *
     * @param id -- the uid of user
     * @return list -- the receive msg
     * @throws SQLException
     */
    public List<Msg> getMsg(String id) throws SQLException {
        String sql = "SELECT tittle, content FROM msg WHERE recevId=" + id;
        DbUtil dbUtil = new DbUtil();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        List<Msg> lst = new ArrayList<>();

        try {
            conn = dbUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs == null) return Collections.emptyList();
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnCount = rsmd.getColumnCount();
            while(rs.next()) {
                Msg msgEntity = new Msg();
                msgEntity.setTittle(rs.getString("tittle"));
                msgEntity.setContent(rs.getString("content"));
                lst.add(msgEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return lst;
    }

    /**
     *
     * @param sendId -- 发送者id
     * @param recevId -- 接受者id
     * @param title -- 标题
     * @param content -- 信件内容
     * @throws SQLException
     */
    public void insertMsg(String sendId, String recevId, String title, String content) throws SQLException {
        String sql = "INSERT INTO msg(sendId, recevId, tittle, content) VALUES (" + sendId +
                ", " + recevId + ", " + title + ", " + content + ")";
        DbUtil dbUtil = new DbUtil();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

    }

}
