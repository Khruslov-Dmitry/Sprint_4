package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String name;
    private final boolean result;

    public AccountTest(String name, boolean result) {
        this.name = name;
        this.result = result;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} - входящее имя, {1} - ожидаемый результат")
    public static Object[][] dataForTest() {
        return new Object[][] {
                { "Тимоти Шаламе", true}, // проверка условия "в строке есть только один пробел"
                { "ТимотейШевроле", false}, // проверка условия "в строке есть только один пробел"
                { "Тимоти  Шаламе", false}, // проверка условия "в строке есть только один пробел"
                { "ТШ", false}, // проверка нижней границы диапазона - 2 символа
                { "Т Ш", true}, // проверка нижней границы диапазона - 3 символа
                { "Т Ша", true}, // проверка нижней границы диапазона - 4 символа
                { "Тимотей Шаламеевич", true}, // проверка верхней границы диапазона - 18 символов
                { "Тимотей Шаламейевич", true}, // проверка верхней границы диапазона - 19 символов
                { "Тимотей Шаламейевичъ", false}, // проверка верхней границы диапазона - 20 символов
                { " ТимотиШ", false}, // проверка условия "пробел стоит не в начале строки"
                { "ТШаламе ", false}, // проверка условия "пробел стоит не в конце строки"
        };
    }

    @Test
    public void checkNameToEmbossTest() {

        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        Assert.assertEquals(result, actual);
    }
}
