/**
 * Created by LenaV on 12.03.2017.
 */
public class Task30_2 {
    /*
    Task 30-2. Create By variables, which covers all possible types of location in Selenium WebDriver.
    WebElement loginField = driver.findElement(By.id("Username"));
    WebElement submitButton = driver.findElement(By.className("submit-button"));
    WebElement submitButton2 = driver.findElement(By.linkText("submit"));
    WebElement checkBox = driver.findElement(By.xpath("//label[@for='Remember']/span"));
    WebElement passwordInput = driver.findElement(By.cssSelector("#Password"));

Ошибки:
--1. В одном классе точно видел, что у тебя заимпорчен ассерт из JUnit, не смотрел юзаешь ли ты его, но импорт нужно точно убрать, используем только TestNG
2. Во всех тестовых классах давай давай полям класса модификаторы (для FirefoxDriver и т.д.)
--3. Иметь поле FirefoxDriver плохо, лучше иметь WebDriver, это позволит масштабировать твой код минимальными затратами
4. В Login один тест, который делает сразу несколько пунктов, разнеси, пожалуйста, такой код нечитабелен, тест имеет несколько проверок, что тоже плохо
5. DDT тест, покрывающий функциональность логина - это ввод верных данных, ввод неверного пароля(пустого например) и проверка, что пишется правильная ошибка, ввод двух пустых полей и проверка, что другая ошибка
6. AlertsTest - неверно, тесты не должны содержать таких блоков try-catch, ибо в реальности будут пропускаться баги, поэтому избавься от них
7. Локаторы в AlertsTest содержат индексы - это плохие локаторы, нужно заменить их другими
8. FramesTest - тест содержит две проверки, надо одну. Если ты хочешь две проверки - то делай два авто-теста
*/
}
