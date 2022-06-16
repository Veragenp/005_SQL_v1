package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLUtils;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class DBIntegrationTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanUp() {
        SQLUtils.clearTables();
    }

    @Test
    void shouldLogin() {
        val validUser = DataHelper.getAuthInfo();
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        verificationPage.validVerify(SQLUtils.getVerificationCode());
    }

    @Test
    void shouldBlock() {
        val invalidUser = DataHelper.getOtherAuthInfo();
        val loginPage = new LoginPage();
        loginPage.login(invalidUser);
        loginPage.cleanFields();
        loginPage.login(invalidUser);
        loginPage.cleanFields();
        loginPage.login(invalidUser);
        loginPage.blockNotice();
    }
//    @Test
//    @SneakyThrows
//    void stubTest() {
//        Faker faker = new Faker();
//        String usersSQL = "SELECT password FROM users WHERE login = ?";//шаблон команды изьятия данных из таблицы users
//
//        try (
//                Connection conn = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/app-db", "user", "pass"
//                );
//                PreparedStatement userStatement = conn.prepareStatement(usersSQL); //подготовленное состояние
//        ) {
//          userStatement.setString(1,"vasya");
//          try (ResultSet rs = userStatement.executeQuery()) {
//              while (rs.next()) {
//                  //int id = rs.getInt("id");
//                  //String login = rs.getString("login");
//                  String password = rs.getString("password");
//                  System.out.println(password + "");
//              }
//          }
        }






