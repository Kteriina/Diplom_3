package praktikum.burger.client;


import io.qameta.allure.Step;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;

import java.util.Locale;

public class UserClient {


    @Step("Удаление пользователя")
    public static String deleteUser(String accessToken){
        Response response = given()
                .header("Authorization", accessToken)
                .when()
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");

        if (response.getStatusCode() == 200) {
            System.out.println("Пользователь успешно удален");
            return accessToken;
        } else {
            System.out.println("Не удалось удалить пользователя");
            return null;
        }
    }

    @Step("Логин под существующим пользователем.")
    public static String checkRequestAuthLogin(User user) {
        Response response = given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                //.post("/api/auth/login");
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");

        if (response.getStatusCode() == 200) {
            String accessToken = response.jsonPath().getString("accessToken");
            return accessToken;
        } else {
            System.out.println("Ошибка: Вход не выполнен");
            return null;
        }
    }
}

