package edu.sm;

import edu.sm.dao.CartDao;
import edu.sm.dao.UserDao; // Assume you have a UserDao for managing user data
import edu.sm.dto.Cart;
import edu.sm.dto.User;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class MainApplication {
    private static final String URL = "jdbc:mysql://210.119.34.205/shopdb";
    private static final String USER = "smuser";
    private static final String PASSWORD = "111111";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        UserService userService = new UserService();
        // 장바구니 관리
        CartDao cartDao = new CartDao();

        try {
            connection = getConnection();
            System.out.println("데이터베이스에 연결되었습니다.");

            UserDao userDao = new UserDao();
            String userId = null;

            while (true) {
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.print("선택하세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    // 로그인 기능
                    System.out.print("User ID: ");
                    userId = scanner.nextLine();
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
                    userId = scanner.nextLine();
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
                    scanner.nextLine(); // Consume newline

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
                        userDao.insert(newUser, connection);
                        System.out.println("회원가입 성공!");
                    } catch (DuplicatedIdException e) {
                        System.out.println("중복된 아이디입니다: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("회원가입 실패: " + e.getMessage());
                    }
                } else {
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                }
            }


            while (true) {
                manageCarts(scanner, cartDao, connection);
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                scanner.close();
            } catch (SQLException e) {
                System.out.println("연결 해제 중 오류 발생: " + e.getMessage());
            }
        }
    }

    private static void manageCarts(Scanner scanner, CartDao cartDao, Connection connection) {
        while (true) {
            System.out.println("1. 장바구니 추가");
            System.out.println("2. 장바구니 조회");
            System.out.println("3. 장바구니 수정");
            System.out.println("4. 장바구니 삭제");
            System.out.println("5. 돌아가기");
            System.out.print("선택하세요: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCart(scanner, cartDao, connection);
                    break;
                case 2:
                    selectCart(scanner, cartDao, connection);
                    break;
                case 3:
                    updateCart(scanner, cartDao, connection);
                    break;
                case 4:
                    deleteCart(scanner, cartDao, connection);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private static void addCart(Scanner scanner, CartDao cartDao, Connection connection) {
        System.out.print("제품 ID를 입력하세요: ");
        int productId = scanner.nextInt();
        System.out.print("수량을 입력하세요: ");
        int quantity = scanner.nextInt();
        System.out.print("사용자 ID를 입력하세요: ");
        String userId = scanner.next(); // 사용자 ID를 String으로 받음

        Cart cart = Cart.builder()
                .createAt(new Date(System.currentTimeMillis()))
                .productId(productId)
                .quantity(quantity)
                .userId(userId)
                .build();

        try {
            cartDao.insert(cart, connection);
            System.out.println("장바구니가 성공적으로 추가되었습니다.");
        } catch (DuplicatedIdException e) {
            System.out.println("중복된 장바구니입니다: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("장바구니 추가 실패: " + e.getMessage());
        }
    }

    private static void selectCart(Scanner scanner, CartDao cartDao, Connection connection) {
        System.out.print("장바구니 ID를 입력하세요: ");
        int idToSelect = scanner.nextInt();
        try {
            Cart selectedCart = cartDao.select(idToSelect, connection);
            if (selectedCart != null) {
                System.out.println("장바구니 정보: " + selectedCart);
            } else {
                System.out.println("ID " + idToSelect + "에 해당하는 장바구니 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 조회 실패: " + e.getMessage());
        }
    }

    private static void updateCart(Scanner scanner, CartDao cartDao, Connection connection) {
        System.out.print("수정할 장바구니 ID를 입력하세요: ");
        int idToUpdate = scanner.nextInt();
        System.out.print("수정할 수량을 입력하세요: ");
        int newQuantity = scanner.nextInt();

        Cart updateCart = Cart.builder()
                .quantity(newQuantity)
                .sbagId(idToUpdate) // Assuming Cart has a cartId field
                .build();

        try {
            cartDao.update(updateCart, connection);
            System.out.println("장바구니가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            System.out.println("장바구니 수정 실패: " + e.getMessage());
        }
    }

    private static void deleteCart(Scanner scanner, CartDao cartDao, Connection connection) {
        System.out.print("삭제할 장바구니 ID를 입력하세요: ");
        int idToDelete = scanner.nextInt();
        try {
            boolean isDeleted = cartDao.delete(idToDelete, connection);
            if (isDeleted) {
                System.out.println("장바구니가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("ID " + idToDelete + "에 해당하는 장바구니를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 삭제 실패: " + e.getMessage());
        }
    }
}

