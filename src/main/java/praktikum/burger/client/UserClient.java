package praktikum.burger.client;


import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;

import java.util.Locale;

public class UserClient {

    private static final String DELETE_USER = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String LOGIN_USER = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String CREATE_USER = "https://stellarburgers.nomoreparties.site/api/auth/register/";

    @Step("Успешное создание уникального пользователя.")
    public static String postCreateNewUser(User user) {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        } else {
            System.out.println("Ошибка: Регистрация не удалась");
            return null;
        }
    }


    @Step("Удаление пользователя")
    public static Response delete(String accessToken) {
        return RestAssured.given()
                .headers("Authorization", accessToken)
                .delete(DELETE_USER);
    }

    @Step("Логин под существующим пользователем.")
    public static String checkRequestAuthLogin(User user) {
        Response response = given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_USER);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("accessToken");
        } else {
            System.out.println("Ошибка: Вход не выполнен");
            return null;
        }
    }
}

