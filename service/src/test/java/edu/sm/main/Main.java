package edu.sm.main;



        import edu.sm.dto.User;
        import edu.sm.service.UserService;

        import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            // 로그인 기능
            System.out.print("User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            try {
                User user = userService.login(userId, password);
                System.out.println("로그인 성공! 환영합니다, " + user.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (choice == 2) {
            // 회원가입 기능
            System.out.print("User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Phone number: ");
            String phoneno = scanner.nextLine();
            System.out.print("Gender (1 for Female, 0 for Male): ");
            int gender = scanner.nextInt();
            System.out.print("Age: ");
            int age = scanner.nextInt();

            User newUser = User.builder()
                    .user_id(userId)
                    .name(name)
                    .email(email)
                    .pwd(password)
                    .phoneno(phoneno)
                    .gender(gender)
                    .age(age)
                    .build();

            try {
                userService.add(newUser);
                System.out.println("회원가입 성공!");
            } catch (Exception e) {
                System.out.println("회원가입 실패: " + e.getMessage());
            }
        }

        scanner.close();
    }
}




