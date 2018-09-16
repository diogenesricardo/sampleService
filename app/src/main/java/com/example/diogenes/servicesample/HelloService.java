package com.example.diogenes.servicesample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HelloService extends Service {

    private static final String TAG = "fafica";
    private static final int MAX = 10;

    protected int count;
    private boolean running;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "HelloService.onCreate() - Service criado");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "HelloService.onStartCommand() - Service iniciado: " + startId);
        count = 0;
        // Método chamado depois do onCreate(), logo depois que o serviço é iniciado
        // O parÂmetro startId representa o identificador deste serviço
        running = true;
        // Delega para uma thread
        new WorkerThread().start();

        // Chama a implementação da superclasse
        return super.onStartCommand(intent, flags, startId);
    }

    // Thread que faz o trabalho pesado
    class WorkerThread extends Thread {
        public void run() {
            try {
                while (running && count < MAX) {
                    // Simula algum processamento
                    Thread.sleep(1000);
                    Log.d(TAG, "HelloService executando... " + count);
                    count++;
                }
                Log.d(TAG, "HelloService fim.");
            } catch (InterruptedException e) {
                Log.e(TAG,e.getMessage(),e);
            } finally {
                // Auto-Encerra o serviço se o contador chegou a 10
                stopSelf();
                // Cria uma notificação para avisar o usuário que terminou.
                Context context = HelloService.this;
                Intent intent = new Intent(context,MainActivity.class);
                NotificationUtil.criarNotificacaoSimples(context, intent, "HelloService", "Fim do serviço.",1);
            }
        }
    }
    @Override
    public void onDestroy() {
        // Ao encerrar o serviço, altere o flag para a thread parar (isto é importante para encerrar
        // a thread caso alguém tenha chamado o stopService(intent)
        running = false;
        Log.d(TAG, "HelloService.onDestroy() - Service destruído");
    }
}
