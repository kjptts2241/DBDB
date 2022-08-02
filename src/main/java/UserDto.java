public class UserDto {

    private String userId; // 유저id
    private String userPw; // 패스워드
    private String name; // 이름
    private int gusl; // 구슬 갯수
    private int id; // pk

    public void UserDto(String userId, String userPw, String name, int gusl, int id) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.gusl = gusl;
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGusl() {
        return gusl;
    }

    public void setGusl(int gusl) {
        this.gusl = gusl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
