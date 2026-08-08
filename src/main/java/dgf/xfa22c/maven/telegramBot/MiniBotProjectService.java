package dgf.xfa22c.maven.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("deprecation")
public class MiniBotProjectService extends TelegramLongPollingBot {
    public Scanner sc = new Scanner(System.in);


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            System.out.println("Сообщение " + chatId + " " +  update.getMessage().getText());
            String message = update.getMessage().getText();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);


            if (message.startsWith("/start")){
                sendMessage(chatId, "||Бот запущен||");
            }
            if (message.startsWith("/image")){
                String fileName = message.replace("/image ", "").trim();
                if (fileName.isEmpty()) {
                    sendMessage(chatId, "Укажи название файла после /image + (Название)");
                } else {
                    sendImage(chatId, fileName + ".png");
                }
            }
            if (message.startsWith("/contact")){
                sendMessage(chatId, "Сообщение отправлено создателю.");
                System.out.println("Получено сообщение" + " " + message);
                reply(chatId);
            }
            if (message.startsWith("/help")){
                sendMessage(chatId, "-/listImages-, -/contact-, -/image 'imageName'- -/listVideos-" +
                        " -/video 'videoName'-   .png или .mp4 НЕ ТРЕБУЕТСЯ, только название");
            }
            if (message.startsWith("/listImages")){
                sendImagesList(chatId);
            }
            if (message.startsWith("/video")){
                String fileName = message.replace("/video ", "").trim();
                if (fileName.isEmpty()) {
                    sendMessage(chatId, "Укажи название файла после /video + (Название)");
                } else {
                    sendVideo(chatId, fileName);
                }
            }
            if (message.startsWith("/listVideos")){
                videosList(chatId);
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "Example_study_bot";
    }

    @Override
    public String getBotToken() {
        return "8892606056:AAET7EGv2Opn674iI3XY1PjSRw_NpA7wQC4";
    }

    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("Ошибка отправки: " + e.getMessage());
        }
    }

    public  void sendImage(String chatId, String fileName) {
        try{
            File file = new File("images/" + fileName.toLowerCase());
            if (!file.exists()) {
                sendMessage(chatId, "Картинка не найдена.");
                return;
            }
                InputFile inputFile = new InputFile(file);
            SendPhoto  sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(inputFile);
            execute(sendPhoto);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    private void reply(String chatId) {
        Thread replyThread = new Thread(() -> {
            System.out.println("Режим ответа. Введите сообщение для пользователя " + chatId + " (exit для выхода):");

        while(true){
            String input = sc.nextLine();
            if(input.equals("exit")){
                System.out.println("Выход из режима ответа.");
                break;
            }else{
                sendMessage(chatId, "Ответ от создателя: " + input);
                System.out.println("Отправлено.");
            }
        }
        });
        replyThread.start();
    }

    public void sendImagesList(String chatId) {
        File files = new File("images");
        if (!files.exists() &&  !files.isDirectory()) {
            sendMessage(chatId, "Не найдена папука");
            return;
        }

        String [] fileNames = files.list((dir, name) -> name.toLowerCase().endsWith(".png")
                || name.toLowerCase().endsWith(".jpg"));
        if (files.length() == 0){
            sendMessage(chatId, "Нет изображений в папке");
        }

        String list = Arrays.toString(fileNames);
        sendMessage(chatId, "Доступные изображения:\n" + list);

    }

    public void sendVideo(String chatId, String fileName) {
        File video = new File("videos/" + fileName.toLowerCase() + ".mp4");
        if (!video.exists() && !video.isDirectory()) {
            sendMessage(chatId, "Видео не найдено");
            return;
        }

        InputFile inputFile = new InputFile(video);
        SendVideo video1 = new SendVideo();
        video1.setChatId(chatId);
        video1.setVideo(inputFile);
        try{
            execute(video1);
        }catch (TelegramApiException e){
            System.err.println(e.getMessage());
        }
    }

    public void videosList(String chatId) {
        File files = new File("videos");
        if (!files.exists() &&  !files.isDirectory()) {
            sendMessage(chatId, "Не найдена папука");
            return;
        }
        String [] fileNames = files.list((dir, name) -> name.toLowerCase().endsWith(".mp4"));

        assert fileNames != null;
        if (fileNames.length == 0){
            sendMessage(chatId, "Видео нет");
        }
        String list = Arrays.toString(fileNames);
        sendMessage(chatId, list);
    }
}
