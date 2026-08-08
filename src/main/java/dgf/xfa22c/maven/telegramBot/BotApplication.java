package dgf.xfa22c.maven.telegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

public class BotApplication {

    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ExampleNewTelegramBot());
            System.out.println("Бот запущен");

            Scanner sc  = new Scanner(System.in);

            while (true) {
                System.out.print("Введите chatId: ");
                String chatId = sc.nextLine();
                if (chatId.equals("exit")) break;

                System.out.print("Введите сообщение: ");
                String text = sc.nextLine();

                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(text);
                ExampleNewTelegramBot bot = new ExampleNewTelegramBot();
                bot.execute(message);

                System.out.println("Сообщение отправлено.");
            }
        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
        }

    }

}
