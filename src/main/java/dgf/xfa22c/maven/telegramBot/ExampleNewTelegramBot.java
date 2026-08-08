package dgf.xfa22c.maven.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.List;


@SuppressWarnings("deprecation")
public class ExampleNewTelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Example_study_bot";
    }

    @Override
    public String getBotToken() {
        return "8892606056:AAET7EGv2Opn674iI3XY1PjSRw_NpA7wQC4";
    }

    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            String chatId = update.getMessage().getChatId().toString();
            System.out.println("Сообщение " + chatId + " " +  update.getMessage().getText());
            String message = update.getMessage().getText();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            if (update.hasMessage() && update.getMessage().hasContact()) {
                String phone = update.getMessage().getContact().getPhoneNumber();
                sendMessage(chatId, "Номер получен: " + phone);
                try {
                    execute(createMenu(chatId));
                } catch (TelegramApiException e) {
                    System.err.println(e.getMessage());
                }
            } else if (update.hasMessage() && update.getMessage().hasLocation()) {
                double lat = update.getMessage().getLocation().getLatitude();
                double lon = update.getMessage().getLocation().getLongitude();
                sendMessage(chatId, "Локация: " + lat + ", " + lon);
                try {
                    execute(createMenu(chatId));
                } catch (TelegramApiException e) {
                    System.err.println(e.getMessage());
                }
            }

            switch (message) {
                case "/pupsik":
                    sendMessage(chatId, "Привет пупсик, Ты написал:  " + message);
                    break;
                case "/start":
                    sendMessage(chatId, "Привет пупсик, бот запущен");
                    break;
                case "/menu":
                    try {
                        execute(createMenu(chatId));
                    } catch (TelegramApiException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "Инфа":
                    sendMessage(chatId, "Я бот, созданный на Java. Моя задача — показывать примеры работы с кнопками.");
                    break;
                case "Картинка":
                    sendImage(chatId);
                    break;
                case "Котик":
                    sendMessage(chatId, "Котики всякие не нужны, есть только кошкодевочки");
                    break;

                case "Файл":
                    sendFile(chatId);
                    break;

                case "Номер":
                    try {
                        execute(requestContactKeyboard(chatId));
                    }catch (TelegramApiException e){
                        System.err.println(e.getMessage());
                    }
                    break;

                case "Локация":
                    try{
                        execute(requestLocationKeyboard(chatId));
                    }catch (TelegramApiException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case "Выход":
                    SendMessage message1 = new SendMessage();
                    message1.setChatId(chatId);
                    message1.setText("Клавиатура скрыта.");
                    ReplyKeyboardRemove r = new ReplyKeyboardRemove();
                    r.setRemoveKeyboard(true);
                    message1.setReplyMarkup(r);
                    try {
                        execute(message1);
                    } catch (TelegramApiException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "Назад":
                    try {
                        execute(createMenu(chatId));
                    } catch (TelegramApiException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    sendMessage(chatId, "Я не знаю такой команды.");
                    break;
            }
        }
    }

    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.err.println("Ошибка отправки: " + e.getMessage());
        }
    }

    private SendMessage createMenu(String chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выбери действие");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        KeyboardRow keyboardRow = new KeyboardRow();

        keyboardRow.add("Инфа");
        keyboardRow.add("Картинка");
        keyboardRow.add("Номер");
        keyboardRow.add("Локация");


        KeyboardRow row2 = new KeyboardRow();
        row2.add("Котик");
        row2.add("Выход");
        row2.add("Файл");

        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow, row2));
        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }

    //Add filePath after chatID if needed
    private void sendImage(String chatId) {
        try {
            File file = new File("C:\\Users\\shaxr\\IdeaProjects\\NewStartMaven\\Error404.png");
            InputFile inputFile = new InputFile(file);
            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(inputFile);
            execute(photo);
        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
        }
    }

    //Add filePath after chatID if needed
    private void sendFile(String chatId) {
        try {
            File file = new File("C:\\Users\\shaxr\\IdeaProjects\\NewStartMaven\\study_log.txt");
            InputFile inputFile = new InputFile(file);
            SendDocument document = new SendDocument();
            document.setChatId(chatId);
            document.setDocument(inputFile);
            execute(document);
        }catch (TelegramApiException e) {
            System.err.println(e.getMessage());
        }
    }

    private SendMessage requestContactKeyboard(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Нажмите кнопку, чтобы отправить номер:");

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();

        KeyboardButton contactButton = new KeyboardButton();
        contactButton.setText("Отправить номер");
        contactButton.setRequestContact(true);
        row.add(contactButton);

        KeyboardButton backButton = new KeyboardButton();
        backButton.setText("Назад");
        row.add(backButton);

        keyboard.setKeyboard(List.of(row));
        message.setReplyMarkup(keyboard);

        return message;
    }

    private SendMessage requestLocationKeyboard(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Нажмите кнопку, чтобы отправить локацию:");

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();

        KeyboardButton locationButton = new KeyboardButton();
        locationButton.setText("Отправить локацию");
        locationButton.setRequestLocation(true);
        row.add(locationButton);

        KeyboardButton backButton = new KeyboardButton();
        backButton.setText("Назад");
        row.add(backButton);

        keyboard.setKeyboard(List.of(row));
        message.setReplyMarkup(keyboard);

        return message;
    }

}
