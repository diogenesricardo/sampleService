package com.example.diogenes.servicesample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.List;

/**
 * Classe utilitária para criar notificações.
 *
 * http://developer.android.com/guide/topics/ui/notifiers/notifications.html
 */
public class NotificationUtil {

//    public static final String ACTION_VISUALIZAR = "br.com.livroandroid.hellonotification.ACTION_VISUALIZAR";

    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    public static void criarNotificacaoSimples(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        Notification.Builder nb = new Notification.Builder(context);
//        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        nb.setDefaults(Notification.DEFAULT_VIBRATE); // Ativa configurações padrão
        nb.setSmallIcon(R.drawable.ic_launcher_background); // Ícone
        nb.setContentTitle(contentTitle); // Título
        nb.setContentText(contentText); // Mensagem
        nb.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        nb.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);

        nm.notify(id, nb.build());
    }

    public static void criarNotificacaoGrande(Context context, Intent intent, String contentTitle, String contentText,List<String> lines, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Configura o estilo Inbox
        int size = lines.size();
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
        inboxStyle.setBigContentTitle(contentTitle);
        for (String s: lines) {
            inboxStyle.addLine(s);
        }
        inboxStyle.setSummaryText(contentText);

        // Cria a notificação
        Notification.Builder b = new Notification.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
//        b.setSmallIcon(R.drawable.ic_cifrao); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela
        b.setNumber(size); // Número para aparecer na notificação
        b.setStyle(inboxStyle); // Estilo customizado


        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void onClickNotificacaoComAcao(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        Notification.Builder b = new Notification.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
//        b.setSmallIcon(R.drawable.ic_notification_icon); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // Ação customizada (deixei a mesma intent para os dois)
//        PendingIntent actionIntent = PendingIntent.getBroadcast(
//                context, 0, new Intent(ACTION_VISUALIZAR), 0);
//        b.addAction(R.drawable.ic_acao_pause, "Pause", actionIntent);
//        b.addAction(R.drawable.ic_acao_play, "Play", actionIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    // Notification no Android 5.0 Lollipop (Cor vermelha e heads-up e tela de bloqueio)
    public static void createHeadsUpNotification(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        Notification.Builder b = new Notification.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
//        b.setSmallIcon(R.drawable.ic_notification_icon); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // No Android 5.0
        b.setColor(Color.RED);
        // Heads-up notification
        b.setFullScreenIntent(p,false);
        // Privada se estiver na tela de bloqueio
        b.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    // Notification no Android 5.0 Lollipop (tela de bloqueio)
    public static void createPrivateNotification(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        Notification.Builder b = new Notification.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
//        b.setSmallIcon(R.drawable.ic_notification_icon); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // PUBLIC, PRIVATE, SECRET
        b.setVisibility(Notification.VISIBILITY_SECRET);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void cancell(Context context, int id) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public static void cancellAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
