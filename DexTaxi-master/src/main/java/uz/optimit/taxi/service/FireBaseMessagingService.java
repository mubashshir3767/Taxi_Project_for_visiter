package uz.optimit.taxi.service;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.optimit.taxi.exception.FirebaseConnectionException;
import uz.optimit.taxi.model.response.NotificationMessageResponse;

import static uz.optimit.taxi.entity.Enum.Constants.FIREBASE_EXCEPTION;
import static uz.optimit.taxi.entity.Enum.Constants.SUCCESSFULLY;


@Service
@RequiredArgsConstructor
public class FireBaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public String sendNotificationByToken(NotificationMessageResponse notificationMessageResponse) {
        Notification notification = Notification.builder()
                .setTitle(notificationMessageResponse.getTitle())
                .setBody(notificationMessageResponse.getBody())
                .build();

        Message message = Message.builder()
                .setToken(notificationMessageResponse.getReceiverToken())
                .setNotification(notification)
                .putAllData(notificationMessageResponse.getData())
                .build();
        try {
            firebaseMessaging.send(message);
            return SUCCESSFULLY;
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            throw new FirebaseConnectionException(FIREBASE_EXCEPTION);
        }
    }
}
