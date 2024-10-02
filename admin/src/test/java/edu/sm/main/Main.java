package edu.sm.main;

import edu.sm.user.UserSelect;
import edu.sm.user.UserSelectOne;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=== 관리자 모드 ===");
            System.out.println("1. 회원 관리");
            System.out.println("2. 배송 관리");
            System.out.println("3. 상품 관리");
            System.out.println("4. 리뷰 관리");
            System.out.println("5. 통계 관리");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageMembers();
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("관리자 모드를 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private static void manageMembers() {
        System.out.println("=== 회원 관리 ===");
        System.out.println("1. 전체 회원 조회");
        System.out.println("2. 상세 회원 조회");
        System.out.println("0. 뒤로가기");
        System.out.print("선택: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1:
                // 전체 회원 조회 기능 호출
                UserSelect.main(null);
                break;
            case 2:
                // 특정 회원 조회 기능 호출
                UserSelectOne.main(null);
                break;
            case 0:
                System.out.println("이전 메뉴로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }
}
