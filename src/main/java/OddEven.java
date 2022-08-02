import java.util.Random;
import java.util.Scanner;

public class OddEven {
    // 변수
    int userGu = 10; // 유저가 가진 구슬 갯수
    int yourGu = 10; // 상대가 가진 구슬 갯수
    int bet = 0; // 유저가 배팅하는 변수
    String dab; // 컴퓨터 답
    String answer; // 유저의 답

    Random random = new Random();
    Scanner sc = new Scanner(System.in);
    GameDto gdto = new GameDto();

    // 메소드

    // 인트로
    public void intro(int userGu, int yourGu) {
        // 인트로
        System.out.println("=======================================");
        System.out.println("오징어 게임에 오신것을 환영합니다");
        System.out.println("이번 게임은 구슬 게임입니다.");
        System.out.println("당신과 나는 각각 10개의 구슬을 가지고 있습니다.");
        System.out.println("10개의 구슬 다 잃으면 죽습니다");
        System.out.println("게임을 시작합니다.");
        System.out.println("배팅 하세요");
        System.out.println("=======================================");

        // 배팅 구슬 설정
        gdto.setUserGu(userGu); // 나의 구슬
        gdto.setYourGu(yourGu); // 상대방의 구슬

        // 현재 구슬 상황
        System.out.println("나의 시작 구슬 갯수 : " + gdto.getUserGu() + " / 상대의 시작 구슬 갯수 : " + gdto.getYourGu());
        System.out.println("=======================================");
    }

    // 배팅 기능
    public void betting() {
        // 배팅 시작
        while (true) {
            try {
                System.out.print("배팅 갯수 입력 >> ");
                int bet = sc.nextInt(); // 숫자를 입력 받아서 bet 변수에 저장
                // 만약 내가 가진 갯수 보다 많이 배팅을 하면
                // 안된다 다시 배팅해라
                if (gdto.getBet() > gdto.getUserGu()) {
                    System.out.println("소지하고 있는 구슬과 갯수가 맞지 않습니다");
                } else if (gdto.getYourGu() < bet) {
                    System.out.println("배팅한 갯수가 상대의 구슬 보다 많습니다");
                } else {
                    gdto.setBet(bet); // 구슬 배팅 저장
                    System.out.println(gdto.getBet() + "개의 구슬 배팅");
                    System.out.println("=======================================");
                    break; // 무한 반복 종료
                }
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요!!");
                System.out.println("=======================================");
                sc.nextLine();
            }
        }
    }

    // 컴퓨터가 하는 기능
    public String comTurn() {
        // 컴퓨터가 문제를 낸다
        int rNum = random.nextInt(10) + 1; // 1~10까지의 숫자가 랜덤으로 나오게 된다
        // System.out.println("컴퓨타가 낸 문제(구슬갯수): " + rNum);
        dab = "짝"; // 상대의 답
        if (rNum % 2 == 1) {
            dab = "홀";
            // System.out.println("홀");
        } else {
            // System.out.println("짝");
        }
        return dab;
    }

    // 유저가 하는 기능
    public String userTurn() {
        // 유저가 (홀, 짝) 답 입력
        while(true) {
            System.out.print("정답 입력(홀, 짝) >> ");
            answer = sc.next();
            if(answer.equals("짝") || answer.equals("홀")) {
                break;
            } else {
                System.out.println("[홀, 짝] 중 하나를 입력해주시기 바랍니다.");
            }
        }
        return answer;
    }

    // 정답 / 오답 판별
    public void correctAnswer() {
        // 맞을을 시, 틀렸을 시
        if (userTurn().equals(comTurn())) { // 맞았을 시
            // 맞으면 구슬을 더하고 반복 / 틀리면 상대의 구슬을 뺀다
            gdto.userGuAdd();
            gdto.yourGuRemove();
            System.out.println(gdto.getBet() + "개의 구슬을 획득 했습니다.");
            System.out.println("정답입니다");
            System.out.println("=======================================");
            System.out.println("나의 구슬 갯수 : " + gdto.getUserGu() + " / 상대의 구슬 갯수 : " + gdto.getYourGu());
            System.out.println("=======================================");

        } else if (!userTurn().equals(comTurn())) { // 틀렸을 시
            // 틀리면 갯수를 빼고 반복 / 상대는 더한다
            gdto.userGuRemove();
            gdto.yourGuAdd();
            System.out.println(gdto.getBet() + "개의 구슬을 뺏겼습니다.");
            System.out.println("오답입니다");
            System.out.println("=======================================");
            System.out.println("나의 구슬 갯수 : " + gdto.getUserGu() + " / 상대의 구슬 갯수 : " + gdto.getYourGu());
            System.out.println("=======================================");
        }
    }

    // 게임 종료
    public boolean gameOver() {
        boolean isGameOver = true;
        // 내가 가진 구슬의 갯수가 20이 되면 승리
        if (gdto.getUserGu() >= 20) {
            System.out.println("이겼습니다.");
            System.out.println("=======================================");
            isGameOver = false;
        } else if (gdto.getUserGu() <= 0) { // 내가 가진 구슬이 갯수가 0이 되면 패배
            System.out.println("졌습니다.");
            System.out.println("=======================================");
            isGameOver = false;
        }
        return isGameOver;
    }
}
