package dgf.xfa22c.maven.telegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MiniBotProject {

    public static void main(String[] args) {

        try{
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MiniBotProjectService());
            System.out.println("Бот запущен");

        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }

    }

}
