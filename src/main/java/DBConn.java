import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {

    String driver = "org.mariadb.jdbc.Driver";
    final String DB_IP = "localhost";
    final String DB_PORT = "3306";
    final String DB_NAME = "dbdb";
    final String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // DB 연결
    public void DBconnect() {
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
    }

    // 로그인
    public boolean login(String login_userId, String login_userPw) {
        boolean logincheck = true; // 로그인 체크
        DBconnect();

        UserDto udto = new UserDto();
        udto.setUserId(login_userId);
        udto.setUserPw(login_userPw);

        try {
            //String sql = "SELECT * FROM `game`";
            String sql = "SELECT * FROM `game` WHERE `userid`=? and `userpw`=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, udto.getUserId());
            pstmt.setString(2, udto.getUserPw());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                udto.setName(rs.getString("name"));
                udto.setGusl(rs.getInt("gusl"));
                udto.setId(rs.getInt("id"));
                System.out.println("==============================");
                System.out.println(udto.getName() + " 님께서 로그인을 하셨습니다.");
                System.out.println(udto.getName() + " 님은 현재 " + udto.getGusl() + " 개의 구슬을 소지하고 계십니다.");

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

    // 회원가입
    public void save(String userId, String userPw, String name) {
        DBconnect();
        UserDto udto = new UserDto();
        udto.setUserId(userId);
        udto.setUserPw(userPw);
        udto.setName(name);

        try {
            String sql = "INSERT INTO `game` (`userid`, `userpw`, `name`) VALUES (?, ?, ?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, udto.getUserId());
            pstmt.setString(2, udto.getUserPw());
            pstmt.setString(3, udto.getName());

            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("회원가입에 실패 하셨습니다.");
            } else {
                System.out.println("회원가입에 성공 하셨습니다.");
            }

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

    public void gusl_update(int gusl, int id) {
        DBconnect();
        UserDto udto = new UserDto();

        try {
            String sql = "UPDATE `game` SET `gusl`=?, update_at = NOW() WHERE  `id`= ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gusl);
            pstmt.setInt(2, id);

            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("데이터 저장에 실패 하였습니다.");
            } else {
                System.out.println("데이터 저장에 성공 하였습니다.");
            }


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