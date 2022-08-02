import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {

    public static boolean login(String login_userId, String login_userPw, String login_name) {
        boolean logincheck = true; // 로그인 체크
         String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        System.out.println(DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            //String sql = "SELECT * FROM `game`";
            String sql = "SELECT * FROM `game` WHERE `userid`=? and `userpw`=? and `name`=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login_userId);
            pstmt.setString(2, login_userPw);
            pstmt.setString(3, login_name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("==============================");
                System.out.println(login_name + "님께서 로그인을 하셨습니다.");
                logincheck = false;
            } else {
                System.out.println("존재하지 않는 유저 정보입니다.");
                logincheck = true;
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return logincheck;
    }

    public static void save(String userId, String userPw, String name) {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
        System.out.println(DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }

        try {
            String sql = "INSERT INTO `game` (`userid`, `userpw`, `name`) VALUES (?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            pstmt.setString(3, name);

            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("데이터 넣기 실패");
            } else {
                System.out.println("데이터 넣기 성공");
            }

            System.out.println("생성하신 아이디:" + userId);
            System.out.println("생성하신 패스워드:" + userPw);
            System.out.println("생성하신 이름:" + name);
            System.out.println("회원가입에 성공 하셨습니다.");

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}